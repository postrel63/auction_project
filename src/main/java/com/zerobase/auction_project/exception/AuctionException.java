package com.zerobase.auction_project.exception;


import com.zerobase.auction_project.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionException extends RuntimeException {
    private ErrorCode errorcode;
    private String errorMessage;

}
