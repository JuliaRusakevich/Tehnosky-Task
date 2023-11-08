package com.juliar.tehnoskytask.repository;

import com.juliar.tehnoskytask.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
