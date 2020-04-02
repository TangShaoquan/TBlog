package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Article;
import cn.betterts.blog.domain.Comment;
import cn.betterts.blog.domain.User;
import cn.betterts.blog.service.ArticleService;
import cn.betterts.blog.service.CommentService;
import cn.betterts.blog.service.UserService;
import cn.betterts.blog.util.IP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 管理页面的控制器
 * @Author: BetterTs
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    /**
    * @Description: 页面登录
    * @Param: []
    * @return: java.lang.String
    */
    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }

    /**
    * @Description: 后台首页
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping("/admin")
    public String adminIndex(Model model){
        //文章列表
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList",articleList);

        //评论列表
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("commentList",commentList);

        return "Admin/index";
    }

    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody//标志返回内容不是页面转发
    public String loginVerify(HttpServletRequest request, HttpServletResponse response)  {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");

        User user = userService.getUserByNameOrEmail(username);
        if(user==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getUserPassword().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(IP.getIpAddr(request));
            userService.update(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
    * @Description: 退出登录
    * @Param: [session]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session)  {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


}
