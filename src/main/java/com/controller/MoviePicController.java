package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrew on 2016/6/13.
 */
@Controller
@RequestMapping("movie")
public class MoviePicController {

    @RequestMapping("pic")
    public String getPic(){
        return "pic_index";
    }



}
