package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.PageDao;
import cn.betterts.blog.domain.Page;
import cn.betterts.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageService")
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    @Override
    public List<Page> listPage(Integer pageStatus) {
        return pageDao.listPage(pageStatus);
    }

    @Override
    public Page getPageByKey(Integer pageStatus, String pageKey) {
        return pageDao.getPageByKey(pageStatus,pageKey);
    }

    @Override
    public Page getPageById(Integer pageId) {
        return pageDao.getPageById(pageId);
    }

    @Override
    public void deleteById(Integer pageId) {
        pageDao.deleteById(pageId);
    }

    @Override
    public void insert(Page page) {
        pageDao.insert(page);
    }

    @Override
    public void update(Page page) {
        pageDao.update(page);
    }
}
