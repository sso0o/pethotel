package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userid", updatable = false, nullable = false, unique = true)
    private String userid;

    @Column(name = "user_n")
    private String usern;

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
    public User(String userid, String username, String password, String userrole, String userphone, String nickname, String userstatus) {
        this.userid = userid;
        this.usern = username;
        this.password = password;
        this.userrole = userrole;
        this.userphone = userphone;
        this.nickname = nickname;
        this.userstatus = (userstatus != null) ? userstatus : "Y";
    }

    public void update(String password, String userrole, String userphone, String nickname, String userstatus) {
        this.password = password;
        this.userrole = userrole;
        this.userphone = userphone;
        this.nickname = nickname;
        this.userstatus = userstatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userrole));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getUsername() {
        return userid;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
