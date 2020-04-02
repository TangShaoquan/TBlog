package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.domain.User;
import org.apache.ibatis.jdbc.SQL;

public class TagUpdateSql {
    public String Update(final Tag tag){
        return new SQL(){
            {
                UPDATE("TAG");
                if(tag.getTagName() != null)
                    SET("tag_name = #{tagName}");
                if(tag.getTagDescription() != null)
                    SET("tag_description = #{tagDescription}");
                WHERE("tag_id = #{tagId}");
            }
        }.toString();
    }
}
