package cn.betterts.blog.dao.sql;
import cn.betterts.blog.domain.User;
import org.apache.ibatis.jdbc.SQL;

public class UserUpdateSql {
    public String Update(final User user){
        return new SQL(){
            {
                UPDATE("USER");
                if(user.getUserName() != null)
                    SET("user_name = #{userName}");
                if(user.getUserPassword() != null)
                    SET("user_pass = #{userPassword}");
                if(user.getUserNickName() != null)
                    SET("user_nickname = #{userNickName}");
                if(user.getUserEmail() != null)
                    SET("user_email = #{userEmail}");
                if(user.getUserUrl() != null)
                    SET("user_url = #{userUrl}");
                if(user.getUserAvatar() != null)
                    SET("user_avatar = #{userAvatar}");
                if(user.getUserLastLoginIp() != null)
                    SET("user_last_login_ip = #{userLastLoginIp}");
                if(user.getUserRegisterTime() != null)
                    SET("user_register_time = #{userRegisterTime}");
                if(user.getUserLastLoginTime() != null)
                    SET("user_last_login_time = #{userLastLoginTime}");
                if(user.getUserStatus() != null)
                    SET("user_status = #{userStatus}");
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }
}
