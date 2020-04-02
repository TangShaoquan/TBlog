package cn.betterts.blog.dao.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class ArticleDeleteByIds {
    public String Delete(final Map<String, Object> map){
        final List<Integer> ids = (List<Integer>) map.get("ids");
        final StringBuilder sb = new StringBuilder();
        for (Integer id : ids){
            sb.append(id.toString());
            sb.append(",");
        }
        final String Ids = sb.toString().substring(0,sb.toString().length()-1);
        System.out.println(Ids);
         return new SQL(){
            {
                DELETE_FROM("article");
                WHERE("article_id IN "+"("+Ids+")");
            }
        }.toString();
    }
}
