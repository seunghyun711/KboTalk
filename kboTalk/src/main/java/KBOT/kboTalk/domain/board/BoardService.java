package KBOT.kboTalk.domain.board;

import KBOT.kboTalk.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private static final String UPLOAD_DIR = "C:/kbt/uploads"; // 이미지가 저장될 경로
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * <게시글 작성>
     * TODO : 작성자 관련 정보도 추가할 것
     */
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
            return "/upload/" + savedFilename;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }
}
