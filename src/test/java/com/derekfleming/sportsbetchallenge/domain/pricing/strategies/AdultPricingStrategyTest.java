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

class AdultPricingStrategyTest {
    private AdultPricingStrategy adultPricingStrategy;

    @BeforeEach
    public void setup() {
        adultPricingStrategy = new AdultPricingStrategy();
    }

    @ParameterizedTest
    @MethodSource("allValidAges")
    public void shouldBeApplicableToAges18to64(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = adultPricingStrategy.isApplicable(customer);

        assertTrue(isApplicable);
    }

    @ParameterizedTest
    @MethodSource({"youngerAges", "olderAges"})
    public void shouldNotBeApplicableOtherwise(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        Boolean isApplicable = adultPricingStrategy.isApplicable(customer);

        assertFalse(isApplicable);
    }

    @Test
    public void shouldHaveAdultTicketWithAdultPricing() {
        Customer customer = Customer.builder().age(25).name("name").build();

        Ticket ticket = adultPricingStrategy.getTicket(customer);

        assertEquals(TicketType.ADULT, ticket.getTicketType());
        assertEquals(25.0, ticket.getCost());
    }

    private static Stream<Arguments> allValidAges() {
        return IntStream.range(18, 64).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> youngerAges() {
        return IntStream.range(0, 17).mapToObj(Arguments::of);
    }
    private static Stream<Arguments> olderAges() {
        return IntStream.range(65, 150).mapToObj(Arguments::of);
    }
}