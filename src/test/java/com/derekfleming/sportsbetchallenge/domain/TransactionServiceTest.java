package com.derekfleming.sportsbetchallenge.domain;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.TicketSummary;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        transactionService = new TransactionService(new FakePricingService());
    }

    @Test
    public void shouldReturnEmptyForEmptyCustomers() {
        List<TicketSummary> expected = List.of();
        List<Customer> input = List.of();

        List<TicketSummary> actual = transactionService.getTicketSummary(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSummariseTicketsIntoDistinctCategories() {
        List<Customer> input = List.of(
                Customer.builder().age(5).name("Bob").build(),
                Customer.builder().age(6).name("Not Bob").build(),
                Customer.builder().age(15).name("Teen").build(),
                Customer.builder().age(15).name("Not Teen").build(),
                Customer.builder().age(31).name("Jerry").build(),
                Customer.builder().age(31).name("Not Jerry").build(),
                Customer.builder().age(90).name("Grace").build(),
                Customer.builder().age(91).name("Not Grace").build());

        List<TicketSummary> results = transactionService.getTicketSummary(input);

        assertTrue(results.stream().filter(ticketSummary -> ticketSummary.getTicketType().equals(TicketType.CHILD)).count() == 1);
        assertTrue(results.stream().filter(ticketSummary -> ticketSummary.getTicketType().equals(TicketType.TEEN)).count() == 1);
        assertTrue(results.stream().filter(ticketSummary -> ticketSummary.getTicketType().equals(TicketType.ADULT)).count() == 1);
        assertTrue(results.stream().filter(ticketSummary -> ticketSummary.getTicketType().equals(TicketType.SENIOR)).count() == 1);
    }

    @Test
    public void shouldAggregateCostsInEachCategory() {
        List<Customer> input = List.of(
                Customer.builder().age(5).name("Bob").build(),
                Customer.builder().age(6).name("Not Bob").build(),
                Customer.builder().age(15).name("Teen").build(),
                Customer.builder().age(15).name("Not Teen").build(),
                Customer.builder().age(30).name("Jerry").build(),
                Customer.builder().age(30).name("Not Jerry").build(),
                Customer.builder().age(90).name("Grace").build(),
                Customer.builder().age(91).name("Not Grace").build());

        List<TicketSummary> results = transactionService.getTicketSummary(input);

        assertTrue(results.size() > 0);
        assertTrue(results.stream().allMatch(result -> result.getTotalCost() > 10));
    }
}