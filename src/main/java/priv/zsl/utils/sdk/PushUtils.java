package priv.zsl.utils.sdk;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class PushUtils {
	//定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "7YWYNn7nSu9zxvahXSRKF1";
    private static String appKey = "RqoodSTwGE6drMTQM2gev9";
    private static String masterSecret = "hnudyiaQ5b6g0VGdV4xjh4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    //static String CID = "59f7264e914626f6d7c3727beeb809be";
    //别名推送方式
    static String Alias = "";

    
    @SuppressWarnings("deprecation")
	public static void pushAll(String title,String content) {
    	IGtPush push = new IGtPush(url, appKey, masterSecret);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(title);
        template.setText(content);
        template.setUrl("sh://12");
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
 

    public static void  pushOne(String CID,String content) {

        IIGtPush push = new IGtPush(host, appKey, masterSecret);
        IBatch batch = push.getBatch();

        try {
            //构建客户a的透传消息a
            constructClientTransMsg(CID,content,batch);
            batch.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void constructClientTransMsg(String cid, String msg ,IBatch batch) throws Exception {

        SingleMessage message = new SingleMessage();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent(msg);
        template.setTransmissionType(0); // 这个Type为int型，填写1则自动启动app
        
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(1 * 1000);

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(cid);
        batch.add(message, target);
    }
}
