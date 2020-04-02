package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

public class LinkSelectListSql {
    public String Select(final Integer linkStatus){
        return new SQL(){
            {
                SELECT("*");
                FROM("link");
                if (linkStatus != null)
                    WHERE("link_status=#{linkStatus}");
                ORDER_BY("link_order");
            }
        }.toString();
    }
}
