package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.CommentUpdateSql;
import cn.betterts.blog.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 16:39.2020/3/28
*/
@Repository
public interface CommentDao {



    /**
    * @Description: 查询所有评论
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Comment>
    */
    @Select("select * from comment")
    @Results(id = "commentMap",value = {
            @Result(id=true,column = "comment_id",property = "commentId"),
            @Result(column = "comment_pid",property = "commentPid"),
            @Result(column = "comment_pname",property = "commentPName"),
            @Result(column = "comment_article_id",property = "commentArticleId"),
            @Result(column = "comment_author_name",property = "commentAuthorName"),
            @Result(column = "comment_author_email",property = "commentAuthorEmail"),
            @Result(column = "comment_author_url",property = "commentAuthorUrl"),
            @Result(column = "comment_author_avatar",property = "commentAuthorAvatar"),
            @Result(column = "comment_content",property = "commentContent"),
            @Result(column = "comment_agent",property = "commentAgent"),
            @Result(column = "comment_ip",property = "commentIp"),
            @Result(column = "comment_create_time",property = "commentCreateTime"),
            @Result(column = "comment_role",property = "commentRole")
    })
    public List<Comment> listComment();

    /**
    * @Description: 根据文章id查看评论
    * @Param: [commentId]
    * @return: java.util.List<cn.betterts.blog.domain.Comment>
    */
    @Select("select * from comment where comment_article_id=#{commentArticleId}")
    @ResultMap("commentMap")
    public List<Comment> listCommentByArticleId(@Param("commentArticleId") Integer commentArticleId);

    /**
    * @Description: 查询子评论
    * @Param: [commentPid]
    * @return: java.util.List<cn.betterts.blog.domain.Comment>
    */
    @Select("select * from comment where comment_pid=#{commentPid}")
    @ResultMap("commentMap")
    public List<Comment> listChildComment(@Param("commentPid") Integer commentPid);

    /**
    * @Description: 查询最近评论
    * @Param: [limit]
    * @return: java.util.List<cn.betterts.blog.domain.Comment>
    */
// 这里的MySql语句有个小bug ,当执行SELECT * FROM COMMENT WHERE comment_role = 0 ORDER BY comment_id DESC LIMIT #{limit}时，结果会返回0
    @Select("SELECT * FROM COMMENT WHERE comment_role = 0 ORDER BY comment_id DESC , comment_id DESC LIMIT  #{limit}")
    @ResultMap("commentMap")
    public List<Comment> listRecentComment(@Param("limit") Integer limit);


    /**
    * @Description: 根据评论id获取评论
    * @Param: [commentId]
    * @return: cn.betterts.blog.domain.Comment
    */
    @Select("select * from comment where comment_id = #{commentId}")
    @ResultMap("commentMap")
    public Comment getCommentById(Integer commentId);

    /**
    * @Description: 评论总数
    * @Param: []
    * @return: java.lang.Integer
    */
    @Select("select count(*) from comment")
    public Integer countComment();

    /**
    * @Description: 根据评论id删除评论
    * @Param: [commentId]
    * @return: int
    */
    @Delete("delete from comment where comment_id = #{commentId}")
    @ResultMap("commentMap")
    public int deleteById(Integer commentId);

    /**
    * @Description: 新增评论
    * @Param: [comment]
    * @return: int
    */
    @Insert("insert into comment " +
            "(comment_pid, comment_pname, comment_article_id," +
            "comment_author_name, comment_author_email," +
            "comment_author_url, comment_author_avatar, " +
            "comment_content, comment_agent," +
            "comment_ip, comment_create_time, comment_role) " +
            " values " +
            "(#{commentPid}, #{commentPName}, #{commentArticleId}," +
            "#{commentAuthorName}, #{commentAuthorEmail}," +
            "#{commentAuthorUrl},#{commentAuthorAvatar}, " +
            "#{commentContent},#{commentAgent}," +
            "#{commentIp}, #{commentCreateTime}, #{commentRole})")
    @ResultMap("commentMap")
    public int insert(Comment comment);

    /**
    * @Description: 修改评论
    * @Param: [comment]
    * @return: int
    */
    @UpdateProvider(type = CommentUpdateSql.class,method = "Update" )
    @ResultMap("commentMap")
    public int update(Comment comment);

}
