package com.juliar.tehnoskytask.service;

import com.juliar.tehnoskytask.dto.ReadAccountDto;
import com.juliar.tehnoskytask.entity.Account;
import com.juliar.tehnoskytask.mapper.AccountMapper;
import com.juliar.tehnoskytask.repository.AccountRepository;
import com.juliar.tehnoskytask.service.api.CrudService;
import com.juliar.tehnoskytask.service.api.IAccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class AccountService implements CrudService<Account, ReadAccountDto>, IAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public ReadAccountDto findById(Integer id) {
        var accountFromDb = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
        return accountMapper.getReadAccountDto(accountFromDb);

    }

    @Override
    public BigDecimal getCurrentBalance(Integer accountId) {
        var accountFromDb = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
        return accountFromDb.getBalance();

    }

    @Override
    public BigDecimal updateBalance(Integer accountId, BigDecimal amount) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));
        var currentBalance = getCurrentBalance(accountId);
        account.setBalance(currentBalance.add(amount));
        accountRepository.saveAndFlush(account);
        return currentBalance.add(amount);
    }



}
