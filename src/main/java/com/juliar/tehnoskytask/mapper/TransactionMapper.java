package com.juliar.tehnoskytask.mapper;

import com.juliar.tehnoskytask.dto.CreateTransactionDto;
import com.juliar.tehnoskytask.dto.TransactionInfo;
import com.juliar.tehnoskytask.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {

    public Transaction getInflowTransaction(CreateTransactionDto dto) {
        return Transaction
                .builder()
                .type(dto.getType())
                .sum(dto.getSum())
                .accountId(dto.getReceiverAccountId())
                .build();
    }

    public Transaction getWriteOffTransaction(CreateTransactionDto dto) {
        return Transaction
                .builder()
                .type(dto.getType())
                .sum(dto.getSum().multiply(BigDecimal.valueOf(-1)))
                .accountId(dto.getSenderAccountId())
                .build();
    }

    public TransactionInfo mapToTransactionInfo(Transaction entity) {

        return TransactionInfo.builder()
                .accountId(entity.getId())
                .transactionType(entity.getType())
                .sumInCurrencyAccount(entity.getSum())
                .date(entity.getCreatedAt())
                .build();
    }

}
