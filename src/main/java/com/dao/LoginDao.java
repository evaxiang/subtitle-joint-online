package com.dao;

import com.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 2016/3/9.
 */
@Repository
public class LoginDao extends BaseDao {

    public static final String DEFAULT_USERIMG = "/hib/public/images/user_default.jpg";
    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;


    public boolean login(User user) {
        String sql = "select * from t_user where username=:username and password=:password ";
        Map<String, Object> t = new HashMap<>();
        t.put("username", user.getUsername());
        t.put("password", user.getPassword());
        Map<String, Object> map;
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


    public boolean userHasRegistered(String username) {
        String s1 = "select * from t_user where username=:username";
        List<Map<String, Object>> resultList = nameJdbcTpl.queryForList(s1, Collections.singletonMap("username", username));
        return !resultList.isEmpty();

    }

    public boolean register(User user) throws Exception {
        if (userHasRegistered(user.getUsername()))
        {
            throw new Exception("该用户已注册");
        }

        String sql = "insert into t_user(username,password,is_single,is_sister,img_src) values(:username,:password,:is_single,:is_sister,:img_src)";
        Map<String, Object> t = new HashMap<>();
        t.put("username", user.getUsername());
        t.put("password", user.getPassword());
        t.put("is_single", user.getIs_single());
        t.put("is_sister", user.getIs_sister());
        t.put("img_src", StringUtils.isEmpty(user.getImg_src()) ? DEFAULT_USERIMG : user.getImg_src());

        return nameJdbcTpl.update(sql, t) > 0;
    }

    public void modifyUseMessage(User user) throws Exception {
        String sql = "update t_user set username=:username, password=:password, is_single=:is_single, is_sister=:is_sister, " +
                "img_src=:img_src, introduction =:introduction,  mobile=:mobile  where id=:id";
        updateForBean(sql,user);
    }


    public User findUser(String username) {
        String sql = "select * from t_user where username=:username";
        Map<String, Object> t = new HashMap<>();
        t.put("username", username);
        Map<String, Object> userMap = nameJdbcTpl.queryForMap(sql, t);
        User user = new User();
        user.setId((Integer) userMap.get("id"));
        user.setUsername((String) userMap.get("username"));
        user.setPassword((String) userMap.get("password"));
        user.setIs_single((String) userMap.get("is_single"));
        user.setIs_sister((String) userMap.get("is_sister"));
        user.setIntroduction((String) userMap.get("introduction"));
        user.setMobile((String) userMap.get("mobile"));
        user.setImg_src((String) userMap.get("img_src"));
        return user;
    }

//    public void updateUser(User user){
//        String sql = ""
//    }

    public User findUserById(int id) {
        String sql = "select * from t_user where id=:id";
        Map<String, Object> t = new HashMap<>();
        t.put("id", id);
        Map<String, Object> userMap = nameJdbcTpl.queryForMap(sql, t);
        User user = new User();
        user.setId((Integer) userMap.get("id"));
        user.setUsername((String) userMap.get("username"));
        user.setPassword((String) userMap.get("password"));
        user.setMobile((String) userMap.get("mobile"));
        user.setIs_single((String) userMap.get("is_single"));
        user.setIs_sister((String) userMap.get("is_sister"));
        user.setIntroduction((String) userMap.get("introduction"));
        return user;
    }


}
