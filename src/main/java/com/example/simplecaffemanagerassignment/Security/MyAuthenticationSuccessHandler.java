package com.example.simplecaffemanagerassignment.Security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else if (roles.contains("ROLE_MANAGER")) {
            response.sendRedirect("/manager");
        } else if (roles.contains("ROLE_WAITER")) {
            response.sendRedirect("/waiter");
        } else if (roles.contains("ROLE_CUSTOMER")) {
            response.sendRedirect("/user");
        }
    }
}
