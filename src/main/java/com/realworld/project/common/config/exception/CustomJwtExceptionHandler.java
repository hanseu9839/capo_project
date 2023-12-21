package com.realworld.project.common.config.exception;

import com.realworld.project.common.code.ErrorCode;
import lombok.Getter;

public class CustomJwtExceptionHandler extends RuntimeException{
    @Getter
    ErrorCode errorCode;

    public CustomJwtExceptionHandler(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public CustomJwtExceptionHandler(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
