/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import project.ezgo.BLO.AccountMng;
import project.ezgo.BLO.TourMng;
import project.ezgo.Entity.ListAccount;
import project.ezgo.Entity.ListTour;
import project.ezgo.Util.XMLUtil;

/**
 * Web application lifecycle listener.
 *
 * @author Dells
 */
public class RequestServletListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String servletPath = req.getParameter("p");
        
        if ("register".equals(servletPath)) {    
            AccountMng accountManager = new AccountMng();
            accountManager.getAllAccounts();
            ListAccount accounts = new ListAccount(accountManager.getAllAccounts());
            try {
                String strAccount = XMLUtil.marshalToXmlString(accounts);
                System.out.println("Account string " + strAccount);
                sre.getServletRequest().setAttribute("ACCOUNTLIST", strAccount);
            } catch (JAXBException ex) {
                System.out.println("Error eeeeeeeee: " + ex);
            }
        } else if("accountinfo".equals(servletPath)){
            System.out.println("Request initialized!");
            TourMng tourManager = new TourMng();
            tourManager.getAllTours();
            ListTour tours = new ListTour(tourManager.getAllTours());
            
            try {
                String strTour = XMLUtil.marshalToXmlString(tours);
                System.out.println("Tour string " + strTour);
                sre.getServletRequest().setAttribute("TOURLIST", strTour);
            } catch (JAXBException ex) {
                System.out.println("Error eeeeeeeee: " + ex);
            }
        }

    }
}
