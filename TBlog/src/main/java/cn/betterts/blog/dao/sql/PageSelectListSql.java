package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

public class PageSelectListSql {
    public String Select(final Integer pageStatus){
        return new SQL(){
            {
                SELECT("*");
                FROM("Page");
                if(pageStatus != null)
                    WHERE("page_status = #{pageStatus}");
                ORDER_BY("page_status ASC");
            }
        }.toString();
    }
}
