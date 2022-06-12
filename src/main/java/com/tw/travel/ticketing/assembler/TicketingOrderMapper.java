package com.tw.travel.ticketing.assembler;

import com.tw.travel.ticketing.controller.dto.CreateTicketingOrderRequest;
import com.tw.travel.ticketing.infrastructure.repository.entity.TicketingOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(imports = {UUID.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketingOrderMapper {

    TicketingOrderMapper MAPPER = Mappers.getMapper(TicketingOrderMapper.class);


    @Mapping(target = "orderNo", expression = "java(UUID.randomUUID().toString())")
    TicketingOrder toTicketingOrder(CreateTicketingOrderRequest createTicketingOrderRequest);
}
