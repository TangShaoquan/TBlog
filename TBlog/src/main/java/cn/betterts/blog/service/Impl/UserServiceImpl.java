package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleDao;
import cn.betterts.blog.dao.UserDao;
import cn.betterts.blog.domain.User;
import cn.betterts.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
//    依赖注入
    @Autowired(required = false)
    private UserDao userDao;

    @Autowired(required = false)
    private ArticleDao articleDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    };

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    };




    @Override
    public int deleteById(Integer userId) { return userDao.deleteById(userId); };

    @Override
    public int insert(User user) { return userDao.insert(user); };

    @Override
    public User getUserById(Integer userId) { return userDao.getUserById(userId);};

    @Override
    public int update(User user) { return userDao.update(user); };

    @Override
    public List<User> ListUser() {
        List<User> userList = userDao.ListUser();
        for (User user : userList){
            Integer articleCount = articleDao.countArticleByUser(user.getUserId());
            user.setArticleCount(articleCount);
        }
        return userList;
    };

    @Override
    public User getUserByNameOrEmail(String str) {
        return userDao.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String userName) { return userDao.getUserByName(userName); };

    @Override
    public User getUserByEmail(String userEmail) { return userDao.getUserByEmail(userEmail);};
}
