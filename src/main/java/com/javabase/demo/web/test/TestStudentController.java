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


import com.javabase.demo.entity.TestStudent;
import com.javabase.demo.service.test.TestStudentService;

/**
 * TestStudentController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/testStudent")
public class TestStudentController extends BaseController {
	
	@Autowired
	private TestStudentService testStudentService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestStudent")
    public void addTestStudent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        TestStudent object = testStudentService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudentByID")
    public void getTestStudentByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        TestStudent object = testStudentService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudentByUUID")
    public void getTestStudentByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        TestStudent object = testStudentService.getObjectByUUID(uuid);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestStudentByID")
    public void updateTestStudentByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testStudentService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestStudentByUUID")
    public void updateTestStudentByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testStudentService.updateObjectByUUID(param, uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"修改成功"));
    }
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestStudentByID")
    public void delTestStudentByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testStudentService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    /**
     * 通过UUID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestStudentByUUID")
    public void delTestStudentByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testStudentService.deleteObjectByUUID(uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudents")
    public void getTestStudents(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(testStudentService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudentsByPage")
    public void getTestStudentsByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<TestStudent> result = testStudentService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


