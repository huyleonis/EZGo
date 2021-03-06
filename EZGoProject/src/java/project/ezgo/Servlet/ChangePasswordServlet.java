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
import project.ezgo.BLO.AccountMng;
import project.ezgo.Entity.Account;

/**
 *
 * @author Dells
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepass"})
public class ChangePasswordServlet extends HttpServlet {

    String managePage = "?p=account";

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
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        HttpSession session = request.getSession();
        String accountID = session.getAttribute("ACCOUNT_ID").toString();
        try {
            AccountMng manager = new AccountMng();
            Account account = manager.getAccountById(accountID);
            if (oldpass.equals(account.getPassword())) {
                boolean result = manager.updatePassword(accountID, newpass);
                if (!result) {
                    request.setAttribute("ERROR", "Lỗi xảy ra, vui lòng thử lại sau!");
                } else {
                    request.setAttribute("ERROR", "Đã đổi mật khẩu thành công!");
                    request.setAttribute("ACCOUNT", account);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            request.setAttribute("ERROR", "Lỗi xảy ra, vui lòng thử lại sau!");
        } finally {
            request.setAttribute("currentTab", "change-pass");
            RequestDispatcher rd = request.getRequestDispatcher(managePage);
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
