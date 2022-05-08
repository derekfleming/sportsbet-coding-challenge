package com.derekfleming.sportsbetchallenge.api;

import com.derekfleming.sportsbetchallenge.api.dto.TicketDto;
import com.derekfleming.sportsbetchallenge.api.dto.TransactionRequest;
import com.derekfleming.sportsbetchallenge.api.dto.TransactionResponse;
import com.derekfleming.sportsbetchallenge.domain.TransactionService;
import com.derekfleming.sportsbetchallenge.domain.model.Customer;
import com.derekfleming.sportsbetchallenge.domain.model.TicketSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public @ResponseBody TransactionResponse postTransaction(@RequestBody TransactionRequest request) {
        List<Customer> customers = request.getCustomers().stream()
                .map(dto -> Customer.builder()
                        .age(dto.getAge())
                        .name(dto.getName())
                        .build())
                .collect(Collectors.toList());
        List<TicketSummary> results = transactionService.getTicketSummary(customers);
        return TransactionResponse.builder()
                .transactionId(request.getTransactionId())
                .tickets(results.stream()
                        .map(summary -> TicketDto.builder()
                                .ticketType(summary.getTicketType().toString())
                                .totalCost(summary.getTotalCost()).quantity(summary.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .totalCost(results.stream().map(TicketSummary::getTotalCost).reduce(0.0, Double::sum))
                .build();
    }
}
