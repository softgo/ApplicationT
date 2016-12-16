package com.application.base.mongo.api;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 构建顶级查询抽象实现
 * @author rocky
 *
 * @param <T>
 */
public abstract class BaseQueryService<T> {
	
	public Query buildBaseQuery(T t, String tag, List<Sort.Order> orders) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		List<Criteria> listC = new ArrayList<Criteria>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(t);
				if (tag.equals("gt")) {
					listC.add(Criteria.where(field.getName()).gt(value));
				}
				else if (tag.equals("lt")) {
					listC.add(Criteria.where(field.getName()).lt(value));
				}
				else if (tag.equals("eq")) {
					listC.add(Criteria.where(field.getName()).is(value));
				}
				else if (tag.equals("gte")) {
					listC.add(Criteria.where(field.getName()).gte(value));
				}
				else if (tag.equals("lte")) {
					listC.add(Criteria.where(field.getName()).lte(value));
				}
				else if (tag.equals("regex")) {
					listC.add(Criteria.where(field.getName()).regex(value.toString()));
				}
				else if (tag.equals("in")) {
					listC.add(Criteria.where(field.getName()).in(new Object[] { value }));
				}
				else if (tag.equals("ne")) {
					listC.add(Criteria.where(field.getName()).ne(value));
				}
				else
					listC.add(Criteria.where(field.getName()).is(value));
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if ((listC != null) && (listC.size() > 0) && (listC.size() > 0)) {
			Criteria[] cs = new Criteria[listC.size()];
			criteria.andOperator((Criteria[]) listC.toArray(cs));
			query.addCriteria(criteria);
		}
		Sort sort = null;
		if ((orders != null) && (orders.size() > 0)) {
			sort = new Sort(orders);
		}
		if (sort != null) {
			query = query.with(sort);
		}
		return query;
	}

	@SuppressWarnings("rawtypes")
	public Query buildBaseQuery(Map<String, Object> params, String tag, List<Sort.Order> orders) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		List<Criteria> listC = new ArrayList<Criteria>();
		for (Map.Entry entry : params.entrySet()) {
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (tag.equals("gt")) {
				listC.add(Criteria.where(key).gt(value));
			}
			else if (tag.equals("lt")) {
				listC.add(Criteria.where(key).lt(value));
			}
			else if (tag.equals("eq")) {
				listC.add(Criteria.where(key).is(value));
			}
			else if (tag.equals("gte")) {
				listC.add(Criteria.where(key).gte(value));
			}
			else if (tag.equals("lte")) {
				listC.add(Criteria.where(key).lte(value));
			}
			else if (tag.equals("regex")) {
				listC.add(Criteria.where(key).regex(value.toString()));
			}
			else if (tag.equals("in")) {
				listC.add(Criteria.where(key).in(new Object[] { value }));
			}
			else if (tag.equals("ne")) {
				listC.add(Criteria.where(key).ne(value));
			}
			else {
				listC.add(Criteria.where(key).is(value));
			}
		}
		if ((listC != null) && (listC.size() > 0) && (listC.size() > 0)) {
			Criteria[] cs = new Criteria[listC.size()];
			criteria.andOperator((Criteria[]) listC.toArray(cs));
			query.addCriteria(criteria);
		}
		Sort sort = null;
		if ((orders != null) && (orders.size() > 0)) {
			sort = new Sort(orders);
		}
		if (sort != null) {
			query = query.with(sort);
		}
		return query;
	}

	
	public Update buildBaseUpdate(T t) {
		Update update = new Update();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(t);
				if ((value != null) && (!field.getName().equals("_id"))) {
					update.set(field.getName(), value);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return update;
	}

	@SuppressWarnings("rawtypes")
	public Update buildBaseUpdate(Map<String, Object> params) {
		Update update = new Update();
		for (Map.Entry entry : params.entrySet()) {
			if (!((String) entry.getKey()).equals("_id")) {
				update.set((String) entry.getKey(), entry.getValue());
			}
		}
		return update;
	}

	public Criteria createCriteria(Map<String, Object> param, String tag) {
		Criteria result = new Criteria();
		List<Criteria> listC = new ArrayList<Criteria>();
		Set<String> _set = null;
		if ((param != null) && (param.size() > 0)) {
			_set = param.keySet();
			for (String _s : _set) {
				if (tag.equals("gt")) {
					listC.add(Criteria.where(_s).gt(param.get(_s)));
				}
				else if (tag.equals("lt")) {
					listC.add(Criteria.where(_s).lt(param.get(_s)));
				}
				else if (tag.equals("eq")) {
					listC.add(Criteria.where(_s).is(param.get(_s)));
				}
				else if (tag.equals("gte")) {
					listC.add(Criteria.where(_s).gte(param.get(_s)));
				}
				else if (tag.equals("lte")) {
					listC.add(Criteria.where(_s).lte(param.get(_s)));
				}
				else if (tag.equals("regex")) {
					listC.add(Criteria.where(_s).regex(param.get(_s).toString()));
				}
				else if (tag.equals("in")) {
					listC.add(Criteria.where(_s).in(new Object[] { param.get(_s) }));
				}
				else if (tag.equals("ne")) {
					listC.add(Criteria.where(_s).ne(param.get(_s)));
				}
				else {
					listC.add(Criteria.where(_s).is(param.get(_s)));
				}
			}
		}
		if (listC.size() > 0) {
			Criteria[] cs = new Criteria[listC.size()];
			result.andOperator((Criteria[]) listC.toArray(cs));
		}
		return result;
	}

	
	@SuppressWarnings("rawtypes")
	public Criteria createCriteria(Map<String, Object> gtMap, Map<String, Object> ltMap, Map<String, Object> eqMap,
			Map<String, Object> gteMap, Map<String, Object> lteMap, Map<String, String> regexMap,
			Map<String, Collection> inMap, Map<String, Object> neMap) {
		Criteria result = new Criteria();
		List<Criteria> listC = new ArrayList<Criteria>();
		Set<String> _set = null;
		if ((gtMap != null) && (gtMap.size() > 0)) {
			_set = gtMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).gt(gtMap.get(_s)));
			}
		}
		if ((ltMap != null) && (ltMap.size() > 0)) {
			_set = ltMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).lt(ltMap.get(_s)));
			}
		}
		if ((eqMap != null) && (eqMap.size() > 0)) {
			_set = eqMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).is(eqMap.get(_s)));
			}
		}
		if ((gteMap != null) && (gteMap.size() > 0)) {
			_set = gteMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).gte(gteMap.get(_s)));
			}
		}
		if ((lteMap != null) && (lteMap.size() > 0)) {
			_set = lteMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).lte(lteMap.get(_s)));
			}
		}

		if ((regexMap != null) && (regexMap.size() > 0)) {
			_set = regexMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).regex((String) regexMap.get(_s)));
			}
		}

		if ((inMap != null) && (inMap.size() > 0)) {
			_set = inMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).in((Collection) inMap.get(_s)));
			}
		}
		if ((neMap != null) && (neMap.size() > 0)) {
			_set = neMap.keySet();
			for (String _s : _set) {
				listC.add(Criteria.where(_s).ne(neMap.get(_s)));
			}
		}
		if (listC.size() > 0) {
			Criteria[] cs = new Criteria[listC.size()];
			result.andOperator((Criteria[]) listC.toArray(cs));
		}
		return result;
	}
}