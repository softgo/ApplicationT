package com.javabase.demo.web.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.base.core.obj.Pagination;
import com.application.base.utils.common.StringDefaultValue;
import com.application.base.common.BaseController;


import com.javabase.demo.entity.TestData;
import com.javabase.demo.service.test.TestDataService;

/**
 * TestDataController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/testData")
public class TestDataController extends BaseController {
	
	@Autowired
	private TestDataService testDataService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestData")
    public void addTestData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        TestData object = testDataService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestDataByID")
    public void getTestDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        TestData object = testDataService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestDataByID")
    public void updateTestDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testDataService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestDataByID")
    public void delTestDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testDataService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestDatas")
    public void getTestDatas(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(testDataService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestDatasByPage")
    public void getTestDatasByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<TestData> result = testDataService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


