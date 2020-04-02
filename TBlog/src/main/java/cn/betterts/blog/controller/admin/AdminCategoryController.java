package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Category;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


    /**
    * @Description: 首页以及转发页面
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "")
    public ModelAndView categoryList()  {
        ModelAndView modelandview = new ModelAndView();
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelandview.addObject("categoryList",categoryList);
        modelandview.setViewName("Admin/Category/index");
        return modelandview;
    }

    /**
    * @Description: 添加分类
    * @Param: [category]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category)  {
        categoryService.insert(category);
        return "redirect:/admin/category";
    }

    /**
    * @Description: 删除分类
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id)  {
        //禁止删除有文章的分类
        int count = articleService.countArticleByCategoryId(id);

        if (count == 0) {
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }

    /**
    * @Description: 编辑页面转发
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Category category =  categoryService.getCategoryById(id);
        modelAndView.addObject("category",category);

        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList",categoryList);

        modelAndView.setViewName("Admin/Category/edit");
        return modelAndView;
    }

    /**
    * @Description: 提交
    * @Param: [category]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category)  {
        categoryService.update(category);
        return "redirect:/admin/category";
    }

}
