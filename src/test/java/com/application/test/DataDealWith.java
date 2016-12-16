package com.application.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataDealWith {

	public static void main(String[] args) throws IOException {
		File file = new  File("/Users/rocky/Desktop/mobile.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = null;
			Map<String,String> idcardMap = new HashMap<>();
			Map<String,String> mobileMap = new HashMap<>();
			Map<String,String> emailMap = new HashMap<>();
			
			while ((line = reader.readLine())!=null) {
				String[] strs = line.split(",");
				//System.out.println("idcard="+strs[0]+",mobile="+strs[1]+",email="+strs[2]);
				String[] idcards = strs[0].split(" ");
				String[] mobiles = strs[1].split(" ");
				String[] emails = strs[2].split(" ");
				for (String idcard : idcards) {
					if (idcard.length()==18 || idcard.length()==15) {
						idcard = new String(idcard.getBytes(), "UTF-8");
						idcardMap.put(idcard.toUpperCase(),idcard.toUpperCase());
					}
				}
				for (String mobile : mobiles) {
					if (mobile.length()==11) {
						mobile = new String(mobile.getBytes(), "UTF-8");
						try {
							mobileMap.put(Long.parseLong(mobile)+"",Long.parseLong(mobile)+"");
						}
						catch (Exception e) {
							System.out.println("异常的号"+mobile);
						}
					}
				}
				for (String email : emails) {
					if (email.contains("@") && email.contains(".")) {
						if (!email.contains("*")) {
							email = new String(email.getBytes(), "UTF-8");
							emailMap.put(email.toLowerCase(),email.toLowerCase());
						}
					}
				}
			}
			StringBuffer idCardBuffer = new StringBuffer("");
			for (Map.Entry<String, String> entry : idcardMap.entrySet() ) {
				idCardBuffer.append("INSERT INTO `BLACK_IDCARD_MENU` (`idcard`,`disabled`,`create_time`) values ('"+entry.getValue()+"',0,now()); \n");
			}
			
			StringBuffer mobileBuffer = new StringBuffer("");
			for (Map.Entry<String, String> entry : mobileMap.entrySet() ) {
				mobileBuffer.append("INSERT INTO `BLACK_MOBILE_MENU` (`mobile`,`disabled`,`create_time`) values ('"+entry.getValue()+"',0,now()); \n");
			}
			
			StringBuffer emailBuffer = new StringBuffer("");
			for (Map.Entry<String, String> entry : emailMap.entrySet() ) {
				emailBuffer.append("INSERT INTO `BLACK_EMAIL_MENU` (`email`,`disabled`,`create_time`) values ('"+entry.getValue()+"',0,now()); \n");
			}
			
			String idcardFile = "/Users/rocky/Desktop/idcard.sql";
			String mobileFile = "/Users/rocky/Desktop/mobile.sql";
			String emailFile = "/Users/rocky/Desktop/email.sql";
			
			writeFile(idcardFile,idCardBuffer.toString());
			writeFile(mobileFile,mobileBuffer.toString());
			writeFile(emailFile,emailBuffer.toString());
			
			System.out.println("处理黑名单完成");
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写文件
	 *  
	 * @param filePath
	 * @param content
	 */
	private static void writeFile(String filePath,String content){
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(content);
			writer.close();
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
