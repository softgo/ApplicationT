package com.application.base.utils.message.template;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 消息通知短信模板编写.
 */
public class MessageTemplate {
    
    private final static String encodeing = "UTF-8";
    
    /**
     * 注册验证码模板
     * @param code
     * @return
     */
    public String registCodeMsg(String code){
        String result = new StringBuffer("您的验证码为:"+code+",为保证账号安全,其勿泄露,如非本人操作,请忽略。").toString();
        return getUTF8(result);
    }
    
    /**
     * 提交申请
     * @param money
     * @return
     */
    public String submitMessageMsg(String money){
        String result = new StringBuffer("您已成功提交申请,申请金额:"+money+"元,请等待5-10分钟查看结果。如有疑问请咨询麦芽分期微信公众账号。").toString();
        return getUTF8(result);
    }
    
    /**
     * 审核通过
     * @param money
     * @return
     */
    public String windControlPassMsg(String money){
        String result = new StringBuffer("您的分期消费已审批通过,通过金额:"+money+"元。请进入麦芽分期公众号(微信号:maiyafenqi)确认订单,获取放款。如有问题可拨打客服热线 400-064-6668。").toString();
        return getUTF8(result);
    }
    
    /**
     * 审核拒绝
     * @return
     */
    public String windControlRefuseMsg(){
        String result = new StringBuffer("很遗憾,您的分期消费未审批通过。非常感谢您的支持,您可进入麦芽分期公众号(微信号:maiyafenqi)查看其他优惠服务。").toString();
        return getUTF8(result);
    }
    
    /**
     * 审核回退
     * @return
     */
    public String windControlBackMsg(){
        String result = new StringBuffer("您的申请正在审核中,个人信息填写有误,请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 平台放款
     * @param dateStr
     * @return
     */
    public String platformGiveMoneyMsg(String dateStr){
        String result = new StringBuffer("您的消费分期已筹资完毕,且放款成功。请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 平台放款祝福语
     * @param dateStr
     * @return
     */
    public String platformGiveWishesMsg(){
        String result = new StringBuffer("麦芽分期为您提供了一次免费保理服务。选择麦芽，每天都是美美哒。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户还款
     * @param dateStr 2016年6月8日
     * @param money
     * @return
     */
    public String userReturnMoneyNoticeMsg(String dateStr,String money){
        String result = new StringBuffer("您"+dateStr+"前记得登录麦芽分期公众号(微信号:maiyafenqi)还款哦,本期要还"+money+"元。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户还款成功
     * @param money
     * @return
     */
    public String userReturnMoneySuccessMsg(String money){
        String result = new StringBuffer("您本期账单已还款成功,本期还款金额"+money+"元。麦芽分期,为更好的自己。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户还款失败
     * @return
     */
    public String userReturnMoneyFailMsg(String money){
        String result = new StringBuffer("亲爱的用户,您本期麦芽分期应还款金额"+money+"元,您的还款操作失败,请稍后重试或使用其他银行卡还款!").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户逾期
     * @return
     */
    public String userOverdueOneMoneyMsg(){
        String result = new StringBuffer("亲爱的用户,您已逾期1天,请今日务必点击“还款”按钮进行还款,减轻逾期带来的不良影响。请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户逾期
     * @return
     */
    public String userOverdueTwoMoneyMsg(){
        String result = new StringBuffer("您已逾期2天,速还款。若逾期超30天或失联等,系统会将您的案子移交专催团队,通过法律手段要求一次性全额还款并视情况记入征信系统。请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户逾期
     * @return
     */
    public String userOverdueFiftheenMoneyMsg(){
        String result = new StringBuffer("您已逾期15天,速还款。若逾期超30天或失联等,系统会将您的案子移交专催团队,通过法律手段要求一次性全额还款并视情况记入征信系统 。请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 用户逾期
     * @return
     */
    public String userOverdueThirtyMoneyMsg(){
        String result = new StringBuffer("您离逾期30天仅剩3天,即将被系统移交专催团队通过法律手段要求一次性全额还款并视情况记入征信系统。速还款,以避免被移交专催团队。请进入麦芽分期公众号(微信号:maiyafenqi)查看。").toString();
        return getUTF8(result);
    }
    
    /**
     * 简单注册验证码模板(注册验证).
     * @param code
     * @return
     */
    public String getRegistCodeMsg(String code){
        String result = new StringBuffer("感谢您的注册,验证码:"+code+",请及时输入").toString();
        return getUTF8(result);
    }
    
    /**
     * 简单验证码模板.
     * @param code
     * @return
     */
    public String getSimpleCodeMsg(String code){
        String result = "您的验证码是:"+code;
        return getUTF8(result);
    }
    
    
    /**
     * 简单验证码模板(10分钟有效).
     * @param code
     * @return
     */
    public String getTenCodeMsg(String code){
        String result = "您好,您的验证码:"+code+",10分钟内有效";
        return getUTF8(result);
    }
    
    
    /**
     * 注册通知模板.
     * 
     * @param group
     * @param phone
     * @return
     */
    public String getRegistNoticeMsg(String group, String phone) {
        String result = "感谢您注册" + group + "会员,为了您的安全请及时去更换您的初始密码,如有疑问请联系:" + phone;
        return getUTF8(result);
    }
    
    /**
     * 密码验证码模板.
     * 
     * @param pass
     * @param phone
     * @return
     */
    public String getPassNoticeCodeMsg(String pass, String phone) {
        String result = "您的帐号密码为:" + pass + ",请查收!客服电话:" + phone;
        return getUTF8(result);
    }
    
    /**
     * 订单验证码模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public String getOrderNoticeCodeMsg(String orderId, String code) {
        String result = "您此次购买商品的订单号为:" + orderId + ",提货码为:" + code + ",门店提货以此提货码为凭据，请牢记！";
        return getUTF8(result);
    }
    
    /**
     * 订单信息验证码模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public String getOrderInfoNoticeCodeMsg(String orderId, String code) {
        String result = "您此次购买商品的订单号为:" + orderId + ",提货码为:" + code + ",门店提货以此提货码为凭据,请牢记!";
        return getUTF8(result);
    }
    
    /**
     * 充值10000模板.
     * 
     * @param orderId
     * @param code
     * @return
     */
    public String getRechargeMsg(String name) {
        String result = "尊敬的" + name + ",您已成功充值10000元,登录后可在我的账户中查看";
        return getUTF8(result);
    }
    
    /**
     * utf8 编码
     * @param content
     * @return
     */
    public String getUTF8(String content) {
        try {
            content = new String(content.getBytes(), encodeing);
        }
        catch (UnsupportedEncodingException e) {
            try {
                content = URLEncoder.encode(content, encodeing);
            }
            catch (UnsupportedEncodingException e1) {
            }
        }
        return content;
    }
}
