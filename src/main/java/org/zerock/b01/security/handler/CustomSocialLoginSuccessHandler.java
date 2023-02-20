package org.zerock.b01.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.b01.security.dto.MemberSecurityDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements
        AuthenticationSuccessHandler {
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess (HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException {
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        String encodePw = memberSecurityDTO.getMpw();

        // 소셜 로그인이며 회원의 패스워드 1111

        if (memberSecurityDTO.isSocial() && memberSecurityDTO.getMpw().equals("1111")
        || passwordEncoder.matches("1111", memberSecurityDTO.getMpw())) {
            log.info("Should change Password");
            response.sendRedirect("/member/modify");

            return;
        } else {
            response.sendRedirect("/board/list");
        }
    }
}
