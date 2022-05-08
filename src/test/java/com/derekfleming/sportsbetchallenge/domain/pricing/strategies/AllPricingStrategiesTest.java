package com.derekfleming.sportsbetchallenge.domain.pricing.strategies;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.pricing.PricingStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AllPricingStrategiesTest {

    @Autowired
    private List<PricingStrategy> pricingStrategies;

    @ParameterizedTest
    @MethodSource("allValidAges")
    public void shouldMatchOnlyOneStrategy(Integer age) {
        Customer customer = Customer.builder().age(age).name("name").build();

        List<PricingStrategy> applicableStrategies = pricingStrategies.stream()
                .filter(pricingStrategy -> pricingStrategy.isApplicable(customer))
                .collect(Collectors.toList());

        assertEquals(1, applicableStrategies.size());
    }

    private static Stream<Arguments> allValidAges() {
        return IntStream.range(0, 150).mapToObj(Arguments::of);
    }
}
