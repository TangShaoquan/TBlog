package cn.betterts.blog.service;

import cn.betterts.blog.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {
    public List<Notice> listNotice(@Param(value = "noticeStatus") Integer noticeStatus);
    public Notice getNoticeById(Integer noticeId);
    public Integer countNotice(@Param(value = "noticeStatus") Integer noticeStatus);
    public int deleteById(Integer noticeId);
    public int insert(Notice notice);
    public int updateByPrimaryKey(Notice notice);
    public int update(Notice notice);

}
