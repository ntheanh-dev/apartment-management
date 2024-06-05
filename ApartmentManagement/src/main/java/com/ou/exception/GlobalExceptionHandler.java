package com.ou.exception;

import com.ou.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exception tự tạo
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<Object>> appExceptionHandler(AppException e) {
        // Lấy errorcode đường truyền vào khi thor new AppException
        ErrorCode errorCode = e.getErrorCode();


        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }
}
