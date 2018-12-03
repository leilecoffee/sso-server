package cn.mcsj.sso.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.entity.User;

public class ApplicationUtil {

	public static User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Object obj = session.getAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USER);
		User u = (User) SerializeUtil.unserialize(((byte[]) obj));
		if (u == null) {
			throw new UnauthenticatedException();
		}
		return u;
	}

	public static Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession(true);
	}
}
