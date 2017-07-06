/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.JAXB;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 *
 * @author Dells
 */
public class JAXBValidationHandler implements ValidationEventHandler{

    @Override
    public boolean handleEvent(ValidationEvent event) {
        if(event.getSeverity() == ValidationEvent.FATAL_ERROR
                || event.getSeverity() == ValidationEvent.ERROR) {
            ValidationEventLocator locator = event.getLocator();
            
            // Thông báo lỗi
            System.out.println("Invalid XML document: "
                    + locator.getURL());
            System.out.println("Error: " + event.getMessage());
            
            // In hàng & cột lỗi
            System.out.println("Error at column " +
                    locator.getColumnNumber() + 
                    ", line " +
                    locator.getLineNumber());
            return false;
        }
        return true;
    }
    
}
