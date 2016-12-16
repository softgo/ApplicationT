package com.application.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class AAAA {

	/**
	 * 
	 * test data
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		File alldata = new File("/Users/rocky/Desktop/data/alldatarempincel.txt");
		File userdata = new File("/Users/rocky/Desktop/data/alluser.txt");
		File userdetaildata = new File("/Users/rocky/Desktop/data/alluserdetail.txt");
		File resultdata = new File("/Users/rocky/Desktop/data/result_100W_50W_Clean.txt");
		if (!resultdata.exists()) {
			resultdata.createNewFile();
		}
		
		Map<String, Integer> allDataMap = new HashMap<String, Integer>();
		FileReader in1 = new FileReader(alldata);
		BufferedReader reader1 = new BufferedReader(in1);
		String line1 = null;
		while ((line1 = reader1.readLine()) != null) {
			String[] strs = line1.split(",");
			Integer value = (int) Math.round(Double.parseDouble(strs[0]));
			allDataMap.put(strs[1], value);
		}
		allDataMap = sortMap(allDataMap); // 从大到小的排列组合.

		Map<String, String> userDataMap = new HashMap<String, String>();
		FileReader in2 = new FileReader(userdata);
		BufferedReader reader2 = new BufferedReader(in2);
		String line2 = null;
		while ((line2 = reader2.readLine()) != null) {
			String[] strs = line2.split(",");
			userDataMap.put(strs[0], strs[1]);
		}

		Map<String, String> userDetailDataMap = new HashMap<String, String>();
		FileReader in3 = new FileReader(userdetaildata);
		BufferedReader reader3 = new BufferedReader(in3);
		String line3 = null;
		while ((line3 = reader3.readLine()) != null) {
			String[] strs = line3.split(",");
			if (strs.length == 2) {
				userDetailDataMap.put(strs[0], strs[1]);
			}
			else {
				userDetailDataMap.put(strs[0], null);
			}
		}

		FileWriter fw = new FileWriter(resultdata);
		fw.write("名字\t联系电话\t金额\t地址信息\n\t");

		/**
		 * 结果...
		 */
		System.out.println("用户数:" + userDataMap.size() + " , 详情数：" + userDetailDataMap.size());
		for (Map.Entry<String, Integer> entry : allDataMap.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			//if ( value<500000 ) {
				String phone =userDataMap.get(key);
				if (!StringUtils.isEmpty(phone)) {
					phone = phone.substring(0,11);
					fw.write(userDetailDataMap.get(key) + "\t" + userDataMap.get(key) + "\t" + value +"\t" + MobileLocationUtil.getMobileLocation(userDataMap.get(key))+ "\n\t");
				}
			//}
		}
		fw.close();
		System.out.println("操作完成...");
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
