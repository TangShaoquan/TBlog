package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.ArticleTagRefDao;
import cn.betterts.blog.dao.TagDao;
import cn.betterts.blog.domain.Tag;
import cn.betterts.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;


@Service("tagService")
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private ArticleTagRefDao articleTagRefDao;

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList=null;
        try{
            tagList = tagDao.listTag();
        } catch (Exception e){
            e.printStackTrace();
            log.error("活得所有标签失败, cause:{}",e);
        }
        return tagList;
    }

    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tagList=null;
        try{
            tagList = tagDao.listTag();
            for (Tag tag : tagList){
                int count = articleTagRefDao.countArticleByTagId(tag.getTagId());
                tag.setArticleCount(count);
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("活得所有标签失败, cause:{}",e);
        }
        return tagList;
    }

    @Override
    public Tag getTagByName(String tagName) {
        Tag tag = null;
        try {
            tag = tagDao.getTagByName(tagName);
        } catch (Exception e) {            e.printStackTrace();
            log.error("根据名称获得标签, name:{}, cause:{}", tagName, e);
        }
        return tag;
    }

    @Override
    public Tag getTagById(Integer tagId) {
        Tag tag = null;
        try{
            tag = tagDao.getTagById(tagId);
        } catch (Exception e){
            e.printStackTrace();
            log.error("根据Id获得标签失败,id:{},cause:{}",tagId,e);
        }
        return tag;
    }

    @Override
    public Integer countTag() {

        return tagDao.countTag();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer tagId) {
        try{
            tagDao.deleteById(tagId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除标签失败, id:{}, cause:{}", tagId, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public void update(Tag tag) {
       try{
           int update = tagDao.update(tag);
       } catch (Exception e){
           e.printStackTrace();
           log.error("更新标签失败, tag:{}, cause:{}", tag, e);
       }
    }

    @Override
    public Tag insert(Tag tag) {

        try {
            tagDao.insert(tag);
        } catch (Exception e){
            e.printStackTrace();
            log.error("添加标签失败, tag:{}, cause:{}", tag, e);
        }
        return tag;
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        List<Tag> tagList = null;
        try {
            tagList = articleTagRefDao.listTagByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章ID获得标签失败，articleId:{}, cause:{}", articleId, e);
        }
        return tagList;
    }
}
