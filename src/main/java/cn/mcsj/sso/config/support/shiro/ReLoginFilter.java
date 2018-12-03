package cn.mcsj.sso.config.support.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSON;

import cn.mcsj.sso.constant.ApplicationEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dto.base.ResultVO;

/**
 * 
 * @Description: TODO(角色或权限改变控制类)
 * @author 黄明彪
 * @date 2018年6月21日 上午9:54:28
 *
 */
public class ReLoginFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() != null) {
			Session session = subject.getSession();
			System.out.println(session.getId());
			Object relogin = session.getAttribute(GlobalConstant.ROLE_OR_PERMISSION_CHANGED);
			if (relogin != null) {
				boolean flag = (boolean) relogin;
				if (flag) {
					subject.logout();
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter()
							.print(JSON.toJSONString(new ResultVO(ApplicationEnum.ROLE_OR_PERMISSION_CHANGED)));
					response.flushBuffer();
					response.getWriter().close();
					return false;
				}
			}
		}
		return true;
	}

}
