package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Category;
import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.domain.User;
import cn.betterts.blog.dto.ArticleParam;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CategoryService;
import cn.betterts.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
    * @Description: 转发至后台文章页面以及准备所需数据
    * @Param: [pageIndex, pageSize, status, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status, Model model) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "Admin/Article/index";
    }

    /**
    * @Description: 后台文章页面显示 插入页面
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insert")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "Admin/Article/insert";
    }

    /**
    * @Description: 添加文章
    * @Param: [session, articleParam]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        //用户ID
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        String summaryText = articleParam.getArticleSummary();
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }



        //文章主体内容
        //弃用
//        article.setArticleContent(Markdown2Html.M2H(articleParam.getArticleContent()));
        article.setArticleStatus(articleParam.getArticleStatus());
        article.setArticleContent(articleParam.getArticleContent());


        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insert(article);
        return "redirect:/admin/article";
    }


    /**
    * @Description: 删除文章
    * @Param: [id]
    * @return: void
    */
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteById(id);
    }

    /**
    * @Description: 转发到文章编辑页面
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: 唐绍权
    * @Date: 15:39.2020/3/31
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Article article = articleService.getArticleByStatusAndId(null, id);


        modelAndView.addObject("article", article);
        List<Category> categoryList = categoryService.listCategory();


        modelAndView.addObject("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);


        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }


    /**
    * @Description: 文章提交
    * @Param: [articleParam]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam) {
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
//        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        String summaryText = articleParam.getArticleSummary();
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
        return "redirect:/admin/article";
    }


}
