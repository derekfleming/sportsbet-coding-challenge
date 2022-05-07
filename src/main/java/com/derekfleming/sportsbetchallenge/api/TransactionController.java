package com.derekfleming.sportsbetchallenge.api;

import com.derekfleming.sportsbetchallenge.api.dto.TransactionRequest;
import com.derekfleming.sportsbetchallenge.api.dto.TransactionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TransactionController {

    @PostMapping("/transactions")
    public @ResponseBody TransactionResponse postTransaction(@RequestBody TransactionRequest request) {
        return TransactionResponse.builder()
                .transactionId(request.getTransactionId())
                .build();
    }
}
