/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import project.ezgo.BLO.AccountMng;
import project.ezgo.BLO.FavoriteMng;
import project.ezgo.Entity.Account;
import project.ezgo.Entity.ListFavorite;
import project.ezgo.Util.XMLUtil;

/**
 *
 * @author hp
 */
public class Login extends HttpServlet {

    private final String index = ".";
    private final String login = "?p=login";

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

        String url = login;
        String error = null;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");

            if (email != null && password != null) {

                AccountMng mng = new AccountMng();

                boolean result = mng.checkLogin(email, password);

                if (result) {
                    Account a = mng.findAccount(email);

                    Integer accountId = a.getAccountID();
                    FavoriteMng favMng = new FavoriteMng();
                    ListFavorite lst = new ListFavorite(accountId, favMng.getFavoriteByAccount(accountId));                    

                    
                    
                    HttpSession session = request.getSession(true);
                    session.setAttribute("ACCOUNT_ID", a.getAccountID());
                    session.setAttribute("ACCOUNT_FULLNAME", a.getFullname());
                    session.setAttribute("LIST_FAVORITE", lst.toString());
                    session.removeAttribute("LAST_UPDATE");

                    url = index;

                    if (remember != null) {
                        Cookie ckUsename = new Cookie("ACC_USERNAME", a.getUsername());
                        Cookie ckPassword = new Cookie("ACC_PASSWORD", a.getPassword());
                        ckUsename.setMaxAge(60 * 60 * 24 * 30);
                        ckPassword.setMaxAge(60 * 60 * 24 * 30);
                        response.addCookie(ckUsename);
                        response.addCookie(ckPassword);
                    } // end if check remember

                } else { //end if check login result
                    error = "Email/Mật khẩu không chính xác";
                }//end else check login result

            } else { //end if check null
                error = "Email/Mật khẩu không gửi được";
            }// end else check null

            if (error != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_ERROR", error);
            }

            response.sendRedirect(url);
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
