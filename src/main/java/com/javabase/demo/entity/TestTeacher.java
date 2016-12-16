package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

/**
 * TestTeacher实体
 * 
 * @author 系统生成
 *
 */
public class TestTeacher extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "test_teacher";
	
	/**老师的名字*/
	private String  name = null;
	/**老师的名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**联系方式*/
	private String  mobile = null;
	/**联系方式 对应的静态变量值*/
	public static final String FIELD_MOBILE = "mobile";
	/**1男,0女*/
	private int  gender = -1;
	/**1男,0女 对应的静态变量值*/
	public static final String FIELD_GENDER = "gender";
	/**年龄*/
	private int  age = -1;
	/**年龄 对应的静态变量值*/
	public static final String FIELD_AGE = "age";
	/**教授专业*/
	private String  major = null;
	/**教授专业 对应的静态变量值*/
	public static final String FIELD_MAJOR = "major";

	public TestTeacher () {
		super();
	}
	
	public TestTeacher (String name ,String mobile ,int gender ,int age ,String major ) {
		super();
		 this.name = name;
		 this.mobile = mobile;
		 this.gender = gender;
		 this.age = age;
		 this.major = major;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
}
