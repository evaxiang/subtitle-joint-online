package com.dao;

import com.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 2016/3/22.
 */
@Repository
public class UserDao extends BaseDao{

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;

    public List listUnDatedSisters(int weeks, int years) {
        String sql = "SELECT * FROM t_user WHERE is_sister = 1 " +
                " AND id NOT IN (SELECT sis_id FROM t_date_record where status = 1 and weeks =:weeks and years =:years)";
        HashMap map = new HashMap<String, Object>();
        map.put("weeks", weeks);
        map.put("years", years);
        List<Map<String,Object>> list = nameJdbcTpl.queryForList(sql,map);
        return list == null? Collections.emptyList() : list;
    }

    public void updateImgSrc(User user) {
        try {
            updateForBean("update t_user set img_src =:img_src where id=:id", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String,Object> findById(String id) {
        String sql = "select * from t_user where id=:id";
        HashMap map = new HashMap<String, Object>();
        map.put("id", id);
        return nameJdbcTpl.queryForMap(sql, map);
    }


    public boolean editIntroduction(User user){
        String sql = "update t_user set introduction =:introduction where id=:id";
        Map<String, Object> t = new HashMap<>();
        t.put("introduction",user.getIntroduction());
        t.put("id",user.getId());
        return nameJdbcTpl.update(sql,t) > 0;
    }


    public List<Map<String, Object>> listAllBrothers() {
        String sql = "SELECT * ,count(from_user_id) as like_num FROM t_user left join t_like on t_like.to_user_id = t_user.id WHERE t_user.is_sister = 0 group by t_user.id";
        List<Map<String,Object>> list = nameJdbcTpl.queryForList(sql, Collections.<String, Object>emptyMap());
        return list == null? Collections.<Map<String, Object>>emptyList() : list;
    }
}
