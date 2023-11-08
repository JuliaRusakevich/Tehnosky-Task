package com.juliar.tehnoskytask.mapper;

import com.juliar.tehnoskytask.dto.ReadAccountDto;

import com.juliar.tehnoskytask.dto.UserReadDto;
import com.juliar.tehnoskytask.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public ReadAccountDto getReadAccountDto(Account entity) {

        return ReadAccountDto.builder()

                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .currentBalance(entity.getBalance())
                .currency(entity.getCurrency().toString())

                .user(UserReadDto.builder()
                        .fullName(entity.getUser().getFullName())
                        .build())

                .build();
    }
}
