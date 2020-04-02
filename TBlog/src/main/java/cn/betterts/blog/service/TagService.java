package cn.betterts.blog.service;

import cn.betterts.blog.domain.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> listTag();
    public List<Tag> listTagWithCount();
    public Tag getTagByName(String tagName);
    public Tag getTagById(Integer tagId);
    public Integer countTag();
    public void deleteById(Integer tagId);
    public void update(Tag tag);
    public Tag insert(Tag tag);
    public List<Tag> listTagByArticleId(Integer articleId);
}
