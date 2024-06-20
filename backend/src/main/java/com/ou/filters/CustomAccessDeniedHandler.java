package com.ou.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.dto.response.ApiResponse;
import com.ou.exception.ErrorCode;
import org.springframework.http.MediaType;
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
        ErrorCode errorcode = ErrorCode.FORBIDDEN;

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorcode.getCode())
                .message(errorcode.getMessage())
                .build();
        // Convert object sang string (JSON)
        ObjectMapper mapper = new ObjectMapper();

        response.getWriter().write(mapper.writeValueAsString(apiResponse));
        response.flushBuffer();


//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write("Access Denied!");
    }
}
