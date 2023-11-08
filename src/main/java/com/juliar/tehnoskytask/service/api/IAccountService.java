package com.juliar.tehnoskytask.service.api;

import java.math.BigDecimal;

public interface IAccountService {

    BigDecimal getCurrentBalance(Integer accountId);

    BigDecimal updateBalance(Integer accountId, BigDecimal amount);
}
