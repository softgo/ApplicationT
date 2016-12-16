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


import com.javabase.demo.entity.TestMessages;
import com.javabase.demo.service.test.TestMessagesService;

/**
 * TestMessagesController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/testMessages")
public class TestMessagesController extends BaseController {
	
	@Autowired
	private TestMessagesService testMessagesService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestMessages")
    public void addTestMessages(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        TestMessages object = testMessagesService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestMessagesByID")
    public void getTestMessagesByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        TestMessages object = testMessagesService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestMessagesByUUID")
    public void getTestMessagesByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        TestMessages object = testMessagesService.getObjectByUUID(uuid);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestMessagesByID")
    public void updateTestMessagesByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testMessagesService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestMessagesByUUID")
    public void updateTestMessagesByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testMessagesService.updateObjectByUUID(param, uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"修改成功"));
    }
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestMessagesByID")
    public void delTestMessagesByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"id"); // 根据实际情况填写.
        Object objId = param.get("id");
        //根据实际情况去验证 objId 的类型的合法性。
        testMessagesService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    /**
     * 通过UUID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestMessagesByUUID")
    public void delTestMessagesByUUID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"uuid"); // 根据实际情况填写.
        String uuid = param.get("uuid").toString();
        testMessagesService.deleteObjectByUUID(uuid);
        printWriter(response, successResultJSON("通过UUID="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestMessagess")
    public void getTestMessagess(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(testMessagesService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestMessagessByPage")
    public void getTestMessagessByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<TestMessages> result = testMessagesService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


