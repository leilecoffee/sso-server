package cn.mcsj.sso.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String email;
	private String password;
	private String salt;
	private String name;
	private String phone;
	private Integer isDelete;
	private Date createTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCredentialsSalt() {
		// SecureRandomNumberGenerator
		return this.email + this.salt;
	}
}