package cn.betterts.blog.service;

import cn.betterts.blog.domain.Article;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {
    public List<Article> listArticle(HashMap<String, Object> criteria);
    public  List<Article> listAllNotWithContent();
    public List<Article> listArticle();
    public Integer countArticleByCategoryId(Integer categoryId);
    public Integer countArticleByTagId(Integer tagId);
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);;
    public List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);
    public Article getAfterArticle(@Param(value = "id") Integer id);
    public Article getPreArticle(@Param(value = "id") Integer id);
    public List<Article> listRandomArticle(@Param(value = "limit") Integer limit);
    public List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);
    public List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                                 @Param("limit") Integer limit);
    public List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                                  @Param("limit") Integer limit);
    public List<Article> listRecentArticle(Integer limit);
    public void deleteBatch(@Param("ids") List<Integer> ids);
    public Article getLastUpdateArticle();
    public Article getArticleByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);
    public Integer countArticleByUser(@Param(value = "id") Integer id);
    public void deleteById(Integer articleId);
    public Integer countArticle(@Param(value = "status") Integer status);
    public Integer countArticleComment();
    public Integer countArticleView();
    public void insert(Article article);
    public void updateArticleDetail(Article article);
    public void update(Article article);
    public void updateCommentCount(@Param(value = "articleId") Integer articleId);
    public List<Integer> listCategoryIdByArticleId(Integer articleId);


}
