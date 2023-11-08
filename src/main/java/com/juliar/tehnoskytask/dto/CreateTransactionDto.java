package com.juliar.tehnoskytask.dto;

import com.juliar.tehnoskytask.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionDto {

    private TransactionType type;
    private String currency;
    private BigDecimal sum;
    private Integer senderAccountId;
    private Integer receiverAccountId;
}
