package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Options;
import cn.betterts.blog.domain.User;
import org.apache.ibatis.jdbc.SQL;

public class OptionsUpdateSql {
    public String Update(final Options options){
        return new SQL(){
            {
                UPDATE("Options");
                if(options.getOptionSiteTitle() != null)
                    SET("option_site_title = #{optionSiteTitle}");
                if(options.getOptionSiteDescrption() != null)
                    SET("option_site_descrption = #{optionSiteDescrption}");
                if(options.getOptionMetaDescrption() != null)
                    SET("option_meta_descrption = #{optionMetaDescrption}");
                if(options.getOptionMetaKeyword() != null)
                    SET("option_meta_keyword = #{optionMetaKeyword}");
                if(options.getOptionAboutsiteAvatar() != null)
                    SET("option_aboutsite_avatar = #{optionAboutsiteAvatar}");
                if(options.getOptionAboutsiteTitle() != null)
                    SET("option_aboutsite_title = #{optionAboutsiteTitle}");
                if(options.getOptionAboutsiteContent() != null)
                    SET("option_aboutsite_content = #{optionAboutsiteContent}");
                if(options.getOptionAboutsiteWechat() != null)
                    SET("option_aboutsite_wechat = #{optionAboutsiteWechat}");
                if(options.getOptionAboutsiteQq() != null)
                    SET("option_aboutsite_qq = #{optionAboutsiteQq}");
                if(options.getOptionAboutsiteGithub() != null)
                    SET("option_aboutsite_github = #{optionAboutsiteGithub}");
                if(options.getOptionAboutsiteWeibo() != null)
                    SET("option_aboutsite_weibo = #{optionAboutsiteWeibo}");
                if(options.getOptionTongji() != null)
                    SET("option_tongji = #{optionTongji}");
                if(options.getOptionStatus() != null)
                    SET("option_status = #{optionStatus}");
                WHERE("option_id = #{optionId}");
            }
        }.toString();
    }
}
