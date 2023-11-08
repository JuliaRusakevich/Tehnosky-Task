package com.juliar.tehnoskytask.repository;

import com.juliar.tehnoskytask.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByAccountId(Integer accountId);

    Optional<Transaction> findByAccountIdAndId(Integer accountId, Integer id);
    //List<Transaction> findAllByAccountIdAndCreatedAtIsBetween(Integer accountId, LocalDateTime createdAt, LocalDateTime createdAt2);

}
