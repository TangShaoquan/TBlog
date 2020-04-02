package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

public class NoticeListSelectSql {
    public String Select(final Integer noticeStatus){
        return new SQL(){
            {
                SELECT("*");
                FROM("NOTICE");
                if(noticeStatus != null)
                    WHERE("notice_status = #{noticeStatus}");
                ORDER_BY("notice_order ASC, notice_id ASC");
            }
        }.toString();
    }
}
