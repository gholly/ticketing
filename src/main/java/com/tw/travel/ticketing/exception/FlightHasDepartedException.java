package com.tw.travel.ticketing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Flight has departed")
public class FlightHasDepartedException extends RuntimeException {

    public FlightHasDepartedException() {
        super();
    }
}
