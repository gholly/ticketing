package com.tw.travel.ticketing.controller;

import com.tw.travel.ticketing.assembler.TicketingOrderMapper;
import com.tw.travel.ticketing.controller.dto.CreateTicketingOrderRequest;
import com.tw.travel.ticketing.controller.dto.TicketingOrderResponse;
import com.tw.travel.ticketing.service.TicketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

    @PostMapping
    @ResponseStatus(CREATED)
    public TicketingOrderResponse createOrder(@RequestBody CreateTicketingOrderRequest createTicketingOrderRequest) {
        String orderNumber = ticketingService.createOrder(TicketingOrderMapper.MAPPER.toTicketingOrder(createTicketingOrderRequest));
        return TicketingOrderResponse.builder().orderNumber(orderNumber).build();
    }

    @PostMapping("/{orderNumber}/cancellation")
    public void cancelOrder(@PathVariable String orderNumber) {
        ticketingService.cancelOrder(orderNumber);
    }
}
