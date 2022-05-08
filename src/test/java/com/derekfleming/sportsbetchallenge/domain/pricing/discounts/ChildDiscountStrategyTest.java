package com.derekfleming.sportsbetchallenge.domain.pricing.discounts;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ChildDiscountStrategyTest {
    private ChildDiscountStrategy childDiscountStrategy;

    @BeforeEach
    public void setup() {
        childDiscountStrategy = new ChildDiscountStrategy();
    }

    @Test
    public void shouldApplyDiscountToChildTicketsWhenThereAre3OrMore() {
        Double adultCost = 30.0;
        Double childCost = 20.0;
        Double expectedChildCost = 20 * 0.75;
        List<Ticket> input = List.of(
                Ticket.builder().ticketType(TicketType.CHILD).cost(childCost).build(),
                Ticket.builder().ticketType(TicketType.CHILD).cost(childCost).build(),
                Ticket.builder().ticketType(TicketType.CHILD).cost(childCost).build(),
                Ticket.builder().ticketType(TicketType.ADULT).cost(adultCost).build());

        List<Ticket> discountedTickets = childDiscountStrategy.applyDiscount(input);

        List<Ticket> childTickets = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.CHILD)).collect(Collectors.toList());
        Ticket adultTicket = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.ADULT)).findFirst().orElse(null);
        assertEquals(3, childTickets.size());
        assertNotNull(adultTicket);
        assertEquals(adultCost, adultTicket.getCost());
        assertTrue(childTickets.stream().allMatch(ticket -> ticket.getCost().equals(expectedChildCost)));
    }

    @Test
    public void shouldNotApplyDiscountOtherwise() {
        Double adultCost = 30.0;
        Double childCost = 20.0;
        List<Ticket> input = List.of(
                Ticket.builder().ticketType(TicketType.CHILD).cost(childCost).build(),
                Ticket.builder().ticketType(TicketType.CHILD).cost(childCost).build(),
                Ticket.builder().ticketType(TicketType.ADULT).cost(adultCost).build());

        List<Ticket> discountedTickets = childDiscountStrategy.applyDiscount(input);

        List<Ticket> childTickets = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.CHILD)).collect(Collectors.toList());
        Ticket adultTicket = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.ADULT)).findFirst().orElse(null);
        assertEquals(2, childTickets.size());
        assertNotNull(adultTicket);
        assertEquals(adultCost, adultTicket.getCost());
        assertTrue(childTickets.stream().allMatch(ticket -> ticket.getCost().equals(childCost)));
    }
}