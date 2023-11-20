package com.zerobase.auction_project.exception.handler;


import com.zerobase.auction_project.exception.code.ErrorCode;
import com.zerobase.auction_project.exception.custom.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler({ CustomException.class})
    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException c) {
        log.warn("api Exception: {} ", c.getErrorcode());
        return ResponseEntity.badRequest().body(new ExceptionResponse(c.getMessage(), c.getErrorcode()));
    }


    @Getter
    @AllArgsConstructor
    @ToString
    public static class ExceptionResponse {
        private String message;
        private ErrorCode errorCode;
    }
}
