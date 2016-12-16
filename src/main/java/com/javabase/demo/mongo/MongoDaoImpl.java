package com.javabase.demo.mongo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.application.base.mongo.api.BaseQueryService;
import com.application.base.mongo.page.PageView;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service("iMongoDao")
public class MongoDaoImpl extends BaseQueryService<Mongo> implements IMongoDao {
	
	private Logger logger = LoggerFactory.getLogger(MongoDaoImpl.class.getName());

	@Autowired
	private MongoTemplate mongoTemplate;

	@SuppressWarnings("rawtypes")
	public Class getClassName() {
		return Mongo.class;
	}

	@SuppressWarnings("unchecked")
	public Mongo findObjById(Object id) {
		try {
			return (Mongo) this.mongoTemplate.findById(id, getClassName());
		}
		catch (Exception e) {
			this.logger.error("添加一个对象到mongodb中去失败,error=" + e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Mongo findObjByName(String proKey, String proValue) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(proKey).is(proValue));
			return (Mongo) this.mongoTemplate.findOne(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过key=" + proKey + ",value=" + proValue + "查找一个Document对象失败.");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Mongo findObjByProps(Mongo t) {
		try {
			Query query = buildBaseQuery(t, "eq", null);
			return (Mongo) this.mongoTemplate.findOne(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过属性查找对象失败,", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Mongo findObjByProps(Map<String, Object> params) {
		try {
			Query query = buildBaseQuery(params, "eq", null);
			return (Mongo) this.mongoTemplate.find(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过属性查找对象失败,", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Mongo> findObjList(Mongo t) {
		try {
			Query query = buildBaseQuery(t, "eq", null);
			return this.mongoTemplate.find(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("查找List对象失败了", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Mongo> findObjList(Map<String, Object> params) {
		try {
			Query query = buildBaseQuery(params, "eq", null);
			return this.mongoTemplate.find(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过属性查找List失败了", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PageView findObjsByPage(PageView pageView, Mongo t) {
		try {
			int pageNow = pageView.getPageNow();
			int startIndex = 0;
			int pageSize = pageView.getPageSize();
			if (pageNow == 1) startIndex = 0;
			else {
				startIndex = (pageNow - 1) * pageView.getPageSize();
			}
			Query query = buildBaseQuery(t, "eq", null);
			query.skip(startIndex);
			query.limit(pageSize);
			List<Mongo> list = this.mongoTemplate.find(query, getClassName());
			pageView.setRecords(list);
			pageView.setRowCount(getObjsByProsCount(t));
			return pageView;
		}
		catch (Exception e) {
			this.logger.error("分页查找对象失败了", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PageView findObjsByPage(PageView pageView, Map<String, Object> params) {
		try {
			int pageNow = pageView.getPageNow();
			int startIndex = 0;
			int pageSize = pageView.getPageSize();
			if (pageNow == 1) startIndex = 0;
			else {
				startIndex = (pageNow - 1) * pageView.getPageSize();
			}
			Query query = buildBaseQuery(params, "eq", null);
			query.skip(startIndex);
			query.limit(pageSize);
			List<Mongo> list = this.mongoTemplate.find(query, getClassName());
			pageView.setRecords(list);
			pageView.setRowCount(getObjsByProsCount(params));
			return pageView;
		}
		catch (Exception e) {
			this.logger.error("分页查找对象失败了", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Mongo> findObjAll() {
		try {
			Query query = new Query();
			return this.mongoTemplate.find(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("查找所有失败了", e);
		}
		return null;
	}

	
	public boolean addObjOne(Mongo obj) {
		try {
			this.mongoTemplate.insert(obj);
			return true;
		}
		catch (Exception e) {
			this.logger.error("保存对象失败了", e);
		}
		return false;
	}

	public boolean addObjAll(List<Mongo> ts) {
		try {
			this.mongoTemplate.insertAll(ts);
			return true;
		}
		catch (Exception e) {
			this.logger.error("添加所有失败了", e);
		}
		return false;
	}

	public int updateObjOne(Mongo t, Object id) {
		try {
			Query query = new Query(new Criteria("_id").is(id));
			Update update = buildBaseUpdate(t);
			WriteResult result = this.mongoTemplate.updateFirst(query, update, getClassName());
			if (result != null) {
				return 1;
			}
			return -1;
		}
		catch (Exception e) {
			this.logger.error("修改对象失败了", e);
		}
		return -1;
	}

	public int updateObjOne(Map<String, Object> params, Object id) {
		try {
			Query query = new Query(new Criteria("_id").is(id));
			Update update = buildBaseUpdate(params);
			WriteResult result = this.mongoTemplate.updateFirst(query, update, getClassName());
			if (result != null) {
				return 1;
			}
			return -1;
		}
		catch (Exception e) {
			this.logger.error("修改对象失败了", e);
		}
		return -1;
	}

	public boolean updateObjAll(List<Map<String, Object>> ts, List<Object> ids) {
		try {
			boolean result = true;
			int i = 0;
			for (int  j = 0; i < ids.size(); j++) {
				Object id = ids.get(i);
				Map<String, Object> param = ts.get(j);
				updateObjOne(param, id);
				i++;
			}
			return result;
		}
		catch (Exception e) {
			this.logger.error("修改所有对象失败了", e);
		}
		return false;
	}

	public boolean deleteByObjId(Object id) {
		try {
			Query query = new Query(new Criteria("_id").is(id));
			this.mongoTemplate.remove(query, getClassName());
			return true;
		}
		catch (Exception e) {
			this.logger.error("删除对象失败了,id=" + id);
		}
		return false;
	}

	public boolean deleteAll(List<Mongo> ts) {
		try {
			for (Mongo t : ts) {
				Object id = t.get_id();
				deleteByObjId(id);
			}
			return true;
		}
		catch (Exception e) {
			this.logger.error("删除所有失败了", e);
		}
		return false;
	}

	public long getObjsCount() {
		try {
			return this.mongoTemplate.count(new Query(), getClassName());
		}
		catch (Exception e) {
			this.logger.error("查找所有记录失败了", e);
		}
		return 0L;
	}

	public long getObjsByProsCount(Mongo t) {
		try {
			Query query = buildBaseQuery(t, "eq", null);
			return this.mongoTemplate.count(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过属性查找对象失败了", e);
		}
		return 0L;
	}

	public long getObjsByProsCount(Map<String, Object> params) {
		try {
			Query query = buildBaseQuery(params, "eq", null);
			return this.mongoTemplate.count(query, getClassName());
		}
		catch (Exception e) {
			this.logger.error("通过属性查找对象失败了", e);
		}
		return 0L;
	}
}