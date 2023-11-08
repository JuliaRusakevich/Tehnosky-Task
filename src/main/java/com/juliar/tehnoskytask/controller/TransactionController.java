package com.juliar.tehnoskytask.controller;

import com.juliar.tehnoskytask.dto.CreateTransactionDto;
import com.juliar.tehnoskytask.dto.TransactionInfo;
import com.juliar.tehnoskytask.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transaction")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody CreateTransactionDto dto) {
        transactionService.transferInOneCurrency(dto);
    }

    @GetMapping("/account/{accountId}/transaction/{transactionId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TransactionInfo findById(@PathVariable Integer accountId,
                                    @PathVariable Integer transactionId) {
        return transactionService.findByAccountIdAndTransactionId(accountId, transactionId);
    }


    @GetMapping("/transaction/account/{accountId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionInfo> getTransactions(@PathVariable Integer accountId) {
        return transactionService.findAll(accountId);
    }


}
