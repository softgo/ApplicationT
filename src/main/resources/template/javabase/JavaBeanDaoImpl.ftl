package ${JavaBeanDaoImplPath};

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


import ${JavaBeanPath}.${poName};
import ${JavaBeanDaoPath}.${poName}Dao;

/**
 * ${poName}DaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Repository("${firstLowerPoName}Dao")
public class ${poName}DaoImpl extends MultiStrutsBaseDaoImpl<${poName}> implements ${poName}Dao {
	
	@Override
	@Transactional
	public ${poName} saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		${poName} object = CommonBeanUtils.transMap2BasePO(param, ${poName}.class);
		return factory.getCacheWriteDataSession().saveObject(${poName}.class,object);
	}

	@Override
	@Transactional
	public boolean saveBatchObject(List<${poName}> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(${poName}.class,objs);
	}


	@Override
	public ${poName} getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(${poName}.class,"${tablePKVal}", objId);
	}


	<#if existUuid == "0">
	@Override
	public ${poName} getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(${poName}.class, uuid);
	}
	<#else>
	@Override
	public ${poName} getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return null;
	}
	</#if>
	

	@Override
	@Transactional
	public int updateObjectByID(Map<String, Object> param, Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//${poName} object = factory.getCacheReadDataSession().querySingleResultById(${poName}.class,"${tablePKVal}",objId);
		${poName} object = new ${poName}();
		<#if "${primaryKeyType}" == "NUMERIC">
		object.set${primaryKeySet}(Integer.parseInt(objId.toString()));
		<#else>
		object.set${primaryKeySet}(objId.toString());
		</#if>
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByID(${poName}.class, object);
	}

	<#if existUuid == "0">
	@Override
	@Transactional
	public int updateObjectByUUID(Map<String, Object> param, String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		//不要再去查一次了,浪费数据库资源.
		//${poName} object = factory.getCacheReadDataSession().querySingleResultByUUID(${poName}.class,uuid);
		${poName} object = new ${poName}();
		object.setUuid(uuid);
		object = CommonBeanUtils.transMap2BasePO(param, object);
		return factory.getCacheWriteDataSession().updateObjectByUUID(${poName}.class, object);
	}
	<#else>
	@Override
	public int updateObjectByUUID(Map<String, Object> param, String uuid,String factoryTag) throws BusinessException {
		return 0;
	}
	</#if>
	
	
	@Override
	@Transactional
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(${poName}.class, params, where);
		
	}

	@Override
	@Transactional
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		<#if existDisabled == "0">
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("${tablePKVal}",objId));
		return factory.getCacheWriteDataSession().logicDelete(${poName}.class, param);
		<#else>
		return factory.getCacheWriteDataSession().physicalDelete(${poName}.class,"${tablePKVal}", objId);
		</#if>
	}


	<#if existUuid == "0">
	@Override
	@Transactional
	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		<#if existDisabled == "0">
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(${poName}.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(${poName}.class, param);
		<#else>
		CustomSQL where = SQLCreator.where().cloumn("uuid").operator(ESQLOperator.EQ).value(uuid); //根据实际情况填写.
		return factory.getCacheWriteDataSession().physicalDelete(${poName}.class,where);
		</#if>
	}
	<#else>
	@Override
	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return 0;  //没有uuid,该方法就为不可用
	}
	</#if>


	@Override
	@Transactional
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		<#if existDisabled == "0">
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(${poName}.class, params);
		<#else>
		//根据实际情况填写要查的列和对应的值.
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
		return factory.getCacheWriteDataSession().physicalWhereDelete(${poName}.class,where);
		</#if>		
	}


	@Override
	public List<${poName}> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(${poName}.class,params);
	}
	
	
	@Override
	public Pagination<${poName}> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(${poName}.class, params, pageNo, pageSize, factoryTag);
	}


	@Override
	public ${poName} findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(${poName}.class, params);
	}
	
	
	@Override
	public List<${poName}> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(${poName}.class, params);
	}
	
	
	@Override
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(${poName}.class, ParamBuilder.getInstance().getParam());
	}
	
	
	@Override
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(${poName}.class, params);
	}

}
