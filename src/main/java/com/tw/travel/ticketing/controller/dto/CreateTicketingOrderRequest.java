package com.tw.travel.ticketing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTicketingOrderRequest {

    @NotBlank
    private String flightNumber;

    @NotNull
    private Integer quantity;
}
