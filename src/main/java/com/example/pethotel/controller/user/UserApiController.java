package com.example.pethotel.controller.user;

import com.example.pethotel.dto.AddUserRequest;
import com.example.pethotel.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signUp(AddUserRequest req, HttpServletResponse response) throws IOException {
        try {
            // 회원가입 처리
            userService.save(req);

            // 회원가입 성공 후 리다이렉트
            response.sendRedirect("/login"); // 성공 시 홈으로 리다이렉트
        } catch (Exception e) {
            // 오류 발생 시 500 상태 코드로 응답
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("회원가입 중 오류가 발생했습니다.");
        }
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        // 세션 무효화 (검색 조건도 삭제됨)
        request.getSession().invalidate();
        // 리다이렉트
        response.sendRedirect("/");  // "/" 경로로 리다이렉트
    }


}
