package Parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 16/9/9.
 */

public class XmlParser_Pull {
    private static final String TAG="XmlParser_Pull";


    public XmlParser_Pull(String xmlData){
        parseXmlWithPull(xmlData);
    }

    private void parseXmlWithPull(String xmlData){
        try{

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();

            String title="";

            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG: {
                            if(nodeName.equals("title"))
                                title = xmlPullParser.nextText();
//                            else if(nodeName.equals())
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if(nodeName.equals("entry"))
                        mlog.d("title:"+title);
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
