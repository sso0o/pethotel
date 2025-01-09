package com.example.pethotel.config;

import com.example.pethotel.service.UserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  {

    private final UserDetailsService userDetailsService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**",
                        "/css/**", "/js/**",
                        "/img/**", "/scss/**",
                        "/font-awesome-4.7.0/**", "/slick/**",
                        "/tem2/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        return http
                .requestCache(request -> request
                        .requestCache(requestCache))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/", "/hotel/**", "/main/**", "/uploads/**", "/account/**").permitAll()
                        .requestMatchers("/booking/**").hasAnyAuthority("USER", "MANAGER")
                        .requestMatchers("/mybooking", "/mybooking/**").hasAnyAuthority("USER")
                        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureHandler(failureHandler())
                        .successHandler(successHandler()))
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 후 리디렉션할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .clearAuthentication(true) // 인증 정보 초기화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제 (세션 ID를 포함한 쿠키 삭제)
                        .invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);  // 리퍼러 사용 설정

        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // 로그인 성공 시 에러 메시지 세션에서 제거
                request.getSession().removeAttribute("error");

                // prevPage 세션 값이 있으면 해당 페이지로 리디렉션, 없으면 기본 '/' 페이지로 리디렉션
                String prevPage = (String) request.getSession().getAttribute("prevPage");

                if (prevPage == null || prevPage.equals("http://localhost:8081/signup")) {
                    prevPage = "/";
                }

                // 세션에서 prevPage를 삭제 (다시 로그인하지 않도록)
                request.getSession().removeAttribute("prevPage");
                // 리디렉션
                response.sendRedirect(prevPage);
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            String errorMessage = "Invalid username or password"; // 로그인 실패 메시지 설정

            // 예외에 따라 동적으로 에러 메시지 처리
            if (exception instanceof UsernameNotFoundException) {
                errorMessage = "사용자를 찾을 수 없습니다.";
            } else if (exception instanceof BadCredentialsException) {
                errorMessage = "잘못된 사용자명 또는 비밀번호입니다.";
            } else {
                errorMessage = "인증에 실패했습니다. 다시 시도해주세요.";
            }

            // 세션에 error 메시지 저장
            request.getSession().setAttribute("error", errorMessage);

            // 로그인 페이지로 리디렉션하면서 에러 메시지 전달
            response.sendRedirect("/login?error=true");
        };
    }


}
