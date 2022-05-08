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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransactionResponse)) {
            return false;
        }
        TransactionResponse response = (TransactionResponse) obj;
        return response.getTransactionId().equals(transactionId)
                && totalCost.equals(response.getTotalCost())
                && tickets.stream().allMatch(ticket -> response.getTickets().stream().anyMatch(ticket::equals));
    }
}
