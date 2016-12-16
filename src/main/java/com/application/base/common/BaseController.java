package com.application.base.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.base.core.apisupport.BaseResultSupport;
import com.application.base.core.constant.Constants;
import com.application.base.core.exception.BusinessException;
import com.application.base.mongo.page.Common;
import com.application.base.mongo.page.PageView;
import com.application.base.utils.common.ExceptionInfo;
import com.application.base.utils.common.JSONUtils;
import com.application.base.utils.common.StringDefaultValue;
import com.javabase.demo.exception.InfoEmptyException;
import com.javabase.demo.exception.ValideErrorException;

/**
 * 基础控制Controller
 * @author rocky
 */
public class BaseController extends BaseResultSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    protected String PAGE_NO = "pageNo";
    protected String PAGE_SIZE = "pageSize";

    /**
     * 对外打印JSON数据
     *
     * @param response
     * @param data
     */
    protected void printWriter(HttpServletResponse response, String data) {
        setBasicResponseHeader(response); //设置响应头
        response.setContentType("application/json;charset=UTF-8");
        try {
            write(data, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置响应头
     *
     * @param response
     */
    protected void setBasicResponseHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 数据打印功能
     *
     * @param data
     * @param response
     * @throws IOException
     */
    private void write(String data, HttpServletResponse response) throws IOException {
        setBasicResponseHeader(response); //设置响应头
    	PrintWriter printWriter = response.getWriter();
        printWriter.write(data);
        printWriter.flush();
        printWriter.close();
    }

    public Map<String, Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        // 处理分页参数
        String pageNoStr = request.getParameter(Constants.SQLConstants.PAGE_NO);
        String pageSizeStr = request.getParameter(Constants.SQLConstants.PAGE_SIZE);
        // 起始页
        int pageNo = StringDefaultValue.intValue(pageNoStr) == 0 ? DEFAULT_PAGE_NO : StringDefaultValue.intValue(pageNoStr) ;
        // 每页显示的条数
        int pageSize = StringDefaultValue.intValue(pageSizeStr) == 0 ? DEFAULT_PAGE_SIZE : StringDefaultValue.intValue(pageSizeStr);
        
        paramsMap.put(Constants.SQLConstants.PAGE_NO, pageNo);
        paramsMap.put(Constants.SQLConstants.PAGE_SIZE, pageSize);

        Enumeration<?> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = StringDefaultValue.StringValue(parameterNames.nextElement());
            try {
                String value = null;
                String[] values = request.getParameterValues(name);
                if (values != null && values.length == 1) {
                    value = values[0];
                    paramsMap.put(name, value.trim());
                }
            } catch (Exception e) {
                logger.error("获取页面参数时异常，参数名称【{}】异常信息【{}】", name, e);
            }
        }

        return paramsMap;
    }

    /**
     * Controller 全局异常处理
     *
     * @param response
     * @param e
     */
    @ExceptionHandler
    public void exception(HttpServletResponse response, Exception e) {
        // 添加自己的异常处理逻辑，如日志记录
        if (!(e instanceof BusinessException)) {
            e.printStackTrace();
            ExceptionInfo.exceptionInfo(e, logger);
            e = new BusinessException(e);
        }
        printWriter(response, resultJSON((BusinessException) e));
    }

    /**
     * 非空验证统一方法
     * @param request
     * @param args
     */
    protected Map<String ,Object> nullAbleValidation(HttpServletRequest request, String... args) {
        Map<String, Object> params = getRequestParams(request);
        if (args == null || args.length == 0)
            throw new InfoEmptyException();
        for(String key:args){
            if(StringUtils.isEmpty(params.get(key)))
                throw new InfoEmptyException();
        }
        return params;
    }

    protected Object validation(HttpServletRequest request, Validator validator) {
        Map<String, Object> param = getRequestParams(request);
        return validator.valid(param);
    }

    protected interface Validator {
        Object valid(Map<String, Object> map);
    }

    /**
     * id校验
     *
     * @author rocky
     */
    public class IdValid implements Validator {
        public Object valid(Map<String, Object> map) {
            if (StringUtils.isEmpty(map.get("id")))
                throw new InfoEmptyException();
            int id;
            try {
                id = StringDefaultValue.intValue(map.get("id"));
            } catch (Exception e) {
               throw new ValideErrorException();
            }
            return id;
        }

        public int getValidId(Map<String, Object> map) {
            return StringDefaultValue.intValue(valid(map));
        }
    }

    /**
     * uuid校验
     *
     * @author rocky
     */
    public class UUIDValid implements Validator {
        public Object valid(Map<String, Object> map) {
            if (StringUtils.isEmpty(map.get("uuid")))
                throw new InfoEmptyException();
            return StringDefaultValue.StringValue(map.get("uuid"));
        }

        public String getValidUUID(Map<String, Object> map) {
            return (String) valid(map);
        }
    }

    /**
     * trim value
     * @param map
     * @param key
     * @return
     */
    public Object getMapVal(Map<String, Object> map, String key) {
        Object object = map.get(key);
        if (object instanceof String) {
            if (object == null || "".equals(object)) {
                return "";
            } else {
                return ((String) object).trim();
            }
        }
        return object;
    }
    
    
	/**
	 * 请求参数的打印.
	 * @param request
	 */
	private void printRequestParams(HttpServletRequest request) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Enumeration<?> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = StringDefaultValue.StringValue(parameterNames.nextElement());
			try {
				String value = null;
				String[] values = request.getParameterValues(name);
				if (values != null && values.length == 1) {
					value = values[0];
					paramsMap.put(name, value);
				}
			} catch (Exception e) {
				logger.error("获取页面参数时异常，参数名称【{}】异常信息【{}】", name, e);
			}
		}
		logger.debug("\r\n— — — 请求url：【" + request.getRequestURL() + "】 \r\n— — — 参数：【" + JSONUtils.toJson(paramsMap) + "】");
	}
 	
}
