package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

/**
 * TestStudent实体
 * 
 * @author 系统生成
 *
 */
public class TestStudent extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "test_student";
	
	/**学生的名字*/
	private String  name = null;
	/**学生的名字 对应的静态变量值*/
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

	public TestStudent () {
		super();
	}
	
	public TestStudent (String name ,String mobile ,int gender ,int age ) {
		super();
		 this.name = name;
		 this.mobile = mobile;
		 this.gender = gender;
		 this.age = age;
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
}
