package com.dnm.test;

import com.dnm.dao.IAccountDao;
import com.dnm.dao.IUserDao;
import com.dnm.domain.Account;
import com.dnm.domain.User;
import com.dnm.domain.UserAccount;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class AccountTest {
    private SqlSession session;
    IAccountDao accountDao;
    IUserDao userDao;
    @Before
    public void init(){
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            session = factory.openSession(true);
            accountDao = session.getMapper(IAccountDao.class);
            userDao = session.getMapper(IUserDao.class);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public void destroy() {
        session.close();
    }

    /**
     * 查询所有账户
     */
    @Test
    public void findAll(){
        List<Account> accounts = accountDao.findAll();
        for(Account account : accounts){
            System.out.println(account);
        }
    }
    /**
     * 创建账户
     */
    @Test
    public void saveAccount(){
        // 3 6 12
        Account account = new Account();
        account.setMoney(10000.0);
        account.setUid(3);
        accountDao.saveAccount(account);
        findAll();
    }

    /**
     * 批量创建客户
     */
    @Test
    public void saveMany(){
        List<User> users = userDao.findAll();
        Random random = new Random();
        Double avg = new Double(50000f);
        double std = new Double(30000f);
        for(User u : users){
            Account account = new Account();
            account.setMoney(avg + (random.nextDouble() - 0.5f)*std);
            account.setUid(u.getId());
            accountDao.saveAccount(account);
        }
        findAll();
    }

    /**
     * 根据账户编号删除账户
     */
    @Test
    public void deleteByAccountId(){
        accountDao.deleteByAccountId(6);
        findAll();
    }

    @Test
    public void deleteByUserId(){
        accountDao.deleteByUserId(3);
        findAll();
    }

    /**
     * 查询所有账户，包括用户名和地址
     */
    @Test
    public void findAllUserAccount(){
        List<UserAccount> userAccounts = accountDao.findAllUserAccount();

        for(UserAccount u : userAccounts){
            System.out.println(u);
        }
    }
}
