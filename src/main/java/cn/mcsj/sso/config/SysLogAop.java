package cn.mcsj.sso.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mcsj.sso.config.support.log.annotation.SysLog;

public class SysLogAop {

	@Autowired

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("@annotation(sysLog)")
	public void before(JoinPoint point, SysLog sysLog) {
	}

	@After("@annotation(sysLog)")
	public void after(JoinPoint point, SysLog sysLog) {

		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 获取切入点所在的方法
		Method method = signature.getMethod();

		logger.info("aop 日志记录");
	}
}
