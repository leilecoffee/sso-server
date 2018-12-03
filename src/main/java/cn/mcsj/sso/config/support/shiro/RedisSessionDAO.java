package cn.mcsj.sso.config.support.shiro;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import cn.mcsj.sso.constant.GlobalConstant;

/**
* @Description:redis的session维护
* @author xiewm
* @date 2017年12月4日 下午3:00:15
* 
*/
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	// 创建session，保存到数据库
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		logger.debug("创建session:{}", session.getId());
		redisTemplate.opsForValue().set(GlobalConstant.SHIRO_SESSION_PREX + sessionId.toString(), session);
		return sessionId;
	}

	// 获取session
	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("获取session:{}", sessionId);
		// 先从缓存中获取session，如果没有再去数据库中获取
		Session session = super.doReadSession(sessionId);
		if (session == null) {
			session = (Session) redisTemplate.opsForValue()
					.get(GlobalConstant.SHIRO_SESSION_PREX + sessionId.toString());
		}
		return session;
	}

	// 更新session的最后一次访问时间
	@Override
	protected void doUpdate(Session session) {
		logger.debug("=====更新session:{}", session.getId());
		super.doUpdate(session);
		String key = GlobalConstant.SHIRO_SESSION_PREX + session.getId().toString();
		if (!redisTemplate.hasKey(key)) {
			redisTemplate.opsForValue().set(key, session);
		}
		redisTemplate.expire(key, GlobalConstant.SHIRO_SESSION_EXPIRETIME, TimeUnit.SECONDS);
	}

	// 删除session
	@Override
	protected void doDelete(Session session) {
		logger.debug("删除session:{}", session.getId());
		super.doDelete(session);
		// String username = ApplicationUtil.getUsername(session);
		// 删除用户会话
		redisTemplate.delete(GlobalConstant.SHIRO_SESSION_PREX + session.getId().toString());
		// 删除缓存的用户会话对应的session
		redisTemplate.delete(GlobalConstant.SHIRO_CACHE_PREX + GlobalConstant.SHIRO_ACTIVE_SESSIONS_CACHE_NAME + ":"
				+ session.getId().toString());
		// 删除缓存的认证信息
		// redisTemplate.delete(GlobalConstant.SHIRO_CACHE_PREX +
		// GlobalConstant.SHIR_AUTHENTICATION_CACHE_NAME + ":" + username);
		// 删除缓存的权限信息
		// redisTemplate.delete(GlobalConstant.SHIRO_CACHE_PREX +
		// GlobalConstant.SHIR_AUTHORIZATION_CACHE_NAME + ":" + username);
	}

}
