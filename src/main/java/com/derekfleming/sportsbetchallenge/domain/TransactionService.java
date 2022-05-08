package com.derekfleming.sportsbetchallenge.domain;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketSummary;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class TransactionService {
    private final PricingService pricingService;

    public List<TicketSummary> getTicketSummary(List<Customer> customers) {
        List<Ticket> tickets = pricingService.calculateTickets(customers);
        Map<TicketType, TicketSummary> summaryMap = new HashMap<>();
        Arrays.stream(TicketType.values()).forEach(ticketType -> {
            tickets.stream()
                    .filter(ticket -> ticket.getTicketType().equals(ticketType))
                    .forEach(ticket -> summaryMap.compute(ticketType, (key, value) ->
                            value == null
                            ? TicketSummary.builder().ticketType(ticketType).quantity(1).totalCost(ticket.getCost()).build()
                            : TicketSummary.builder().ticketType(ticketType).quantity(value.getQuantity() + 1).totalCost(value.getTotalCost() + ticket.getCost()).build()));
        });
        return new ArrayList<>(summaryMap.values());
    }
}
