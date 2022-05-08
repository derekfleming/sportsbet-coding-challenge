package com.derekfleming.sportsbetchallenge.domain;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;

import java.util.List;

public interface PricingService {
    List<Ticket> calculateTickets(List<Customer> customers);
}
