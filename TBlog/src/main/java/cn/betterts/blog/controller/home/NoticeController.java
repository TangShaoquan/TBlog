package cn.betterts.blog.controller.home;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Notice;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: 通知控制器
 * @Author: BetterTs
 */
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;

    /**
    * @Description: 公告详情
    * @Param: [noticeId, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/notice/{noticeId}")
    public String NoticeDetailView(@PathVariable("noticeId") Integer noticeId,
                                   Model model) {
        //公告内容和信息显示
        Notice notice  = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice",notice);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/noticeDetail";
    }

}
