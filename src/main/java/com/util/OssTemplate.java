package com.util;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component("oss")
public class OssTemplate {
   /* private static final String ACCESS_ID = "aJlS29v55SlRZxa3";
    private static final String ACCESS_KEY = "RlkVA5Iga2o0ndvBYQ9lmDYiOnGPVg";
    private static final String OSS_ENDPOINT = "http://oss.aliyuncs.com";
    private static OSSClient client = null;
    private static final String bucket = "byhz";*/
   private static final String ACCESS_ID = "62ZnsvFJ6EnfyFMF";
    private static final String ACCESS_KEY = "cq6QdfpGSaY1y0aKXYylNn78wT963W";
    private static final String OSS_ENDPOINT = "http://oss.aliyuncs.com";
    private static OSSClient client = null;
    private static final String bucket = "atdoctor";

    static {
        // 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
        ClientConfiguration config = new ClientConfiguration();
        client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);
    }

    public String putFile(String fileName, byte[] data) {
        String fileType = getFileType(fileName);
        String key = FileDigest.getFileMD5(data);
        key += fileType;
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(data.length);
        // 可以在metadata中标记文件类型
        objectMeta.setContentType(getMime(fileType));
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        client.putObject(bucket, key, input, objectMeta);

        return key;
    }

    public InputStream getFile(String key) {
        OSSObject obj = client.getObject(bucket, key);
        if (obj == null || obj.getObjectMetadata().getContentLength() == 0) {
            return null;
        }
        return obj.getObjectContent();
    }

    private String getFileType(String fileName) {
        String ret = null;
        if (fileName == null) {
            return null;
        }
        if (fileName.lastIndexOf(".") == -1) {
            ret = fileName;
        } else {
            ret = fileName.substring(fileName.lastIndexOf("."));
        }

        return ret;
    }

    private static Map<String, String> mimeMap = new HashMap<String, String>();

    static {
        mimeMap.put(".gif", "image/gif");
        mimeMap.put(".jpg", "image/jpeg");
        mimeMap.put(".png", "image/png");
        mimeMap.put(".txt", "text/plain");
        mimeMap.put(".3gp", "video/3gpp");
        mimeMap.put(".flv", "video/flv");
        mimeMap.put(".wmv", "video/x-ms-wmv");
        mimeMap.put(".mp3", "audio/x-mpeg");
        mimeMap.put(".wav", "audio/x-wav");
        mimeMap.put(".cab", "application/vnd.ms-cab-compressed");
        mimeMap.put(".sisx", "application/octet-stream");
        mimeMap.put(".apk", "application/octet-stream");
        mimeMap.put(".fun", "application/octet-stream");
        mimeMap.put(".zip", "application/zip");
    }

    public static String getMime(String s) {
        String mime = null;
        if (s.lastIndexOf('.') != -1) {
            mime = mimeMap.get(s.substring(s.lastIndexOf('.')));
        }
        if (mime == null) {
            mime = "application/octet-stream";
        }
        return mime;
    }

}
