package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.base.core.exception.CommonException;
import com.application.base.core.obj.Pagination;

import com.application.base.utils.constant.CommonResultConstant;

import com.javabase.demo.entity.ResultData;
import com.javabase.demo.dao.test.ResultDataDao;
import com.javabase.demo.service.test.ResultDataService;

/**
 * ResultDataServiceImpl实现
 * 
 * @author 系统生成
 *
 */

@Service("resultDataService")
public class ResultDataServiceImpl implements ResultDataService {

	@Autowired
	private ResultDataDao resultDataDao; //Dao 注入.

	@Override
	@Transactional
	public ResultData saveObject(Map<String, Object> param) {
		try {
			//添加的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			ResultData object = resultDataDao.saveObject(param,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}


	@Override
	@Transactional
	public boolean saveBatchObject(List<ResultData> objs) {
		try {
			//批量添加处理,自己去装数据
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			boolean result = resultDataDao.saveBatchObject(objs,factoryTag);
			if(!result){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}


	@Override
	public ResultData getObjectByID(Object objId) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			ResultData object = resultDataDao.getObjectByID(objId,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	@Override
	public ResultData getObjectByUUID(String uuid) {
		return null;
	}

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.updateObjectByID(param, objId,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	@Override
	public int updateObjectByUUID(Map<String, Object> param, String uuid) {
		return 0;
	}
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.updateObjectByWhere(param,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	

	@Override
	@Transactional
	public int deleteObjectByID(Object objId) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.deleteObjectByID(objId,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	@Override
	public int deleteObjectByUUID(String uuid) {
		return 0;
	}
	
	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.deleteObjectByWhere(param,factoryTag);
			if(count < 1 ){
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}


	@Override
	public List<ResultData> getObjects(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<ResultData> objects = resultDataDao.getObjects(param,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}


	@Override
	public Pagination<ResultData> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		try {
			//根据条件查询分页操作.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			Pagination<ResultData> pagination = resultDataDao.paginationObjects(param, pageNo, pageSize,factoryTag);
			if(pagination==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return pagination;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	

	@Override
	public ResultData findObjectByPros(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			ResultData object = resultDataDao.findObjectByPros(param,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	

	@Override
	public List<ResultData> findObjectListByPros(Map<String, Object> param) {
		try {
			//根据条件查询对象.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<ResultData> objects = resultDataDao.findObjectListByPros(param,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	

	@Override
	public int getObjectCount() {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.getObjectCount(factoryTag);
			if(count < 1 ){
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_COUNT_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}


	@Override
	public int getObjectCount(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = resultDataDao.getObjectCount(param,factoryTag);
			if(count < 1){
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_COUNT_FAILD);
			}	
			return count;		
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
}
