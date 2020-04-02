package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.NoticeListSelectSql;
import cn.betterts.blog.dao.sql.NoticeUpdateSql;
import cn.betterts.blog.domain.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Author: 唐绍权
* @Date: 14:03.2020/3/28
*/
@Repository
public interface NoticeDao {

    /**
    * @Description: 获取通告列表，获取时要设置通知的状态
    * @Param: [noticeStatus]
    * @return: java.util.List<cn.betterts.blog.domain.Notice>
    */
    @SelectProvider(type = NoticeListSelectSql.class,method = "Select")
    @Results(id="noticeMap",value = {
            @Result(id = true ,column = "notice_id",property = "noticeId"),
            @Result(column = "notice_title",property = "noticeTitle"),
            @Result(column = "notice_content",property = "noticeContent"),
            @Result(column = "notice_create_time",property = "noticeCreateTime"),
            @Result(column = "notice_update_time",property = "noticeUpdateTime"),
            @Result(column = "notice_status",property = "noticeStatus"),
            @Result(column = "notice_order",property = "noticeOrder"),
    })
    public List<Notice> listNotice(Integer noticeStatus);

    /**
    * @Description: 根据ID获取通知
    * @Param: [noticeId]
    * @return: cn.betterts.blog.domain.Notice
    */
    @Select("select * from notice where notice_id=#{noticeId}")
    @ResultMap("noticeMap")
    public Notice getNoticeById(Integer noticeId);


    /** 
    * @Description: 获取通知总数
    * @Param: [noticeStatus]
    * @return: java.lang.Integer
    * @Author: 唐绍权 
    * @Date: 14:15.2020/3/28
    */
    @Select("select count(*) from notice where notice_status=#{noticeStatus}")
    public Integer countNotice(@Param("noticeStatus") Integer noticeStatus);


    /**
    * @Description: 根据Id删除通告
    * @Param: [noticeId]
    * @return: int
    */
    @Delete("delete from notice where notice_id=#{noticeId}")
    @ResultMap("noticeMap")
    public int deleteById(Integer noticeId);

    /**
    * @Description: 新增通知
    * @Param: [notice]
    * @return: int
    * @Author: 唐绍权
    * @Date: 14:19.2020/3/28
    */
    @Insert("insert into notice " +
            "(notice_id, notice_title, notice_content," +
            "notice_create_time, notice_update_time, " +
            "notice_status, notice_order)" +
            " values " +
            "(#{noticeId}, #{noticeTitle}, " +
            "#{noticeContent},#{noticeCreateTime}," +
            "#{noticeUpdateTime},#{noticeStatus}, " +
            "#{noticeOrder})")
    @ResultMap("noticeMap")
    public int insert(Notice notice);


    /**
    * @Description: 根据主键修改通知内容
    * @Param: [notice]
    * @return: int
    */
    @Update("Update notice " +
            "set notice_title = #{noticeTitle}, " +
            "notice_content = #{noticeContent}, " +
            "notice_create_time = #{noticeCreateTime}," +
            "notice_update_time = #{noticeUpdateTime}," +
            "notice_status=#{noticeStatus}," +
            "notice_order = #{noticeOrder} " +
            "where notice_id = #{noticeId}")
    @ResultMap("noticeMap")
    public int updateByPrimaryKey(Notice notice);

    /**
    * @Description: 更新
    * @Param: [notice]
    * @return: int
    */
    @UpdateProvider(type = NoticeUpdateSql.class,method = "Update" )
    @ResultMap("noticeMap")
    public int update(Notice notice);

}
