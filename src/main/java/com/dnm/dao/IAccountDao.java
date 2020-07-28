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
     * 查询所有账户，并包含用户名和地址信息，使用继承Account的方式，一对一映射
     * @return
     */
    List<UserAccount> findAllUserAccount();

    /**
     * 查询所有账户，并包含用户名和地址信息，Account组合User的方式，一对一映射
     * @return
     */
    List<Account> findAllAccountUser();
}
