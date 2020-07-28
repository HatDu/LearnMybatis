package com.dnm.test;

import com.dnm.dao.IUserDao;
import com.dnm.domain.Account;
import com.dnm.domain.QueryVo;
import com.dnm.domain.User;
import com.sun.org.apache.xml.internal.security.Init;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Resource;

//import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mybatis入门案例
 */
public class UserTest {
    private SqlSession session;
    IUserDao userDao;
    @Before
    public void init(){
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            session = factory.openSession();
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
    public void main(String[] args) throws IOException {
        // 1. 读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3. 使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        // 4. 使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5. 使用代理对象执行方法
        List<User> users = userDao.findAll();
        System.out.println(users);
        // 6. 释放资源
        session.close();
        in.close();
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }
    /**
     * 测试保存
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("瑞克");
        user.setAddress("四川绵阳");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作前的user：" + user);
        // 执行保存方法
        userDao.saveUser(user);
        session.commit();
        System.out.println("保存操作后的user：" + user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3);
        user.setUsername("普利莫");
        user.setAddress("河北唐山");
        user.setSex("男");
        user.setBirthday(new Date());

        // 执行保存方法
        userDao.updateUser(user);
        session.commit();
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(4);
        session.commit();
        findAll();
    }

    /**
     * 查询一个用户
     */
    @Test
    public void findOne(){
        User user = userDao.findById(5);
        System.out.println(user);
    }

    /**
     * 根据名称进行查询，包括模糊查询
     */
    @Test
    public void testFindByName(){
        User user = userDao.findByName("佩%");
        System.out.println(user);
    }

    /**
     * 查询记录条数
     */
    @Test
    public void findTotalCount(){
        Integer count = userDao.findTotalCount();
        System.out.println("共有" + count + "条数据。");
    }

    /**
     * Object-Graph Navigation Language(ONGL)表达式，查询
     */
    @Test
    public void queryUserByVo(){
        User user = new User();
        user.setUsername("%比%");
        QueryVo vo = new QueryVo();
        vo.setUser(user);

        List<User> users = userDao.queryUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }
    }

    /**
     * 根据条件查询
     */
    @Test
    public void findByCondition(){
        User user = new User();
        user.setUsername("比比");
        user.setSex("女");
        //user.setSex("男");
        List<User> users = userDao.findUserByCondition(user);

        for(User u : users){
            System.out.println(u);
        }
    }

    @Test
    public void findUserInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(3);
        ids.add(6);
        ids.add(12);
        vo.setIds(ids);
        List<User> users = userDao.findUserInIds(vo);

        for(User u : users){
            System.out.println(u);
        }
    }

    @Test
    public void findAllUserAccount(){
        List<User> users = userDao.findAllUserAccount();
        for(User user : users){
            System.out.println(user);
            for(Account account : user.getAccounts()){
                System.out.println("\t|---->" + account);
            }
        }
    }
}
