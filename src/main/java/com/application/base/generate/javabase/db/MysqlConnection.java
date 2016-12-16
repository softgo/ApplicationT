package com.application.base.generate.javabase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 连接conn
 */
public class MysqlConnection {

	static Connection conn = null;

	/**
	 * 创建数据库连接
	 * 
	 * @return
	 */
	public static Connection newConnection() {
		try {
			if (conn == null) {
				Class.forName(DBInfoConfig.DRIVER_URL);
				conn = DriverManager.getConnection(DBInfoConfig.URL_VALUE, DBInfoConfig.USERNAME_VALUE,
						DBInfoConfig.PASSWORD_VALUE);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static boolean closeConnection(Connection conn) {
		return closeConnection(conn, null, null);
	}

	public static boolean closeConnection(Connection conn, Statement pamStatement, ResultSet rs) {
		boolean flag = true;
		try {
			if (rs != null) {
				rs.close();
			}
			if (pamStatement != null) {
				pamStatement.close();
			}
			
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
