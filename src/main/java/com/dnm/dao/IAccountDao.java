package com.dnm.dao;

import com.dnm.domain.Account;
import com.dnm.domain.UserAccount;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    void saveAccount(Account account);

    void deleteByAccountId(Integer id);

    void deleteByUserId(Integer id);

    /**
     * 查询所有账户，并包含用户名和地址信息
     * @return
     */
    List<UserAccount> findAllUserAccount();
}
