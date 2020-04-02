package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.LinkSelectListSql;
import cn.betterts.blog.dao.sql.LinkUpdateSql;
import cn.betterts.blog.domain.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 15:15.2020/3/28
*/
@Repository
public interface LinkDao {
    /**
    * @Description: 获取列表
    * @Param: [linkStatus]
    * @return: java.util.List<cn.betterts.blog.domain.Link>
    */
    @SelectProvider(type = LinkSelectListSql.class, method = "Select")
    @Results(id="linkMap",value = {
            @Result(id = true,column = "link_id",property = "linkId"),
            @Result(column = "link_url",property = "linkUrl"),
            @Result(column = "link_name",property = "linkName"),
            @Result(column = "link_image",property = "linkImage"),
            @Result(column = "link_description",property = "linkDescription"),
            @Result(column = "link_owner_nickname",property = "linkOwnerNickname"),
            @Result(column = "link_owner_contact",property = "linkOwnerContact"),
            @Result(column = "link_update_time",property = "linkUpdateTime"),
            @Result(column = "link_create_time",property = "linkCreateTime"),
            @Result(column = "link_order",property = "linkOrder"),
            @Result(column = "link_status",property = "linkStatus")
    })
    public List<Link> listLink(Integer linkStatus);

    @Select("select * from link where link_id=#{linkId}")
    @ResultMap("linkMap")
    public Link getLinkById(Integer linkId);


    /**
    * @Description: 获取链接总数
    * @Param: [linkStatus]
    * @return: java.lang.Integer
    */
    @Select("select count(*) from link where link_status=#{linkStatus}")
    public Integer countLink(@Param("linkStatus") Integer linkStatus);


    /**
    * @Description: 根据id删除
    * @Param: [linkId]
    * @return: int
    */
    @Delete("delete from link where link_id=#{linkId}")
    @ResultMap("linkMap")
    public int deleteById(Integer linkId);

    /**
    * @Description: 插入新的链接
    * @Param: [link]
    * @return: int
    */
    @Insert("insert into link " +
            "(link_id, link_url, link_name,link_image, " +
            "link_description, link_owner_nickname," +
            "link_owner_contact, link_update_time, link_create_time," +
            "link_order, link_status) " +
            " values " +
            "(#{linkId}, #{linkUrl}, #{linkName}," +
            "#{linkImage}, #{linkDescription}, #{linkOwnerNickname}," +
            "#{linkOwnerContact}, #{linkUpdateTime}, #{linkCreateTime}," +
            "#{linkOrder}, #{linkStatus})")
    @ResultMap("linkMap")
    public int insert(Link link);

    /**
    * @Description: 修改
    * @Param: [link]
    * @return: int
    */
    @UpdateProvider(type = LinkUpdateSql.class,method = "Update")
    @ResultMap("linkMap")
    public int update(Link link);
}
