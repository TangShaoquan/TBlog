package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class PageSelectByKeySql {
    public String Select(final HashMap<String ,Object> map){
        return new SQL(){
            {
//                select * from page where page_key=#{pageKey} and page_status=#{pageStatus}
                SELECT("*");
                FROM("page");
                if (map.get("pageStatus") != null)
                    WHERE("page_status=#{pageStatus}");
                if (map.get("pageKey") != null)
                    WHERE("page_key=#{pageKey}");

            }
        }.toString();
    }
}
