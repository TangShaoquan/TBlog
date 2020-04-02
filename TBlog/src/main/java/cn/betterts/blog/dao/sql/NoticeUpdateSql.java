package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

public class NoticeUpdateSql {
    public String Update(final Notice notice){
        return new SQL(){
            {
                UPDATE("NOTICE");
                if(notice.getNoticeTitle() != null)
                    SET("notice_title = #{noticeTitle}");
                if(notice.getNoticeContent() != null)
                    SET("notice_content = #{noticeContent}");
                if(notice.getNoticeCreateTime() != null)
                    SET("notice_create_time = #{noticeCreateTime}");
                if(notice.getNoticeUpdateTime() != null)
                    SET("notice_update_time = #{noticeUpdateTime}");
                if(notice.getNoticeStatus() != null)
                    SET("notice_status = #{noticeStatus}");
                if(notice.getNoticeOrder() != null)
                    SET("notice_order = #{noticeOrder}");
                WHERE("notice_id = #{noticeId}");
            }
        }.toString();
    }
}
