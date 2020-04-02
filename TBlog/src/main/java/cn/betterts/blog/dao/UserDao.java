package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.UserUpdateSql;
import cn.betterts.blog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description: 持久层: 操纵User表
* @Author: 唐绍权
* @Date: 2020/3/28
*/
@Repository
public interface UserDao {
    /** 
    * @Description: 查询所有用户的所有信息
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.User>
    */
    @Select("select * from user")
    @Results(id="userMap",value = {
            @Result(id=true,column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_pass",property = "userPassword"),
            @Result(column = "user_nickname",property = "userNickName"),
            @Result(column = "user_url",property = "userUrl"),
            @Result(column = "user_avatar",property = "userAvatar"),
            @Result(column = "user_last_login_ip",property = "userLastLoginIp"),
            @Result(column = "user_register_time",property = "userRegisterTime"),
            @Result(column = "user_last_login_time",property = "userLastLoginTime"),
            @Result(column = "user_status",property = "userStatus")
    })
    public List<User> findAll();

    /**
     * @Description: 获取用户列表
     * @Param: []
     * @return: java.util.List<cn.betterts.blog.domain.User>
     */
    @Select("select * from user order by 'user_status' asc")
    @ResultMap("userMap")
    public List<User> ListUser();

    /**
     * @Description: 根据用户名获取用户信息
     * @Param: [userName]
     * @return: cn.betterts.blog.domain.User
     */
    @Select("select * from user where user_name=#{userName}")
    @ResultMap("userMap")
    public User getUserByName(String userName);

    /**
     * @Description: 根据邮箱获取用户信息
     * @Param: [userEmail]
     * @return: cn.betterts.blog.domain.User
     * @Author: 唐绍权
     * @Date: 1:01.2020/3/28
     */
    @Select("select * from user where user_email=#{userEmail}")
    public User getUserByEmail(String userEmail);

    /**
     * @Description: 根据Id查询用户的所有信息
     * @Param: [userId]
     * @return: cn.betterts.blog.domain.User
     */
    @Select("select * from user where user_id=#{userId}")
    @ResultMap("userMap")
    public User getUserById(Integer userId);

    /**
    * @Description: 根据ID删除用户
    * @Param: [userId]
    * @return: int 影响的行数
    */
    @Delete("delete from user where user_id=#{userId}")
    @ResultMap("userMap")
    public int deleteById(Integer userId);

    /**
    * @Description: 插入用户,作用跟保存用户一样
    * @Param: [user]
    * @return: int 返回影响的行数
    */
    @Insert("insert into user(user_name,user_pass,user_nickname,user_url,user_avatar,user_last_login_ip,user_register_time,user_last_login_time,user_status) " +
            "values(#{userName},#{userPassword},#{userNickName},#{userUrl},#{userAvatar},#{userLastLoginIp},#{userRegisterTime},#{userLastLoginTime},#{userStatus})")
    @ResultMap("userMap")
    public int insert(User user);

    /**
    * @Description: 更新用户信息,设定只能更改用户名,密码,昵称,网址,头像五种信息
    * @Param: [user]
    * @return: int
    */
    @UpdateProvider(type = UserUpdateSql.class,method = "Update")
    @ResultMap("userMap")
    public int update(User user);

    /**
     * @Description: 添加用户
     * @Param: [user]
     * @return: void
     */
    @Insert("insert into user" +
            "(user_name,user_pass,user_nickname,user_url," +
            "user_avatar,user_last_login_ip,user_register_time," +
            "user_last_login_time,user_status) " +
            " values " +
            "(#{userName},#{userPassword},#{userNickName}," +
            "#{userUrl},#{userAvatar},#{userLastLoginIp}," +
            "#{userRegisterTime},#{userLastLoginTime},#{userStatus})")
    @ResultMap("userMap")
    public void saveUser(User user);



    @Select("SELECT * from user WHERE user_name=#{str} OR user_email")
    @ResultMap("userMap")
    public User getUserByNameOrEmail(String str) ;




}
