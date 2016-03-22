package com;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * Created with IntelliJ IDEA. User: UncleYeee Date: 12-8-1 Time: 上午10:55
 */
public class AjaxData {

	public int status;// 状态 0 : fail ; 1: success

	public String info;// msg 信息

	public Object data;// 业务数据

	public AjaxData() {
		this.status = 0;
		this.info = "fail";
		this.data = null;
	}

	public AjaxData(int status, String info, Object data) {
		this.status = status;
		this.info = info;
		this.data = data;
	}

	public String toJson() {
		DBObject d = new BasicDBObject();
		d.put("status", this.status);
		d.put("info", this.info);
		d.put("data", this.data);
		return JSON.serialize(d);
	}

	public void error(Exception e) {
		error(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
	}

	public void error(String error) {
		this.info = error;
		this.status = -1;
	}

	public void success(String success) {
		this.info = success;
		this.status = 1;
	}

	public void message(String error, String success) {
		if (error == null)
			success(success);
		else
			error(error);
	}

}
