package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class FakeDiscountStrategy implements DiscountStrategy {
    private final Double discount = 0.5;
    private Boolean isApplicable = false;

    @Override
    public List<Ticket> applyDiscount(List<Ticket> tickets) {
        if (!isApplicable) return tickets;
        return tickets.stream().peek(ticket -> ticket.setCost(ticket.getCost() * discount)).collect(Collectors.toList());
    }

    public void setIsApplicable(Boolean applicable) {
        isApplicable = applicable;
    }
}
