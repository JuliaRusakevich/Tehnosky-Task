package com.juliar.tehnoskytask.service.api;

import com.juliar.tehnoskytask.dto.CreateTransactionDto;
import com.juliar.tehnoskytask.dto.TransactionInfo;

import java.util.List;


public interface ITransactionService {

    void transferInOneCurrency(CreateTransactionDto dto);

    List<TransactionInfo> findAll(Integer accountId);

    TransactionInfo findByAccountIdAndTransactionId(Integer accountId, Integer transactionId);
}
