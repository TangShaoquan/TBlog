package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Page;
import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.jdbc.SQL;

public class PageUpdateSql {
    public String Update(final Page page){
        return new SQL(){
            {
                UPDATE("Page");
                if(page.getPageKey() != null)
                    SET("page_key = #{pageKey}");
                if(page.getPageTitle() != null)
                    SET("page_title = #{pageTitle}");
                if(page.getPageContent() != null)
                    SET("page_content = #{pageContent}");
                if(page.getPageCreateTime() != null)
                    SET("page_create_time = #{pageCreateTime}");
                if(page.getPageUpdateTime() != null)
                    SET("page_update_time = #{pageUpdateTime}");
                if(page.getPageViewCount() != null)
                    SET("page_view_count = #{pageViewCount}");
                if(page.getPageCommentCount() != null)
                    SET("page_comment_count = #{pageCommentCount}");
                if(page.getPageStatus() != null)
                    SET("page_status = #{pageStatus}");
                WHERE("page_id = #{pageId}");
            }
        }.toString();
    }
}
