package com.controller;

import com.AjaxData;
import com.dao.MovieDao;
import com.model.Picture;
import com.util.OssTemplate;
import com.wangpos.wcomp.page.BootPage;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Andrew on 2016/6/13.
 */
@Controller
@RequestMapping("movie")
public class MoviePicController {

    private static Logger logger = Logger.getLogger(MoviePicController.class);

    @Resource(name = "oss")
    private OssTemplate ossTemplate;

    @Autowired
    private MovieDao movieDao;

    @RequestMapping("")
    public String index(){
        return "pic_index";
    }

    @RequestMapping("pic")
    public void getPic(HttpServletResponse response) throws IOException {
        response.sendRedirect("");
    }

    @RequestMapping("log")
    public void log(Picture picture) throws Exception {
        logger.info("------" + picture.getName() + "----" + picture.getType() + "------" + picture.getSize());
    }

    @RequestMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, String img,
                         @RequestParam(value = "needDownload" , defaultValue = "") String needDownload) throws Exception {
        AjaxData ajaxData = new AjaxData();
        String encodingPrefix = "base64,";
        int contentStartIndex = img.indexOf(encodingPrefix) + encodingPrefix.length();
        byte[] imageData = Base64.decodeBase64(img.substring(contentStartIndex));
        String filename = new Random().nextInt()+".jpg";
        String resId = ossTemplate.putFile(filename, imageData);
        String imgSrc = FileUpload.resUrl + resId;
        logger.info("========imgSrc========" + imgSrc);

        Map<String,Object> params = new HashMap<>();
        params.put("userId",0);
        params.put("img_src",imgSrc);

        movieDao.saveScreen(params);

        ajaxData.success("上传成功");
//        if(needDownload.equals("true")){
//            response.setContentType("image/jpeg");
//            response.setHeader("Content-Disposition", "attachment; filename=icon" + ".jpg");
//            response.getOutputStream().write(imageData);
//            response.getOutputStream().close();
//            response.getOutputStream().flush();
//        }

        return ajaxData.toJson();
    }

    @RequestMapping("download")

    public byte[] download(HttpServletRequest request, HttpServletResponse response, String img,
                         @RequestParam(value = "needDownload" , defaultValue = "") String needDownload) throws IOException {
        AjaxData ajaxData = new AjaxData();
        String encodingPrefix = "base64,";
        int contentStartIndex = img.indexOf(encodingPrefix) + encodingPrefix.length();
        byte[] imageData = Base64.decodeBase64(img.substring(contentStartIndex));
        return imageData;
    }


    @RequestMapping("listAllMoviePic")
    public String getPicList(@RequestParam(value = "pageIndex", defaultValue = "1", required = false) int startIndex,
                             @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize,
                             HttpServletRequest req,HttpServletResponse resp,
                             ModelMap data){
        Map params = new HashMap();
        params.put("size",pageSize);
        params.put("pageIndex",startIndex);
        try {
            Map result  =  movieDao.listAllScreenShot(params);
            Integer count = (Integer)result.get("count");
            List<Map<String,Object>> picList = (List)result.get("list");
            data.put("list",picList);
            data.put("count",count);
            BootPage page = new BootPage(count, startIndex, pageSize, req);
            data.put("page", page.show());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pic_list";
    }

    @RequestMapping("last15")
    @ResponseBody
    public AjaxData last15(){
        AjaxData ajaxData = new AjaxData();


        return ajaxData;
    }

}
