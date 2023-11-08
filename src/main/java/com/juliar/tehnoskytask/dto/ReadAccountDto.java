package com.juliar.tehnoskytask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReadAccountDto {

    @JsonIgnore
    private Integer id;
    private String accountNumber;
    private String currency;
    private BigDecimal currentBalance;
    private UserReadDto user;
}
