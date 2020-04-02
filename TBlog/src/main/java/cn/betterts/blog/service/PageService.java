package cn.betterts.blog.service;

import cn.betterts.blog.domain.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageService {
    public List<Page> listPage(@Param("pageStatus") Integer pageStatus );
    public Page getPageByKey(@Param("pageStatus") Integer pageStatus,@Param("pageKey") String pageKey);
    public Page getPageById(Integer pageId);
    public void deleteById(Integer pageId);
    public void insert(Page page);
    public void update(Page page);
}
