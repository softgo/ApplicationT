package com.application.base.core.apisupport;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.application.base.mongo.page.Common;
import com.application.base.mongo.page.PageView;
import com.application.base.utils.common.JSONUtils;

/**
 * 抽象的结果类.
 * 
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class.getName());

	// 请求信息.
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	// 返回状态.
	public final static String SUCCESS = "success";
	public final static String FAILD = "faild";

	// 返回设置.
	public final static String MSG = "msg";
	public final static String DATA = "data";
	public final static String FLAG = "flag";

	// LoginFlag
	public final static String LOGIN_FLAG = "loginFlag";
	public final static String LOGOUT_FLAG = "logoutFlag";

	@ModelAttribute
	protected void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String address = request.getHeader("x-forwarded-for");
		if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
			address = request.getHeader("Proxy-Client-IP");
		}
		if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
			address = request.getHeader("WL-Proxy-Client-IP");
		}
		if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
			address = request.getRemoteAddr();
		}
		return address;
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * @return
	 */
	public Map<String, Object> getRootMap(HttpServletRequest request) {
		return getQueryString(request);
	}

	public ModelAndView forword(String viewName, Map context) {
		return new ModelAndView(viewName, context);
	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	/**
	 * 
	 * 提示成功信息
	 * 
	 * @param message
	 * 
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message, Object object) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(MSG, message);
		result.put(DATA, object);
		result.put(FLAG, SUCCESS);
		ajaxJson(response, JSONUtils.toJsonWithStringNull2Empty(result));
	}

	/**
	 * 
	 * 提示失败信息
	 * 
	 * @param message
	 * 
	 */
	public void sendFailureMessage(HttpServletResponse response, String message, Object object) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(MSG, message);
		result.put(DATA, object);
		result.put(FLAG, FAILD);
		ajaxJson(response, JSONUtils.toJsonWithStringNull2Empty(result));
	}

	
	protected Map<String, Object> getQueryString(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Enumeration<?> paramNames = request.getParameterNames();
		if (paramNames != null) {
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				if (StringUtils.isNotBlank(paramValue)) {
					logger.info("parameter：--->> " + paramName + "=" + paramValue);
					result.put(paramName, paramValue);
				}
				else {
					String[] paramValues = request.getParameterValues(paramName);
					if (paramValues != null && paramValues.length == 1) {
						paramValue = paramValues[0];
						result.put(paramName, paramValue);
					}
				}
			}
		}
		return result;
	}

	// AJAX输出，返回null
	public void ajax(HttpServletResponse response, String content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			setResponseNoCache(response);
			response.getWriter().write(content);
			response.getWriter().flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// AJAX输出文本，返回null
	public void ajaxText(HttpServletResponse response, String text) {
		ajax(response, text, "text/plain");
	}

	// AJAX输出HTML，返回null
	public void ajaxHtml(HttpServletResponse response, String html) {
		ajax(response, html, "text/html");
	}

	// AJAX输出XML，返回null
	public void ajaxXml(HttpServletResponse response, String xml) {
		ajax(response, xml, "text/xml");
	}

	// 根据字符串输出JSON，返回null
	public void ajaxJson(HttpServletResponse response, String jsonString) {
		ajax(response, jsonString, "application/json");
	}

	// 设置页面不缓存
	public void setResponseNoCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	 /**
     * 底层分页设置方法
     * @param model
     * @param pageNo
     * @param pageSize
     */
	public PageView findByPage(String pageNo,String pageSize ){
		PageView pageView = null;
		//默认显示.
		if(Common.isEmpty(pageNo) && Common.isEmpty(pageSize)){
			pageView = new PageView(1);
		//输入有页数,默认展示条数.
		}else if(!Common.isEmpty(pageNo) && Common.isEmpty(pageSize) ){
			pageView = new PageView(Integer.parseInt(pageNo));
		//设置页数和每页显示的条数.
		}else if(Common.isEmpty(pageNo) && !Common.isEmpty(pageSize)) {
			pageView = new PageView(1,Integer.parseInt(pageSize));
		//设置页数和每页显示的条数.
		}else {
			pageView = new PageView(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		}
		return pageView;
	}
}
