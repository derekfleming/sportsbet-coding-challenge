package com.derekfleming.sportsbetchallenge.api.transaction;

import com.derekfleming.sportsbetchallenge.api.transaction.dto.TransactionRequest;
import com.derekfleming.sportsbetchallenge.api.transaction.dto.TransactionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
