package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.FakePricingService;
import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPricingServiceTest {
    private DefaultPricingService defaultPricingService;
    private FakePricingStrategy pricingStrategy;
    private FakeDiscountStrategy discountStrategy;

    @BeforeEach
    public void setup() {
        pricingStrategy = new FakePricingStrategy();
        discountStrategy = new FakeDiscountStrategy();
        List<PricingStrategy> pricingStrategies = List.of(pricingStrategy);
        List<DiscountStrategy> discountStrategies = List.of(discountStrategy);
        defaultPricingService = new DefaultPricingService(pricingStrategies, discountStrategies);
    }

    @Test
    public void shouldApplyPricingStrategyToDetermineTicketPrice() {
        Double expectedCost = 15.0;
        TicketType expectedTicketType = TicketType.CHILD;
        pricingStrategy.setTicketCost(expectedCost);
        pricingStrategy.setTicketType(expectedTicketType);
        List<Customer> input = List.of(Customer.builder().age(10).name("Bob").build());

        List<Ticket> result = defaultPricingService.calculateTickets(input);

        assertEquals(1, result.size());
        assertEquals(expectedCost, result.get(0).getCost());
        assertEquals(expectedTicketType, result.get(0).getTicketType());
    }

    @Test
    public void shouldApplyDiscountStrategyWhenItIsApplicable() {
        discountStrategy.setIsApplicable(true);
        List<Customer> input = List.of(Customer.builder().age(10).name("Bob").build());

        List<Ticket> result = defaultPricingService.calculateTickets(input);

        assertEquals(1, result.size());
        assertEquals(5.0, result.get(0).getCost());
    }

    @Test
    public void shouldReturnEmptyWhenNoPricingStrategyIsFound() {
        defaultPricingService = new DefaultPricingService(List.of(), List.of());

        List<Customer> input = List.of(Customer.builder().age(10).name("Bob").build());

        List<Ticket> result = defaultPricingService.calculateTickets(input);

        assertEquals(0, result.size());
    }
}