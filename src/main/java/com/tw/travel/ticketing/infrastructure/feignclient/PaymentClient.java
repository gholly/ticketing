package com.tw.travel.ticketing.infrastructure.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-management", url = "http://localhost:8082")
public interface PaymentClient {

    @PostMapping("/{oid}/payment/cancellation")
    PaymentResponse payment(@PathVariable(value = "oid") String oid);
}
