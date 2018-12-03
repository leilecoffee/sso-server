package cn.mcsj.sso.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @Description: TODO(描述类)
 * @author admin
 * @date 2018年11月21日 下午5:01:57
 *
 */
public class ReqLoginBean {
	@NotBlank(message="{user.username.notBlank}")
	private String username;

	@NotBlank(message="{user.password.notBlank}")
	private String password;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
