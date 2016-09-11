package XmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import OrmliteSharp.Bean.BP;
import OrmliteSharp.Bean.BPList;

/**
 * Created by Vaycent on 16/9/9.
 */
public class XmlParserHelper {
    private XmlParser_Dom xmlPD=new XmlParser_Dom();

    public String checkXmlParserDom_Status(String content){
        Document doc = xmlPD.XMLfromString(content);

        NodeList nodes = doc.getElementsByTagName("response");
        if(nodes==null||nodes.getLength()==0){
            return "response is null";
        }

        Element e = (Element)nodes.item(0);
        String status=xmlPD.getValue(e, "status");
        if(status==null||status.equalsIgnoreCase("fail")){
            String errorcode=xmlPD.getValue(e, "errorcode");
            return errorcode;
        }
        return "succ";
    }

    public BPList xmlParserDom_Demo(String content){
        Document doc = xmlPD.XMLfromString(content);

        BPList bpList = new BPList();
        NodeList records = doc.getElementsByTagName("records");
        if(records!=null&&records.getLength()>0){
            for(int i=0;i<records.getLength();i++) {
                BP bp=new BP();

                Element record = (Element) records.item(i);
                String sys=xmlPD.getValue(record, "systolic");
                String dia=xmlPD.getValue(record, "diastolic");
                String hr=xmlPD.getValue(record, "heartrate");

                String month=xmlPD.getValue(record, "month");
                String missprevious=xmlPD.getValue(record, "missprevious");

                bp.setSys(sys);
                bp.setDia(dia);
                bp.setHr(hr);
                bp.setMonth(month);
                bp.setMissprevious(missprevious);

                bpList.add(bp);
            }
        }
        return bpList;
    }
}
