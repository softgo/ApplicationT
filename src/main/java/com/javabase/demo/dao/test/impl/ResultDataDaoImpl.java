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


import com.javabase.demo.entity.ResultData;
import com.javabase.demo.dao.test.ResultDataDao;

/**
 * ResultDataDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Repository("resultDataDao")
public class ResultDataDaoImpl extends MultiStrutsBaseDaoImpl<ResultData> implements ResultDataDao {
	
	@Override
	@Transactional
	public ResultData saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		ResultData object = CommonBeanUtils.transMap2BasePO(param, ResultData.class);
		return factory.getCacheWriteDataSession().saveObject(ResultData.class,object);
	}

	@Override
	@Transactional
	public boolean saveBatchObject(List<ResultData> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(ResultData.class,objs);
	}


	@Override
	public ResultData getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(ResultData.class,"userCode", objId);
	}


	@Override
	public ResultData getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return null;
	}
	

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//ResultData object = factory.getCacheReadDataSession().querySingleResultById(ResultData.class,"userCode",objId);
		ResultData object = new ResultData();
		object.setUserCode(Integer.parseInt(objId.toString()));
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByID(ResultData.class, object);
	}

	@Override
	public int updateObjectByUUID(Map<String, Object> param, String uuid,String factoryTag) throws BusinessException {
		return 0;
	}
	
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(ResultData.class, params, where);
		
	}

	@Override
	@Transactional
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().physicalDelete(ResultData.class,"userCode", objId);
	}


	@Override
	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return 0;  //没有uuid,该方法就为不可用
	}


	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
		return factory.getCacheWriteDataSession().physicalWhereDelete(ResultData.class,where);
	}


	@Override
	public List<ResultData> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(ResultData.class,params);
	}
	
	
	@Override
	public Pagination<ResultData> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(ResultData.class, params, pageNo, pageSize, factoryTag);
	}


	@Override
	public ResultData findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(ResultData.class, params);
	}
	
	
	@Override
	public List<ResultData> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(ResultData.class, params);
	}
	
	
	@Override
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(ResultData.class, ParamBuilder.getInstance().getParam());
	}
	
	
	@Override
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(ResultData.class, params);
	}

}
