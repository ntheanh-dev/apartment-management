package com.ou.filters;

import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler  implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest hsr,
            HttpServletResponse response,
            org.springframework.security.access.AccessDeniedException ade
    ) throws IOException, ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied!");
    }
}
