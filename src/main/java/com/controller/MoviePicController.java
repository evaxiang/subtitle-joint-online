package com.controller;

import com.dao.UserDao;
import com.model.Picture;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrew on 2016/6/13.
 */
@Controller
@RequestMapping("movie")
public class MoviePicController {

    private static Logger logger = Logger.getLogger(MoviePicController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping("pic")
    public String getPic(){
        return "pic_index";
    }

    @RequestMapping("log")
    public void log(Picture picture) throws Exception {
//        userDao.log(picture);
        logger.info("------"+picture.getName()+"----"+picture.getType()+"------"+picture.getSize());
    }


}
