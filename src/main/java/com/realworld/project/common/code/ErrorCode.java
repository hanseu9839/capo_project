package com.realworld.project.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 잘못된 이메일 요청
    EMAIL_REQUEST_ERROR(400, "존재 하지 않는 이메일입니다."),
    // 사용자 잘못된 요청
    LOGIN_REQUEST_ERROR(400,"비밀번호 또는 아이디가 올바르지 않습니다."),
    //
    LOGIN_DUPLICATION_ERROR(409, "이미 존재하는 아이디 또는 이메일 입니다."),
    // Token 오류
    JWT_TOKEN_REQUEST_ERROR(400,"Refresh Token이 유효하지 않습니다."),
    // 잘못된 서버 요청
    BAD_REQUEST_ERROR(400,"Bad Request Exception"),
    // @RequestBody 데이터 미 존재
    REQUEST_BODY_MISSING_ERROR(400,"Required request body is missing"),
    // 유효하지 않은 타입
    INVALID_TYPE_VALUE(400,"Invalid Type Value"),
    // Request Parameter 로 데이터가 전달되지 않을 경우
    MISSING_REQUEST_PARAMETER_ERROR(400,"Missing Servlet RequestParameter Exception"),

    // 입력/출력 값이 유효하지 않음
    IO_ERROR(400,"I/O Exception"),
    // JSON 파싱 실패
    JSON_PARSE_ERROR(400,"JsonParseException"),
    // jackson.core processing error
    JACKSON_PROCESS_ERROR(400,"com.fasterxml.jackson.core Exception"),
    // 권한이 없음
    FORBIDDEN_ERROR(403,"Forbidden Exception"),

    // 서버로 요청한 리소스가 존재하지 않음
    NOT_FOUND_ERROR(404,"Not Found Exception"),

    // NULL Point Exception 발생
    NULL_POINT_ERROR(404,"Null Point Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(404,"handle Validation Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_HEADER_ERROR(404,"Header에 데이터가 존재하지 않는 경우"),


    // 서버가 처리 할 방법을 모르는 경우 발생
    INTERVAL_SERVER_ERROR(500,"Internal Server Error Exception"),

    // Transaction Insert Error
    INSERT_ERROR(200,"Insert Transaction Error Exception"),

    // Transaction Update Error
    UPDATE_ERROR(200,"Update Transaction Error Exception"),

    // Transaction Delete Error
    DELETE_ERROR(200,"Delete Transaction Error Exception")
    ;

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
