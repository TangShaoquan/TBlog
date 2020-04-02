package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleTagRefDao;
import cn.betterts.blog.domain.ArticleTagRef;
import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.service.ArticleTagRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleTagRefService")
public class ArticleTagRefServiceImpl implements ArticleTagRefService {

    @Autowired
    private ArticleTagRefDao articleTagRefDao;

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        return articleTagRefDao.listTagByArticleId(articleId);
    }

    @Override
    public int countArticleByTagId(Integer tagId) {
        return articleTagRefDao.countArticleByTagId(tagId);
    }

    @Override
    public int deleteByTagId(Integer tagId) {
        return articleTagRefDao.deleteByTagId(tagId);
    }

    @Override
    public int deleteByArticleId(Integer articleId) {
        return articleTagRefDao.deleteByArticleId(articleId);
    }

    @Override
    public int insert(ArticleTagRef record) {
        return articleTagRefDao.insert(record);
    }
}
