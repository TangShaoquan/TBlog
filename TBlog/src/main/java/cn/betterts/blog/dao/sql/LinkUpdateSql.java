package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Link;
import cn.betterts.blog.domain.Menu;
import org.apache.ibatis.jdbc.SQL;

public class LinkUpdateSql {
    public String Update(final Link link){
        return new SQL(){
            {
                UPDATE("Link");
                if(link.getLinkUrl() != null)
                    SET("link_url = #{linkUrl}");
                if(link.getLinkName() != null)
                    SET("link_name = #{linkName}");
                if(link.getLinkImage() != null)
                    SET("link_image = #{linkImage}");
                if(link.getLinkDescription() != null)
                    SET("link_description = #{linkDescription}");
                if(link.getLinkOwnerNickname() != null)
                    SET("link_owner_nickname = #{linkOwnerNickname}");
                if(link.getLinkOwnerContact() != null)
                    SET("link_owner_contact = #{linkOwnerContact}");
                if(link.getLinkUpdateTime() != null)
                    SET("link_update_time = #{linkUpdateTime}");
                if(link.getLinkCreateTime() != null)
                    SET("link_create_time = #{linkCreateTime}");
                if(link.getLinkOrder() != null)
                    SET("link_order = #{linkOrder}");
                if(link.getLinkStatus() != null)
                    SET("link_status = #{linkStatus}");
                WHERE("link_id = #{linkId}");
            }
        }.toString();
    }
}
