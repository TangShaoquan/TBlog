package cn.betterts.blog.service;

import cn.betterts.blog.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public List<User> ListUser();
    public User getUserByName(String userName);
    public User getUserByEmail(String userEmail);
    public User getUserById(Integer userId);
    public int deleteById(Integer userId);
    public int insert(User user);
    public int update(User user);
    public void saveUser(User user);
    public User getUserByNameOrEmail(String str);
}
