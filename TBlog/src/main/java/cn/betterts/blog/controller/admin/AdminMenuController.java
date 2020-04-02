package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Menu;
import cn.betterts.blog.enums.MenuLevel;
import cn.betterts.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {
    @Autowired
    private MenuService menuService;


    /**
    * @Description: 菜单页
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "")
    public String indexView(Model model)  {
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList",menuList);
        return "Admin/Menu/index";
    }

    /**
    * @Description: 添加
    * @Param: [menu]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu)  {
        if(menu.getMenuOrder() == null) {
            menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
        }
        menuService.insert(menu);
        return "redirect:/admin/menu";
    }

   /**
   * @Description: 删除
   * @Param: [id]
   * @return: java.lang.String
   */
    @RequestMapping(value = "/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id)  {
        menuService.deleteById(id);
        return "redirect:/admin/menu";
    }

    /**
    * @Description: 编辑菜单显示
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Menu menu =  menuService.getMenuById(id);
        modelAndView.addObject("menu",menu);

        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);

        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }

    /**
    * @Description: 提交表单
    * @Param: [menu]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu)  {
        menuService.update(menu);
        return "redirect:/admin/menu";
    }
}
