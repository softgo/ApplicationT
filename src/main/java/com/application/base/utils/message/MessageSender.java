package com.application.base.utils.message;

import com.application.base.utils.message.constant.MessageType;
import com.application.base.utils.message.service.impl.FinanceMessager;

/**
 * 消息发送测试类
 */
public class MessageSender {

	public static void main(String[] args) {
		
		FinanceMessager manager = new FinanceMessager();
		
		 //String tag = "验证码";
        
        String head = "";
        String tails = "退订回复TD";
        
        String[] phones = new String[]{"18810056855","15311555699"};
        
        //String[] phones = new String[]{"18810056855"};
        
        //String phone = "18911415132";
        
        MessageType type = MessageType.WLWX;
        String money = "1000" ;
        
        try {
        	
        	manager.registCodeMsg("123456", phones, type);
            manager.submitMessageMsg(money, phones, type);
            manager.windControlPassMsg(money, phones, type);
            manager.windControlRefuseMsg(phones, type);
            manager.windControlBackMsg(phones, type);
            manager.platformGiveMoneyMsg("2016-6-21", phones, type);
            manager.platformGiveWishesMsg(phones, type);
            manager.userReturnMoneySuccessMsg(money, phones, type);
            manager.userReturnMoneyNoticeMsg("2016-6-21", money, phones, type);
            manager.userReturnMoneyFailMsg(money, phones, type);
            manager.userOverdueOneMoneyMsg(phones, type);
            manager.userOverdueTwoMoneyMsg(phones, type);
            manager.userOverdueFiftheenMoneyMsg(phones, type);
            manager.userOverdueThirtyMoneyMsg(phones, type);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
        
	}
}
