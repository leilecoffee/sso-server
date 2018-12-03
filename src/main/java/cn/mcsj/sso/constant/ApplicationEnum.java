package cn.mcsj.sso.constant;

public enum ApplicationEnum {
	SUCCESS(0, "操作成功"),
	FAIL(1, "系统异常，请稍后重试!"),
	PATH_ERROR(1, "访问地址未找到"),
	LOGIN_SUCCESS(0, "登录成功"),
	LOGIN_FAIL(1, "账号或密码错误"),
	UNAUTHORIZED(1, "您没有当前操作的权限！"),
	UNLOGIN(2, "未登录"),
	ROLE_OR_PERMISSION_CHANGED(-1, "当前用户角色或权限发生改变,请重新登录!"),
	SESSION_EXPIRED(-2, "会话过期，请重新登录"),
	LOGOUT(0, "退出成功"),
	REQUEST_INVALID(3, "请求无效"),
	PARAM_ERROR(1002, "参数异常"),
	VERIFY_CODE_ERROR(1003,"验证码错误"),
	EMAIL_SEND_ERROR(1007, "邮件发送失败,请稍后重试"),
	REGISTER_FAIL(1008, "注册失败"),
	EMAIL_VERIFY_CODE_SEND_ERROR(1009, "邮箱验证码发送失败");
	private int code;

	private String message;

	private ApplicationEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
