package cn.betterts.blog.service;

import cn.betterts.blog.domain.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkService {
    public List<Link> listLink(@Param("linkStatus") Integer linkStatus);
    public Link getLinkById(Integer linkId);
    public Integer countLink(@Param("linkStatus") Integer linkStatus);
    public void deleteById(Integer linkId);
    public void insert(Link link);
    public void update(Link link);
}
