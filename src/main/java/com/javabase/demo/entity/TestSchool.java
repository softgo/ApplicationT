package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

/**
 * TestSchool实体
 * 
 * @author 系统生成
 *
 */
public class TestSchool extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "test_school";
	
	/**学校名字*/
	private String  name = null;
	/**学校名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**学校地址*/
	private String  address = null;
	/**学校地址 对应的静态变量值*/
	public static final String FIELD_ADDRESS = "address";
	/**联系方式*/
	private String  phone = null;
	/**联系方式 对应的静态变量值*/
	public static final String FIELD_PHONE = "phone";
	/**地区*/
	private String  area = null;
	/**地区 对应的静态变量值*/
	public static final String FIELD_AREA = "area";

	public TestSchool () {
		super();
	}
	
	public TestSchool (String name ,String address ,String phone ,String area ) {
		super();
		 this.name = name;
		 this.address = address;
		 this.phone = phone;
		 this.area = area;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
