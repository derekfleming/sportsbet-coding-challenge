package com.derekfleming.sportsbetchallenge.domain.pricing.strategies;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component
public class TeenPricingStrategy implements PricingStrategy {
    @Override
    public Boolean isApplicable(Customer customer) {
        return customer.getAge() >= 11 && customer.getAge() < 18;
    }

    @Override
    public Ticket getTicket(Customer customer) {
        return null;
    }
}
