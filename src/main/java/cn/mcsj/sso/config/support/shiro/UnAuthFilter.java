package cn.mcsj.sso.config.support.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSONObject;

import cn.mcsj.sso.constant.ApplicationEnum;
import cn.mcsj.sso.dto.base.ResultVO;

/**
 * 
 * @Description: TODO(角色或权限改变控制类)
 * @author 黄明彪
 * @date 2018年6月21日 上午9:54:28
 *
 */
public class UnAuthFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setContentType("application/json");
			httpServletResponse.getWriter().write(JSONObject.toJSON(new ResultVO(ApplicationEnum.UNLOGIN)).toString());
			return false;
		}
		return true;
	}

}
