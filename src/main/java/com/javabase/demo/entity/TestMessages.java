package com.javabase.demo.entity;

import com.application.base.core.obj.BaseBean;

/**
 * TestMessages实体
 * 
 * @author 系统生成
 *
 */
public class TestMessages extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "test_messages";
	
	/**短信的内容*/
	private String  content = null;
	/**短信的内容 对应的静态变量值*/
	public static final String FIELD_CONTENT = "content";
	/**结果标识:1成功,0失败*/
	private int  status = -1;
	/**结果标识:1成功,0失败 对应的静态变量值*/
	public static final String FIELD_STATUS = "status";
	/**给谁发的*/
	private String  mobile = null;
	/**给谁发的 对应的静态变量值*/
	public static final String FIELD_MOBILE = "mobile";
	/**1示远,2未来无线*/
	private int  type = -1;
	/**1示远,2未来无线 对应的静态变量值*/
	public static final String FIELD_TYPE = "type";

	public TestMessages () {
		super();
	}
	
	public TestMessages (String content ,int status ,String mobile ,int type ) {
		super();
		 this.content = content;
		 this.status = status;
		 this.mobile = mobile;
		 this.type = type;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
