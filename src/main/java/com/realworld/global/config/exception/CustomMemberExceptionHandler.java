package com.realworld.global.config.exception;

import com.realworld.global.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomMemberExceptionHandler extends RuntimeException{
    private final ErrorCode errorCode;

    @Builder
    public CustomMemberExceptionHandler(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public CustomMemberExceptionHandler(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
