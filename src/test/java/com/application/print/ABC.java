package com.application.print;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ABC {

	static Map<Integer, String> data = new HashMap<Integer, String>();

	public static void main(String[] args) throws InterruptedException, IOException {
		int index = 0 ;
		for (int i = 25; i < 30; i++) {
			index = i * 100000;
			getData(index);
		}
		System.out.println("======================");
		System.out.println("获得的data="+data.size());
		System.out.println("======================");
		
		File file = new File("/Users/rocky/Desktop/1-1.txt");
		FileWriter writer = new FileWriter(file);
		for (Map.Entry<Integer, String> entry : data.entrySet()) {
			writer.write(entry.getKey()+","+entry.getValue()+"\n\t");
		}
		writer.close();
	}
	
	
	
	public static void getData(int index) {
		String sql = "select user_code, mobile_number from user where is_deleted=0 limit " + index + "," + 100000;
		System.out.println("sql = " + sql);
		CommandUtil util = new CommandUtil();
		ResultSet set = util.selectSQL(sql);
		try {
			while (set.next()) {
				int code = set.getInt("user_code");
				String mobile = set.getString("mobile_number");
				data.put(code, mobile);
				//System.out.println("code="+code+",mobile="+mobile);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				set.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			util.closeAll();
		}
	}
	
}
