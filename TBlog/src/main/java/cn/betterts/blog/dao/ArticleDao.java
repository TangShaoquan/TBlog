package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.*;
import cn.betterts.blog.domain.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
* @Author: 唐绍权
* @Date: 19:23.2020/3/28
*/
@Repository
public interface ArticleDao {

    /**
    * @Description: 获取所有文章
    * @Param: [criteria]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */

    @SelectProvider(type = ArticleSelectFindAllSql.class,method = "Select" )
    @Results(id = "articleMap",value = {
            @Result(id = true,column = "article_id",property = "articleId"),
            @Result(column = "article_user_id",property = "articleUserId"),
            @Result(column = "article_title",property = "articleTitle"),
            @Result(column = "article_view_count",property = "articleViewCount"),
            @Result(column = "article_comment_count",property = "articleCommentCount"),
            @Result(column = "article_like_count",property = "articleLikeCount"),
            @Result(column = "article_create_time",property = "articleCreateTime"),
            @Result(column = "article_update_time",property = "articleUpdateTime"),
            @Result(column = "article_is_comment",property = "articleIsComment"),
            @Result(column = "article_status",property = "articleStatus"),
            @Result(column = "article_order",property = "articleOrder"),
            @Result(column = "article_content",property = "articleContent"),
            @Result(column = "article_summary",property = "articleSummary")
    })
    public List<Article> findAll(HashMap<String, Object> criteria);

    /**
    * @Description: 文章归档
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("SELECT article_id, article_user_id, article_title, article_create_time " +
            "From article " +
            "WHERE article_status = 1 " +
            "ORDER BY article_id DESC")
    @ResultMap("articleMap")
    public  List<Article> listAllNotWithContent();

    /**
    * @Description: 文章归档
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("select * from article WHERE article_status = 1 " +
            "ORDER BY article_status ASC, article_order DESC, article_id DESC")
    @ResultMap("articleMap")
    public List<Article> listArticle();

    /**
    * @Description: 分页
    * @Param: [status, pageIndex, pageSize] 状态，从第几页开始，数量
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("select * from article where article_status=#{status} " +
            " ORDER BY article_status ASC, article_order DESC, article_id DESC " +
            " limit #{pageIndex},#{pageSize}")
    @ResultMap("articleMap")
    @Deprecated
    public List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);


    /**
    * @Description: 获得访问最多的文章——>用于实现猜你喜欢
    * @Param: [limit]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */

