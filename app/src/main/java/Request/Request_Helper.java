package Request;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import DataBase.Bean.BPList;
import XmlParser.XmlParserHelper;
import vaycent.vaycentproject.ApplicationContext;
import vaycent.volleysharp.JsonListenerInterface;
import vaycent.volleysharp.StringListenerInterface;

/**
 * Created by Vaycent on 16/9/7.
 */
public class Request_Helper {
    private Context context;
    private ApplicationContext appContext;
    private XmlParserHelper xmlParserHelper=new XmlParserHelper();

    public Request_Helper(Context ct, ApplicationContext ac){
        context=ct;
        appContext=ac;
    }

    public  void testGetRequest(){
        StringListenerInterface listenerInterface;
        listenerInterface=new StringListenerInterface(context, StringListenerInterface.responseListener, StringListenerInterface.errorListener)
        {
            @Override
            public void responseAction(String response) {
                System.out.println("response:"+response);
            }
            @Override
            public void errorAction(VolleyError error) {
                System.out.println("error:"+error.getMessage());
            }
        };

        String link="http://waptest.smartone.com/ehc.api.app.BP?action=R&mac=04:EA:72:EA:50:49:80&period=360&grouptype=m";
        appContext.volleySharp.startGetRequest(link,listenerInterface);


    }


    public  void testPostRequest(){
        StringListenerInterface listenerInterface;
        listenerInterface=new StringListenerInterface(context, StringListenerInterface.responseListener, StringListenerInterface.errorListener)
        {
            @Override
            public void responseAction(String response) {
//                System.out.println("response:"+response);
                String respnseStatus=xmlParserHelper.checkXmlParserDom_Status(response);
                if(respnseStatus.equalsIgnoreCase("succ")){
                    BPList bpList=xmlParserHelper.xmlParserDom_Demo(response);
                    //Can add db here.
                }
            }
            @Override
            public void errorAction(VolleyError error) {
//                System.out.println("error:"+error.getMessage());
            }
        };

        String link="http://waptest.smartone.com/ehc.api.app.BP";

        Map<String, String> map = new HashMap<String, String>();
        map.put("action", "R");
        map.put("period", "360");
        map.put("mac", "04:09:9A:F2:58:2B:80");
        map.put("grouptype", "m");


        appContext.volleySharp.startPostRequest(link,listenerInterface,map);


    }


    public void testJsonRequest(){
        JsonListenerInterface listenerInterface;
        listenerInterface=new JsonListenerInterface(context, JsonListenerInterface.responseListener, StringListenerInterface.errorListener)
        {
            @Override
            public void responseAction(String response) {
                System.out.println("response:"+response);

            }
            @Override
            public void errorAction(VolleyError error) {
                System.out.println("error:"+error.getMessage());
            }
        };

        String link="http://gc.ditu.aliyun.com/geocoding?a=%E5%B9%BF%E5%B7%9E%E5%B8%82";
        appContext.volleySharp.startJsonRequest(link,listenerInterface);
    }
}
