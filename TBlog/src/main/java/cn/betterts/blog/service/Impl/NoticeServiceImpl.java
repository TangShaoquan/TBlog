package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.NoticeDao;
import cn.betterts.blog.domain.Notice;
import cn.betterts.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired(required = false)
    private NoticeDao noticeDao;

    @Override
    public List<Notice> listNotice(Integer noticeStatus) {
        return noticeDao.listNotice(noticeStatus);
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeDao.getNoticeById(noticeId);
    }

    @Override
    public Integer countNotice(Integer noticeStatus) {
        return noticeDao.countNotice(noticeStatus);
    }

    @Override
    public int deleteById(Integer noticeId) {
        return noticeDao.deleteById(noticeId);
    }

    @Override
    public int insert(Notice notice) {
        return noticeDao.insert(notice);
    }

    @Override
    public int updateByPrimaryKey(Notice notice) {
        return noticeDao.updateByPrimaryKey(notice);
    }

    @Override
    public int update(Notice notice) {
        return noticeDao.update(notice);
    }
}
