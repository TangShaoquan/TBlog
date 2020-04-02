package cn.betterts.blog.service;

import cn.betterts.blog.domain.ArticleTagRef;
import cn.betterts.blog.domain.Tag;

import java.util.List;

public interface ArticleTagRefService {
    public List<Tag> listTagByArticleId(Integer articleId);
    public int countArticleByTagId(Integer tagId);
    public int deleteByTagId(Integer tagId);
    public int deleteByArticleId(Integer articleId);
    public int insert(ArticleTagRef record);
}
