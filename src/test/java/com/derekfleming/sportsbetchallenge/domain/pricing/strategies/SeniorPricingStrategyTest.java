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

class SeniorPricingStrategyTest {
    private AdultPricingStrategy adultPricingStrategy;
    private SeniorPricingStrategy seniorPricingStrategy;

    @BeforeEach
    public void setup() {
        adultPricingStrategy = new AdultPricingStrategy();
        seniorPricingStrategy = new SeniorPricingStrategy();
    }

    @ParameterizedTest
    @MethodSource("allValidAges")
    public void shouldBeApplicableToAgesGreaterThan64(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = seniorPricingStrategy.isApplicable(customer);

        assertTrue(isApplicable);
    }

    @ParameterizedTest
    @MethodSource({"youngerAges"})
    public void shouldNotBeApplicableOtherwise(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = seniorPricingStrategy.isApplicable(customer);

        assertFalse(isApplicable);
    }

    @Test
    public void shouldHaveSeniorTicketWithAdultPricing() {
        Customer adult = Customer.builder().age(25).name("name").build();
        Double adultCost = adultPricingStrategy.getTicket(adult).getCost();
        Customer senior = Customer.builder().age(70).name("name").build();

        Ticket ticket = seniorPricingStrategy.getTicket(senior);

        assertEquals(TicketType.SENIOR, ticket.getTicketType());
        assertEquals(adultCost, ticket.getCost());
    }

    private static Stream<Arguments> allValidAges() {
        return IntStream.range(65, 150).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> youngerAges() {
        return IntStream.range(0, 64).mapToObj(Arguments::of);
    }
}