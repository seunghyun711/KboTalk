package KBOT.kboTalk.domain.Team;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column
    private String teamName; // 팀명

    @Column
    private Long fans; // 팬 수
}
