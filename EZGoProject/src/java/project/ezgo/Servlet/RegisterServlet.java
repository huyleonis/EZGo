/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import project.ezgo.BLO.AccountMng;
import project.ezgo.BLO.RoleMng;
import project.ezgo.BLO.TourMng;
import project.ezgo.Entity.Account;
import project.ezgo.Entity.ListAccount;
import project.ezgo.JAXB.JAXBValidationHandler;

/**
 *
 * @author hp
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private String loginPage = "?p=login";
    private String registerPage = "?p=register";

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
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = "";
        String url = registerPage;
        try {

            // unmarshall & validate data
//            JAXBContext context = JAXBContext.newInstance("project.ezgo.JAXB");          
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            unmarshaller.setEventHandler(new JAXBValidationHandler());
//            File file = new File("web/WEB-INF/account.xml");
//            Account account = (Account) unmarshaller.unmarshal(file);

            // lưu xuống DB
            AccountMng manager = new AccountMng();
            RoleMng rolemng = new RoleMng();
            
            boolean createResult = manager.createNewAccount(new Account(Integer.BYTES, 
                    username, password, email, rolemng.getRoleByID(1)));
            if (!createResult) {
                error = "Xảy ra lỗi, xin vui lòng thử lại sau!";
            } else {
                url = loginPage;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("Registration fail - Error: " + e);
            error = "Xảy ra lỗi, xin hãy thử lại sau!";
        } finally {
            request.setAttribute("ERROR", error);
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
