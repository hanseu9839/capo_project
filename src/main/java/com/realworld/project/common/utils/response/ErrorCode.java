package com.realworld.project.common.utils.response;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 잘못된 서버 요청
    BAD_REQUEST_ERROR("Bad Request Exception"),
    // @RequestBody 데이터 미 존재
    REQUEST_BODY_MISSING_ERROR("Required request body is missing"),
    // 유효하지 않은 타입
    INVALID_TYPE_VALUE("Invalid Type Value"),
    // Request Parameter 로 데이터가 전달되지 않을 경우
    MISSING_REQUEST_PARAMETER_ERROR("Missing Servlet RequestParameter Exception"),
    // 입력/출력 값이 유효하지 않음
    IO_ERROR("I/O Exception"),
    // JSON 파싱 실패
    JSON_PARSE_ERROR("JsonParseException"),
    // jackson.core processing error
    JACKSON_PROCESS_ERROR("com.fasterxml.jackson.core Exception"),
    // 권한이 없음
    FORBIDDEN_ERROR("Forbidden Exception"),

    INSERT_ERROR("Insert Transaction Error Exception"),
    UPDATE_ERROR("Update Transaction Error Exception"),
    DELETE_ERROR("Delete Transaction Error Exception")
    ;
    private final String status;
    private final String message;

    ErrorCode(String message) {
        this.status = "error";
        this.message = message;
    }
}
