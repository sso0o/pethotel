package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "commoncode")
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "code_head", updatable = false)
    private String codeHead;

    @Column(name = "code_detail", updatable = false)
    private String codeDetail;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_use")
    private String codeUse = "Y";

    @Column(name = "code_seq")
    private int codeSeq;

    @Column(name = "code")
    private String code;

    @Column(name = "remark")
    private String remark;

    @Builder
    public CommonCode(String codeHead, String codeDetail, String codeName, String codeUse, int codeSeq, String code, String remark) {
        this.codeHead = codeHead;
        this.codeDetail = codeDetail;
        this.codeName = codeName;
        this.codeUse = codeUse;
        this.codeSeq = codeSeq;
        this.code = code;
        this.remark = remark;

        String trimmedCodeDetail = (codeDetail == null) ? "" : codeDetail.trim();
        this.codeDetail = codeHead.trim()+trimmedCodeDetail;
    }

    @PrePersist
    public void makeCd() {
        String trimmedCodeDetail = (codeDetail == null) ? "" : codeDetail.trim();
        this.codeDetail = codeHead.trim()+trimmedCodeDetail;
    }

    public void update(String codeName, int codeSeq, String remark, String codeUse) {
        this.codeName = codeName;
        this.codeSeq = codeSeq;
        this.remark = remark;
        this.codeUse = codeUse;

    }
}
