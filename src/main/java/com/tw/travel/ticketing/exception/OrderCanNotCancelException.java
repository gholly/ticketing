package com.tw.travel.ticketing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Order can not cancel")
public class OrderCanNotCancelException extends RuntimeException {

    public OrderCanNotCancelException() {
        super();
    }
}