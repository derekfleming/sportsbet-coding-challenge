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

class TeenPricingStrategyTest {
    private TeenPricingStrategy teenPricingStrategy;

    @BeforeEach
    public void setup() {
        teenPricingStrategy = new TeenPricingStrategy();
    }

    @ParameterizedTest
    @MethodSource("allValidAges")
    public void shouldBeApplicableToAges11to17(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = teenPricingStrategy.isApplicable(customer);

        assertTrue(isApplicable);
    }

    @ParameterizedTest
    @MethodSource({"youngerAges", "olderAges"})
    public void shouldNotBeApplicableOtherwise(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = teenPricingStrategy.isApplicable(customer);

        assertFalse(isApplicable);
    }

    @Test
    public void shouldHaveTeenTicketWithTeenPricing() {
        Customer customer = Customer.builder().age(15).name("name").build();

        Ticket ticket = teenPricingStrategy.getTicket(customer);

        assertEquals(TicketType.TEEN, ticket.getTicketType());
        assertEquals(12.0, ticket.getCost());
    }

    private static Stream<Arguments> allValidAges() {
        return IntStream.range(11, 17).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> youngerAges() {
        return IntStream.range(0, 10).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> olderAges() {
        return IntStream.range(18, 150).mapToObj(Arguments::of);
    }
}