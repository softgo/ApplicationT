package com.application.base.core.datasource.service;

import java.util.List;
import java.util.Map;

import com.application.base.core.obj.Pagination;

/**
 * 上级的通用service接口
 * @param <T>
 */
public interface StrutsBaseService<T> {

	/**
	 * 添加对象。
	 * 
	 * @param param
	 * @return
	 */
	T saveObject(Map<String, Object> param) ;

	
	/**
	 * 添加对象。
	 * 
	 * @param param
	 * @return
	 */
	boolean saveBatchObject(List<T> objs) ;
	
	
	/**
	 * 通过id获得对象。
	 * 
	 * @param id
	 * @return
	 */
	T getObjectByID(Object objId) ;

	
	/**
	 * 通过uuid获得对象.
	 * 
	 * @param uuid
	 * @return
	 */
	T getObjectByUUID(String uuid) ;

	
	/**
	 * 通过id修改对象.
	 * 
	 * @param param
	 * @return
	 */
	int updateObjectByID(Map<String, Object> param, Object objId) ;

	
	/**
	 * 通过uuid修改对象.
	 * 
	 * @param param
	 * @return
	 */
	int updateObjectByUUID(Map<String, Object> param, String uuid) ;

	
	/**
	 * 通过条件修改对象.
	 * 
	 * @param param
	 * @ CustomSQL where : 由自己来创建！！！
	 * @return
	 */
	int updateObjectByWhere(Map<String, Object> param) ;
	
	
	/**
	 * 通过id删除对象.
	 * 
	 * @param id
	 * @return
	 */
	int deleteObjectByID(Object objId) ;

	
	/**
	 * 通过uuid删除对象..
	 * 
	 * @param uuid
	 * @return
	 */
	int deleteObjectByUUID(String uuid) ;

	/**
	 * 通过条件删除对象..
	 * 
	 * @param uuid
	 * @return
	 */
	int deleteObjectByWhere(Map<String, Object> param) ;

	
	/**
	 * 获得一个list.
	 * 
	 * @return
	 */
	List<T> getObjects(Map<String, Object> param) ;

	
	/**
	 * 分页获得对象集合.
	 * 
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination<T> paginationObjects(Map<String, Object> param, int pageNo, int pageSize) ;

	
	/**
	 * 通过属性查找对象.
	 * 
	 * @param param
	 * @return
	 * @
	 */
	T findObjectByPros(Map<String, Object> param) ;

	
	/**
	 * 通过属性查找对象.
	 * 
	 * @param param
	 * @return
	 * @
	 */
	List<T> findObjectListByPros(Map<String, Object> param) ;
	
	
	/**
	 * 获得总条数
	 */
	int getObjectCount() ;
	
	
	/**
	 * 通过属性获得总条数
	 */
	int getObjectCount(Map<String, Object> param) ;
	
}
