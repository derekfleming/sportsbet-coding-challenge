package com.derekfleming.sportsbetchallenge.api.dto;

import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.TicketSummary;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public TicketDto map(TicketSummary summary) {
        return TicketDto.builder()
                .ticketType(summary.getTicketType().toString())
                .totalCost(summary.getTotalCost()).quantity(summary.getQuantity())
                .build();
    }

    public Customer map(CustomerDto dto) {
        if (dto.getAge() == null) {
            throw new IllegalArgumentException("Age can't be null");
        }
        if (dto.getAge() < 0 || dto.getAge() > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
        if (dto.getName() == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        return Customer.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .build();
    }
}
