package com.controller;

import cn.shellinfo.cn.w.common.AjaxData;
import cn.shellinfo.cn.w.common.ConfUtil;
import cn.shellinfo.cn.w.common.util.FileUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.util.OssTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传
 * Created by xiek on 2014/4/23.
 */
@Controller
@RequestMapping("/file")
public class FileUpload {
    private static Logger logger = Logger.getLogger(FileUpload.class);

    @Resource(name = "oss")
    private OssTemplate ossTemplate;

    private static String extName = "gif,jpg,jpeg,png,bmp"; //扩展名

    @RequestMapping("/res/{resId:.*}")
    public void res(@PathVariable String resId, HttpServletRequest req, HttpServletResponse resp) {
        OutputStream os = null;
        resp.reset();
        resp.setHeader("Content-Disposition", "attachment; filename=" + resId);
        resp.setContentType("application/octet-stream; charset=utf-8");
        try {
            os = resp.getOutputStream();
            InputStream in = ossTemplate.getFile(resId);
            if (in != null) {
                byte[] buf = new byte[16384];
                int n = 0;
                ByteArrayOutputStream baos = new ByteArrayOutputStream(8192);
                while ((n = in.read(buf)) > 0) {
                    baos.write(buf, 0, n);
                }
                os.write(baos.toByteArray());
                os.flush();
            } else {
                logger.info("文件：" + resId + " 未找到！");
            }
        } catch (IOException e) {
            logger.error("文件：" + resId + " 读取出错！", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @ResponseBody
    @RequestMapping("upload")
    public AjaxData uploadV(MultipartHttpServletRequest request) throws IOException {
        AjaxData ajaxData = new AjaxData();
        MultipartFile mf = request.getFile("myFile");
        String filename = mf.getOriginalFilename();
        String fileExt = FileUtil.getExtensionName(filename);
        byte[] buf = mf.getBytes();

        logger.info("========filename========" + filename + "========length========" + buf.length);

        if(buf == null){
            ajaxData.info = "文件为空";
            ajaxData.status = -2;
            return ajaxData;
        }
        String resId = ossTemplate.putFile(filename, buf);
        DBObject ret = new BasicDBObject("v_url", ConfUtil.getConf("resUrl") + resId).append("v_length", buf.length);
        ajaxData.data = ret;
        ajaxData.status = 1;
        ajaxData.info = "上传成功";
        return ajaxData;
    }

    private String getError(String message) {
        DBObject obj = new BasicDBObject();
        obj.put("error", 1);
        obj.put("message", message);
        return JSON.serialize(obj);
    }
}
