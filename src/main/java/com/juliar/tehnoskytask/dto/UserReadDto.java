package com.juliar.tehnoskytask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juliar.tehnoskytask.entity.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserReadDto {

    private Integer id;
    private String fullName;
    private String mail;
    private DocumentType documentType;
    private String documentNumber;

    public UserReadDto(String mail) {
        this.mail = mail;
    }
}
