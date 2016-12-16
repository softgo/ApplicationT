package com.javabase.demo.mongo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javabase.demo.mongo.entity.Mongo;
import com.javabase.demo.mongo.service.MongoService;
import com.application.base.common.BaseController;
import com.application.base.core.constant.Constants;
import com.application.base.mongo.page.PageView;
import com.application.base.utils.common.JSONUtils;
import com.application.base.core.utils.CommonBeanUtils;

/**
 * Controller
 * @author bruce
 */
@Controller
@RequestMapping("/background/mongo/")
public class  MongoController extends BaseController {

	private final static Logger logger= LoggerFactory.getLogger(MongoController.class.getName());
	
	// Servrice start
	@Autowired
	private MongoService  mongoService; 
    
	@RequestMapping("findObjPage")
	public String findObjPage(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params =  nullAbleValidation(request, "");
	    PageView pageView = findByPage(params.get(Constants.SQLConstants.PAGE_NO).toString(),params.get(Constants.SQLConstants.PAGE_SIZE).toString());  
        pageView = mongoService.findObjsByPage(pageView, params);
		return "/background/mongo/list";
	}
	
	@RequestMapping("findObjById")
    public void findObjById(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params =  nullAbleValidation(request, "id");
		Mongo mongo =mongoService.findObjById(params.get("id"));
		//返回.
		ajaxJson(response,JSONUtils.toJson(mongo));
    }
	
	@RequestMapping("findObjByProps")
	public void findObjByProps(HttpServletRequest request,HttpServletResponse response) {
		String data = null;
		try {
    		Map<String, Object> params =  nullAbleValidation(request, "");
            Mongo object = mongoService.findObjByProps(params);
            if (object != null) {
				data = "({msg:'Y',content:'按需求填写!'})";
			}else {
				data = "({msg:'N',content:'按需求填写!'})";
			}
			//返回.
			ajaxJson(response,data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
    @RequestMapping("addObjUI")
    public String addObjUI() {
        return "/background/mongo/add";
    }
	
	@RequestMapping("addObjOne")
	public String addObjOne(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = nullAbleValidation(request, "");
		Mongo mongo = CommonBeanUtils.transMap2Bean(params, Mongo.class);
		mongoService.addObjOne(mongo);
		return "redirect:find.html";
	}
	
	@RequestMapping("deleteObjById")
	public void deleteObjById(HttpServletRequest request,HttpServletResponse response) {
		String data = null;
        try {
        	Map<String, Object> params = nullAbleValidation(request, "id");
            boolean result = mongoService.deleteByObjId(params.get("id"));
            if (result ) {
                data = "({msg:'Y',content:'删除成功!'})";
            }else {
                data = "({msg:'N',content:'删除失败!'})";
            }
            //返回.
            ajaxJson(response,data);
        } catch (Exception e) {
            logger.info("查找出错!"+e.getLocalizedMessage());
        }
	}
	
	@RequestMapping("updateObjById")
	public String updateObjById(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = nullAbleValidation(request, "id");
        mongoService.updateObjOne(params, params.get("id"));
		return "redirect:find.html";
	}
	
}
