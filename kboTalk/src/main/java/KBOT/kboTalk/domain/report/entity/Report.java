package KBOT.kboTalk.domain.report.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REPORT_ID")
    private Long id;

    @Column
    private int reportType; // 신고 유형

    @Column
    private String reason; // 신고 사유
}
