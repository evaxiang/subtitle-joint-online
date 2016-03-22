package com.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 2016/3/9.
 */
@Repository
public class LoginDao extends BaseDao {

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;


    public boolean login(String name, String password) {
        String sql = "select * from t_user where username=:name and password=:password ";
        Map<String, Object> t = new HashMap<>();
        t.put("name", name);
        t.put("password", password);
        Map<String, Object> map ;
        try {
            map = nameJdbcTpl.queryForMap(sql, t);
        } catch (Exception e) {
            map = new HashMap<>();
        }
        if (map.size() > 0) {
            return true;
        }
        return false;
    }


    public boolean register(String name, String password) {
        String sql = "insert into t_user(username,password) values(:name,:password)";
        Map<String, Object> t = new HashMap<>();
        t.put("name", name);
        t.put("password", password);
        return nameJdbcTpl.update(sql, t) > 0;
    }
}
