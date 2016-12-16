package com.application.log;

import org.junit.Test;

import com.application.base.core.apisupport.BaseResultSupport;
import com.application.base.core.result.ResultData;
import com.application.base.utils.LOGBackUtils;
import com.application.base.utils.common.JSONUtils;
import com.application.base.utils.test.BaseJunit4Test;
import com.javabase.demo.dao.test.TestStudentDao;
import com.javabase.demo.dao.test.impl.TestTeacherDaoImpl;
import com.javabase.demo.entity.TestTeacher;
import com.javabase.demo.service.test.TestStudentService;

  
public class TestLog extends BaseJunit4Test{

	@Test
	public void test1() {
		ResultData res =new ResultData("1","test message");
        res.setResult("qqqqbbaa");
        String resultJson = JSONUtils.toJson(res, false);
        LOGBackUtils.logInfo(BaseResultSupport.class, "successResultJSON", "125", "调用接口成功，返回JSON数据 resultJson:["+resultJson+"]");
        
		System.out.println("======================================================================");
		System.out.println("======================================================================");
		System.out.println("======================================================================");
		LOGBackUtils.logTrace(TestStudentDao.class,null,null,"1234567");
		LOGBackUtils.logDebug(TestLog.class,null,null,"1234567");
		LOGBackUtils.logInfo(TestTeacher.class,null,null,"1234567");
		LOGBackUtils.logWarn(TestStudentService.class,null,null,"1234567");
		LOGBackUtils.logError(TestTeacherDaoImpl.class,null,null,"1234567");
	}
}
