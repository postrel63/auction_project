package com.zerobase.auction_project.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "회원 정보가 없습니다."),
    ALREADY_VERIFY(HttpStatus.BAD_REQUEST, "이미 인증된 회원입니다."),
    EXPIRED_VERIFY(HttpStatus.BAD_REQUEST, "인증번호가 유효하지 않습니다."),
    WRONG_VERIFY(HttpStatus.BAD_REQUEST, "잘못된 인증 시도입니다."),
    //
    ALREADY_BID(HttpStatus.BAD_REQUEST, "이미 입찰된 가격입니다."),
    NOT_FOUND_AUCTION(HttpStatus.BAD_REQUEST, "경매상품이 존재하지 않습니다."),
    BIDDING_INITIATED(HttpStatus.BAD_REQUEST, "입찰이 시작된 경매상품입니다.");



    private final HttpStatus httpStatus;
    private final String detail;

}
