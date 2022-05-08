package com.derekfleming.sportsbetchallenge.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Integer transactionId;
    private List<TicketDto> tickets;
    private Double totalCost;
}
