package com.javabase.demo.mongo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javabase.demo.mongo.entity.Mongo;
import com.javabase.demo.mongo.dao.MongoDao;
import com.javabase.demo.mongo.service.MongoService;
import com.application.base.mongo.page.PageView;

/**
 * 接口定义实现
 * @author bruce
 */
@Service("mongoService")
public class MongoServiceImpl implements MongoService {

	@Autowired
	private MongoDao mongoDao;

	public Mongo findObjById(Object id) {
		return mongoDao.findObjById(id);
	}

	public Mongo findObjByName(String proKey, String proValue) {
		return mongoDao.findObjByName(proKey, proValue);
	}

	public Mongo findObjByProps(Map<String, Object> params) {
		return mongoDao.findObjByProps(params);
	}

	public List<Mongo> findObjList(Map<String, Object> params) {
		return mongoDao.findObjList(params);
	}

	public PageView findObjsByPage(PageView pageView, Map<String, Object> params) {
		return mongoDao.findObjsByPage(pageView, params);
	}

	public List<Mongo> findObjAll() {
		return mongoDao.findObjAll();
	}

	public boolean addObjOne(Mongo obj) {
		return mongoDao.addObjOne(obj);
	}

	public boolean addObjAll(List<Mongo> ts) {
		return mongoDao.addObjAll(ts);
	}

	public int updateObjOne(Map<String, Object> params, Object id) {
		return mongoDao.updateObjOne(params, id);
	}

	public boolean updateObjAll(List<Map<String, Object>> ts, List<Object> ids) {
		return mongoDao.updateObjAll(ts, ids);
	}

	public boolean deleteByObjId(Object id) {
		return mongoDao.deleteByObjId(id);
	}

	public boolean deleteAll(List<Mongo> ts) {
		return mongoDao.deleteAll(ts);
	}

	public long getObjsCount() {
		return mongoDao.getObjsCount();
	}

	public long getObjsByProsCount(Map<String, Object> params) {
		return mongoDao.getObjsByProsCount(params);
	}

	public Mongo findObjByProps(Mongo t) {
		return mongoDao.findObjByProps(t);
	}

	public List<Mongo> findObjList(Mongo t) {
		return mongoDao.findObjList(t);
	}

	public PageView findObjsByPage(PageView pageView, Mongo t) {
		return mongoDao.findObjsByPage(pageView, t);
	}

	public int updateObjOne(Mongo t, Object id) {
		return mongoDao.updateObjOne(t, id);
	}

	public long getObjsByProsCount(Mongo t) {
		return mongoDao.getObjsByProsCount(t);
	}

}
