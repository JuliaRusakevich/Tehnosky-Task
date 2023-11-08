package com.juliar.tehnoskytask.repository;


import com.juliar.tehnoskytask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByFullName(String fullName);

    Optional<User> findByMail(String mail);


}
