package com.example.demo.dao;

import com.example.demo.domain.User;

import java.util.List;

/**
 * Created by gaonl on 2018/9/28.
 */
public interface UserDao {
    /**
     * 保存用户
     * @param user 要保存的用户实体
     * @return 保存的用户
     */
    User save(User user);

    /**
     * 根据用户id获取用户
     * @param id 用户id
     * @return 返回用户
     */
    User getById(Integer id);

    /**
     * 根据用户名称获取用户
     * @param name 用户名称
     * @return 返回用户
     */
    List<User> getByName(String name);
}
