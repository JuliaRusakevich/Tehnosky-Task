package com.juliar.tehnoskytask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity<T> {

    @Column(name = "createdat")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "createdby")
    @CreatedBy
    private String createdBy;
}
