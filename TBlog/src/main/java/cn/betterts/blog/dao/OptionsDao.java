package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.OptionsUpdateSql;
import cn.betterts.blog.domain.Options;
import org.apache.ibatis.annotations.*;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

/**
* @Author: 唐绍权
* @Date: 14:02.2020/3/28
*/
@Repository
public interface OptionsDao {

    /**
    * @Description: 根据id获取信息
    * @Param: [optionId]
    * @return: cn.betterts.blog.domain.Options
    */
    @Select("select * from options where option_id=#{optionId}")
    @Results(id="optionsMap",value = {
            @Result(id=true,column = "option_id",property = "optionId"),
            @Result(column ="option_site_title",property ="optionSiteTitle"),
            @Result(column ="option_site_descrption",property ="optionSiteDescrption"),
            @Result(column ="option_meta_descrption",property ="optionMetaDescrption"),
            @Result(column ="option_meta_keyword",property ="optionMetaKeyword"),
            @Result(column ="option_aboutsite_avatar",property ="optionAboutsiteAvatar"),
            @Result(column ="option_aboutsite_title",property ="optionAboutsiteTitle"),
            @Result(column ="option_aboutsite_content",property ="optionAboutsiteContent"),
            @Result(column ="option_aboutsite_wechat",property ="optionAboutsiteWechat"),
            @Result(column ="option_aboutsite_qq",property ="optionAboutsiteQq"),
            @Result(column ="option_aboutsite_github",property ="optionAboutsiteGithub"),
            @Result(column ="option_aboutsite_weibo",property ="optionAboutsiteWeibo"),
            @Result(column ="option_tongji",property = "optionTongji"),
            @Result(column ="option_status",property = "optionStatus")


    })
    public Options getOptionsById(Integer optionId);

    /**
    * @Description: 获取系统信息
    * @Param: []
    * @return: cn.betterts.blog.domain.Options
    */
    @Select("Select * from options")
    @ResultMap("optionsMap")
    public List<Options> getOptions();

    /**
    * @Description: 根据ID删除
    * @Param: [optionId]
    * @return: int
    */
    @Delete("delete from options where option_id = #{optionId}")
    @ResultMap("optionsMap")
    public int deleteById(Integer optionId);

    /**
    * @Description: 插入信息
    * @Param: [options]
    * @return: int
    */
    @Insert("insert into options " +
            "(option_id, option_site_title, option_site_descrption," +
            "option_meta_descrption, option_meta_keyword," +
            "option_aboutsite_avatar, option_aboutsite_title," +
            "option_aboutsite_content, option_aboutsite_wechat," +
            "option_aboutsite_qq, option_aboutsite_github," +
            "option_aboutsite_weibo, option_tongji, option_status )" +
            " values " +
            "(#{optionId}, " +
            "#{optionSiteTitle}," +
            "#{optionSiteDescrption}," +
            "#{optionMetaDescrption}, " +
            "#{optionMetaKeyword}," +
            "#{optionAboutsiteAvatar}, " +
            "#{optionAboutsiteTitle}," +
            "#{optionAboutsiteContent}, " +
            "#{optionAboutsiteWechat}," +
            "#{optionAboutsiteQq}, " +
            "#{optionAboutsiteGithub}," +
            "#{optionAboutsiteWeibo}, " +
            "#{optionTongji" +
            "}, #{optionStatus})")
    @ResultMap("optionsMap")
    public int insert(Options options);

    @UpdateProvider(type = OptionsUpdateSql.class,method = "Update")
    @ResultMap("optionsMap")
    public int update(Options options);
}
