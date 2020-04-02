package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Page;
import cn.betterts.blog.enums.PageStatus;
import cn.betterts.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/page")
public class AdminPageController {
    @Autowired
    private PageService pageService;

    @RequestMapping(value = "")
    public ModelAndView indexView(){
        ModelAndView modelAndView = new ModelAndView();
        List<Page> pageList = pageService.listPage(null);
        modelAndView.addObject("pageList", pageList);
        modelAndView.setViewName("Admin/Page/index");
        return modelAndView;
    }

    /**
    * @Description: 添加
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/insert")
    public ModelAndView insertPageView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/Page/insert");
        return modelAndView;
    }

    /**
    * @Description: 插入
    * @Param: [page]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertPageSubmit(Page page) {

        //判断别名是否存在
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        if (checkPage == null) {
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            page.setPageStatus(PageStatus.NORMAL.getValue());
            pageService.insert(page);
        }
        return "redirect:/admin/page";
    }

    /**
    * @Description: 删除页面
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/delete/{id}")
    public String deletePage(@PathVariable("id") Integer id) {
        //调用service批量删除
        pageService.deleteById(id);
        return "redirect:/admin/page";
    }

    /**
    * @Description: 编辑页面
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editPageView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Page page = pageService.getPageById(id);
        modelAndView.addObject("page", page);

        modelAndView.setViewName("Admin/Page/edit");
        return modelAndView;
    }

    /**
    * @Description: 提交修改
    * @Param: [page]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editPageSubmit(Page page) {
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        //判断别名是否存在且不是这篇文章
        if (Objects.equals(checkPage.getPageId(), page.getPageId())) {
            page.setPageUpdateTime(new Date());
            pageService.update(page);
        }
        return "redirect:/admin/page";
    }
}
