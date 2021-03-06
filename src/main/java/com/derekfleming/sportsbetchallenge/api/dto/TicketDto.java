package com.derekfleming.sportsbetchallenge.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String ticketType;
    private Integer quantity;
    private Double totalCost;
}
