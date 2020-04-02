package cn.betterts.blog.controller.home;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Comment;
import cn.betterts.blog.dto.JsonResult;
import cn.betterts.blog.enums.ArticleStatus;
import cn.betterts.blog.enums.Role;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CommentService;
import cn.betterts.blog.util.Gravatar;
import cn.betterts.blog.util.IP;
import cn.hutool.http.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: 评论控制器
 * @Author: BetterTs
 */
//如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
@Controller
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
    * @Description: 添加评论
    * @Param: [request, comment]
    * @return: cn.betterts.blog.dto.JsonResult
    */
    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    public JsonResult insertComment(HttpServletRequest request, Comment comment){
//        添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(IP.getIpAddr(request));

        if (request.getSession().getAttribute("user") != null) {
            comment.setCommentRole(Role.ADMIN.getValue());
        } else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        comment.setCommentAuthorAvatar(Gravatar.getGravatar(comment.getCommentAuthorEmail()));

        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));

        try {
            commentService.insert(comment);
            //更新文章的评论数
            Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
//            操作失败
            return new JsonResult().fail();
        }
//        操作成功
        return new JsonResult().ok();
    }

}
