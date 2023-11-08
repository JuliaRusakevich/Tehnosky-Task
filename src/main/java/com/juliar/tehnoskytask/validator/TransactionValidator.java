package com.juliar.tehnoskytask.validator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionValidator {

    public void checkCurrentBalance(BigDecimal one, BigDecimal two) {
        if (one.compareTo(two) < 0) {
            throw new UnsupportedOperationException("Not enough money!");
        }
    }

    public void checkCurrency(String sender, String receiver) {
        if (!sender.equals(receiver)) {
            throw new IllegalArgumentException("Transfer is possible in one currency!");
        }
    }
}
