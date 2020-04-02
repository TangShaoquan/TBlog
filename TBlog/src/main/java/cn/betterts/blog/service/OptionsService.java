package cn.betterts.blog.service;

import cn.betterts.blog.domain.Options;

import java.util.List;

public interface OptionsService {
    public Options getOptionsById(Integer optionId);
    public List<Options> getOptions();
    public int deleteById(Integer optionId);
    public int insert(Options options);
    public int update(Options options);
}
