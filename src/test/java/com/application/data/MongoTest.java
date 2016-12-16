package com.application.data;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.base.mongo.page.Common;
import com.application.base.mongo.page.PageView;
import com.application.base.utils.test.BaseJunit4Test;
import com.javabase.demo.mongo.service.MongoService;
import com.javabase.demo.mongo.entity.Mongo;

public class MongoTest extends BaseJunit4Test {

	@Autowired
	private MongoService mongoService;

	@Test
	public void deleteOne() {
		this.mongoService.deleteByObjId("57ba5f36e883ec394e8e291b");
		System.out.println("修改完成.");
	}

	@Test
	public void updateOne() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("title", "NewDDBB 教程");
		this.mongoService.updateObjOne(params, "57baf28fe883ec3b88119887");
		System.out.println("修改完成.");
	}

	@Test
	public void addOne() {
		Mongo obj = new Mongo();
		obj.setTitle("NewDBa 教程");
		obj.setDescription("NewDBa 是一个全新的数据库");
		obj.setBy("rockya教程");
		obj.setUrl("www.arocky.com");
		obj.setTags("newdb,newlife,new one");
		obj.setLikes(200+"");
		this.mongoService.addObjOne(obj);
		System.out.println("添加完成.");
	}

	public PageView findPage(String pageNow, String pageSize) {
		PageView pageView = null;

		if ((Common.isEmpty(pageNow)) && (Common.isEmpty(pageSize))) {
			pageView = new PageView(1);
		}
		else if ((!Common.isEmpty(pageNow)) && (Common.isEmpty(pageSize))) {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		else if ((Common.isEmpty(pageNow)) && (!Common.isEmpty(pageSize))) {
			pageView = new PageView(1, Integer.parseInt(pageSize));
		}
		else {
			pageView = new PageView(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
		}
		return pageView;
	}
}