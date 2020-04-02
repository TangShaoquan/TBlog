package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Link;
import cn.betterts.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/link")
public class AdminLinkController {
    @Autowired
    private LinkService linkService;


    /**
    * @Description: 链接页显示
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "")
    public ModelAndView linkView()  {
        ModelAndView modelandview = new ModelAndView();

        List<Link> linkList = linkService.listLink(null);
        modelandview.addObject("linkList",linkList);

        modelandview.setViewName("Admin/Link/index");
        return modelandview;
    }

    /**
    * @Description: 添加页面
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/insert")
    public ModelAndView insertLinkView()  {
        ModelAndView modelAndView = new ModelAndView();

        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    /**
    * @Description: 添加链接
    * @Param: [link]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertLinkSubmit(Link link)  {
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(1);
        linkService.insert(link);
        return "redirect:/admin/link/insert";
    }

    /**
    * @Description: 删除
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id)  {

        linkService.deleteById(id);
        return "redirect:/admin/link";
    }

    /**
    * @Description: 咋混发至编辑页面
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Link linkCustom =  linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }

    /**
    * @Description: 提交链接
    * @Param: [link]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editLinkSubmit(Link link)  {
        link.setLinkUpdateTime(new Date());
        linkService.update(link);
        return "redirect:/admin/link";
    }
}
