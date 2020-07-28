package com.dnm.test;

import com.dnm.dao.IRoleDao;
import com.dnm.dao.IUserDao;
import com.dnm.domain.Role;
import com.dnm.domain.User;
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

public class RoleTest {

    private SqlSession session;
    private IUserDao userDao;
    private IRoleDao roleDao;
    @Before
    public void init(){
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            session = factory.openSession();
            userDao = session.getMapper(IUserDao.class);
            roleDao = session.getMapper(IRoleDao.class);
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
     * 擦护照承担此角色的所有用户，多对多测试
     */
    @Test
    public void findAll(){
        List<Role> roles = roleDao.findAll();

        for(Role role: roles){
            System.out.println(role);
            List<User> users = role.getUsers();
            for(User user : users){
                System.out.println("\t|---->" + user);
            }
        }
    }
}


