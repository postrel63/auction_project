package com.zerobase.auction_project.exception.custom;


import com.zerobase.auction_project.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorcode;

    public CustomException(ErrorCode errorcode) {
        super(errorcode.getDetail());
        this.errorcode = errorcode;
    }

}
