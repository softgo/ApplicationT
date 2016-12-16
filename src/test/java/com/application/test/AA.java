package com.application.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AA {

	public static void main(String[] args) throws Exception {

		String idcard = "612326198809196717";
		idcard = "130182199109093598";
		String uri = "http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=" + idcard;
		URL url = new URL(uri);
		URLConnection urlConnection = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
		String str = "";
		StringBuffer contents = new StringBuffer();
		while ((str = br.readLine()) != null) {
			contents.append(str);
		}
		br.close();
		String regx1 = "<td class=\"tdc2\">(.*?)</td>";
		Pattern p = Pattern.compile(regx1);
		String text = contents.toString();
		Matcher macher = p.matcher(text);
		String result = "";
		while (macher.find()) {
			result = macher.group(1).trim().toString();
		}
		result = result.replaceAll("<br/>", "").replaceAll(" ", "-");
		System.out.println(result);

	}
}
