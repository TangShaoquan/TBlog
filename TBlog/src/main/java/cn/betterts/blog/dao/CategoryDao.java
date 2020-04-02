package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.CategoryUpdateSql;
import cn.betterts.blog.domain.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 17:08.2020/3/28
*/
@Repository
public interface CategoryDao {

    /**
    * @Description: 查询所有标签列表
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Category>
    */
    @Select("select * from category")
    @Results(id = "categoryMap",value = {
            @Result(id = true,column = "category_id",property = "categoryId"),
            @Result(column = "category_pid",property = "categoryPid" ),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_description",property = "categoryDescription"),
            @Result(column = "category_order",property = "categoryOrder"),
            @Result(column = "category_icon",property = "categoryIcon")
    })
    public List<Category> listCategory();

    /**
    * @Description: 根据父分类查找子标签
    * @Param: [categoryPid]
    * @return: java.util.List<cn.betterts.blog.domain.Category>
    */
    @Select("select * from category where category_pid = #{categoryPid}")
    @ResultMap("categoryMap")
    public List<Category> findChildCategory(@Param("categoryPid") Integer categoryPid);

    /**
    * @Description: 根据id查找标签
    * @Param: [categoryId]
    * @return: cn.betterts.blog.domain.Category
    */
    @Select("select * from category where category_id = #{categoryId}")
    @ResultMap("categoryMap")
    public Category getCategoryById(Integer categoryId);

    /**
    * @Description: 根据标签名查找
    * @Param: [categoryName]
    * @return: cn.betterts.blog.domain.Category
    */
    @Select("select * from category where category_name = #{categoryName}")
    @ResultMap("categoryMap")
    public Category getCategoryByName(String categoryName);

    /**
    * @Description: 查找标签总数
    * @Param: []
    * @return: java.lang.Integer
    */
    @Select("select count(*) from category")
    public Integer countCategory();

    /**
    * @Description: 根据id删除标签
    * @Param: [categoryId]
    * @return: int
    */
    @Delete("delete from category where category_id = #{categoryId}")
    @ResultMap("categoryMap")
    public int deleteCategory(Integer categoryId);

    /**
    * @Description: 新增标签
    * @Param: [category]
    * @return: int
    */
    @Insert("insert into category" +
            "(category_pid, category_name," +
            "category_description, category_order, " +
            "category_icon)" +
            " values" +
            " (#{categoryPid}, #{categoryName}," +
            "#{categoryDescription}, #{categoryOrder}, " +
            "#{categoryIcon})")
    @ResultMap("categoryMap")
    public int insert(Category category);

    /**
    * @Description: 更新标签
    * @Param: [category]
    * @return: int
    */
    @UpdateProvider(type = CategoryUpdateSql.class,method = "Update" )
    @ResultMap("categoryMap")
    public int update(Category category);

}
