package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

import  java.math.BigDecimal;
/**
 * ResultData实体
 * 
 * @author 系统生成
 *
 */
public class ResultData extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "result_data";
	
	/**主键*/
	private int  userCode = -1;
	/**主键 对应的静态变量值*/
	public static final String FIELD_USER_CODE = "userCode";
	/**名字*/
	private String  name = null;
	/**名字 对应的静态变量值*/
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

	public ResultData () {
		super();
	}
	
	public ResultData (int userCode ,String name ,String mobile ,BigDecimal amount ,String city ) {
		super();
		 this.userCode = userCode;
		 this.name = name;
		 this.mobile = mobile;
		 this.amount = amount;
		 this.city = city;
	}
	
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
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
