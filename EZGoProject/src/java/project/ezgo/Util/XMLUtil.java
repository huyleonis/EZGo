/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Util;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author hp
 */
public class XMLUtil {
    
    public static String marshalToXmlString(Object obj) throws JAXBException {
        
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller mar = context.createMarshaller();        
        //mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        
        StringWriter sw = new StringWriter();
        mar.marshal(obj, sw);
        
        return sw.toString();
    }
}
