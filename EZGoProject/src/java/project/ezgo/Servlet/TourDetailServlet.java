/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ezgo.BLO.TourMng;
import project.ezgo.BLO.ViewHistoryMng;
import project.ezgo.Entity.Tour;

/**
 *
 * @author hp
 */
@WebServlet(name = "TourDetailServlet", urlPatterns = {"/tour-detail"})
public class TourDetailServlet extends HttpServlet {

    private final String index = "fgh";
    private final String tourDetailView = ".?p=tourDetail";

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
            String url = index;
            String id = request.getParameter("id");

            if (id != null) {
                TourMng mng = new TourMng();
                Tour t = mng.getTourById(id);

                if (t != null) {
                    request.setAttribute("TOUR", t);
                    url = tourDetailView;

                    //save to history
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        Integer accId = (Integer) session.getAttribute("ACCOUNT_ID");
                        if (accId != null) {
                            ViewHistoryMng historyMng = new ViewHistoryMng();
                            historyMng.updateHistory(id, accId);
                        }
                    }
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
