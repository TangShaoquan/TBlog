package cn.betterts.blog.dao;

import cn.betterts.blog.dao.sql.PageSelectByKeySql;
import cn.betterts.blog.dao.sql.PageSelectListSql;
import cn.betterts.blog.dao.sql.PageUpdateSql;
import cn.betterts.blog.domain.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 持久层: 操纵Page表
 * @Author: 唐绍权
 * @Date: 2020/3/28
 */
@Repository
public interface PageDao {

    /**
    * @Description: 根据页面状态获取所有列表，页面状态有隐藏，显示两种
    * @Param: [pageStatus]
    * @return: java.util.List<cn.betterts.blog.domain.Page>
    */
    @SelectProvider(type = PageSelectListSql.class , method = "Select")
    @Results(id="pageMap",value = {
            @Result(id=true,column = "page_id",property = "pageId"),
            @Result(column = "page_key",property = "pageKey"),
            @Result(column = "page_title",property = "pageTitle"),
            @Result(column = "page_content",property = "pageContent"),
            @Result(column = "page_create_time",property = "pageCreateTime"),
            @Result(column = "page_update_time",property = "pageUpdateTime"),
            @Result(column = "page_view_count",property = "pageViewCount"),
            @Result(column ="page_comment_count",property ="pageCommentCount"),
            @Result(column = "page_status",property = "pageStatus")
    })
    public List<Page> listPage(Integer pageStatus );

    /**
    * @Description: 根据页面的Key来查询
    * @Param: [pageStatus, pageKey]
    * @return: cn.betterts.blog.domain.Page
    */
    @SelectProvider(type = PageSelectByKeySql.class,method = "Select")
    @ResultMap("pageMap")
    public Page getPageByKey(@Param("pageStatus") Integer pageStatus,@Param("pageKey") String pageKey);


    /**
    * @Description: 根据页面的id来查询
    * @Param: [pageId]
    * @return: cn.betterts.blog.domain.Page
    */
    @Select("select * from page where page_id=#{pageId}")
    @ResultMap("pageMap")
    public Page getPageById(Integer pageId);


    /**
    * @Description: 根据Id删除页面
    * @Param: [pageId]
    * @return: int
    */
    @Delete("delete from page where page_id=#{pageId}")
    @ResultMap("pageMap")
    public int deleteById(Integer pageId);

    /**
    * @Description: 新增页面
    * @Param: [page]
    * @return: int
    */
    @Insert("insert into page " +
            "(page_key,page_title,page_content,page_create_time," +
            "page_update_time,page_view_count," +
            "page_comment_count,page_status)" +
            " values " +
            "(#{pageKey},#{pageTitle},#{pageContent},#{pageCreateTime},#{pageUpdateTime},#{pageViewCount},#{pageCommentCount},#{pageStatus})")
    @ResultMap("pageMap")
    public int insert(Page page);

    /**
    * @Description: 更新页面
    * @Param: [page]
    * @return: int
    */
    @UpdateProvider(type = PageUpdateSql.class,method = "Update" )
    @ResultMap("pageMap")
    public int update(Page page);
}
