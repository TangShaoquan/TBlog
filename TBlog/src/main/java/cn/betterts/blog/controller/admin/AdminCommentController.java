package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Comment;
import cn.betterts.blog.enums.ArticleStatus;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CommentService;
import cn.betterts.blog.util.IP;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    /**
    * @Description: 评论列表默认页
    * @Param: [pageIndex, pageSize, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "")
    public String indexView(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                  Model model) {
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex, pageSize);
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "Admin/Comment/index";
    }

    /**
    * @Description: 添加评论
    * @Param: [request, comment]
    * @return: void
    */
    @RequestMapping(value = "/insert", method = {RequestMethod.POST})
    @ResponseBody
    public void insertComment(HttpServletRequest request, Comment comment) {
        //添加评论
        comment.setCommentIp(IP.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        commentService.insert(comment);
        //更新文章的评论数
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
    * @Description: 删除
    * @Param: [id]
    * @return: void
    */
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        Comment comment = commentService.getCommentById(id);
        //删除评论
        commentService.deleteById(id);
        //删除其子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for (int i = 0; i < childCommentList.size(); i++) {
            commentService.deleteById(childCommentList.get(i).getCommentId());
        }
        //更新文章的评论数
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
    * @Description: 转发至编辑页面
    * @Param: [id, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/edit/{id}")
    public String editCommentView(@PathVariable("id") Integer id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "Admin/Comment/edit";
    }



    /**
    * @Description: 提交评论
    * @Param: [comment]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) {
        commentService.update(comment);
        return "redirect:/admin/comment";
    }

    /**
    * @Description: 转发回复评论页面
    * @Param: [id, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/reply/{id}")
    public String replyCommentView(@PathVariable("id") Integer id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "Admin/Comment/reply";
    }

    /**
    * @Description: 提交回复的评论
    * @Param: [request, comment]
    * @return: java.lang.String
    * @Author: 唐绍权
    * @Date: 15:22.2020/3/31
    */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request, Comment comment) {
        //文章评论数+1
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        articleService.update(article);
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IP.getIpAddr(request));
        commentService.insert(comment);
        return "redirect:/admin/comment";
    }

}
