package com.demo.repository;


import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, String> {
    Optional<Account> findByUsernameAndPassword(String un, String pwd);
    Optional<Account> findByUsername(String un);
}
