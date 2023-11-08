package com.juliar.tehnoskytask.entity;

import com.juliar.tehnoskytask.entity.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "users", schema = "accounts")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String password;
    private String mail;
    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String documentNumber;
    //active user or not
    private Boolean status;


    public User(Integer id, String fullName, String mail, String password, DocumentType documentType, String documentNumber) {
        this.id = id;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User(Integer id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }
}
