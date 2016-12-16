package com.application.base.utils.message.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.application.base.utils.message.constant.MessageType;


public interface MessageService {
    
    /**
     * 获取当前账户余额
     * @param phone:电话
     * @return
     */
    public Map<String, Object> getAccountBalance(MessageType type) throws Exception ;
    
    /**
     * 注册麦芽分期验证码模板
     * @param code:编码
     * @return
     */
    public Map<String, Object> registCodeMsg(String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 提交借款申请模板
     * @param money:钱
     * @return
     */
    public Map<String, Object> submitMessageMsg(String money,String[] phones,MessageType type) throws Exception;
    
    /**
     * 借款审核通过模板
     * @param money:钱
     * @return
     */
    public Map<String, Object> windControlPassMsg(String money,String[] phones,MessageType type) throws Exception;
    
    /**
     * 借款审核拒绝模板
     * @return
     */
    public Map<String, Object> windControlRefuseMsg(String[] phones,MessageType type) throws Exception;
    
    /**
     * 借款审核回退模板
     * @return
     */
    public Map<String, Object> windControlBackMsg(String[] phones,MessageType type) throws Exception;
    
    /**
     * 平台放款通知模板
     * @param dateStr: eg:2016年5月28日
     * @return
     */
    public Map<String, Object> platformGiveMoneyMsg(String dateStr,String[] phones,MessageType type) throws Exception;
    
    /**
     * 平台放款祝福语模板
     * @return
     */
    public Map<String, Object> platformGiveWishesMsg(String[] phones,MessageType type) throws Exception;
    
    /**
     * 用户还款提示模板
     * @param money
     * @return
     */
    public Map<String, Object> userReturnMoneyNoticeMsg(String dateStr,String money,String[] phones,MessageType type) throws Exception;
    
    /**
     * 用户还款成功提示模板
     * @param money
     * @return
     */
    public Map<String, Object> userReturnMoneySuccessMsg(String money,String[] phones,MessageType type) throws Exception;
    
    /**
     * 用户还款失败提示模板
     * @return
     */
    public Map<String, Object> userReturnMoneyFailMsg(String money,String[] phones,MessageType type) throws Exception;
    
    /**
     * 用户逾期提示模板 1
     * @return
     */
    public Map<String, Object> userOverdueOneMoneyMsg(String[] phones,MessageType type) throws Exception;
    
    
    /**
     * 用户逾期提示模板 2
     * @return
     */
    public Map<String, Object> userOverdueTwoMoneyMsg(String[] phones,MessageType type) throws Exception;
    
    
    /**
     * 用户逾期提示模板 15
     * @return
     */
    public Map<String, Object> userOverdueFiftheenMoneyMsg(String[] phones,MessageType type) throws Exception;
    
    
    /**
     * 用户逾期提示模板 30
     * @return
     */
    public Map<String, Object> userOverdueThirtyMoneyMsg(String[] phones,MessageType type) throws Exception;
    
    
    
    
    
    
    
    
    
    /**
     * 简单注册验证码模板
     * @param code
     * @return
     */
    public Map<String, Object> getRegistCodeMsg(String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 简单验证码模板.
     * @param code
     * @return
     */
    public Map<String, Object> getSimpleCodeMsg(String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 简单验证码模板(10分钟有效).
     * @param code
     * @return
     */
    public Map<String, Object> getTenCodeMsg(String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 注册通知模板.
     * 
     * @param group
     * @param phone
     * @return
     */
    public Map<String, Object> getRegistNoticeMsg(String group,String companyPhone,String[] phones,MessageType type) throws Exception;
    
    /**
     * 密码验证码模板.
     * 
     * @param pass
     * @param phone
     * @return
     */
    public Map<String, Object> getPassNoticeCodeMsg(String pass,String companyPhone, String[] phones,MessageType type) throws Exception;
    
    /**
     * 订单验证码模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public Map<String, Object> getOrderNoticeCodeMsg(String orderId, String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 订单信息验证码模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public Map<String, Object> getOrderInfoNoticeCodeMsg(String orderId, String code,String[] phones,MessageType type) throws Exception;
    
    /**
     * 充值10000模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public Map<String, Object> getRechargeMsg(String name,String[] phones,MessageType type) throws Exception;

    /**
     * 短信结果.
     * @param result
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, Object> getMessageResult(String result,String message) throws Exception;

    /**
     * 余额结果
     * @param result
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, Object> getBalanceResult(String result) throws Exception;
    
}
