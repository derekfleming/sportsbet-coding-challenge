package com.derekfleming.sportsbetchallenge.domain;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;

import java.util.List;
import java.util.stream.Collectors;

public class FakePricingService implements PricingService {
    @Override
    public List<Ticket> calculateTickets(List<Customer> customers) {
        return customers.stream()
                .map(customer -> Ticket.builder().ticketType(customer.getAge() > 12 ? (customer.getAge() > 18 ? customer.getAge() > 60 ? TicketType.SENIOR : TicketType.ADULT : TicketType.TEEN) : TicketType.CHILD).cost(10.0).build())
                .collect(Collectors.toList());
    }
}
