package com.example.pethotel.service;

import com.example.pethotel.entity.User;
import com.example.pethotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserid(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        if (!"Y".equals(user.getUserstatus())) {
            throw new UsernameNotFoundException(username + " is disabled");
        }

        return user;
    }

    public void updateUserDetails(User updatedUser) {
        // 새로 변경된 유저 정보를 기반으로 Authentication 객체를 생성
        UserDetails updatedUserDetails = loadUserByUsername(updatedUser.getUserid());  // 최신 UserDetails 가져오기

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,  // Authentication에 설정할 principal
                updatedUser.getPassword(),
                updatedUserDetails.getAuthorities()  // UserDetails에서 권한 가져오기
        );

        // SecurityContext에 새로운 인증 객체 설정
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
