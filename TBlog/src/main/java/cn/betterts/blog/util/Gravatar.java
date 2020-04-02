package cn.betterts.blog.util;

/**
* @Description: 头像
* @Param:
* @return:
*/
public class Gravatar {
    public static String getGravatar(String email) {
        String emailMd5 = MD5.strToMd5(email);
        //设置图片大小32px
        String avatar = "http://cn.gravatar.com/avatar/" + emailMd5 + "?s=128&d=identicon&r=PG";
        return avatar;
    }
}
