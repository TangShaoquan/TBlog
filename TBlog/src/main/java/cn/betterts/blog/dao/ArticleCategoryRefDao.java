package cn.betterts.blog.dao;

import cn.betterts.blog.domain.ArticleCategoryRef;
import cn.betterts.blog.domain.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 19:03.2020/3/28
*/
@Repository
public interface ArticleCategoryRefDao {


    /**
     * @Description: 根据文章Id获取分类列表
     * @Param: [articleId]
     * @return: java.util.List<cn.betterts.blog.domain.Category>
     */
    @Select("select category.category_id, category.category_pid, category.category_name " +
            "from category, article_category_ref " +
            "WHERE article_category_ref.article_id = #{articleId} " +
            "AND article_category_ref.category_id = category.category_id " +
            "ORDER BY category.category_pid asc")
    @Results(id = "ArticleCategoryRefMap", value = {
            @Result(id =true ,column = "article_id",property = "articleId"),
            @Result(column = "category_id",property = "categoryId")
    })
    public List<Category> listCategoryByArticleId(Integer articleId);


    /**
     * @Description: 根据文章Id查询分类id
     * @Param: [articleId]
     * @return: java.util.List<java.lang.Integer>
     */
    //    这里如果使用了ResultMap,结果就很映射成Category类
    @Select("SELECT category_id FROM article_category_ref where article_id = #{articleId}")
    public List<Integer> selectCategoryIdByArticleId(Integer articleId);

    /**
     * @Description: 根据分类Id查询文章Id
     * @Param: [categoryId]
     * @return: java.util.List<java.lang.Integer>
     */
//    这里如果使用了ResultMap,结果就很映射成Category类
    @Select("SELECT article_id FROM article_category_ref where category_id = #{categoryId}")
    public List<Integer> selectArticleIdByCategoryId(Integer categoryId);



    /**
    * @Description: 根据分类Id删除记录
    * @Param: [categoryId]
    * @return: int
    */
    @Delete("delete from article_category_ref where category_id = #{categoryId}" )
    @ResultMap("ArticleCategoryRefMap")
    public int deleteByCategoryId(Integer categoryId);

    /**
    * @Description: 根据文章Id删除记录
    * @Param: [articleId]
    * @return: int
    */
    @Delete("delete from article_category_ref where  article_id = #{articleId}" )
    @ResultMap("ArticleCategoryRefMap")
    public int deleteByArticleId(Integer articleId);

    /**
    * @Description: 根据文章分类标签统计文章数目
    * @Param: [categoryId]
    * @return: int
    */
    @Select("select count(*) from article_category_ref where category_id = #{categoryId}")
    public int countArticleByCategoryId(Integer categoryId);

    /**
     * @Description: 添加文字和分类关联记录
     * @Param: [record]
     * @return: int
     */
    @Insert("insert into article_category_ref (article_id, category_id) " +
            "values (#{articleId}, #{categoryId})")
    @ResultMap("ArticleCategoryRefMap")
    public int insert(ArticleCategoryRef record);





}
