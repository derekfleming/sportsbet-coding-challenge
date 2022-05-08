package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.PricingService;
import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultPricingService implements PricingService {
    private final List<PricingStrategy> pricingStrategies;
    private final List<DiscountStrategy> discountStrategies;

    @Override
    public List<Ticket> calculateTickets(List<Customer> customers) {
        List<Ticket> tickets = customers.stream()
                .map(customer -> pricingStrategies.stream()
                        .filter(pricingStrategy -> pricingStrategy.isApplicable(customer))
                        .findFirst()
                        .map(pricingStrategy -> pricingStrategy.getTicket(customer))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (DiscountStrategy discountStrategy : discountStrategies) {
            tickets = discountStrategy.applyDiscount(tickets);
        }

        return tickets;
    }
}
