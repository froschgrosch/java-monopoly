package IO;

import java.io.FileInputStream;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLparser
{
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;

    public XMLparser()
    {
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  
        }
    }

    public Element getDocumentElement(String filename){
        Element output = null;
        try {
            output = builder.parse(new FileInputStream(filename)).getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return output;
    }
}
