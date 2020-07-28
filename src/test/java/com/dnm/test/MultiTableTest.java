package com.dnm.test;

import com.dnm.dao.IAccountDao;
import com.dnm.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

public class MultiTableTest {
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
}
