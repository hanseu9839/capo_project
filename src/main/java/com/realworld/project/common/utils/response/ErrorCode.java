package com.realworld.project.common.utils.response;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCode{
    private String status;
    private String message;
}