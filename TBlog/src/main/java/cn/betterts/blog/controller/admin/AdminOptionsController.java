package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Options;
import cn.betterts.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/options")
public class AdminOptionsController {
    @Autowired
    private OptionsService optionsService;

    @RequestMapping(value = "")
    public ModelAndView indexView()  {
        ModelAndView modelAndView = new ModelAndView();
        List<Options> options = optionsService.getOptions();
        modelAndView.addObject("option",options.get(0));

        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editOptionView()  {
        ModelAndView modelAndView = new ModelAndView();
        List<Options> options = optionsService.getOptions();
        modelAndView.addObject("option",options.get(0));

        modelAndView.setViewName("Admin/Options/edit");
        return modelAndView;
    }
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options)  {
        //如果记录不存在，那就新建
        List<Options> optionsCustom = optionsService.getOptions();
        if(optionsCustom.get(0).getOptionId()==null) {
            optionsService.insert(options);
        } else {
            optionsService.update(options);
        }
        return "redirect:/admin/options";
    }
}
