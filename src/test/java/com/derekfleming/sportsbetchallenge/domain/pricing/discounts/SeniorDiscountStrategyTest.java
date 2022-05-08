package com.derekfleming.sportsbetchallenge.domain.pricing.discounts;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeniorDiscountStrategyTest {
    private SeniorDiscountStrategy seniorDiscountStrategy;

    @BeforeEach
    public void setup() {
        seniorDiscountStrategy = new SeniorDiscountStrategy();
    }

    @Test
    public void shouldApplyDiscountToSeniorTickets() {
        Double adultCost = 30.0;
        Double expectedSeniorCost = adultCost * 0.7;
        List<Ticket> input = List.of(
                Ticket.builder().ticketType(TicketType.SENIOR).cost(adultCost).build(),
                Ticket.builder().ticketType(TicketType.ADULT).cost(adultCost).build());

        List<Ticket> discountedTickets = seniorDiscountStrategy.applyDiscount(input);

        Ticket seniorTicket = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.SENIOR)).findFirst().orElse(null);
        Ticket adultTicket = discountedTickets.stream().filter(ticket -> ticket.getTicketType().equals(TicketType.ADULT)).findFirst().orElse(null);
        assertNotNull(seniorTicket);
        assertNotNull(adultTicket);
        assertEquals(adultCost, adultTicket.getCost());
        assertEquals(expectedSeniorCost, seniorTicket.getCost());
    }
}