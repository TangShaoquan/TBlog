package cn.betterts.blog.controller.home;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Category;
import cn.betterts.blog.domain.Page;
import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CategoryService;
import cn.betterts.blog.service.PageService;
import cn.betterts.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * @Description: 页面控制器
 * @Author: BetterTs
 */
@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private TagService tagService;

    @RequestMapping("/{key}")
    public String pageDetail(@PathVariable("key") String key, Model model){
        Page page = pageService.getPageByKey(1, key);
        if (page == null) {
            return "redirect:/404";
        }
        model.addAttribute("page", page);
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/page";
    }

    /**
    * @Description: 归档
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/articleFile")
    public String articleFile(Model model) {
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/articleFile";
    }

    /**
    * @Description: 站点地图
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/map")
    public String siteMap(Model model) {
        //文章显示
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        //标签显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/siteMap";
    }

    /**
    * @Description: 留言板
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/message")
    public String message(Model model) {
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/message";
    }
}
