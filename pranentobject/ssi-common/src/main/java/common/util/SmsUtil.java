/** 
 * <pre>项目名称:ssi-common 
 * 文件名称:SmsUtil.java 
 * 包名:common.util 
 * 创建日期:2017年8月7日下午2:20:19 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package common.util;

import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/** 
 * <pre>项目名称：ssi-common    
 * 类名称：SmsUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月7日 下午2:20:19    
 * 修改人：徐叶    
 * 修改时间：2017年8月7日 下午2:20:19    
 * 修改备注：       
 * @version </pre>    
 */
public class SmsUtil {
	//产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIZ6tzJwEGtBBN";
    static final String accessKeySecret = "LoBj5qdnYN8M5euR4d988S9WrOAU9O";

    public static SendSmsResponse sendSms(String PhoneNumber) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(PhoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("金科教育");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_81315049");
        
        //生成验证码
        String code = String.valueOf((new Random().nextInt(899999) + 100000));
        System.out.println(code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        
        sendSmsResponse.setCode(code);

        return sendSmsResponse;
    }
}
