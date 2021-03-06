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

/**
 *
 * @author hp
 */
@WebServlet(name = "ProcessServlet", urlPatterns = {"/process"})
public class ProcessServlet extends HttpServlet {
    private final String index = ".";
    private final String loginServlet = "/login";
    private final String accountInfoServlet = "/AccountInfo";
    private final String registerServlet = "/register";
    private final String findAccountServlet = "/findacc";
    private final String tourDetailServlet = "/tour-detail";
    private final String saveFavoriteServlet = "/save-favorite";
    private final String deleteAccountServlet = "/delaccount";
    private final String changePassServlet = "/changepass";
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
        String url = index;
        
        try {
            String btn = request.getParameter("action");
            
            if (btn.equals("Login")) {
                url = loginServlet;
            } else if (btn.equals("AccountInfo")) {
                url = accountInfoServlet;
            } else if(btn.equals("Register")){
                url = registerServlet;
            } else if(btn.equals("SearchAccount")){
                url = findAccountServlet;
            } else if(btn.equals("TourDetail")) {
                url = tourDetailServlet;
            } else if(btn.equals("SaveFavorite")) {
                url = saveFavoriteServlet;
            } else if(btn.equals("DeleteAccount")){
                url = deleteAccountServlet;
            } else if(btn.equals("changepass")){
                url = changePassServlet;
            }
            
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
