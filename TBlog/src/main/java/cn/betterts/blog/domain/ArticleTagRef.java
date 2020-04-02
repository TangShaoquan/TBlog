package cn.betterts.blog.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTagRef implements Serializable {
    private Integer articleId;
    private Integer tagId;

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
