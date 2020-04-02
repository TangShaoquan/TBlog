package cn.betterts.blog.service;

import cn.betterts.blog.domain.ArticleCategoryRef;
import cn.betterts.blog.domain.Category;

import java.util.List;

public interface ArticleCategoryRefService {
    public List<Category> listCategoryByArticleId(Integer articleId);
    public List<Integer> selectCategoryIdByArticleId(Integer articleId);
    public List<Integer> selectArticleIdByCategoryId(Integer categoryId);
    public int deleteByCategoryId(Integer categoryId);
    public int deleteByArticleId(Integer articleId);
    public int countArticleByCategoryId(Integer categoryId);
    public int insert(ArticleCategoryRef record);
}
