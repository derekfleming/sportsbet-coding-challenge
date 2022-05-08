package com.derekfleming.sportsbetchallenge.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketSummary {
    private TicketType ticketType;
    private Integer quantity;
    private Double totalCost;
}
