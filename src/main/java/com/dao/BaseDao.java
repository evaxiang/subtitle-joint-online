package com.dao;

import com.model.BModel;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BaseDao {

    @Resource(name = "nameJdbcTpl")
    private NamedParameterJdbcTemplate nameJdbcTpl;

    /**
     * 插入记录
     *
     * @param sql   eq insert into tb_test1 values(:username,:password,:sex)
     * @param model
     * @return
     */
    protected int insertForBean(String sql, BModel model) throws Exception {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(model);
        KeyHolder keyholder = new GeneratedKeyHolder();
        nameJdbcTpl.update(sql, ps, keyholder); //加上KeyHolder这个参数可以得到添加后主键的值
        return keyholder.getKey().intValue();
    }

    /**
     * 插入记录,无主键返回
     *
     * @param sql   eq insert into tb_test1 values(:username,:password,:sex)
     * @param model
     * @return
     */
    protected int insertNoKeyForBean(String sql, BModel model) throws Exception {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(model);
        return nameJdbcTpl.update(sql, ps);

    }

    /**
     * 插入记录
     *
     * @param sql     eq insert into tb_test1 values(:username,:password,:sex)
     * @param paraMap
     * @return
     */
    protected int insertForMap(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        KeyHolder keyholder = new GeneratedKeyHolder();
        nameJdbcTpl.update(sql, ps, keyholder); //加上KeyHolder这个参数可以得到添加后主键的值
        return keyholder.getKey().intValue();
    }

    /**
     * 插入记录,无主键返回
     *
     * @param sql     eq insert into tb_test1 values(:username,:password,:sex)
     * @param paraMap
     * @return
     */
    protected int insertNoKeyForMap(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.update(sql, paraMap);
    }

    /**
     * 更新记录
     *
     * @param sql   eq update tb_test1 set username=:username,sex=:sex,password=:password where id=:id
     * @param model
     * @return
     */
    protected int updateForBean(String sql, BModel model) throws Exception {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(model);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 更新记录
     *
     * @param sql     eq update tb_test1 set username=:username,sex=:sex,password=:password where id=:id
     * @param paraMap
     * @return
     */
    protected int updateForMap(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 删除记录
     *
     * @param sql   eq delete tb_test1 where id=:id
     * @param model
     * @return
     */
    protected int deleteForBean(String sql, BModel model) throws Exception {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(model);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 删除记录
     *
     * @param sql     eq delete tb_test1 where id=:id
     * @param paraMap
     * @return
     */
    protected int deleteForMap(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 根据主键id删除记录
     *
     * @param tname 数据表名称
     * @param id    主键id
     * @return
     */
    protected int delete(String tname, int id) throws Exception {
        String sql = "delete " + tname + " where id=:id";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", id);
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 根据删除记录
     *
     * @param
     * @param
     * @return
     */
    protected int delete(String sql, Map paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.update(sql, ps);
    }

    /**
     * 查询记录
     *
     * @param sql     eq select * from tb_test1 where id=:id
     * @param paraMap
     * @return
     */
    public List<Map<String, Object>> list(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForList(sql, ps);
    }

    /**
     * 查询记录
     *
     * @param sql     eq select * from tb_test1 where id=:id
     * @param paraMap
     * @return
     */
    public SqlRowSet queryForRowSet(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForRowSet(sql, ps);
    }

    /**
     * 查询记录总数
     *
     * @param sql     eq select count(*) from tb_test1 where id=:id
     * @param paraMap
     * @return
     */
    public int count(String sql, Map<String, Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForObject(sql, ps, Integer.class);
    }

    /**
     * 查询记录
     *
     * @param tname 数据表名称
     * @param id    主键id
     * @return
     */
    public Map<String, Object> findOne(String tname, int id) throws Exception {
        String sql = "select * from " + tname + " where t_id=:id";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", id);
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForMap(sql, ps);
    }

    /**
     * 查询记录
     *
     * @param tname 数据表名称
     * @param mcode    主键mcode
     * @return
     */
    public Map<String, Object> findOne(String tname, String mcode) throws Exception {
        String sql = "select * from " + tname + " where mcode=:mcode";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("mcode", mcode);
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForMap(sql, ps);
    }

    public Map<String, Object> findOne(String sql,Map<String,Object> paraMap) throws Exception {
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForMap(sql, ps);
    }

    public Map<String, Object> findAccount(int userId) throws Exception {
        String sql = "select * from account where userId=:userId";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userId", userId);
        SqlParameterSource ps = new MapSqlParameterSource(paraMap);
        return nameJdbcTpl.queryForMap(sql, ps);
    }
}
