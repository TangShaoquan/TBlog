package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.MenuUpdateSql;
import cn.betterts.blog.domain.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 14:34.2020/3/28
*/
@Repository
public interface MenuDao {

    /**
    * @Description: 查询菜单列表
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Menu>
    */
    @Select("select * from menu ORDER BY menu_id DESC")
    @Results(id="menuMap",value = {
            @Result(id = true ,column = "menu_id",property = "menuId"),
            @Result(column = "menu_name",property ="menuName"),
            @Result(column = "menu_url",property = "menuUrl"),
            @Result(column = "menu_level",property ="menuLevel"),
            @Result(column = "menu_icon",property = "menuIcon"),
            @Result(column = "menu_order",property = "menuOrder")
    })
    public List<Menu> listMenu();


    /**
    * @Description: 根据id查询
    * @Param: [menuId]
    * @return: cn.betterts.blog.domain.Menu
    */
    @Select("select * from menu where menu_id = #{menuId}")
    @ResultMap("menuMap")
    public Menu getMenuById(Integer menuId);


    /**
    * @Description: 根据id删除
    * @Param: [menuId]
    * @return: int
    */
    @Delete("delete from menu where menu_id = #{menuId}")
    @ResultMap("menuMap")
    public int deleteById(Integer menuId);

    /**
    * @Description: 插入新的菜单
    * @Param: [menu]
    * @return: int
    */
    @Insert("insert into menu " +
            "(menu_id, menu_name, menu_url,menu_level, menu_icon, menu_order) " +
            "values" +
            "( #{menuId}, #{menuName}, #{menuUrl},#{menuLevel}, #{menuIcon}, #{menuOrder})")
    @ResultMap("menuMap")
    public int insert(Menu menu);

    /**
    * @Description: 根据Id修改菜单
    * @Param: [menu]
    * @return: int
    */
    @UpdateProvider(type = MenuUpdateSql.class,method = "Update")
    @ResultMap("menuMap")
    public int update(Menu menu);

}
