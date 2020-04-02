package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleCategoryRefDao;
import cn.betterts.blog.dao.ArticleDao;
import cn.betterts.blog.dao.ArticleTagRefDao;
import cn.betterts.blog.domain.*;
import cn.betterts.blog.enums.ArticleCommentStatus;
import cn.betterts.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("articleService")
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleCategoryRefDao articleCategoryRefDao;

    @Autowired
    private ArticleTagRefDao articleTagRefDao;

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return articleDao.findAll(criteria);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleDao.listAllNotWithContent();
    }

    @Override
    public List<Article> listArticle() {
        return articleDao.listArticle();
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex,
                                         Integer pageSize,
                                         HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Article> articleList = articleDao.findAll(criteria);
        for (int i = 0; i < articleList.size(); i++) {
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefDao.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
        }

        PageInfo pageInfo =new PageInfo(articleList,pageSize);
        return pageInfo;
    }
    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleDao.listArticleByViewCount(limit);
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return articleDao.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        return articleDao.getPreArticle(id);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleDao.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleDao.listArticleByCommentCount(limit);
    }

    @Override
    public List<Article> findArticleByCategoryId(Integer categoryId, Integer limit) {
        return articleDao.findArticleByCategoryId(categoryId,limit);
    }

    @Override
    public List<Article> findArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        if ( categoryIds == null ||  categoryIds.size() == 0) {
            return null;
        }
        return articleDao.findArticleByCategoryIds(categoryIds,limit);
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        return articleDao.listArticleByLimit(limit);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        articleDao.deleteBatch(ids);
    }

    @Override
    public Article getLastUpdateArticle() {
        return articleDao.getLastUpdateArticle();
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        Article article = articleDao.getArticleByStatusAndId(status, id);
        if (article != null) {
            List<Category> categoryList = articleCategoryRefDao.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefDao.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }
        return article;
    }

    @Override
    public Integer countArticleByUser(Integer id) {
        return articleDao.countArticleByUser(id);
    }

    @Override
    public void deleteById(Integer articleId) {
         articleDao.deleteById(articleId);
    }

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        try {
            count = articleDao.countArticle(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据状态统计文章数, status:{}, cause:{}", status, e);
        }
        return count;
    }

    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        try {
            count = articleDao.countArticleComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章评论数失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleView() {
        Integer count = 0;
        try {
            count = articleDao.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章访问量失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = 0;
        try {
            count = articleCategoryRefDao.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类统计文章数量失败, categoryId:{}, cause:{}", categoryId, e);
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return articleTagRefDao.countArticleByTagId(tagId);
    }

    @Override
    public void insert(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        Integer id = articleDao.insert(article);
        //添加分类和文章关联
        for (int i = 0; i < article.getCategoryList().size(); i++) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefDao.insert(articleCategoryRef);
        }
        //添加标签和文章关联
        for (int i = 0; i < article.getTagList().size(); i++) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
            articleTagRefDao.insert(articleTagRef);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleDao.update(article);

        if (article.getTagList() != null) {
            //删除标签和文章关联
            articleTagRefDao.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagRefDao.insert(articleTagRef);
            }
        }


        if (article.getCategoryList() != null) {
            //添加分类和文章关联
            articleCategoryRefDao.deleteByArticleId(article.getArticleId());
            //删除分类和文章关联
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefDao.insert(articleCategoryRef);
            }
        }
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefDao.selectCategoryIdByArticleId(articleId);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleDao.updateCommentCount(articleId);
    }
}
