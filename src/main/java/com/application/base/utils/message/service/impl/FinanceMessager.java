package com.application.base.utils.message.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.application.base.utils.httpclient.HttpHelper;
import com.application.base.utils.httpclient.ResponseContent;
import com.application.base.utils.message.config.SYMessageConfig;
import com.application.base.utils.message.config.WLWXMessageConfig;
import com.application.base.utils.message.constant.Constant;
import com.application.base.utils.message.constant.MessageType;
import com.application.base.utils.message.service.MessageService;
import com.application.base.utils.message.template.MessageTemplate;
import com.application.base.utils.message.util.MessageUtil;

/**
 * 短信实现.
 */
public class FinanceMessager implements MessageService {
    
    private Logger logger = LoggerFactory.getLogger(FinanceMessager.class);
    
    MessageTemplate template = new MessageTemplate();
    
    @Override
    public Map<String, Object> getAccountBalance(MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig();
            ResponseContent result = HttpHelper.postUrl(MessageUtil.getMsgVal("sy_balanceUrl",Constant.SY_BALANCE_URL) + config.toCheck());
            return getBalanceResult(result.getContent());
        }else{
            return null;
        }
    }

    @Override
    public Map<String, Object> registCodeMsg(String code, String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.registCodeMsg(code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.registCodeMsg(code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> submitMessageMsg(String money,String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.submitMessageMsg(money));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.submitMessageMsg(money));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> windControlPassMsg(String money, String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.windControlPassMsg(money));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.windControlPassMsg(money));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> windControlRefuseMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.windControlRefuseMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.windControlRefuseMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> windControlBackMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.windControlBackMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.windControlBackMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> platformGiveMoneyMsg(String dateStr, String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.platformGiveMoneyMsg(dateStr));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.platformGiveMoneyMsg(dateStr));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> platformGiveWishesMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.platformGiveWishesMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.platformGiveWishesMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> userReturnMoneyNoticeMsg(String dateStr,String money, String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneyNoticeMsg(dateStr,money));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneyNoticeMsg(dateStr,money));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> userReturnMoneySuccessMsg(String money, String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneySuccessMsg(money));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneySuccessMsg(money));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public  Map<String, Object> userReturnMoneyFailMsg(String money,String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneyFailMsg(money));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userReturnMoneyFailMsg(money));
            return config.toSendMsg();
        }
        return null;
    }


    @Override
    public Map<String, Object> userOverdueOneMoneyMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueOneMoneyMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueOneMoneyMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> userOverdueTwoMoneyMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueTwoMoneyMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueTwoMoneyMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> userOverdueFiftheenMoneyMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueFiftheenMoneyMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueFiftheenMoneyMsg());
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> userOverdueThirtyMoneyMsg(String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueThirtyMoneyMsg());
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.userOverdueThirtyMoneyMsg());
            return config.toSendMsg();
        }
        return null;
    }
    
    
    @Override
    public Map<String, Object> getRechargeMsg(String name,  String[] phones,MessageType type) throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getRechargeMsg(name));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getRechargeMsg(name));
            return config.toSendMsg();
        }
        return null;
    }

 
    @Override
    public Map<String, Object> getRegistCodeMsg(String code,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getRegistCodeMsg(code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getRegistCodeMsg(code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getSimpleCodeMsg(String code,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getSimpleCodeMsg(code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getSimpleCodeMsg(code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getTenCodeMsg(String code,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getTenCodeMsg(code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getTenCodeMsg(code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getRegistNoticeMsg(String group,String companyPhone, String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getRegistNoticeMsg(group, companyPhone));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getRegistNoticeMsg(group, companyPhone));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getPassNoticeCodeMsg(String pass,String companyPhone,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getPassNoticeCodeMsg(pass, companyPhone));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getPassNoticeCodeMsg(pass, companyPhone));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getOrderNoticeCodeMsg(String orderId, String code,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getOrderNoticeCodeMsg(orderId, code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getOrderNoticeCodeMsg(orderId, code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getOrderInfoNoticeCodeMsg(String orderId, String code,  String[] phones,MessageType type)
            throws Exception {
        if (type==MessageType.SY) {
            SYMessageConfig config = new SYMessageConfig().initConfig(phones);
            config.setContent(template.getOrderInfoNoticeCodeMsg(orderId, code));
            return sendContent(config);
        }else if (type==MessageType.WLWX) {
            WLWXMessageConfig config = new WLWXMessageConfig().initConfig(phones);
            config.setContent(template.getOrderInfoNoticeCodeMsg(orderId, code));
            return config.toSendMsg();
        }
        return null;
    }

    @Override
    public Map<String, Object> getMessageResult(String result,String message) throws Exception {
        String temp[] = result.split("\n");
        String[] resust = temp[0].split(",");
        String status = resust[1];
        if (status.equals("0")) {
            return getResultMap(Constant.SUCCESS, "短信发送成功", message);
        }else {
            return getResultMap(Constant.FAIL, MessageUtil.getMsgVal(status), message);
        }
    }

    @Override
    public Map<String, Object> getBalanceResult(String result) throws Exception {
        logger.info("得到的余额返回信息是:"+result);
        String temp[] = result.split("\n");
        String[] resust = temp[0].split(",");
        String status = resust[1];
        if (status.equals("0")) {
            String[] balances = temp[1].split(",");
            String balance = balances[1];
            return getResultMap(Constant.SUCCESS, "查询余额成功",balance);
        }else {
            return getResultMap(Constant.FAIL, "查询余额失败","0");
        }
    }
    
    /**
     * 发送和处理信息.
     * @param phones
     * @param config
     * @return
     * @throws Exception
     */
    private Map<String, Object> sendContent(SYMessageConfig config) throws Exception {
        ResponseContent result = HttpHelper.postUrl(MessageUtil.getMsgVal("sy_messageUrl",Constant.SY_MESSAGE_URL)+ config.toSendMsg());
        logger.info("短信的类容是:"+config.getContent()+"短信返回的结果是:"+result.getContent());
        return getMessageResult(result.getContent(),config.getContent());
    }
    
    
    /**
     * 返回的map
     * @param code
     * @param message
     * @return
     */
    public Map<String, Object> getResultMap(String code,String msg,String message){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("message", message);
        return result;
    }
    
}
