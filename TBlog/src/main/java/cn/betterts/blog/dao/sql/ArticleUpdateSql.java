package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Article;
import org.apache.ibatis.jdbc.SQL;

public class ArticleUpdateSql {
    public String Update(final Article article){
        return new SQL(){
            {
                UPDATE("article");
                if (article.getArticleUserId() != null)
                    SET("article_user_id = #{articleUserId}");
                if (article.getArticleTitle() != null)
                    SET("article_title = #{articleTitle}");
                if (article.getArticleViewCount() != null)
                    SET("article_view_count = #{articleViewCount}");
                if (article.getArticleCommentCount() != null)
                    SET("article_comment_count = #{articleCommentCount}");
                if (article.getArticleLikeCount() != null)
                    SET("article_like_count = #{articleLikeCount}");
                if (article.getArticleCreateTime() != null)
                    SET("article_create_time = #{articleCreateTime}");
                if (article.getArticleUpdateTime() != null)
                    SET("article_update_time = #{articleUpdateTime}");
                if (article.getArticleIsComment() != null)
                    SET("article_is_comment = #{articleIsComment}");
                if (article.getArticleStatus() != null)
                    SET("article_status = #{articleStatus}");
                if (article.getArticleOrder() != null)
                    SET("article_order = #{articleOrder}");
                if (article.getArticleContent() != null)
                    SET("article_content = #{articleContent}");
                if (article.getArticleSummary() != null)
                    SET("article_summary = #{articleSummary}");
                WHERE("article_id = #{articleId}");
            }
        }.toString();
    }
}
