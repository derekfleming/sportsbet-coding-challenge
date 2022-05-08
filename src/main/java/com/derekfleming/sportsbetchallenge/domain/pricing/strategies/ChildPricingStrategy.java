package com.derekfleming.sportsbetchallenge.domain.pricing.strategies;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import com.derekfleming.sportsbetchallenge.domain.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ChildPricingStrategy implements PricingStrategy {
    @Override
    public Boolean isApplicable(Customer customer) {
        return customer.getAge() >= 0 && customer.getAge() < 11;
    }

    @Override
    public Ticket getTicket(Customer customer) {
        return Ticket.builder().ticketType(TicketType.CHILD).cost(5.0).build();
    }
}
