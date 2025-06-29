package KBOT.kboTalk.domain.board;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 
    @Modifying(clearAutomatically = true) // 영속성 컨텍스트를 거치지 않고 db를 변경하기 때문에 영속성 컨텍스트를 clear하여 실제 db 내 데이터와 영속성 컨텍스트 내 데이터의 불일치를 방지한다.
    @Transactional
    @Query("update Board b set b.viewCount = b.viewCount + :views where b.id = :boardId")
    void incrementViewCount(@Param("boardId") Long boardId, @Param("views") Long views);
}
