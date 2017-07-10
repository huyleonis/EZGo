/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

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

    public static <T extends Object> T unmarshallXmlToObject(String xml,
            String schemaFile, Class<T> classMarshall,
            ValidationEventHandler handler) throws JAXBException, SAXException, IOException {

        JAXBContext jc = JAXBContext.newInstance(classMarshall);        
        Unmarshaller unmar = jc.createUnmarshaller();
        
        
        if (schemaFile != null && !schemaFile.isEmpty()) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(schemaFile));

            
            if (handler == null) {
                handler = new DefaultValidationEventHandler();
            }
                        
            unmar.setSchema(schema);
            unmar.setEventHandler(handler);
        }
        T result = (T) unmar.unmarshal(new StringReader(xml));

        return result;
    }
}
