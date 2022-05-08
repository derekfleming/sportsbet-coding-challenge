package com.derekfleming.sportsbetchallenge.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
    private TicketType ticketType;
    private Double cost;
}
