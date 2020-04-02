package cn.betterts.blog.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userNickName;
    private String userEmail;
    private String userUrl;
    private String userAvatar;
    private String userLastLoginIp;
    private Date userRegisterTime;
    private Date userLastLoginTime;
    private Integer userStatus;
    private Integer articleCount;
}
