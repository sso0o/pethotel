package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userid", updatable = false, nullable = false, unique = true)
    private String userid;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String userrole;

    @Column(name = "user_phone")
    private String userphone;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "user_status")
    private String userstatus;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateAt;



    @Builder
    public User(String username, String password, String userrole, String userphone, String nickname, String userstatus) {
        this.username = username;
        this.password = password;
        this.userrole = userrole;
        this.userphone = userphone;
        this.nickname = nickname;
        this.userstatus = userstatus;
    }

    public void update(String password, String userrole, String userphone, String nickname, String userstatus) {
        this.password = password;
        this.userrole = userrole;
        this.userphone = userphone;
        this.nickname = nickname;
        this.userstatus = userstatus;
    }

}
