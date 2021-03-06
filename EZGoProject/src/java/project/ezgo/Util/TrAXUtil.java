/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author hp
 */
public class TrAXUtil {
    
    
    public static void transform(String xmlSourceFile, String xslTemplateFile, 
            String resultFile) throws TransformerConfigurationException, 
            FileNotFoundException, TransformerException {
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        StreamSource xsl = new StreamSource(xslTemplateFile);
        StreamSource xml = new StreamSource(xmlSourceFile);
        StreamResult result = new StreamResult(new FileOutputStream(resultFile));
        
        Templates template = tf.newTemplates(xsl);
        Transformer trans = template.newTransformer();
        trans.transform(xml, result);
        
    }
    
    public static String transformGetXmlString(String xmlSource, String xslFile) throws TransformerConfigurationException, TransformerException {
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        
        StreamSource xsl = new StreamSource(new File(xslFile));        
        Templates template = tf.newTemplates(xsl);
        Transformer trans = template.newTransformer();
        
        StreamSource xml = new StreamSource(new StringReader(xmlSource));
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        trans.transform(xml, result);
        
        return sw.toString();
    }
                
}
