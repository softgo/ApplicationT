package com.application.base.utils.message.test;

import java.util.Map;

import com.application.base.utils.message.constant.MessageType;
import com.application.base.utils.message.service.impl.FinanceMessager;


public class MsgSender {
    
    public static void main(String[] args) throws Exception {
        FinanceMessager messager = new FinanceMessager();
        String[] phones = null; 
        phones = new String[]{"18810056855"};
        Map<String, Object> result = messager.platformGiveMoneyMsg("2016-06-14", phones, MessageType.WLWX);
        System.out.println(result.get("code"));
        System.out.println(result.get("msg"));
        System.out.println(result.get("message"));

    }
}
