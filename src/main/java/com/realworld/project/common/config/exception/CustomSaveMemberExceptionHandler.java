package com.realworld.project.common.config.exception;

import com.realworld.project.common.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

public class CustomSaveMemberExceptionHandler extends RuntimeException{
    @Getter
    ErrorCode errorCode;

    @Builder
    public CustomSaveMemberExceptionHandler(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public CustomSaveMemberExceptionHandler(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
