package com.dao;

import com.model.DateRecord;
import com.model.Team;
import com.wangpos.wcomp.util.ConfUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Andrew on 2016/3/25.
 */
@Repository
public class DateRecordDao extends BaseDao {


    public static final String DEFAULT_USERIMG = "/public/images/user_default.jpg";

    public int insertPairUp(Team pairRecord) {
        try {
            return insertForBean("insert into t_pair_up(date_time,details) values(:date_time,:details)", pairRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int insertDate(DateRecord dr) {
        try {
            return insertForBean("insert into t_date_record(bro_id, sis_id, weeks, years ) values(:bro_id, :sis_id, :weeks ,:years)", dr);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void updateStatus(String teamId, String pairId){
        Map<String,Object> params = new HashMap<>();
        params.put("teamId",teamId);
        params.put("pairId",pairId);
        try {
            updateForMap("update t_date_record set pair_up_id=:teamId , status = 1 where id=:pairId",params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> list() {
        try {
            return list("select * from t_date_record", new HashMap<String, Object>());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Map<String,Object>> listDateRecords(int user_id,int weeks, int years){
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("user_id",user_id);
            params.put("weeks",weeks);
            params.put("years",years);
            return nameJdbcTpl.queryForList("select * from t_date_record where ( bro_id =:user_id or sis_id=:user_id ) and weeks =:weeks and years =:years ", params);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Map<String, Object> getTeamById(String teamId) {
        try {
            return nameJdbcTpl.queryForMap("select * from t_pair_up where status = 1 and id=:id", Collections.singletonMap("id", teamId));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public List<Map<String, Object>> listAllTeamMember() {
        String sql = "select * from t_pair_up ";
        List<Map<String, Object>> teamList = null;
        try {
            teamList = list(sql, new HashMap<String, Object>());
            for (Map teamMap : teamList) {
                int weeks = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
                int years = Calendar.getInstance().get(Calendar.YEAR);
                Integer teamId = (Integer) teamMap.get("id");
                Map teamIdMap = new HashMap();
                teamIdMap.put("teamId", teamId);
                teamIdMap.put("weeks", weeks);
                teamIdMap.put("years", years);


                List<Map<String, Object>> userIdMap = nameJdbcTpl.queryForList(
                        "select bro_id,sis_id " +
                                "from t_date_record " +
                                "where status = 1 and weeks =:weeks and years =:years and t_date_record.pair_up_id =:teamId", teamIdMap);
                List<String> imgList = new ArrayList();
                for (Map<String, Object> idMap : userIdMap) {
                    Map temp = new HashMap();
                    temp.put("bro_id", idMap.get("bro_id"));
                    temp.put("sis_id", idMap.get("sis_id"));
                    try {
                        String img1 = nameJdbcTpl.queryForObject("select img_src from t_user where id=:bro_id", temp, String.class);
                        imgList.add(img1);
                    }catch(Exception e){
                        imgList.add(ConfUtil.getConf("LOCAL_URL")+DEFAULT_USERIMG);
                    }
                    try {
                        String img2 = nameJdbcTpl.queryForObject("select img_src from t_user where id=:sis_id", temp, String.class);
                        imgList.add(img2);
                    }catch(Exception e){
                        imgList.add(ConfUtil.getConf("LOCAL_URL")+DEFAULT_USERIMG);
                    }
                }
                teamMap.put("list", imgList);
            }
            return teamList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Map<String, Object>> listTeamMemberStatus(String teamId) {
        Map teamIdMap = new HashMap();
        List result = new ArrayList();
        teamIdMap.put("teamId", teamId);
        try {
            List<Map<String, Object>> userIdMap = list("select bro_id,sis_id from t_date_record where t_date_record.pair_up_id =:teamId", teamIdMap);
            List<String> imgList = new ArrayList();
            for (Map<String, Object> idMap : userIdMap) {
                Map temp = new HashMap();
                temp.put("bro_id", idMap.get("bro_id"));
                Map<String, Object> broInfoMap = nameJdbcTpl.queryForMap("select img_src,mobile from t_user where id=:bro_id", temp);
                temp.put("sis_id", idMap.get("sis_id"));
                Map<String, Object> sisInfoMap = nameJdbcTpl.queryForMap("select img_src,mobile from t_user where id=:sis_id", temp);
                result.add(broInfoMap);
                result.add(sisInfoMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean editTeam(Team pairRecord) {
        try {
            updateForBean("update t_pair_up set date_time=:date_time , details=:details where id=:id", pairRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void like(Integer from_user_id, Integer to_user_id) {
        String sql = "insert into t_like (from_user_id, to_user_id) values(:from_user_id, :to_user_id) ";
        Map temp = new HashMap();
        temp.put("from_user_id",from_user_id);
        temp.put("to_user_id",to_user_id);
        nameJdbcTpl.update(sql,temp);
    }
}
