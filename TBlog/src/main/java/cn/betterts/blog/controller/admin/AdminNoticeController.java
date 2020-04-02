package cn.betterts.blog.controller.admin;

import cn.betterts.blog.domain.Notice;
import cn.betterts.blog.enums.NoticeStatus;
import cn.betterts.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
    * @Description: 后台公告列表
    * @Param: [model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "")
    public String indexView(Model model) {
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";
    }

    /**
    * @Description: 转发到新增通知页面
    * @Param: []
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insert")
    public String insertNoticeView() {
        return "Admin/Notice/insert";
    }


    /**
    * @Description: 添加公告
    * @Param: [notice]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insert(notice);
        return "redirect:/admin/notice";
    }


    /**
    * @Description: 删除公告
    * @Param: [id]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteById(id);
        return "redirect:/admin/notice";
    }

    /**
    * @Description: 编辑通知页面转发
    * @Param: [id, model]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Admin/Notice/edit";
    }

    /**
    * @Description: 编辑页面
    * @Param: [notice]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeService.update(notice);
        return "redirect:/admin/notice";
    }

}
