package cn.betterts.blog.controller.admin;
import cn.betterts.blog.domain.User;
import cn.betterts.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
    * @Description: 用户列表
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping("")
    public ModelAndView userListView(){
        ModelAndView modelAndView = new ModelAndView();


        List<User> userList = userService.ListUser();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("Admin/User/index");

        return modelAndView;
    }

    /**
    * @Description: 转发到插入用户页面
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/insert")
    public ModelAndView insertUserView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/User/insert");

        return modelAndView;
    }

    /**
    * @Description: 检验用户
    * @Param: [request]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
    * @Description: 检查Email是否存在
    * @Param: [request]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
    * @Description: 添加用户提交
    * @Param: [user]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertUserSubmit(User user)  {
        User user2 = userService.getUserByName(user.getUserName());
        User user3 = userService.getUserByEmail(user.getUserEmail());
        if(user2==null&&user3==null) {
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insert(user);
        }
        return "redirect:/admin/user";
    }

    /**
    * @Description: 删除用户
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id)  {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }

    /**
    * @Description: 编辑用户
    * @Param: [id]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);

        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }

    /**
    * @Description: 编辑用户提交
    * @Param: [user]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user)  {
        userService.update(user);
        return "redirect:/admin/user";
    }

    /**
    * @Description: 基本信息页面
    * @Param: [session]
    * @return: org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping(value = "/profile/{id}")
    public ModelAndView userProfileView(@PathVariable("id") Integer id)  {

        ModelAndView modelAndView = new ModelAndView();
        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("Admin/User/profile");
        return modelAndView;
    }
}
