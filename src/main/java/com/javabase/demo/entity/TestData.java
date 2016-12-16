package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

import  java.math.BigDecimal;
/**
 * TestData实体
 * 
 * @author 系统生成
 *
 */
public class TestData extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "test_data";
	
	/**老师的名字*/
	private String  name = null;
	/**老师的名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**联系方式*/
	private String  mobile = null;
	/**联系方式 对应的静态变量值*/
	public static final String FIELD_MOBILE = "mobile";
	/**金额*/
	private BigDecimal  amount = null;
	/**金额 对应的静态变量值*/
	public static final String FIELD_AMOUNT = "amount";
	/**地区*/
	private String  city = null;
	/**地区 对应的静态变量值*/
	public static final String FIELD_CITY = "city";

	public TestData () {
		super();
	}
	
	public TestData (String name ,String mobile ,BigDecimal amount ,String city ) {
		super();
		 this.name = name;
		 this.mobile = mobile;
		 this.amount = amount;
		 this.city = city;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
