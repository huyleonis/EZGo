/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 *
 * @author hp
 */
public class FavoriteValidationHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {
        if (event.getSeverity() == ValidationEvent.FATAL_ERROR ||
                event.getSeverity() == ValidationEvent.ERROR) {
            
            ValidationEventLocator locator = event.getLocator();
            
            System.out.println("There is error in parsing [favorite] xml document");            
            System.out.println("Validation  Error at [row,col] = ["
                    + locator.getLineNumber() + "," + locator.getColumnNumber()
                    + "]" );
            
            System.out.println("Error in document: " + locator.getURL());
            System.out.println("Error message: " + event.getMessage());
        }
        
        return true;
    }
    
}
