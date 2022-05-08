package com.derekfleming.sportsbetchallenge.domain.pricing;

import com.derekfleming.sportsbetchallenge.domain.model.Ticket;

import java.util.List;

public interface DiscountStrategy {
    List<Ticket> applyDiscount(List<Ticket> tickets);
}
