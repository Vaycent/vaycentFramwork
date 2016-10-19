package Parser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Vaycent on 16/9/9.
 */

//TODO Do this Dom parser
public class XmlParser_Dom {
    private static final String TAG="XmlParser_Dom";

    public Document XMLfromString(String xml){
        Document doc = null;
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));
            doc = docBuilder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            Log.e(TAG,"XML parse error: " + e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e(TAG,"Wrong XML file structure: " + e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG,"I/O exeption: " + e.getMessage());
            return null;
        }
        return doc;
    }

    /** Returns element value
     * @param elem element (it is XML tag)
     * @return Element value otherwise empty String
     */
    public  String getElementValue( Node elem ) {
        Node node;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( node = elem.getFirstChild(); node != null; node = node.getNextSibling() ){
                    if( node.getNodeType() == Node.TEXT_NODE  ){
                        return node.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    /** Returns element value
     * @param str element (it is XML tag)
     * @return Element value otherwise empty String
     */
    public String getValue(Element item, String str) {
        NodeList nodeList = item.getElementsByTagName(str);
        return getElementValue(nodeList.item(0));
    }
}
