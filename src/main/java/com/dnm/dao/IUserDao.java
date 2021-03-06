package com.dnm.dao;

import com.dnm.domain.QueryVo;
import com.dnm.domain.Role;
import com.dnm.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有数据
     * @return
     */
    List<User> findAll();

    /**
     * 一对多查询，一个User对象中包含多个账户
     * @return
     */
    List<User> findAllUserAccount();

    List<User> findAllUserRole();


    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    /**
     * 根据Id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称查询，包括模糊查询
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 查询总的记录条数
     * @return
     */
    Integer findTotalCount();


    List<User> queryUserByVo(QueryVo queryVo);

    /**
     * Mybatis动态标签，where与if
     */
    List<User> findUserByCondition(User user);

    List<User> findUserInIds(QueryVo vo);


    /**
     * 查询所有成员，以懒加载的方式查询内部accounts成员
     * @return
     */
    List<User> findAllUserAccountLazyLoad();
}
