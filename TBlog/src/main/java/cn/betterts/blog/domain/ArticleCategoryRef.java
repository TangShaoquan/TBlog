package cn.betterts.blog.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleCategoryRef implements Serializable {
    private Integer articleId;
    private Integer categoryId;

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}
