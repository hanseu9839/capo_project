package com.realworld.project.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ApiResponse<T> {

    private T result; // API 응답 결과 Response

    private int result_code; // API 응답 코드 Response

    private String result_msg; // API 응답 코드 Message

    public ApiResponse(final T result, final int resultCode, final String resultMsg){
        this.result = result;
        this.result_code = resultCode;
        this.result_msg = resultMsg;
    }

    public ResponseEntity<?> success(final int resultCode,final T result,final String resultMsg){
        return new ResponseEntity<>(ApiResponse.builder()
                .result_code(200)
                .result_msg("success")
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<?> success(final T result){
        return new ResponseEntity<>(ApiResponse.builder()
                .result(result)
                .result_code(200)
                .result_msg("empty")
                .build(), HttpStatus.OK);
    }

    public static ResponseEntity<?> success(){
        return new ResponseEntity<>(ApiResponse.builder()
                .result_code(200)
                .result_msg("empty"),HttpStatus.OK);
    }
}
