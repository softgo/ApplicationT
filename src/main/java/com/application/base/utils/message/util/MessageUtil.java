package com.application.base.utils.message.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


/**
 *配置文件信息读取.
 */
public class MessageUtil {

    //属性配置文件
    private static Properties properties = null;
    
    /**
     * 初始化配置文件.
     */
    public static void initProperties() {
        if (properties==null) {
            properties = new Properties();
            InputStream inputStream = null;
            try {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/message.properties");
                if (inputStream==null) {
                    inputStream = MessageUtil.class.getClassLoader().getResourceAsStream("config/message.properties");
                }
                if (inputStream != null ) {
                    properties.load(inputStream);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getMsgVal(String status) {
        if (properties==null) {
            initProperties();
        }
         return properties.getProperty(status);
    }
    
    public static String getMsgVal(String status,String defVal) {
        if (properties==null) {
            initProperties();
        }
         return properties.getProperty(status,defVal);
    }
    
    public static void main(String[] args) {
        String value = MessageUtil.getMsgVal("0","0");
        try {
            System.out.println(new String(value.getBytes(), "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
