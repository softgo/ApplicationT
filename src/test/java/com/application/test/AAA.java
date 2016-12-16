package com.application.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.mysql.jdbc.Statement;

public class AAA {

	/**
	 * test data
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		File alldata = new File("/Users/rocky/Desktop/data/resultAll.txt");
		File userdata = new File("/Users/rocky/Desktop/data/alluser.txt");
		File file1 = new File("/Users/rocky/Desktop/data/333.txt");
		
		File resultdata = new File("/Users/rocky/Desktop/data/allSql.txt");
		if (!resultdata.exists()) {
			resultdata.createNewFile();
		}

		Map<String, String> codeLocalMap = new HashMap<String, String>();
		FileReader in = new FileReader(file1);
		BufferedReader reader = new BufferedReader(in);
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] strs = line.split("@");
			if (strs.length==2) {
				codeLocalMap.put(strs[0].trim(), strs[1].trim()); //code : local
			}
		}
		reader.close();
		reader=null;
		in.close();
		in=null;
		
		Map<String, String> allDataMap = new HashMap<String, String>();
		FileReader in1 = new FileReader(alldata);
		BufferedReader reader1 = new BufferedReader(in1);
		String line1 = null;
		String result = null;
		while ((line1 = reader1.readLine()) != null) {
			result = reader1.readLine();
			String[] strs = line1.split("\t");
			allDataMap.put(strs[2].trim(), result); //手机号 value 
		}
		reader1.close();
		reader1=null;
		in1.close();
		in1=null;
		
		//allDataMap = sortMap(allDataMap); // 从大到小的排列组合.

		
		Map<String, String> userDataMap = new HashMap<String, String>();
		FileReader in2 = new FileReader(userdata);
		BufferedReader reader2 = new BufferedReader(in2);
		String line2 = null;
		while ((line2 = reader2.readLine()) != null) {
			String[] strs = line2.split(",");
			userDataMap.put(strs[1].trim(), strs[0].trim());
		}
		reader2.close();
		reader2=null;
		in2.close();
		in2=null;
		
		StringBuffer buffer = new StringBuffer();

		/**
		 * 结果...
		 */
		List<String> list = new  ArrayList<>();
		for (Map.Entry<String, String> entry : allDataMap.entrySet()) {
			String key = entry.getKey(); //手机号
			String value = entry.getValue();
			String userCode = userDataMap.get(key);
			String city = codeLocalMap.get(userCode);
			if (!StringUtils.isEmpty(city)) {
				String[] strings = value.split("\t");
				buffer.append("insert into test_data(name,mobile,amount,city) value ('"+strings[1]+"','"+strings[2]+"',"+strings[3]+",'"+city+"');\n");
				list.add("insert into test_data(name,mobile,amount,city) value ('"+strings[1]+"','"+strings[2]+"',"+strings[3]+",'"+city+"');");
			}
		}
		
		FileWriter fw = new FileWriter(resultdata);
		fw.write(buffer.toString());
		fw.flush();
		fw.close();
		
		DBCommandUtil dbCon = new DBCommandUtil();
		Connection connect = dbCon.getConn();
		try {
			Statement statement = (Statement) connect.createStatement();
			for (int i = 0; i < list.size(); i++) {
				String sql = list.get(i);
				statement.addBatch(sql);
				if (i % 5000==0 && i != 0 ) {
					statement.executeBatch();
					statement.clearBatch();
				}
				if (i==list.size()-1) {
					statement.executeBatch();
					statement.clearBatch();
				}
			}
			statement.close();
			statement = null;
			connect.close();
			connect = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 排序得数据.
	 * 
	 * @param map
	 * @return
	 */
	public static Map sortMap(Map map) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		Map newMap = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}
}
