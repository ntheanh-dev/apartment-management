package com.ou.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(1001,"Unauthorized",HttpStatus.UNAUTHORIZED),
    USER_EXISTED(1002,"User Existed",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003,"User not existed",HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1004,"Invalid Token",HttpStatus.UNAUTHORIZED),
    RESIDENT_NOT_FOUND(1005,"Resident Not Found",HttpStatus.BAD_REQUEST),
    FORBIDDEN(1006,"Forbidden",HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatus statusCode;
}
