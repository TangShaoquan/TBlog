package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class ArticleSelectByIds {
    public String Select(final Map<String,Object> map){
        final List<Integer> categoryIds = (List) map.get("ids");
        final Integer limit = (Integer) map.get("limit");
        final StringBuilder sb = new StringBuilder("");

        for (Integer id : categoryIds){
            sb.append(id.toString());
            sb.append(",");
        }
        final String ids = sb.toString().substring(0,sb.toString().length()-1);
        System.out.println(ids);
        return new SQL(){
            {
                SELECT("article.article_id, article.article_user_id, article.article_title," +
                        "article.article_view_count, article.article_comment_count, " +
                        "article.article_like_count, article.article_create_time, article.article_update_time, " +
                        "article.article_is_comment, article.article_status, article.article_order," +
                        "article.article_summary");
                FROM("article, article_category_ref");
                WHERE("article.article_status = 1 AND " +
                        "article.article_id = article_category_ref.article_id AND" +
                        " article_category_ref.category_id " +
                        "IN "+"("+ids+")");
                LIMIT(limit);
            }
        }.toString();
    }
}
