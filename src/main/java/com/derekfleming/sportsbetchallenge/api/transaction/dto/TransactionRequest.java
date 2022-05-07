package com.derekfleming.sportsbetchallenge.api.transaction.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Integer transactionId;
    private List<CustomerDto> customers;
}
