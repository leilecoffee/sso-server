package cn.mcsj.sso.dto.base;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

import cn.mcsj.sso.constant.ApplicationEnum;

public class ResultVO {
	@JSONField(ordinal = 1)
	private boolean status = true;
	@JSONField(ordinal = 2)
	private int code = 0;
	@JSONField(ordinal = 3)
	private String msg = "success";
	@JSONField(ordinal = 4)
	@SuppressWarnings("rawtypes")
	private Object data = new ArrayList();

	public ResultVO() {
		code = ApplicationEnum.SUCCESS.getCode();
		msg = ApplicationEnum.SUCCESS.getMessage();
	}

	public ResultVO(ApplicationEnum messageEnum) {
		this.status = false;
		this.code = messageEnum.getCode();
		this.msg = messageEnum.getMessage();
	}

	public ResultVO(ApplicationEnum messageEnum, String msg) {
		this.status = false;
		this.code = messageEnum.getCode();
		this.msg = msg;
	}

	public ResultVO(boolean status, ApplicationEnum messageEnum) {
		this.status = status;
		this.code = messageEnum.getCode();
		this.msg = messageEnum.getMessage();
	}

	public ResultVO(boolean status, ApplicationEnum messageEnum, Object data) {
		this.status = status;
		this.code = messageEnum.getCode();
		this.msg = messageEnum.getMessage();
		this.data = data;
	}

	public ResultVO(Object data) {
		if (data != null)
			this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

}
