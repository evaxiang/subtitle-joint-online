package com.converter;


import com.util.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Calendar;
import java.util.Date;

public class CalenderConverter implements Converter<String, Calendar> {


	public Calendar convert(String dateStr) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		Date date = null;
		Calendar c = Calendar.getInstance();
		date = DateUtil.getDate(dateStr);
		c.setTime(date);
		return c;
	}

}
