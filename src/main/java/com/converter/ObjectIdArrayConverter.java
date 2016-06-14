package com.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: UncleYeee
 * Date: 13-4-22
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class ObjectIdArrayConverter implements Converter<String, ObjectId[]> {


    @Override
    public ObjectId[] convert(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        String[] ids = s.split(",");
        ObjectId[] ret = new ObjectId[ids.length];
        for (int i = 0; i < ids.length; i++) {
            try {
                ret[i] = new ObjectId(ids[i]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return ret;
    }
}
