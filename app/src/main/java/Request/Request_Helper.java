package Request;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import Parser.JsonParser;
import Parser.XmlParserHelper;
import Parser.XmlParser_Pull;
import vaycent.magicLog.mlog;
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
        listenerInterface=new StringListenerInterface()
        {
            @Override
            public void responseAction(String response) {
                mlog.xml(response);

                XmlParser_Pull pullParser = new XmlParser_Pull(response);
//                XmlParser_Dom domParser = new XmlParser_Dom(response);
            }
            @Override
            public void errorAction(VolleyError error) {
                mlog.v(error.getMessage());
            }
        };

        String link="https://vaycent.github.io/atom.xml";
        appContext.volleySharp.startGetRequest(link,listenerInterface);
    }


    public  void testPostRequest(){
        StringListenerInterface listenerInterface;
        listenerInterface=new StringListenerInterface()
        {
            @Override
            public void responseAction(String response) {
                mlog.xml(response);
//                String respnseStatus=xmlParserHelper.checkXmlParserDom_Status(response);
//                if(respnseStatus.equalsIgnoreCase("succ")){
//                    BPList bpList=xmlParserHelper.xmlParserDom_Demo(response);
//                    //Can add db here.
//                }
            }
            @Override
            public void errorAction(VolleyError error) {
                mlog.v(error.getMessage());
            }
        };

        String link="http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";

        Map<String, String> map = new HashMap<String, String>();
        map.put("date", "2016-09-16");
        map.put("fromstation", "BJP");
        map.put("tostation", "SHH");
        map.put("starttime", "00:00--24:00");

        appContext.volleySharp.startPostRequest(link,listenerInterface,map);


    }


    public void testJsonRequest(){
        JsonListenerInterface listenerInterface;
        listenerInterface=new JsonListenerInterface()
        {
            @Override
            public void responseAction(String response) {
                mlog.json(response);

                JsonParser jsonParser = new JsonParser(response);
            }
            @Override
            public void errorAction(VolleyError error) {
                mlog.v(error.getMessage());
            }
        };

        String link="http://gc.ditu.aliyun.com/geocoding?a=广州市";
        appContext.volleySharp.startJsonRequest(link,listenerInterface);
    }
}
