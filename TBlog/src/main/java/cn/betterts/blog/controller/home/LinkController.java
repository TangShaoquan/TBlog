package cn.betterts.blog.controller.home;


import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Link;
import cn.betterts.blog.enums.LinkStatus;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * @Description: 链接控制器
 * @Author: BetterTs
 */
@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/applyLink")
    public String applyLinkView(Model model){
//        侧边显示
//        活得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/applyLink";
    }

    @RequestMapping(value = "/applyLinkSubmit",method = {RequestMethod.POST})
    public void applyLinkSubmit(Link link){
        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.insert(link);
    }
}
