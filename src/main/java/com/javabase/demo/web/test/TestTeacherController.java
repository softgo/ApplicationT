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


import com.javabase.demo.entity.TestTeacher;
import com.javabase.demo.service.test.TestTeacherService;

/**
 * TestTeacherController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/testTeacher")
public class TestTeacherController extends BaseController {
	
	@Autowired
	private TestTeacherService testTeacherService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestTeacher")
    public void addTestTeacher(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        TestTeacher object = testTeacherService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeacherByID")
    public void getTestTeacherByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        TestTeacher object = testTeacherService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeacherByUUID")
    public void getTestTeacherByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        TestTeacher object = testTeacherService.getObjectByUUID(uuid);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestTeacherByID")
    public void updateTestTeacherByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testTeacherService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestTeacherByUUID")
    public void updateTestTeacherByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testTeacherService.updateObjectByUUID(param, uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"修改成功"));
    }
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestTeacherByID")
    public void delTestTeacherByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testTeacherService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    /**
     * 通过UUID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestTeacherByUUID")
    public void delTestTeacherByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testTeacherService.deleteObjectByUUID(uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeachers")
    public void getTestTeachers(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(testTeacherService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeachersByPage")
    public void getTestTeachersByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<TestTeacher> result = testTeacherService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


