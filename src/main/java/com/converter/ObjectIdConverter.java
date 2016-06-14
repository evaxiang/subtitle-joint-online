package com.converter;


import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

public class ObjectIdConverter implements Converter<String, ObjectId>{

    public static final String DEFAULT_VAL="551a3aef1cb76d2e52efe093";

	@Override
	public ObjectId convert(String str) {
		 if(str == null ||"".equals(str)){
			 return null;
		 }
		 ObjectId res = null;
		 try {
			 res = new ObjectId(str);
		} catch (Exception e) {
			res = null;
			//System.out.println("====error ObjectId======"+str);
			e.printStackTrace();
		}
		return res;
	}

}
