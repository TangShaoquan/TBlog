package cn.betterts.blog.dao.sql;

import cn.betterts.blog.domain.Menu;
import cn.betterts.blog.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

public class MenuUpdateSql {
    public String Update(final Menu menu){
        return new SQL(){
            {
                UPDATE("MENU");
                if(menu.getMenuName() != null)
                    SET("menu_name=#{menuName}");
                if(menu.getMenuUrl() != null)
                    SET("menu_url = #{menuUrl}");
                if(menu.getMenuLevel() != null)
                    SET("menu_level = #{menuLevel}");
                if(menu.getMenuIcon() != null)
                    SET("menu_icon = #{menuIcon}");
                if(menu.getMenuOrder() != null)
                    SET("menu_order = #{menuOrder}");
                WHERE("menu_id = #{menuId}");
            }
        }.toString();
    }
}
