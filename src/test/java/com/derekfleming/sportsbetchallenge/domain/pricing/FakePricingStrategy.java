package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;

public class FakePricingStrategy implements PricingStrategy {
    private TicketType ticketType = TicketType.ADULT;
    private Double ticketCost = 10.00;

    @Override
    public Boolean isApplicable(Customer customer) {
        return true;
    }

    @Override
    public Ticket getTicket(Customer customer) {
        return Ticket.builder().ticketType(ticketType).cost(ticketCost).build();
    }

    public void setTicketType(TicketType newTicketType) {
        ticketType = newTicketType;
    }

    public void setTicketCost(Double newCost) {
        ticketCost = newCost;
    }
}
