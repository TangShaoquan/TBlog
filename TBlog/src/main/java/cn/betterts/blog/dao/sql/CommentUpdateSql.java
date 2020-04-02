package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Comment;
import org.apache.ibatis.jdbc.SQL;

public class CommentUpdateSql {
    public String Update(final Comment comment){
        return new SQL(){
            {
                UPDATE("COMMENT");
                if(comment.getCommentPid() != null)
                    SET("comment_pid = #{commentPid}");
                if(comment.getCommentPName() != null)
                    SET("comment_pname = #{commentPName}");
                if(comment.getCommentArticleId() != null)
                    SET("comment_article_id = #{commentArticleId}");
                if(comment.getCommentAuthorName() != null)
                    SET("comment_author_name = #{commentAuthorName}");
                if(comment.getCommentAuthorEmail() != null)
                    SET("comment_author_email = #{commentAuthorEmail}");
                if(comment.getCommentAuthorUrl() != null)
                    SET("comment_author_url = #{commentAuthorUrl}");
                if(comment.getCommentContent() != null)
                    SET("comment_content = #{commentContent}");
                if(comment.getCommentAgent() != null)
                    SET("comment_agent = #{commentAgent}");
                if(comment.getCommentIp() != null)
                    SET("comment_ip = #{commentIp}");
                if(comment.getCommentCreateTime() != null)
                    SET("comment_create_time = #{commentCreateTime}");
                if(comment.getCommentAuthorAvatar() != null)
                    SET("comment_author_avatar = #{commentAuthorAvatar}");
                if(comment.getCommentRole() != null)
                    SET("comment_role = #{commentRole}");
                WHERE("comment_id = #{commentId}");
            }
        }.toString();
    }
}
