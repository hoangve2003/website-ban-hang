package com.demo.service;

import com.demo.model.Account;
import com.demo.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: Connect to database

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    public List<Account> getAll() {
        return accountDAO.findAll();
    }

    public Optional<Account> getById(String username) {
        return accountDAO.findById(username);
    }

    public Account addAccount(Account account) {
        return accountDAO.save(account);
    }
}
