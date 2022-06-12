package com.tw.travel.ticketing.service;

import com.tw.travel.ticketing.exception.FlightNoSeatException;
import com.tw.travel.ticketing.exception.OrderNoFoundException;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightClient;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightResponse;
import com.tw.travel.ticketing.infrastructure.feignclient.FlightStatus;
import com.tw.travel.ticketing.infrastructure.feignclient.PaymentClient;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import com.tw.travel.ticketing.infrastructure.repository.TicketingOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TicketingService {


    private final FlightClient flightClient;
    private final PaymentClient paymentClient;
    private final TicketingOrderRepository ticketingOrderRepository;

    public String createOrder(TicketingOrder ticketingOrder) {
        String flightNumber = ticketingOrder.getFlightNumber();
        FlightResponse flightResponse = flightClient.findByFlightNumber(flightNumber);
        if (flightResponse.getStatus().equals(FlightStatus.SEAT_HAS_SOLD_OUT)) {
            log.error("The flight {} have no seat to sold ", flightNumber);
            throw new FlightNoSeatException();
        }
        ticketingOrder.setFlightDepartTime(flightResponse.getDepartTime());
        TicketingOrder createdOrder = ticketingOrderRepository.save(ticketingOrder);
        return createdOrder.getOrderNo();
    }


    public void cancelOrder(String orderNo) {
        Optional<TicketingOrder> orderOptional = ticketingOrderRepository.findByOrderNumber(orderNo);
        if (orderOptional.isPresent()) {
            throw new OrderNoFoundException();
        }
        TicketingOrder order = orderOptional.get();
        if (!order.canBeCancel()) {
            throw new FlightNoSeatException();
        }
        order.cancel();
        TicketingOrder canceledOrder = ticketingOrderRepository.save(order);
        try {
            paymentClient.payment(orderNo);
        } catch (Exception e) {
//            orderCancelRabbitSender.send(canceledOrder, null);
        }

    }
}
