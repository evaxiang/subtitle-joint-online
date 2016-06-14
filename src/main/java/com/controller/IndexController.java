package com.controller;

import com.AjaxData;
import com.dao.DateRecordDao;
import com.dao.LoginDao;
import com.dao.UserDao;
import com.model.DateRecord;
import com.model.Team;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 2016/2/18.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    public static final String SISTER = "1";
    public static final String BROTHER = "0";
    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DateRecordDao dateRecordDao;

    @RequestMapping("")
    public ModelAndView mainPage() {
        return new ModelAndView("index");
    }

    @RequestMapping("login")
    public String login(@ModelAttribute User user,
                              HttpServletRequest req,
                              ModelMap data) throws Exception {
        if (loginDao.login(user)) {
            user =  loginDao.findUser(user.getUsername());
            req.getSession().setAttribute("user", user);
            if (user.getIs_sister().equals(BROTHER)) {
                int weeks = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
                int years = Calendar.getInstance().get(Calendar.YEAR);
                getDateRecord(user, data, weeks, years);
                List<Map<String, Object>> list = userDao.listUnDatedSisters(weeks, years);
                data.put("list", list);
                data.put("user", user);
                return "dateList";
            }else{
                List<Map<String, Object>> list = userDao.listAllBrothers();
                data.put("list", list);
                data.put("user", user);
                return "sis_index";
            }
        } else {
            req.setAttribute("flag","error");
            return "index";
        }
    }

    private void getDateRecord(@ModelAttribute User user, ModelMap data, int weeks, int years) {
        List<Map<String,Object>> dateRecordsList =  dateRecordDao.listDateRecords(user.getId(), weeks, years);
        if (dateRecordsList != null && dateRecordsList.size() != 0) {
            Map<String,Object> dateRecordMap = dateRecordsList.get(0);
            if (!dateRecordMap.isEmpty()) {
                Integer teamId = (Integer) dateRecordMap.get("pair_up_id");
                Integer pairId = (Integer) dateRecordMap.get("id");
                data.put("teamId", teamId);
                data.put("pairId", pairId);
                data.put("isDate", 1);
            }
        }
    }

    @RequestMapping("loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("index");
    }


    @RequestMapping(value = "register")
    public String register(){
        return "register";
    }


    @RequestMapping(value = "doRegister", method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(@ModelAttribute User user) {
        AjaxData ad = new AjaxData();
        try {
            loginDao.register(user);
            ad.success("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            ad.error(e.getMessage());
        }
        return ad.toJson();
    }

    @RequestMapping("modifyUser")
    @ResponseBody
    public void  modifyUser(@ModelAttribute User user) throws Exception {
        loginDao.modifyUseMessage(user);
    }


    @RequestMapping("date/{id}")
    public ModelAndView dateSister(@PathVariable("id") String id,
                                   ModelMap data) {
        Map<String, Object> user = userDao.findById(id);
        data.put("user", user);
        return new ModelAndView("user_details");
    }

    @RequestMapping("dodate")
    @ResponseBody
    public String doDate(DateRecord dateRecord, HttpServletRequest req, ModelMap data) {
        AjaxData ajaxData = new AjaxData();
        User user = (User) req.getSession().getAttribute("user");
        dateRecord.setBro_id(user.getId());
        dateRecord.setWeeks(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        dateRecord.setYears(Calendar.getInstance().get(Calendar.YEAR));
        try {
            dateRecordDao.insertDate(dateRecord);
            ajaxData.success("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxData.error(e.getMessage());
        }

        return ajaxData.toJson();
    }

    @RequestMapping("listTeam")
    public ModelAndView listTeam(HttpServletRequest req,ModelMap data) {
        List<Map<String, Object>> teams = dateRecordDao.listAllTeamMember();
        User user = (User) req.getSession().getAttribute("user");
        int weeks = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int years = Calendar.getInstance().get(Calendar.YEAR);
        getDateRecord(user, data, weeks, years);
        data.put("list", teams);
        data.put("user", user);
        return new ModelAndView("team_list_page");
    }

    @RequestMapping("listSister")
    public ModelAndView listSister( HttpServletRequest req,ModelMap data) {
        User user = (User) req.getSession().getAttribute("user");
        int weeks =  Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int years = Calendar.getInstance().get(Calendar.YEAR);
        List<Map<String, Object>> list = userDao.listUnDatedSisters(weeks, years);
        getDateRecord(user, data, weeks, years);
        data.put("list", list);
        data.put("user", user);

        return new ModelAndView("dateList");
    }

    @RequestMapping("listBrother")
    public ModelAndView listBrother( HttpServletRequest req,ModelMap data) {
        User user = (User) req.getSession().getAttribute("user");
        List<Map<String, Object>> list = userDao.listAllBrothers();
        data.put("list", list);
        data.put("user", user);
        return new ModelAndView("sis_index");
    }


    @RequestMapping("teamDetails")
    public ModelAndView teamDetails(ModelMap data) {
        return new ModelAndView("team_create_page");
    }


    @RequestMapping(value = "createTeam")
    public ModelAndView createTeam(HttpServletRequest request,ModelMap data, String details,String date_time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Team team = new Team();
        team.setDetails(details);
        try {
            Date date = df.parse(date_time);
            team.setDate_time(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int teamId= dateRecordDao.insertPairUp(team);

        User user = (User) request.getSession().getAttribute("user");
        int weeks =  Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int years = Calendar.getInstance().get(Calendar.YEAR);
        List<Map<String,Object>> dateRecordsList =  dateRecordDao.listDateRecords(user.getId(), weeks, years);

        Map<String,Object> dateRecordMap = dateRecordsList.get(0);

        if ( ! dateRecordMap.isEmpty()){
            //date了，还没pair up
            data.put("teamId", teamId);
            data.put("isDate", 1);
        }else{
            //用户还没有date姐妹
            data.put("isDate",0);
        }
        dateRecordDao.updateStatus(Integer.toString(teamId), dateRecordMap.get("id").toString());

        List<Map<String, Object>> teams = dateRecordDao.listAllTeamMember();
        data.put("list", teams);
        data.put("user", user);
        return new ModelAndView("team_list_page");
    }

    @RequestMapping("join")
    public ModelAndView join(@RequestParam(value = "teamId")String teamId,
                             @RequestParam(value = "pairId")String pairId,
                             ModelMap data) {
        dateRecordDao.updateStatus(teamId, pairId);
        data.put("list", dateRecordDao.listTeamMemberStatus(teamId));
        return new ModelAndView("callListPage");
    }

    @RequestMapping("info/{id}")
    public String info(User user, ModelMap data, @PathVariable("id") Integer id){
        if (id != 0){
            User u = loginDao.findUserById(id);
            data.put("user",u);
        }
        return "info";
    }

    @RequestMapping("logout/{id}")
    public String logout(ModelMap data, @PathVariable("id") Integer id,
                         HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return "index";
    }

    @RequestMapping("my_team")
    public String myTeam(ModelMap model, String teamId){
        List<Map<String,Object>> list =  dateRecordDao.listTeamMemberStatus(teamId);
        Map<String,Object> team = dateRecordDao.getTeamById(teamId);

        model.put("list",list);
        model.put("date_time",team.get("date_time"));
        model.put("details", team.get("details"));
        model.put("id", team.get("id"));

        return "my_team";
    }

    @RequestMapping("update_my_team")
    @ResponseBody
    public String UpdateMyTeam(Team team){
        AjaxData ad = new AjaxData();
        boolean success = dateRecordDao.editTeam(team);
        if (success){
            ad.success("");
        }else{
            ad.error("修改出错");
        }
        return ad.toJson();
    }

    @RequestMapping("like")
    public void like(Integer userId,Integer broId){
        dateRecordDao.like(userId,broId);
    }

//    @RequestMapping("exitTeam")
//    public String exitTeam(String teamId){
//        dateRecordDao.exitTeam(teamId);
//        return
//    }

}
