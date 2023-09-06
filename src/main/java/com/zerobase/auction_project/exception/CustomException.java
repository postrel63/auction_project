package com.zerobase.auction_project.exception;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorcode;

    public CustomException(ErrorCode errorcode) {
        super(errorcode.getDetail());
        this.errorcode = errorcode;
    }

}
