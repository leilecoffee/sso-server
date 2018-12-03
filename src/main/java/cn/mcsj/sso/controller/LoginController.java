package cn.mcsj.sso.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.config.support.log.annotation.SysLog;
import cn.mcsj.sso.constant.ApplicationEnum;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqLoginBean;
import cn.mcsj.sso.service.IUserService;

/**
 * 
 * @Description: 用户controller
 * @author admin
 * @date 2018年11月21日 下午5:00:35
 *
 */
@RestController
public class LoginController {
	@Autowired
	private IUserService userService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SysLog(operation = "登录")
	@PostMapping("/login")
	public ResultVO login(@Valid @RequestBody ReqLoginBean loginBean) {
		return userService.login(loginBean);
	}

	/**
	 * 注销
	 * 
	 * @return void
	 */
	@PostMapping("/logout")
	public ResultVO logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return new ResultVO(true, ApplicationEnum.LOGOUT);
	}

}
