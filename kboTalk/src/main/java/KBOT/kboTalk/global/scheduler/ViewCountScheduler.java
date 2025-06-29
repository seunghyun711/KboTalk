package KBOT.kboTalk.global.scheduler;

import KBOT.kboTalk.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewCountScheduler { // 조회수 증가 횟수 db 반영

    private final RedisTemplate<String, String> redisTemplate;
    private final BoardRepository boardRepository;

    @Scheduled(fixedRate = 60000)
    public void syncViewCount() {
        Set<String> keys = redisTemplate.keys("board:views:*");

        if (keys != null) {
            for (String key : keys) {
                Long boardId = Long.valueOf(key.replace("board:views:", ""));
                String value = redisTemplate.opsForValue().get(key);
                if (value != null) {
                    log.info("redis value : {}", value);
                    boardRepository.incrementViewCount(boardId, Long.parseLong(value));
                    try {
                        redisTemplate.delete(key);
                    } catch (Exception e) {
                        log.warn("Redis key delete 실패: {}", key, e);
                    }
                }
            }
        }
    }
}
