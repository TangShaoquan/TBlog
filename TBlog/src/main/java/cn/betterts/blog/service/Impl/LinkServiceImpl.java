package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.LinkDao;
import cn.betterts.blog.domain.Link;
import cn.betterts.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired(required = false)
    private LinkDao linkDao;

    @Override
    public List<Link> listLink(Integer linkStatus) {
        return linkDao.listLink(linkStatus);
    }

    @Override
    public Link getLinkById(Integer linkId) {
        return linkDao.getLinkById(linkId);
    }

    @Override
    public Integer countLink(Integer linkStatus) {
        return linkDao.countLink(linkStatus);
    }

    @Override
    public void deleteById(Integer linkId) {
        linkDao.deleteById(linkId);
    }

    @Override
    public void insert(Link link) {
        linkDao.insert(link);
    }

    @Override
    public void update(Link link) {
        linkDao.update(link);
    }
}
