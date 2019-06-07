package com.example.craily.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.example.craily.po.Emp;

public class UserFilter extends AccessControlFilter {

	private String loginUrl = "/login/index";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO 是否允许访问，mappedValue 就是[urls]配置中拦截器参数部分
		Subject subject = getSubject(request, response);
		if (subject.getSession(false) == null) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO 表示拒绝访问时是否自己处理，true表示自己不处理且继续拦截器链执行，false表示自己已经处理了
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if(isAjaxRequest(httpServletRequest)) {
			httpServletResponse.setHeader("session_status", "time_out");
			httpServletResponse.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
			httpServletResponse.setContentType("application/json;charset=UTF-8");
		}else {
			WebUtils.issueRedirect(request, response, loginUrl);
		}
		return false;
	}

	public static boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
		String header = httpServletRequest.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		}
		return false;
	}

}