    @Select("select * from article WHERE article_status = 1 " +
            "ORDER BY article_view_count DESC,article_order DESC, article_id DESC " +
            "limit #{limit}")
    @ResultMap("articleMap")
    public List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);


    /**
    * @Description: 获得上一篇文章
    * @Param: [id]
    * @return: cn.betterts.blog.domain.Article
    */

    @Select("select * from article " +
            "where article_id < #{id} AND article_status = 1 " +
            "ORDER BY article_id limit 1")
    @ResultMap("articleMap")
    public Article getAfterArticle(@Param(value = "id") Integer id);

    /**
    * @Description: 获得下一篇文章
    * @Param: [id]
    * @return: cn.betterts.blog.domain.Article
    */
    @Select("select * from article " +
            "where article_id > #{id} AND article_status = 1 " +
            "ORDER BY article_id limit 1")
    @ResultMap("articleMap")
    public Article getPreArticle(@Param(value = "id") Integer id);


    /**
    * @Description: 获得随机文章
    * @Param: [limit]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("select * from  article WHERE article_status = 1 ORDER BY RAND() limit #{limit}")
    @ResultMap("articleMap")
    public List<Article> listRandomArticle(@Param(value = "limit") Integer limit);


    /**
    * @Description: 获得热评文章
    * @Param: [limit]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("SELECT * FROM article " +
            "WHERE article_status = 1 " +
            "ORDER BY article_comment_count DESC ,article_order DESC, article_id DESC " +
            "limit #{limit}")
    @ResultMap("articleMap")
    public List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);


    /**
    * @Description: 根据分类id获得文章
    * @Param: [categoryId, limit]分类id,查询数目
    * @return: java.util.List<cn.betterts.blog.domain.Article> 文章列表
    */
    @Select("SELECT " +
            "article.article_id, article.article_user_id, article.article_title, " +
            "article.article_view_count, article.article_comment_count, " +
            "article.article_like_count, article.article_create_time, article.article_update_time, " +
            "article.article_is_comment, article.article_status, article.article_order, " +
            " article.article_summary " +
            "FROM article,article_category_ref " +
            "WHERE article.article_status = 1 AND " +
            "article.article_id = article_category_ref.article_id AND " +
            "article_category_ref.category_id = #{categoryId} " +
            "LIMIT #{limit}")
    @ResultMap("articleMap")
    public List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,@Param("limit") Integer limit);

    /**
    * @Description: 根据多个分类id获取文章列表
    * @Param: [categoryIds, limit]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */

    @SelectProvider(type =ArticleSelectByIds.class,method ="Select")
    @ResultMap("articleMap")
    public List<Article> findArticleByCategoryIds(@Param("ids") List<Integer> categoryIds,@Param("limit") Integer limit);

    /**
    * @Description: 获取最新文章
    * @Param: [limit]
    * @return: java.util.List<cn.betterts.blog.domain.Article>
    */
    @Select("select * from article " +
            "ORDER BY article_id DESC " +
            "LIMIT #{limit}")
    @ResultMap("articleMap")
    public List<Article> listArticleByLimit(Integer limit);

    /**
    * @Description: 批量删除文章
    * @Param: [ids]
    * @return: java.lang.Integer
    */
    @SelectProvider(type =ArticleDeleteByIds.class,method ="Delete")
    @ResultMap("articleMap")
    public Integer deleteBatch(@Param("ids") List<Integer> ids);

    /**
    * @Description: 获得更新后的记录
    * @Param: []
    * @return: cn.betterts.blog.domain.Article
    */
    @Select("SELECT * from article" +
            " WHERE article_status = 1 AND article_update_time=(" +
            " SELECT max(article_update_time) FROM article)")
    @ResultMap("articleMap")
    public Article getLastUpdateArticle();


    /**
    * @Description: 根据id查询用户信息
    * @Param: [status, id] 文章id
    * @return: cn.betterts.blog.domain.Article
    */
    @SelectProvider(type = ArticleSelectByStatusAndId.class,method = "Select")
    @ResultMap("articleMap")
    public Article getArticleByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * @Description:用户的文章数
     * @Param: [id]
     * @return: java.lang.Integer
     */
    @Select("SELECT count(*) from article " +
            "WHERE article_user_id=#{id} AND article_status = 1")
    public Integer countArticleByUser(@Param(value = "id") Integer id);

    /**
    * @Description: 根据id删除
    * @Param: [articleId]
    * @return: java.lang.Integer
    */
    @Delete("DELETE FROM article where article_id = #{articleId}")
    @ResultMap("articleMap")
    public Integer deleteById(Integer articleId);

    /**
    * @Description: 获取文章总数
    * @Param: [status]
    * @return: java.lang.Integer
    */
    @Select("SELECT COUNT(*) FROM article WHERE article_status = 1")
    public Integer countArticle(@Param(value = "status") Integer status);

    /**
    * @Description: 获取留言总数
    * @Param: []
    * @return: java.lang.Integer
    */
    @Select("SELECT SUM(article_comment_count) FROM article WHERE article_status = 1")
    public Integer countArticleComment();

    /**
    * @Description: 获取浏览量总数
    * @Param: []
    * @return: java.lang.Integer
    */
    @Select("SELECT SUM(article_view_count) FROM article WHERE article_status = 1")
    public Integer countArticleView();

    /**
    * @Description: 插入文章
    * @Param: [article]
    * @return: java.lang.Integer
    */
    @Insert("insert into article " +
            "(article_user_id, article_title,article_view_count, article_comment_count, " +
            "article_like_count, article_create_time, article_update_time, " +
            "article_is_comment, article_status, article_order, article_content, article_summary) " +
            "Values " +
            "(#{articleUserId}, #{articleTitle},#{articleViewCount}," +
            "#{articleCommentCount}, #{articleLikeCount}, #{articleCreateTime}," +
            "#{articleUpdateTime},#{articleIsComment}, #{articleStatus}, " +
            "#{articleOrder},#{articleContent}, #{articleSummary})")
//    用于获取id
    @SelectKey(before = false,keyProperty = "articleId",resultType = Integer.class,statement = "SELECT LAST_INSERT_ID() AS articleId")
    @ResultMap("articleMap")
    public Integer insert(Article article);

    /**
    * @Description: 更新文章
    * @Param: [article]
    * @return: java.lang.Integer
    */
    @UpdateProvider(type = ArticleUpdateSql.class,method = "Update")
    @ResultMap("articleMap")
    public Integer update(Article article);

    /**
    * @Description: 更新文章的评论数
    * @Param: [articleId]
    * @return: void
    */
    @Update("UPDATE article " +
            "SET article_comment_count =(" +
            "SELECT count(*) FROM comment  WHERE comment_article_id = #{articleId}) " +
            "WHERE article_id=#{articleId}")
    public void updateCommentCount(@Param(value = "articleId") Integer articleId);


}
