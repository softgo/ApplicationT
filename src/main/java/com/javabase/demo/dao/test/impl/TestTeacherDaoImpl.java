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


import com.javabase.demo.entity.TestTeacher;
import com.javabase.demo.dao.test.TestTeacherDao;

/**
 * TestTeacherDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Repository("testTeacherDao")
public class TestTeacherDaoImpl extends MultiStrutsBaseDaoImpl<TestTeacher> implements TestTeacherDao {
	
	@Override
	@Transactional
	public TestTeacher saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		TestTeacher object = CommonBeanUtils.transMap2BasePO(param, TestTeacher.class);
		return factory.getCacheWriteDataSession().saveObject(TestTeacher.class,object);
	}

	@Override
	@Transactional
	public boolean saveBatchObject(List<TestTeacher> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(TestTeacher.class,objs);
	}


	@Override
	public TestTeacher getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(TestTeacher.class,"id", objId);
	}


	@Override
	public TestTeacher getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(TestTeacher.class, uuid);
	}
	

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//TestTeacher object = factory.getCacheReadDataSession().querySingleResultById(TestTeacher.class,"id",objId);
		TestTeacher object = new TestTeacher();
		object.setId(Integer.parseInt(objId.toString()));
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByID(TestTeacher.class, object);
	}

	@Override
	@Transactional
	public int updateObjectByUUID(Map<String, Object> param, String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//TestTeacher object = factory.getCacheReadDataSession().querySingleResultByUUID(TestTeacher.class,uuid);
		TestTeacher object = new TestTeacher();
		object.setUuid(uuid);
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByUUID(TestTeacher.class, object);
	}
	
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestTeacher.class, params, where);
		
	}

	@Override
	@Transactional
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(TestTeacher.class, param);
	}


	@Override
	@Transactional
	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(TestTeacher.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(TestTeacher.class, param);
	}


	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(TestTeacher.class, params);
	}


	@Override
	public List<TestTeacher> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(TestTeacher.class,params);
	}
	
	
	@Override
	public Pagination<TestTeacher> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestTeacher.class, params, pageNo, pageSize, factoryTag);
	}


	@Override
	public TestTeacher findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestTeacher.class, params);
	}
	
	
	@Override
	public List<TestTeacher> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestTeacher.class, params);
	}
	
	
	@Override
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(TestTeacher.class, ParamBuilder.getInstance().getParam());
	}
	
	
	@Override
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestTeacher.class, params);
	}

}
