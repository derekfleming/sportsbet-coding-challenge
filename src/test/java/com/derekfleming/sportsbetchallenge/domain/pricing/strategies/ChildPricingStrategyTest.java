package com.derekfleming.sportsbetchallenge.domain.pricing.strategies;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import com.derekfleming.sportsbetchallenge.domain.model.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChildPricingStrategyTest {
    private ChildPricingStrategy childPricingStrategyPricingStrategy;

    @BeforeEach
    public void setup() {
        childPricingStrategyPricingStrategy = new ChildPricingStrategy();
    }

    @ParameterizedTest
    @MethodSource("allValidAges")
    public void shouldBeApplicableToAgesLessThan11(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = childPricingStrategyPricingStrategy.isApplicable(customer);

        assertTrue(isApplicable);
    }

    @ParameterizedTest
    @MethodSource({"olderAges"})
    public void shouldNotBeApplicableOtherwise(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = childPricingStrategyPricingStrategy.isApplicable(customer);

        assertFalse(isApplicable);
    }

    @Test
    public void shouldHaveChildTicketWithChildPricing() {
        Customer customer = Customer.builder().age(8).name("name").build();

        Ticket ticket = childPricingStrategyPricingStrategy.getTicket(customer);

        assertEquals(TicketType.CHILD, ticket.getTicketType());
        assertEquals(5.0, ticket.getCost());
    }

    private static Stream<Arguments> allValidAges() {
        return IntStream.range(0, 10).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> olderAges() {
        return IntStream.range(11, 150).mapToObj(Arguments::of);
    }
}