package com.sss.utils;

//统一结果返回的json
public class JsonResult {
	private boolean status;// 状态
	private String msg;// 描述信息
	private Object data;// 返回数据

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	// 成功
	public static JsonResult setOK(Object data, String msg) {
		JsonResult jr = new JsonResult();
		jr.setStatus(true);
		jr.setMsg(msg);
		jr.setData(data);
		return jr;
	}

	//
	public static JsonResult setERROR(Object data, String msg) {
		JsonResult jr = new JsonResult();
		jr.setStatus(false);
		jr.setMsg(msg);
		jr.setData(data);
		return jr;
	}
}
