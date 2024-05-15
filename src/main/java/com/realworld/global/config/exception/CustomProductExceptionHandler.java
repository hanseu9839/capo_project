package com.realworld.global.config.exception;

import com.realworld.global.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomProductExceptionHandler extends RuntimeException {
    private final ErrorCode errorCode;

    @Builder
    public CustomProductExceptionHandler(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public CustomProductExceptionHandler(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
