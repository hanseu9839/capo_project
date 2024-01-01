package com.realworld.project.common.config.exception;

import com.realworld.project.common.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

public class CustomMemberExceptionHandler extends RuntimeException{
    @Getter
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
