package com.javabase.demo.dao.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.application.base.core.apisupport.impl.MultiStrutsBaseDaoImpl;
import com.application.base.core.datasource.param.CustomSQL;
import com.application.base.core.datasource.param.ESQLOperator;
import com.application.base.core.datasource.param.Param;
import com.application.base.core.datasource.param.ParamBuilder;
import com.application.base.core.datasource.param.SQLCreator;
import com.application.base.core.exception.BusinessException;
import com.application.base.core.obj.Pagination;
import com.application.base.core.utils.CommonBeanUtils;


import com.javabase.demo.entity.TestMessages;
import com.javabase.demo.dao.test.TestMessagesDao;

/**
 * TestMessagesDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Repository("testMessagesDao")
public class TestMessagesDaoImpl extends MultiStrutsBaseDaoImpl<TestMessages> implements TestMessagesDao {
	
	@Override
	@Transactional
	public TestMessages saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		TestMessages object = CommonBeanUtils.transMap2BasePO(param, TestMessages.class);
		return factory.getCacheWriteDataSession().saveObject(TestMessages.class,object);
	}

	@Override
	@Transactional
	public boolean saveBatchObject(List<TestMessages> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(TestMessages.class,objs);
	}


	@Override
	public TestMessages getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(TestMessages.class,"id", objId);
	}


	@Override
	public TestMessages getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(TestMessages.class, uuid);
	}
	

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//TestMessages object = factory.getCacheReadDataSession().querySingleResultById(TestMessages.class,"id",objId);
		TestMessages object = new TestMessages();
		object.setId(Integer.parseInt(objId.toString()));
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByID(TestMessages.class, object);
	}

	@Override
	@Transactional
	public int updateObjectByUUID(Map<String, Object> param, String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//TestMessages object = factory.getCacheReadDataSession().querySingleResultByUUID(TestMessages.class,uuid);
		TestMessages object = new TestMessages();
		object.setUuid(uuid);
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByUUID(TestMessages.class, object);
	}
	
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestMessages.class, params, where);
		
	}

	@Override
	@Transactional
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(TestMessages.class, param);
	}


	@Override
	@Transactional
	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(TestMessages.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(TestMessages.class, param);
	}


	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(TestMessages.class, params);
	}


	@Override
	public List<TestMessages> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(TestMessages.class,params);
	}
	
	
	@Override
	public Pagination<TestMessages> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestMessages.class, params, pageNo, pageSize, factoryTag);
	}


	@Override
	public TestMessages findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestMessages.class, params);
	}
	
	
	@Override
	public List<TestMessages> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestMessages.class, params);
	}
	
	
	@Override
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(TestMessages.class, ParamBuilder.getInstance().getParam());
	}
	
	
	@Override
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestMessages.class, params);
	}

}
