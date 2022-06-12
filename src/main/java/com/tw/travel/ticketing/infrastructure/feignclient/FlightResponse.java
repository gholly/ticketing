package com.tw.travel.ticketing.infrastructure.feignclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FlightResponse {
    private String code;
    private LocalDateTime departTime;
    private FlightStatus status;
}
