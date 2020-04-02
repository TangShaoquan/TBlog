package cn.betterts.blog.interceptor;

import cn.betterts.blog.domain.*;
import cn.betterts.blog.enums.ArticleStatus;
import cn.betterts.blog.enums.LinkStatus;
import cn.betterts.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* @Author: BetterTs
* @Date: 11:57.2020/3/30
*/
@Component
public class HomeInterceptor implements HandlerInterceptor {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private MenuService menuService;

//    请求处理之前执行，主备首页所需的数据，将其当作请求属性放到WebRequest中


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        菜单
        List<Menu> menuList = menuService.listMenu();
        request.setAttribute("menuList",menuList);

        List<Category> categoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList",categoryList);

//        获得网站概况
        List<String> siteBasicStatistics = new ArrayList<String>();
        //已发布文章的总数
        siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue()) + "");
        //文章评论数
        siteBasicStatistics.add(articleService.countArticleComment() + "");
        //分类数目
        siteBasicStatistics.add(categoryService.countCategory() + "");
        //标签数
        siteBasicStatistics.add(tagService.countTag() + "");
        //链接数
        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
        //文章点击数
        siteBasicStatistics.add(articleService.countArticleView() + "");
        //放入请求域
        request.setAttribute("siteBasicStatistics",siteBasicStatistics);

//        最后更新的文章
        Article lastUpdateArticle = articleService.getLastUpdateArticle();
        request.setAttribute("lastUpdateArticle",lastUpdateArticle);

//        页脚及博客的基本信息
        List<Options> options = optionsService.getOptions();
        request.setAttribute("options",options.get(0));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
