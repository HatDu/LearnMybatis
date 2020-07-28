package com.dnm.dao;

import com.dnm.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    void saveAccount(Account account);

    void deleteByAccountId(Integer id);

    void deleteByUserId(Integer id);
}
