package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.TagUpdateSql;
import cn.betterts.blog.domain.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description: 持久层: 操纵User表
 * @Author: 唐绍权
 * @Date: 2020/3/28
 */
@Repository
public interface TagDao {

    /**
    * @Description: 查询tag列表
    * @Param: []
    * @return: java.util.List<cn.betterts.blog.domain.Tag>
    */
    @Select("select * from tag")
    @Results(id = "tagMap", value = {
            @Result(id=true,column = "tag_id",property = "tagId"),
            @Result(column = "tag_name",property = "tagName"),
            @Result(column = "tag_description",property = "tagDescription")
    })
    public List<Tag> listTag();

    /**
    * @Description: 根据tag名查询
    * @Param: [tagName]
    * @return: cn.betterts.blog.domain.Tag
    */
    @Select("Select * from tag where tag_name=#{tagName}")
    @ResultMap("tagMap")
    public Tag getTagByName(String tagName);

    /**
    * @Description: 根据tagId查询tag
    * @Param: [tagId]
    * @return: cn.betterts.blog.domain.Tag
    */
    @Select("select * from tag where tag_id=#{tagId}")
    @ResultMap("tagMap")
    public Tag getTagById(Integer tagId);

    /**
    * @Description: 获取标签总数
    * @Param:
    * @return: int
    */
    @Select("select count(*) from tag")
    public Integer countTag();

    /**
    * @Description: 根据id删除tag
    * @Param: [tagId]
    * @return: int
    */
    @Delete("delete from tag where tag_id=#{tagId}")
    @ResultMap("tagMap")
    public int deleteById(Integer tagId);

    /**
    * @Description: 根据id修改标签
    * @Param: [tag]
    * @return: int 修改的行数
    */
    @UpdateProvider(type = TagUpdateSql.class,method = "Update")
    @ResultMap("tagMap")
    public int update(Tag tag);

    @Insert("insert into tag (tag_name, tag_description) " +
            " values (#{tagName}, #{tagDescription})")
    @ResultMap("tagMap")
    public int insert(Tag tag);



}
