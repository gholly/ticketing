package com.tw.travel.ticketing.infrastructure.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "flight-management", url = "http://localhost:8082")
public interface FlightClient {

    @GetMapping("/flights")
    FlightResponse findByFlightNumber(@RequestParam(value = "flightNumber") String flightNumber);
}
