package com.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Andrew on 2016/3/22.
 */
@Repository
public class UserDao {

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;

    public List listSisters() {
        String sql = "select * from t_user where is_sister = 1 and is_single = 1";
        List<Map<String,Object>> list = nameJdbcTpl.queryForList(sql, new HashMap<String, Object>());
        return list == null? Collections.emptyList() : list;
    }
}
