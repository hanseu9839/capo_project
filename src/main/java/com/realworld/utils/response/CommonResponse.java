package com.realworld.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {
    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private T data;
    private String message;


    public static <T> CommonResponse<T> createSuccess(T data) {
        return new CommonResponse<>(SUCCESS_STATUS, data, null);
    }

    private CommonResponse(String status, T data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
