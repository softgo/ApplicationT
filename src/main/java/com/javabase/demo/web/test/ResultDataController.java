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


import com.javabase.demo.entity.ResultData;
import com.javabase.demo.service.test.ResultDataService;

/**
 * ResultDataController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/resultData")
public class ResultDataController extends BaseController {
	
	@Autowired
	private ResultDataService resultDataService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addResultData")
    public void addResultData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        ResultData object = resultDataService.saveObject(param);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getResultDataByID")
    public void getResultDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"userCode"); // 根据实际情况填写.
        Object objId = param.get("userCode");
        //根据实际情况去验证 objId 的类型的合法性。
        ResultData object = resultDataService.getObjectByID(objId);
        String resultStr = successResultJSON(object);
        printWriter(response, resultStr);
    }
    
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateResultDataByID")
    public void updateResultDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"userCode"); // 根据实际情况填写.
        Object objId = param.get("userCode");
        //根据实际情况去验证 objId 的类型的合法性。
        resultDataService.updateObjectByID(param, objId);
        printWriter(response, successResultJSON("通过ID="+objId+"修改成功"));
    }
    
    
    /**
     * 通过ID删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delResultDataByID")
    public void delResultDataByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,"userCode"); // 根据实际情况填写.
        Object objId = param.get("userCode");
        //根据实际情况去验证 objId 的类型的合法性。
        resultDataService.deleteObjectByID(objId);
        printWriter(response, successResultJSON("通过ID="+objId+"删除成功"));
    }
    
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getResultDatas")
    public void getResultDatas(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        String resultStr = successResultJSON(resultDataService.getObjects(param));
        printWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getResultDatasByPage")
    public void getResultDatasByPage(HttpServletRequest request, HttpServletResponse response) {
      	Map<String, Object> param = nullAbleValidation(request,null,null); // 根据实际情况填写.
        int pageNo = StringDefaultValue.intValue(param.get(PAGE_NO));
        int pageSize = StringDefaultValue.intValue(param.get(PAGE_SIZE));
        Pagination<ResultData> result = resultDataService.paginationObjects(param, pageNo, pageSize);
        printWriter(response, successResultJSON(result));
    }
    
}


