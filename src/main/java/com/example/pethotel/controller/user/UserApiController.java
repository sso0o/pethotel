package com.example.pethotel.controller.user;

import com.example.pethotel.dto.AddUserRequest;
import com.example.pethotel.dto.UpdateUserRequest;
import com.example.pethotel.entity.User;
import com.example.pethotel.service.CommonService;
import com.example.pethotel.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private final CommonService commonService;

    @PostMapping("/signup")
    public ResponseEntity signUp(AddUserRequest req, HttpServletRequest request) throws Exception {

        commonService.checkRequiredField(req.getUsername(), "이름은 필수 입력 항목입니다.");
        commonService.checkRequiredField(req.getUserid(), "로그인 아이디는 필수 입력 항목입니다.");
        commonService.checkRequiredField(req.getPassword(), "비밀번호는 필수 입력 항목입니다.");
        commonService.checkRequiredField(req.getNickname(), "닉네임은 필수 입력 항목입니다.");
        commonService.checkRequiredField(req.getUserphone(), "전화번호는 필수 입력 항목입니다.");

        HashMap<Object, Object> resultMap = new HashMap<>();

        // 회원가입 처리
        Long userId = userService.save(req).getId();
        resultMap.put("userId", userId);
        resultMap.put("msg", "회원가입 성공");
        // 세션에 prevPage를 설정
        request.getSession().setAttribute("prevPage", "/signup");
        return ResponseEntity.ok().body(resultMap);
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        // 세션 무효화 (검색 조건도 삭제됨)
        request.getSession().invalidate();
        // 리다이렉트
        response.sendRedirect("/");  // "/" 경로로 리다이렉트
    }

    @GetMapping("/account/check")
    public ResponseEntity checkAccount(@RequestParam String userid) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        boolean isDuplicate = userService.isUserIdTaken(userid);
        resultMap.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok().body(resultMap);
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity updateAccount(@PathVariable Long id, @RequestBody UpdateUserRequest req) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        User updateUser = userService.update(id, req);
        resultMap.put("user", updateUser);
        resultMap.put("msg", "회원 정보 수정 성공");
        return ResponseEntity.ok().body(resultMap);
    }

    @PutMapping("/account/delete")
    public ResponseEntity deleteAccount(@RequestParam Long id, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        userService.updateUserstatus(id, "N");

        // 로그아웃 처리
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(httpRequest, httpResponse, null);

        resultMap.put("msg", "회원 탈퇴 성공");
        return ResponseEntity.ok().body(resultMap);
    }




}
