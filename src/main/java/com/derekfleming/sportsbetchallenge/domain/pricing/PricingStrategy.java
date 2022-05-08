package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;

public interface PricingStrategy {
    Boolean isApplicable(Customer customer);
    Ticket getTicket(Customer customer);
}
