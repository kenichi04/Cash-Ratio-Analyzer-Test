package com.example.cash_ratio_analyzer_test.application.service;

import com.example.cash_ratio_analyzer_test.domain.model.Account;
import com.example.cash_ratio_analyzer_test.domain.repository.IAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
