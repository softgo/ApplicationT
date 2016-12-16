package com.application.base.utils.message.config;

import com.application.base.utils.message.util.MessageUtil;

/**
 * 示远短信参数配置
 */
public class SYMessageConfig extends MessageConfig {

    private static final long serialVersionUID = 1L;
    private boolean needstatus = true; // 是否需要状态报告，取值true或false，true，表明需要状态报告；false不需要状态报告.
    private String product = ""; // 用户订购的产品id，不填写（针对老用户）系统采用用户的默认产品，用户订购多个产品时必填，否则会发生计费错误.
    private String extno = ""; // 可选参数，扩展码，用户定义扩展码，3位(默认不用填写).
    
    public SYMessageConfig() {
        this.setAccount(MessageUtil.getMsgVal("sy_account","ig09k4"));
        this.setPassword(MessageUtil.getMsgVal("sy_password","imA5e0wp"));
    }
    
    
    /**
     * 初始化手机号,用 "," 分割.
     * @return
     */
    public SYMessageConfig initConfig(String[] phones){
        SYMessageConfig config = new SYMessageConfig();
        config.setAccount(this.getAccount());
        config.setPassword(this.getPassword());
        StringBuffer mobiles = new StringBuffer("");
        if (phones!=null && phones.length > 0) {
            for (int i = 0; i < phones.length; i++) {
                if (i==phones.length-1) {
                    if (phones[i].length()==11) {
                        mobiles.append(phones[i]);
                    }
                }else {
                    if (phones[i].length()==11) {
                        mobiles.append(phones[i]+",");
                    }
                }
            }
        }
        config.setMobiles(mobiles.toString());
        config.setNeedstatus(needstatus);
        config.setProduct(product);
        config.setExtno(extno);
        return config;
    }
    
    public boolean getNeedstatus() {
        return needstatus;
    }
    
    public void setNeedstatus(boolean needstatus) {
        this.needstatus = needstatus;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }
    
    /**
     * 检查余额
     * @return
     */
    public String toCheck(){
        return "?account=" + this.getAccount() + "&pswd=" + this.getPassword();
    }
    
    /**
     * 发送消息.
     */
    public String toSendMsg() {
        StringBuffer phones = new StringBuffer("?account=" + this.getAccount() + "&pswd=" + this.getPassword() + "&mobile=");
        phones.append(this.getMobiles());
        phones.append("&msg=" + getContent() + "&needstatus=" + this.getNeedstatus() + "&product=" + this.getProduct()
                + "&extno=" + this.getExtno());
        return phones.toString();
    }
    
}
