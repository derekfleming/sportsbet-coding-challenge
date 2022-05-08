package com.derekfleming.sportsbetchallenge.domain.pricing.discounts;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import com.derekfleming.sportsbetchallenge.domain.pricing.DiscountStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeniorDiscountStrategy implements DiscountStrategy {
    private final Double discountMultiplier = 0.7;
    @Override
    public List<Ticket> applyDiscount(List<Ticket> tickets) {
        return tickets.stream().peek(ticket -> {
            if (ticket.getTicketType().equals(TicketType.SENIOR)) {
                ticket.setCost(ticket.getCost() * discountMultiplier);
            }
        }).collect(Collectors.toList());
    }
}
