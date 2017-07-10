package project.ezgo.Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import project.ezgo.BLO.TourMng;
import project.ezgo.Entity.ListTour;
import project.ezgo.Util.XMLUtil;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
    private final String indexPage = "index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            HttpSession session = request.getSession(false);
            
            boolean stillAvailable = false;
            
            if (session != null) {                
                
                Date lastUpdate = (Date) session.getAttribute("LAST_UPDATE");
                String xml = (String) session.getAttribute("LIST_TOUR");
                if (xml != null && lastUpdate != null) {
                    Date now = new Date();
                    long diff = Math.abs(now.getTime() - lastUpdate.getTime());
                    long diffDay = diff / (1000*60*60*24);
                    if (diffDay < 1) {
                        stillAvailable = true;
                    }
                }    
                
            }
            
            if (!stillAvailable) {                
                TourMng tourMng = new TourMng();
                ListTour list;
                if (session != null && session.getAttribute("ACCOUNT_ID") != null) {
                    Integer accId = (Integer) session.getAttribute("ACCOUNT_ID");
                    list = new ListTour(tourMng.getFavoriteTours(accId));
                } else {
                    list = new ListTour(tourMng.getPopularTour());
                }                 

                String xml = XMLUtil.marshalToXmlString(list);

                session = request.getSession(true);
                session.setAttribute("LIST_TOUR", xml);
                session.setAttribute("LAST_UPDATE", new Date());
            }
            
            
            RequestDispatcher rd = request.getRequestDispatcher(indexPage);
            rd.forward(request, response);
            
        } catch (JAXBException ex) {
            log("JAXB Exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
