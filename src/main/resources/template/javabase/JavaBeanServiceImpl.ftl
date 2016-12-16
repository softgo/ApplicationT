package ${JavaBeanServiceImplPath};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.base.core.exception.CommonException;
import com.application.base.core.obj.Pagination;

import com.application.base.util.constant.CommonResultConstant;

import ${JavaBeanPath}.${poName};
import ${JavaBeanDaoPath}.${poName}Dao;
import ${JavaBeanServicePath}.${poName}Service;

/**
 * ${poName}ServiceImpl实现
 * 
 * @author 系统生成
 *
 */

@Service("${firstLowerPoName}Service")
public class ${poName}ServiceImpl implements ${poName}Service {

	@Autowired
	private ${poName}Dao ${firstLowerPoName}Dao; //Dao 注入.

	@Override
	@Transactional
	public ${poName} saveObject(Map<String, Object> param) {
		try {
			//添加的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			${poName} object = ${firstLowerPoName}Dao.saveObject(param,factoryTag);
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
	public boolean saveBatchObject(List<${poName}> objs) {
		try {
			//批量添加处理,自己去装数据
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			boolean result = ${firstLowerPoName}Dao.saveBatchObject(objs,factoryTag);
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
	public ${poName} getObjectByID(Object objId) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			${poName} object = ${firstLowerPoName}Dao.getObjectByID(objId,factoryTag);
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

	<#if existUuid == "0">
	@Override
	public ${poName} getObjectByUUID(String uuid) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			${poName} object = ${firstLowerPoName}Dao.getObjectByUUID(uuid,factoryTag);
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
	<#else>
	@Override
	public ${poName} getObjectByUUID(String uuid) {
		return null;
	}
	</#if>

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = ${firstLowerPoName}Dao.updateObjectByID(param, objId,factoryTag);
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

	<#if existUuid == "0">
	@Override
	@Transactional
	public int updateObjectByUUID(Map<String, Object> param, String uuid) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = ${firstLowerPoName}Dao.updateObjectByUUID(param, uuid,factoryTag);
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
	<#else>
	@Override
	public int updateObjectByUUID(Map<String, Object> param, String uuid) {
		return 0;
	}
	</#if>
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = ${firstLowerPoName}Dao.updateObjectByWhere(param,factoryTag);
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
			int count = ${firstLowerPoName}Dao.deleteObjectByID(objId,factoryTag);
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

	<#if existUuid == "0">
	@Override
	@Transactional
	public int deleteObjectByUUID(String uuid) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = ${firstLowerPoName}Dao.deleteObjectByUUID(uuid,factoryTag);
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
	<#else>
	@Override
	public int deleteObjectByUUID(String uuid) {
		return 0;
	}
	</#if>
	
	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = ${firstLowerPoName}Dao.deleteObjectByWhere(param,factoryTag);
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
	public List<${poName}> getObjects(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<${poName}> objects = ${firstLowerPoName}Dao.getObjects(param,factoryTag);
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
	public Pagination<${poName}> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		try {
			//根据条件查询分页操作.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			Pagination<${poName}> pagination = ${firstLowerPoName}Dao.paginationObjects(param, pageNo, pageSize,factoryTag);
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
	public ${poName} findObjectByPros(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			${poName} object = ${firstLowerPoName}Dao.findObjectByPros(param,factoryTag);
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
	public List<${poName}> findObjectListByPros(Map<String, Object> param) {
		try {
			//根据条件查询对象.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<${poName}> objects = ${firstLowerPoName}Dao.findObjectListByPros(param,factoryTag);
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
			int count = ${firstLowerPoName}Dao.getObjectCount(factoryTag);
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
			int count = ${firstLowerPoName}Dao.getObjectCount(param,factoryTag);
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
