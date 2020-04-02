package cn.betterts.blog.service;

import cn.betterts.blog.domain.Comment;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    public List<Comment> listComment();
    public List<Comment> listCommentByArticleId(@Param("commentArticleId") Integer commentArticleId);
    public List<Comment> listChildComment(@Param("commentPid") Integer commentPid);
    public List<Comment> listRecentComment(@Param(value = "limit") Integer limit);
    public Comment getCommentById(Integer commentId);
    public Integer countComment();
    public void deleteById(Integer commentId);
    public void insert(Comment comment);
    public void update(Comment comment);
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize);
}
