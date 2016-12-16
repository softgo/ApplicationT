package com.javabase.demo.mongo;

import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PageInfosTest {

	public static void main(String[] args) {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] { "classpath:spring/applicationContext.xml" });
		IMongoService service = (IMongoService) ctx.getBean("iMongoService");
		List<Mongo> cities = service.findObjAll();
		System.err.println("size =" + cities.size());
		for (Mongo city : cities) {
			System.err.println("title=" + city.getTitle() + ",description=" + city.getDescription() + ",url=" + city.getUrl());
		}

		Mongo obj = new Mongo();
		obj.setTitle("abc 教程");
		obj.setDescription("abc 是一个全新的数据库");
		obj.setBy("rockya教程");
		obj.setUrl("www.arocky.com");
		obj.setTags(new String[] { "newdb", "newlife", "new one" });
		obj.setLikes(200);
		service.addObjOne(obj);
	}
}
