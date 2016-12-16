package com.javabase.demo.mongo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.base.mongo.page.Common;
import com.application.base.mongo.page.PageView;
import com.application.base.utils.test.BaseJunit4Test;

public class MongoTest extends BaseJunit4Test {

	@Autowired
	private IMongoService iMongoService;

	@Test
	public void deleteOne() {
		this.iMongoService.deleteByObjId("57ba5f36e883ec394e8e291b");
		System.out.println("修改完成.");
	}

	@Test
	public void updateOne() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("title", "NewDDBB 教程");
		this.iMongoService.updateObjOne(params, "57baf28fe883ec3b88119887");
		System.out.println("修改完成.");
	}

	@Test
	public void addOne() {
		Mongo obj = new Mongo();
		obj.setTitle("NewDBa 教程");
		obj.setDescription("NewDBa 是一个全新的数据库");
		obj.setBy("rockya教程");
		obj.setUrl("www.arocky.com");
		obj.setTags(new String[] { "newdb", "newlife", "new one" });
		obj.setLikes(200);
		this.iMongoService.addObjOne(obj);
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