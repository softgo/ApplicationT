package com.javabase.demo.mongo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.base.mongo.page.PageView;

@Service("iMongoService")
public class MongoServiceImpl implements IMongoService {

	@Autowired
	private IMongoDao iMongoDao;

	public Mongo findObjById(Object id) {
		return iMongoDao.findObjById(id);
	}

	public Mongo findObjByName(String proKey, String proValue) {
		return iMongoDao.findObjByName(proKey, proValue);
	}

	public Mongo findObjByProps(Map<String, Object> params) {
		return iMongoDao.findObjByProps(params);
	}

	public List<Mongo> findObjList(Map<String, Object> params) {
		return iMongoDao.findObjList(params);
	}

	public PageView findObjsByPage(PageView pageView, Map<String, Object> params) {
		return iMongoDao.findObjsByPage(pageView, params);
	}

	public List<Mongo> findObjAll() {
		return iMongoDao.findObjAll();
	}

	public boolean addObjOne(Mongo obj) {
		return iMongoDao.addObjOne(obj);
	}

	public boolean addObjAll(List<Mongo> ts) {
		return iMongoDao.addObjAll(ts);
	}

	public int updateObjOne(Map<String, Object> params, Object id) {
		return iMongoDao.updateObjOne(params, id);
	}

	public boolean updateObjAll(List<Map<String, Object>> ts, List<Object> ids) {
		return iMongoDao.updateObjAll(ts, ids);
	}

	public boolean deleteByObjId(Object id) {
		return iMongoDao.deleteByObjId(id);
	}

	public boolean deleteAll(List<Mongo> ts) {
		return iMongoDao.deleteAll(ts);
	}

	public long getObjsCount() {
		return iMongoDao.getObjsCount();
	}

	public long getObjsByProsCount(Map<String, Object> params) {
		return iMongoDao.getObjsByProsCount(params);
	}

	public Mongo findObjByProps(Mongo t) {
		return iMongoDao.findObjByProps(t);
	}

	public List<Mongo> findObjList(Mongo t) {
		return iMongoDao.findObjList(t);
	}

	public PageView findObjsByPage(PageView pageView, Mongo t) {
		return iMongoDao.findObjsByPage(pageView, t);
	}

	public int updateObjOne(Mongo t, Object id) {
		return iMongoDao.updateObjOne(t, id);
	}

	public long getObjsByProsCount(Mongo t) {
		return iMongoDao.getObjsByProsCount(t);
	}
}