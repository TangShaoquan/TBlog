package cn.betterts.blog.dao;

import cn.betterts.blog.domain.ArticleTagRef;
import cn.betterts.blog.domain.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description: 文章标签关联
* @Author: 唐绍权
* @Date: 17:40.2020/3/28
*/
@Repository
public interface ArticleTagRefDao {

    /**
    * @Description: 根据文章获得列表标签
    * @Param: [articleId]
    * @return: java.util.List<cn.betterts.blog.domain.Tag>
    */
    @Select("select tag.* from tag, article_tag_ref where article_id=#{articleId} " +
            "AND article_tag_ref.tag_id = tag.tag_id")
    @Results(id = "ArticleTagRefMap",value = {
            @Result(id = true, column = "article_id",property = "articleId"),
            @Result(id = true, column = "tag_id",property = "tagId")
    })
    public List<Tag> listTagByArticleId(Integer articleId);

    /**
     * @Description: 根据id统计文章数
     * @Param: [tagId]
     * @return: int
     */
    @Select("select count(*) from article_tag_ref where tag_id = #{tagId}")
    public int countArticleByTagId(Integer tagId);

    /**
    * @Description: 根据标签Id删除记录
    * @Param: [tagId]
    * @return: int
    */
    @Delete("delete from article_tag_ref where tag_id = #{tagId}")
    @ResultMap("ArticleTagRefMap")
    public int deleteByTagId(Integer tagId);

    /**
    * @Description: 根据文章Id删除记录
    * @Param: [articleId]
    * @return: int
    */
    @Delete("delete from article_tag_ref where article_id = #{articleId}")
    @ResultMap("ArticleTagRefMap")
    public int deleteByArticleId(Integer articleId);


    /**
    * @Description: 添加文章和标签的关联记录
    * @Param: [record] 关联对象
    * @return: int
    */
    @Insert("insert into article_tag_ref (article_id, tag_id) values (#{articleId}, #{tagId})")
    @ResultMap("ArticleTagRefMap")
    public int insert(ArticleTagRef record);


}
