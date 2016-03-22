package com.controller;

import com.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Andrew on 2016/2/18.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private LoginDao loginDao;

    @RequestMapping("")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam("name") String name,
                              @RequestParam("password") String password) throws Exception {
        if (loginDao.login(name,password)){
            return new ModelAndView("main");
        }else{
            return new ModelAndView("error");
        }
    }

    @RequestMapping("loginPage")
    public ModelAndView loginPage(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public void register(@RequestParam("username") String name,
                           @RequestParam("password") String password){
        loginDao.register(name,password);
    }

}
