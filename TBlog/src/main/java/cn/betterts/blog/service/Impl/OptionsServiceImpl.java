package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.OptionsDao;
import cn.betterts.blog.domain.Options;
import cn.betterts.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("optionsService")
public class OptionsServiceImpl implements OptionsService {

    @Autowired(required = false)
    private OptionsDao optionsDao;

    @Override
    public Options getOptionsById(Integer optionId) {
        return optionsDao.getOptionsById(optionId);
    }

    @Override
    @Cacheable(value = "default", key = "'options'")
    public List<Options> getOptions() {
        return optionsDao.getOptions();
    }

    @Override
    public int deleteById(Integer optionId) {
        return optionsDao.deleteById(optionId);
    }

    @Override
    @CacheEvict(value = "default", key = "'options'")
    public int insert(Options options) {
        return optionsDao.insert(options);
    }

    @Override
    @CacheEvict(value = "default", key = "'options'")
    public int update(Options options) {
        return optionsDao.update(options);
    }
}
