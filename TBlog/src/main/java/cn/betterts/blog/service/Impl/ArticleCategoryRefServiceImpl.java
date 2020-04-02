package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleCategoryRefDao;
import cn.betterts.blog.domain.ArticleCategoryRef;
import cn.betterts.blog.domain.Category;
import cn.betterts.blog.service.ArticleCategoryRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleCategoryRefService")
public class ArticleCategoryRefServiceImpl implements ArticleCategoryRefService {

    @Autowired
    private ArticleCategoryRefDao articleCategoryRefDao;

    @Override
    public List<Category> listCategoryByArticleId(Integer articleId) {
        return articleCategoryRefDao.listCategoryByArticleId(articleId);
    }

    @Override
    public List<Integer> selectCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefDao.selectCategoryIdByArticleId(articleId);
    }

    @Override
    public List<Integer> selectArticleIdByCategoryId(Integer categoryId) {
        return articleCategoryRefDao.selectArticleIdByCategoryId(categoryId);
    }

    @Override
    public int deleteByCategoryId(Integer categoryId) {
        return articleCategoryRefDao.deleteByCategoryId(categoryId);
    }

    @Override
    public int deleteByArticleId(Integer articleId) {
        return articleCategoryRefDao.deleteByArticleId(articleId);
    }

    @Override
    public int countArticleByCategoryId(Integer categoryId) {
        return articleCategoryRefDao.countArticleByCategoryId(categoryId);
    }

    @Override
    public int insert(ArticleCategoryRef record) {
        return articleCategoryRefDao.insert(record);
    }
}
