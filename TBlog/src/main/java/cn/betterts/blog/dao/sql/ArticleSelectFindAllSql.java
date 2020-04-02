package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class ArticleSelectFindAllSql {
    public String Select(final HashMap<String ,Object> criteria){
        return new SQL(){
            {
                SELECT("article.*");
                FROM("article");
                if (criteria.get("status") !=null)
                    WHERE("article_status = #{status}");
                if(criteria.get("keywords") != null)
                    WHERE("article_title LIKE concat(concat('%',#{keywords}),'%')");
                if(criteria.get("userId") != null)
                    WHERE("article_user_id = #{userId}");
                if(criteria.get("categoryId") != null)
                    WHERE("article_id IN " +
                            "(" +
                            new SQL(){
                                {
                                    SELECT("article_id");
                                    FROM("article_category_ref");
                                    WHERE("category_id = #{categoryId}");
                                }
                            }.toString() +
                            ")");
                if (criteria.get("tagId") != null)
                    WHERE("article_id IN " +
                            "(" +
                            new SQL(){
                                {
                                    SELECT("article_id");
                                    FROM("article_tag_ref");
                                    WHERE("tag_id = #{tagId}");
                                }
                            }.toString() +
                            ")");
                ORDER_BY("`article`.`article_order` DESC, `article`.`article_id` DESC");
            }
        }.toString();
    }
}
