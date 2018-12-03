package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.mcsj.sso.constant.ApplicationEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dao.UserDao;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqLoginBean;
import cn.mcsj.sso.dto.res.ResLoginBean;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IUserService;
import cn.mcsj.sso.util.ApplicationUtil;
import cn.mcsj.sso.util.SerializeUtil;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private SessionDAO sessionDAO;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User getUserByUsername(String email) {
		Map whereMap = new HashMap();
		whereMap.put("email", email);
		List<User> userList = userDao.list(whereMap);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public ResultVO login(ReqLoginBean loginBean) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String email = loginBean.getUsername();
			String password = loginBean.getPassword();
			if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
				return new ResultVO(ApplicationEnum.PARAM_ERROR);
			}
			Session session = subject.getSession();
			ResLoginBean userInfo = new ResLoginBean();
			// 未认证
			if (!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(email, password);
				subject.login(token);
				// 存放用户信息到session
				User user = getUserByUsername(email);
				// 剔除其他此账号在其它地方登录
				List<Session> loginedList = getLoginedSession(user.getUserId());
				for (Session loginedSession : loginedList) {
					sessionDAO.delete(loginedSession);
				}
				logger.info("写入当前用户到session中!{}", JSON.toJSONString(user));
				session.setAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USER, SerializeUtil.serialize(user));
				userInfo.setName(user.getName());
			} else {
				User user = ApplicationUtil.getCurrentUser();
				userInfo.setName(user.getName());
			}
			logger.info("登录成功,返回用户信息!{}", JSON.toJSONString(userInfo));
			return new ResultVO(true, ApplicationEnum.LOGIN_SUCCESS, userInfo);
		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			return new ResultVO(ApplicationEnum.LOGIN_FAIL);
		}
	}

	// 遍历同一个账户的session
	private List<Session> getLoginedSession(Long userId) {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<Session> loginedList = new ArrayList<Session>();
		for (Session session : sessions) {
			Object obj = session.getAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USER);
			User user = (User) SerializeUtil.unserialize(((byte[]) obj));
			if (user != null) {
				if (userId.equals(user.getUserId())) {
					sessionDAO.delete(session);
					loginedList.add(session);
				}
			}
		}
		return loginedList;
	}

}
