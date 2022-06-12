package com.tw.travel.ticketing.assembler;

import com.tw.travel.ticketing.controller.dto.CreateTicketingOrderRequest;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-12T16:01:16+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class TicketingOrderMapperImpl implements TicketingOrderMapper {

    @Override
    public TicketingOrder toTicketingOrder(CreateTicketingOrderRequest createTicketingOrderRequest) {
        if ( createTicketingOrderRequest == null ) {
            return null;
        }

        TicketingOrder ticketingOrder = new TicketingOrder();

        ticketingOrder.setOrderNo( UUID.randomUUID().toString() );

        return ticketingOrder;
    }
}
