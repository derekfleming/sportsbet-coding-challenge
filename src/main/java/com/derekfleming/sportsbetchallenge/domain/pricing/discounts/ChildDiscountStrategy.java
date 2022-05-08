package com.derekfleming.sportsbetchallenge.domain.pricing.discounts;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import com.derekfleming.sportsbetchallenge.domain.pricing.DiscountStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChildDiscountStrategy implements DiscountStrategy {
    private final Integer discountThreshold = 3;
    private final Double discountMultiplier = 0.75;

    @Override
    public List<Ticket> applyDiscount(List<Ticket> tickets) {
        if (tickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.CHILD)).count() >= discountThreshold) {
            return tickets.stream().peek(ticket -> {
                if (ticket.getTicketType().equals(TicketType.CHILD)) {
                    ticket.setCost(ticket.getCost() * discountMultiplier);
                }
            }).collect(Collectors.toList());
        }
        return tickets;
    }
}
