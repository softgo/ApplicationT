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


import com.javabase.demo.entity.TestSchool;
import com.javabase.demo.service.test.TestSchoolService;

/**
 * TestSchoolController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/testSchool")
public class TestSchoolController extends BaseController {
	
	@Autowired
	private TestSchoolService testSchoolService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestSchool")
    public void addTestSchool(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        TestSchool object = testSchoolService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolByID")
    public void getTestSchoolByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        TestSchool object = testSchoolService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolByUUID")
    public void getTestSchoolByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        TestSchool object = testSchoolService.getObjectByUUID(uuid);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestSchoolByID")
    public void updateTestSchoolByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testSchoolService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestSchoolByUUID")
    public void updateTestSchoolByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testSchoolService.updateObjectByUUID(param, uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"修改成功"));
    }
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestSchoolByID")
    public void delTestSchoolByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testSchoolService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    /**
     * 通过UUID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestSchoolByUUID")
    public void delTestSchoolByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testSchoolService.deleteObjectByUUID(uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchools")
    public void getTestSchools(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(testSchoolService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolsByPage")
    public void getTestSchoolsByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<TestSchool> result = testSchoolService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


