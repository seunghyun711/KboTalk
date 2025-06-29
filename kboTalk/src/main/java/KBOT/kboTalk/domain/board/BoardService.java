package KBOT.kboTalk.domain.board;

import KBOT.kboTalk.domain.exception.BoardNotFoundException;
import KBOT.kboTalk.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private static final Duration VIEW_CACHE_TTL = Duration.ofMinutes(30); // redis에 저장되는 데이터의 ttl

    private static final String UPLOAD_DIR = "C:/kbt/uploads"; // 이미지가 저장될 경로
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * <게시글 작성>
     * TODO : 작성자 관련 정보도 추가할 것
     */
    @Transactional
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    /**
     * <게시글 이미지 첨부>
     * 1. 이미지 파일의 유효성 검사
     * 2. 기존 파일의 이름 정보를 받아 UUID를 추가한 가공된 파일의 이름 정보 생성(중복된 파일명이 저장된 경우 구분하기 위함)
     * 3. 업로드할 경로에 2번 과정을 통해 만든 파일명을 저장
     * 4. 파일명 리턴
     */
    public String saveImg(MultipartFile file) {
        // 1. 이미지 파일의 유효성 검사
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }
        // 2. 기존 파일의 이름 정보를 받아 UUID를 추가한 가공된 파일의 이름 정보 생성(중복된 파일명이 저장된 경우 구분하기 위함)
        try{
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedFilename = UUID.randomUUID() + extension;

            // 3. 업로드할 경로에 2번 과정을 통해 만든 파일명을 저장
            Path savePath = Paths.get(UPLOAD_DIR, savedFilename);
            Files.copy(file.getInputStream(), savePath);

            log.info("savePath:{}", savePath);
            // 4. 파일명 리턴
            return "/upload/" + savedFilename;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    /**
     * <게시글 이미지 삭제>
     * 1. 삭제하려는 이미지 유효성 검사
     * 2. 서버 파일 시스템 경로로 변환
     * 3. 이미지 삭제
     */
    public void deleteImageByUrl(String imageUrl) {
        // 1. 삭제하려는 이미지 유효성 검사
        if (imageUrl == null || !imageUrl.startsWith("/upload")) {
            throw new IllegalArgumentException("잘못된 이미지 경로 입니다.");
        }

        // 2. 서버 파일 시스템 경로로 변환
        String filename = imageUrl.substring("/upload/".length());
        Path path = Paths.get("C:/kbt/uploads", filename);
        log.debug("삭제 대상 파일 경로: {}", path.toAbsolutePath());
        log.debug("파일 존재 여부: {}", Files.exists(path));

        try {
            Files.deleteIfExists(path);
            log.info("이미지 삭제: {}", path);
        } catch (IOException e) {
            log.error("이미지 삭제 실패: {}", path, e);
            throw new RuntimeException("이미지 삭제 실패", e);
        }
    }

    /**
     * 게시글 상세 조회
     * 1. 게시글 존재여부 파악
     * 2. 조회수 카운팅
     *  2-1. 로그인 회원인 경우 viewd:{boardId}:member{memberId}형식클 키로 설정
     *  2-2. 비로그인 회원인 경우 viewd:{boardId}:anon{ip+user-agent}형식을 키로 설정
     * 3. 2번 과정을 통해 조합된 키가 redis에 존재하지 않는 경우 조회수+1을 하고 해당 키를 redis에 저장
     * 4. board 리턴
     * @return
     */
    @Transactional(readOnly = true)
    public Board getBoardPage(Long boardId, Long viewerId, String ip, String userAgent) {
        // 1. 게시글 존재여부 파악
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() ->
                new BoardNotFoundException("존재하지 않는 게시글"));


        // 2. 조회수 카운팅
        String key;

        // 2-1. 로그인 회원인 경우 viewd:{boardId}:member{memberId}형식클 키로 설정
        if (viewerId != null) {
            key = String.format("viewed:%d:user:%d", boardId, viewerId);
        } else { // 2-2. 비로그인 회원인 경우 viewd:{boardId}:anon{ip+user-agent}형식을 키로 설정
            String hashed = DigestUtils.md5DigestAsHex((ip + userAgent).getBytes()); // ip+userAgent의 해시
            key = String.format("viewed:%d:anon:%s", boardId, hashed);
        }

        // 3. 2번 과정을 통해 조합된 키가 redis에 존재하지 않는 경우 조회수+1을 하고 해당 키를 redis에 저장
        Boolean isNew = redisTemplate.opsForValue().setIfAbsent(key, "1", VIEW_CACHE_TTL);
        if (Boolean.TRUE.equals(isNew)) {
            redisTemplate.opsForValue().increment("board:views:" + boardId);
        }

        // 4. board 리턴
        return findBoard;
    }
}
