package cn.mcsj.sso.config.support.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import cn.mcsj.sso.entity.Permission;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IPermissionService;
import cn.mcsj.sso.service.IUserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	private IUserService userService;

	@Autowired
	@Lazy
	private IPermissionService permissionService;

	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("#######################执行权限认证#######################");
		String username = (String) principals.getPrimaryPrincipal();
		User user = userService.getUserByUsername(username);
		List<Permission> list = permissionService.getPermissionByUserId(user.getUserId());
		List<String> permissions = new ArrayList<>();
		for (Permission p : list) {
			if (!StringUtils.isEmpty(p.getCode())) {
				permissions.add(p.getCode());
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		logger.info("#######################执行用户认证#######################");
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 查出是否有此用户
		String username = token.getUsername();
		User user = userService.getUserByUsername(username);
		logger.info("#######################查询用户信息#######################");
		if (user != null) {
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			return new SimpleAuthenticationInfo(username, user.getPassword(),
					ByteSource.Util.bytes(user.getCredentialsSalt()), getName());// salt = email+salt
		}
		return null;
	}

	public void clearCacheAuthentizationInfo() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCachedAuthorizationInfo(principals);
	}

	public void clearCacheAuthenticationInfo() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCachedAuthenticationInfo(principals);
	}

	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	public void clearAllCacheAuthentizationInfo() {
		super.getAuthorizationCache().clear();
	}

	public void clearAllCacheAuthenticationInfo() {
		super.getAuthenticationCache().clear();
	}

	public void clearAllCached() {
		clearAllCacheAuthentizationInfo();
		clearAllCacheAuthenticationInfo();
	}
}
