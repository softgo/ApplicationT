package com.javabase.demo.constant;

import com.application.base.core.constant.Constants;
import com.application.base.core.result.ResultData;

/**
 * 常量类
 * @author rocky
 */
public class Constant extends Constants{
	
    public static String TIME_OUT = "10204";

    @SuppressWarnings("rawtypes")
	public static ResultData SESSION_TIMEOUT = new ResultData(TIME_OUT,"登陆超时");

}
