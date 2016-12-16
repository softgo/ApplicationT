package com.application.base.utils.message.config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.application.base.utils.message.constant.Constant;
import com.application.base.utils.message.util.MD5;
import com.application.base.utils.message.util.MessageUtil;


/**
 * 无线未来短信参数配置
 */
public class WLWXMessageConfig extends MessageConfig {
    
    private static final long serialVersionUID = 1L;
    
    protected String spcode = "106904020107808";//接入码（扩展码）
    
    protected int taskId;

    public WLWXMessageConfig() {
        this.setAccount(MessageUtil.getMsgVal("wxwl_account","820004"));
        this.setPassword(MessageUtil.getMsgVal("wxwl_password","TD95H5ARFT"));
    }
    
    /**
     * 初始化手机号,用 "," 分割.
     * @return
     */
    public WLWXMessageConfig initConfig(String[] phones){
        WLWXMessageConfig config = new WLWXMessageConfig();
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
        config.setSpcode(spcode);
        return config;
    }
    
    public String getSpcode() {
        return spcode;
    }
    
    public void setSpcode(String spcode) {
        this.spcode = spcode;
    }
    
    public int getTaskId() {
        return taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * 发送消息.
     */
    public Map<String, Object> toSendMsg() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String urlencContent = URLEncoder.encode(this.getContent(),"utf-8");
            String sign = MD5.sign(urlencContent, this.getPassword(), "utf-8");
            String postData = "content=" + urlencContent + "&destMobiles="
                    + this.getMobiles() + "&sign=" + sign + "&cust_code=" + this.getAccount()
                    + "&sp_code=" + this.getSpcode() + "&task_id=" + getTaskId() ;
            System.err.println("无线未来发送的数据 : "+postData);
            URL myurl = new URL(MessageUtil.getMsgVal("wlwx_messageUrl",Constant.WXWL_MESSAGE_URL));
            URLConnection urlc = myurl.openConnection();
            urlc.setReadTimeout(1000 * 30);
            urlc.setDoOutput(true);
            urlc.setDoInput(true);
            urlc.setAllowUserInteraction(false);
            DataOutputStream server = new DataOutputStream(urlc.getOutputStream());
            server.write(postData.getBytes("UTF-8"));
            server.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF-8"));
            int index = 0 ;
            String result = "";
            while ((result = in.readLine()) != null){
                if (index==0) {
                   break;
                }
            }
            in.close();
            String[] results = result.split(":");
            System.out.println(" result = "+result+" results = "+results);
            if (results[0].equalsIgnoreCase("SUCCESS")) {
                resultMap.put("code",Constant.SUCCESS);
                resultMap.put("msg", "短信发送成功");
                resultMap.put("message", this.getContent());
                return resultMap;
            }else{
                resultMap.put("code",Constant.FAIL);
                resultMap.put("msg", "短信发送失败");
                resultMap.put("message", this.getContent());
                return resultMap;
            }
        }
        catch (Exception e) {
            resultMap.put("code",Constant.FAIL);
            resultMap.put("msg", "短信发送失败");
            resultMap.put("message", this.getContent());
            return resultMap;
        }
    }
}
