package cn.betterts.blog.dao.sql;


import cn.betterts.blog.domain.Category;
import org.apache.ibatis.jdbc.SQL;

public class CategoryUpdateSql {
    public String Update(final Category category){
        return new SQL(){
            {
                UPDATE("CATEGORY");
                if(category.getCategoryPid() != null)
                    SET("category_pid = #{categoryPid}");
                if(category.getCategoryName() != null)
                    SET("category_name = #{categoryName}");
                if (category.getCategoryDescription() != null)
                    SET("category_description = #{categoryDescription}");
                if (category.getCategoryOrder() != null)
                    SET("category_order = #{categoryOrder}");
                if (category.getCategoryIcon() != null)
                    SET("category_icon = #{categoryIcon}");
                WHERE("category_id = #{categoryId}");
            }
        }.toString();
    }
}
