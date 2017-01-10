package com.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 2016/3/9.
 */
@Repository
public class MovieDao extends BaseDao {

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;

//    public List<Map<String,Object>> getLast15(){
//        List list = new ArrayList();
//        String sql = "select * from "
//
//    }


    public Map<String, Object> listAllScreenShot(Map map) throws Exception {
        String sql = "select * from t_screenshot ";
        String countSql = "select count(*) from ("+sql+") t";
        int count= count(countSql, map);
        int size = Integer.parseInt(map.get("size").toString());
        int pageIndex = (Integer.parseInt(map.get("pageIndex").toString())-1)*size;
        sql= sql + "order by create_time desc limit "+(pageIndex)+","+size;
        List<Map<String, Object>> list = list(sql, map);

        Map<String,Object> rs = new HashMap<String,Object>(2);
        rs.put("count",count);
        rs.put("list",list);
        return rs;
    }

    public int saveScreen(Map map) throws Exception {
        String  sql = "insert into t_screenshot(userId,img_src) values(:userId,:img_src)";
        return nameJdbcTpl.update(sql,map);
    }

}
