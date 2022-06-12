package com.tw.travel.ticketing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The Flight have no seats")
public class FlightNoSeatException extends RuntimeException {

    public FlightNoSeatException() {
        super();
    }
}