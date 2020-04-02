package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
    * @Description: 后台标签显示
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "")
    public ModelAndView indexView(){
        ModelAndView modelAndView = new ModelAndView();
        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("Admin/Tag/index");

        return modelAndView;
    }

    /**
    * @Description: 添加分类页面，然后转发到tag首页
    * @Param: [tag]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
        Tag insert = tagService.insert(tag);
        return "redirect:/admin/tag";
    }

    /**
    * @Description: 删除
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id){
        Integer count = articleService.countArticleByTagId(id);
        if (count == 0) {
            tagService.deleteById(id);
        }
        return "redirect:/admin/tag";
    }

    /**
    * @Description: 编辑
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Tag tag =  tagService.getTagById(id);
        modelAndView.addObject("tag",tag);

        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList",tagList);

        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag)  {
        tagService.update(tag);
        return "redirect:/admin/tag";
    }
}
