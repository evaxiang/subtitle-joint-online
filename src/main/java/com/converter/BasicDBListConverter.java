package com.converter;

import com.mongodb.BasicDBList;
import com.mongodb.util.JSON;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by IntelliJ IDEA
 * User: Sirius
 * Date: 2015/4/9
 * Time: 17:40
 */
public class BasicDBListConverter implements Converter<String,BasicDBList> {
    @Override
    public BasicDBList convert(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        try {
            return (BasicDBList) JSON.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
