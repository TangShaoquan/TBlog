package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
/*
*
* Select("SELECT * from article " +
            "where article_status = #{status} AND article_id = #{id}")
* */
public class ArticleSelectByStatusAndId {
    public String Select(final Map<String, Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("article");
                if (map.get("status") != null)
                    WHERE("article_status = #{status}");
                if (map.get("id") != null)
                    WHERE("article_id = #{id}");
            }
        }.toString();
    }
}
