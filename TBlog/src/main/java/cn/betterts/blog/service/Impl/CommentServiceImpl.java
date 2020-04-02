package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleDao;
import cn.betterts.blog.dao.CommentDao;
import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Comment;
import cn.betterts.blog.enums.ArticleStatus;
import cn.betterts.blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentDao commentDao;

    @Autowired(required = false)
    private ArticleDao articleDao;

    @Override
    public List<Comment> listComment() {
        List<Comment> commentList = null;
        try {
            commentList = commentDao.listComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论列表失败：cause:{}", e);
        }
        return commentList;
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer commentArticleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentDao.listCommentByArticleId(commentArticleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章ID获得评论列表失败，articleId:{},cause:{}", commentArticleId, e);
        }
        return commentList;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        try {
            commentList = commentDao.listComment();
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleDao.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页获得评论失败,pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return new PageInfo<>(commentList);
    }

    @Override
    public List<Comment> listChildComment(Integer commentPid) {
        List<Comment> childCommentList = null;
        try {
            childCommentList = commentDao.listChildComment(commentPid);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得子评论失败, id:{}, cause:{}", commentPid, e);
        }
        return childCommentList;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentDao.listRecentComment(limit);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleDao.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新评论失败, limit:{}, cause:{}", limit, e);
        }
        return commentList;
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        Comment comment = null;
        try {
            comment = commentDao.getCommentById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据评论ID获得评论，id:{}, cause:{}", commentId, e);
        }
        return comment;
    }

    @Override
    public Integer countComment() {
        Integer commentCount = null;
        try {
            commentCount = commentDao.countComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计评论数量失败, cause:{}", e);
        }
        return commentCount;
    }

    @Override
    public void deleteById(Integer commentId) {
        try {
            commentDao.deleteById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评论失败, id:{}, cause:{}", commentId, e);
        }
    }

    @Override
    public void insert(Comment comment) {
        try{
            commentDao.insert(comment);
        } catch (Exception e){
            e.printStackTrace();
            log.error("创建评论失败：comment:{}, cause:{}", comment, e);
        }
    }

    @Override
    public void update(Comment comment) {
        try {
            commentDao.update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新评论，comment:{}, cause:{}", comment, e);
        }
    }
}
