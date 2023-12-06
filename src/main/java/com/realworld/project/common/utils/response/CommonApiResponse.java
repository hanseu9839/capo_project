package com.realworld.project.common.utils.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonApiResponse {
    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";
    private static final String EMPTY = "empty";

    private <T, E>ResponseEntity<?> getResponse(String status, @Nullable String message, @Nullable T data, @Nullable E errors, @Nullable Long page, @Nullable Integer size, @Nullable Long total){
        if(status.equals(SUCCESS_STATUS)){
            // Paging Body
            if(page != null && size != null && total != null){
                return new ResponseEntity<>(PagingBody.builder()
                        .status(status)
                        .message(message)
                        .data(data != null ? data : Collections.emptyList())
                        .page(page)
                        .size(size)
                        .total(total)
                        .build(),
                HttpStatus.OK);
            }
            else if(data == null){
                return new ResponseEntity<>(SucceededBody.builder()
                        .status(status)
                        .message(message)
                        .build(),
                        HttpStatus.OK);
            }
            // succeededBody
            else {
                return new ResponseEntity<>(SucceededBody.builder()
                        .status(status)
                        .message(message)
                        .data(data)
                        .build(),
                        HttpStatus.OK);
            }
        }
        else if (status.equals(FAIL_STATUS)) {
            return new ResponseEntity<>(FailedBody.builder()
                    .status(status)
                    .message(message)
                    .errors(errors)
                    .build(),
                    HttpStatus.OK);
        }
        else if (status.equals(ERROR_STATUS)) {
            return new ResponseEntity<>(ErrorCode.builder()
                    .status(status)
                    .message(message)
                    .build(),
                    HttpStatus.OK);
        }
        else {
            throw new RuntimeException("Api Response Error");
        }
    }

    /**
     * <p>성공 응답을 반환합니다. 첫 번째 인자는 message, 두 번째 인자는 data에 표시됩니다.</p>
     * <pre>
     *  {
     *      "status" : "success",
     *      "message" : "success message",
     *      "data" : "배열 또는 단일 데이터"
     *  }
     * </pre>
     * @param message 응답 body message 필드에 포함될 정보
     * @param data 응답 body data 핊드에 포함될 정보
     * @return      응답 객체
     */
    public<T> ResponseEntity<?> success(String message, T data) {
        return getResponse(SUCCESS_STATUS, message, data, null, null, null, null);
    }

    /**
     * <p>성공 응답을 반환합니다. 전달된 인자는 data에 표시된다.</p>
     * <pre>
     * {
     *     "status" : "success",
     *     "message" : "empty",
     *     "data" : "배열 또는 단일 데이터"
     * }
     * </pre>
     * @param data
     * @return  응답 객체
     */
    public<T> ResponseEntity<?> success(T data){
        return getResponse(SUCCESS_STATUS, EMPTY, data, null, null, null, null);
    }
    /**
     * <p>성공 응답을 반환합니다.</p>
     * <pre>
     * {
     *     "status" : "success",
     *     "message" : "empty"
     * }
     * </pre>
     * @return  응답 객체
     */
    public <T> ResponseEntity<?> success(){
        return getResponse(SUCCESS_STATUS, EMPTY, null, null, null, null, null);
    }

    /**
     * <p>페이지네이션 정보를 포함한 성공 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "success",
     *         "message" : "empty",
     *         "data" : [{data1}, {data2} ...],
     *         "page" : 1,
     *         "size" : 10,
     *         "total" : 100
     *     }
     * </pre>
     * @param data 응답 바디 data 필드에 포함될 정보
     * @param page 응답 바디 page 필드에 포함될 정보
     * @param size 응답 바디 size 필드에 포함될 정보
     * @param total 응답 바디 total 필드에 포함될 정보
     * @return 응답 객체
     */
    public <T> ResponseEntity<?> pagination(T data, Long page, Integer size, Long total){
        return getResponse(SUCCESS_STATUS, EMPTY, data, null, page, size, total);
    }

    /**
     * <p> 페이지네이션 정보를 포함한 성공 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "success",
     *         "message" : "empty",
     *         "data" : [{data1}, {data2} ...],
     *         "page" : 1,
     *         "size" : 10,
     *         "total" : 90
     *     }
     * </pre>
     * @param data
     * @param paging
     * @param total
     * @return
     * @param <T>
     * paging 객체 만들기 전까지 주석처리
     */
  /*  public <T> ResponseEntity<?> pagination(T data, PagingObject paging, Long total){
        return getResponse(SUCCESS_STATUS, EMPTY, data, null, paging, paging, total);
    }*/

    /**
     * <p>오류 발생 시 실패 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "fail",
     *         "message" : "fail message",
     *         "errors" : null
     *     }
     * </pre>
     * @param message 응답 바디 message 필드에 포함될 정보
     * @return 응답 객체
     */
    public <T> ResponseEntity<?> fail(String message){
        return getResponse(FAIL_STATUS, message, null, null, null, null, null);
    }

    /**
     * <p>필드 에러로 인해 실패 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "fail",
     *         "message" : fail message,
     *         "errors" : [{error data1}, {error data2} ...]
     *     }
     * </pre>
     * @param message 응답 바디 message 필드에 포함될 정보
     * @param errors 응답 바디 errors 필드에 포함될 정보
     * @return 응답 객체
     */
    public <E> ResponseEntity<?> fail(String message, E errors){
        return getResponse(FAIL_STATUS, message, null, errors, null, null, null);
    }

    /**
     * <p>필드 에러로 인한 실패 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "fail",
     *         "message" : fail message,
     *         "errors" : [{error data1}, {error data2} ...]
     *     }
     * </pre>
     * @param errors 응답 바디 errors 필드에 포함될 정보
     */
    public ResponseEntity<?> fail(Errors errors){
        List<FieldError> fieldErrorList = errors.getAllErrors().stream().map(FieldError::new).collect(Collectors.toList());
        return fail(EMPTY, fieldErrorList);
    }

    /**
     * <p>필드 에러로 인한 실패 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "fail",
     *         "message" : fail message,
     *         "errors" : [ {error data1}, {error data2} ... ]
     *     }
     * </pre>
     * @param bindingResult 응답 바디 errors 필드 포함될 정보를 가진 BindingResult 객체
     * @return 응답 객체
     */
    public ResponseEntity<?> fail(BindingResult bindingResult){
        return fail((Errors) bindingResult);
    }

    /**
     * <p>필드 에러로 인해 실패 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "fail",
     *         "message" : "empty",
     *         "errors" : [{error data1}, {error data2} ...]
     *     }
     * </pre>
     * @param errors 응답 바디 errors 필드에 포함될 정보
     * @return       응답 객체
     * @param <E>
     */
    public <E> ResponseEntity<?> fail(E errors) {
        return getResponse(FAIL_STATUS, EMPTY, null, errors, null, null, null);
    }

    /**
     * <p>예외 발생 시 에러 응답을 반환합니다.</p>
     * <pre>
     *     {
     *         "status" : "error",
     *         "message" : "custom error message"
     *     }
     * </pre>
     * @param message  응답 바디 message 필드에 포함될 정보
     * @return         응답 객체
     * @param <T>
     */
    public <T> ResponseEntity<?> error(String message) {
        return getResponse(ERROR_STATUS, message, null, null, null, null,null);
    }

    /**
     * 성공 응답 객체 바디
     * @param <T>
     */
    @Builder
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SucceededBody<T> {
        private String status;
        private String message;
        private T data;
    }

    /**
     * 페이징 정보가 포함된 응답 객체의 바디
     * @param <T>
     */
    @Builder
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagingBody<T> {
        private String status;
        private String message;
        private T data;
        private Long page;
        private int size;
        private Long total;
    }

    /**
     * 실패 응답 객체
     * @param <E>
     */
    @Builder
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FailedBody<E> {
        private String status;
        private String message;
        private E errors;
    }

    public static class FieldError {
        private String field;
        private String message;

        public FieldError(ObjectError objectError){
            this.field = objectError.getObjectName();
            this.message = objectError.getDefaultMessage();
        }
    }
}
