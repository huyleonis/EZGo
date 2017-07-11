/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import java.util.List;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import project.ezgo.BLO.AccountMng;
import project.ezgo.BLO.TourMng;
import project.ezgo.Entity.Account;
import project.ezgo.Entity.ListAccount;
import project.ezgo.Entity.ListTour;
import project.ezgo.Util.XMLUtil;

/**
 * Web application lifecycle listener.
 *
 * @author Dells
 */
public class RequestServletListener implements ServletRequestListener {
    private final String xmlFile = "WEB-INF/account.xml";
    
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        System.out.println("Request destroyed!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String servletPath = req.getParameter("p");
        String servletPath2 = req.getParameter("action");
        AccountMng accountManager = new AccountMng();
        accountManager.getAllAccounts();
        ListAccount accounts = new ListAccount(accountManager.getAllAccounts());
        
        if ("register".equals(servletPath)) {    
            
            
            try {
                String strAccount = XMLUtil.marshalToXmlString(accounts);
                sre.getServletRequest().setAttribute("ACCOUNTLIST", strAccount);
                
                
//                String realPath = sre.getServletContext().getRealPath("/");
//                String xmlOutputFile = realPath + xmlFile;
//                sre.getServletRequest().setAttribute("FILEPATH", xmlOutputFile);
            } catch (JAXBException ex) {
                System.out.println("Error: " + ex);
            }
        } 
        if("AccountInfo".equals(servletPath2)){
            TourMng tourManager = new TourMng();
            tourManager.getAllTours();
            ListTour tours = new ListTour(tourManager.getAllTours());
            
            try {
                String strAccount = XMLUtil.marshalToXmlString(accounts);
                sre.getServletRequest().setAttribute("ACCOUNTLIST", strAccount);
                String strTour = XMLUtil.marshalToXmlString(tours);
                System.out.println("Tour string " + strTour);
                sre.getServletRequest().setAttribute("TOURLIST", strTour);
            } catch (JAXBException ex) {
                System.out.println("Error eeeeeeeee: " + ex);
            }
        }

    }
}
