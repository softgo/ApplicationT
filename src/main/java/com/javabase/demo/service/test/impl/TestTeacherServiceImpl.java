package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.base.core.exception.CommonException;
import com.application.base.core.obj.Pagination;

import com.application.base.utils.constant.CommonResultConstant;

import com.javabase.demo.entity.TestTeacher;
import com.javabase.demo.dao.test.TestTeacherDao;
import com.javabase.demo.service.test.TestTeacherService;

/**
 * TestTeacherServiceImpl实现
 * 
 * @author 系统生成
 *
 */

@Service("testTeacherService")
public class TestTeacherServiceImpl implements TestTeacherService {

	@Autowired
	private TestTeacherDao testTeacherDao; //Dao 注入.

	@Override
	@Transactional
	public TestTeacher saveObject(Map<String, Object> param) {
		try {
			//添加的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			TestTeacher object = testTeacherDao.saveObject(param,factoryTag);
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
	public boolean saveBatchObject(List<TestTeacher> objs) {
		try {
			//批量添加处理,自己去装数据
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			boolean result = testTeacherDao.saveBatchObject(objs,factoryTag);
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
	public TestTeacher getObjectByID(Object objId) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			TestTeacher object = testTeacherDao.getObjectByID(objId,factoryTag);
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
	public TestTeacher getObjectByUUID(String uuid) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			TestTeacher object = testTeacherDao.getObjectByUUID(uuid,factoryTag);
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
	public int updateObjectByID(Map<String, Object> param, Object objId) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = testTeacherDao.updateObjectByID(param, objId,factoryTag);
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
	public int updateObjectByUUID(Map<String, Object> param, String uuid) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = testTeacherDao.updateObjectByUUID(param, uuid,factoryTag);
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
	public int updateObjectByWhere(Map<String, Object> param) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = testTeacherDao.updateObjectByWhere(param,factoryTag);
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
			int count = testTeacherDao.deleteObjectByID(objId,factoryTag);
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
	public int deleteObjectByUUID(String uuid) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = testTeacherDao.deleteObjectByUUID(uuid,factoryTag);
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
	public int deleteObjectByWhere(Map<String, Object> param) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = testTeacherDao.deleteObjectByWhere(param,factoryTag);
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
	public List<TestTeacher> getObjects(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<TestTeacher> objects = testTeacherDao.getObjects(param,factoryTag);
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
	public Pagination<TestTeacher> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		try {
			//根据条件查询分页操作.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			Pagination<TestTeacher> pagination = testTeacherDao.paginationObjects(param, pageNo, pageSize,factoryTag);
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
	public TestTeacher findObjectByPros(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			TestTeacher object = testTeacherDao.findObjectByPros(param,factoryTag);
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
	public List<TestTeacher> findObjectListByPros(Map<String, Object> param) {
		try {
			//根据条件查询对象.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<TestTeacher> objects = testTeacherDao.findObjectListByPros(param,factoryTag);
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
			int count = testTeacherDao.getObjectCount(factoryTag);
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
			int count = testTeacherDao.getObjectCount(param,factoryTag);
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
