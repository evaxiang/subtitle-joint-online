package com.converter;

import com.util.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class DateConverter implements Converter<String, Date>{
	
	
	

	public Date convert(String dateStr) {
		
		return DateUtil.getDate(dateStr);
	}
	

}
