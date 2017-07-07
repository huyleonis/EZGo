/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import project.ezgo.BLO.FavoriteMng;
import project.ezgo.Entity.Favorite;
import project.ezgo.Entity.FavoriteTransaction;
import project.ezgo.Entity.ListFavorite;
import project.ezgo.Entity.ListFavoriteTransaction;
import project.ezgo.Util.FavoriteValidationHandler;
import project.ezgo.Util.XMLUtil;

/**
 *
 * @author hp
 */
@WebServlet(name = "SaveFavoriteServlet", urlPatterns = {"/save-favorite"})
public class SaveFavoriteServlet extends HttpServlet {

    private final String favSchema = "WEB-INF/schema/favorite.xsd";

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

            String realPath = this.getServletContext().getRealPath("/");
            String schemaFilePath = realPath + favSchema;

            String xml = request.getParameter("xml");
            ListFavoriteTransaction result = XMLUtil.unmarshallXmlToObject(xml,
                    schemaFilePath, ListFavoriteTransaction.class,
                    new FavoriteValidationHandler());

            if (result != null) {
                Integer accountId = result.getAccountId();

                if (accountId != null && result.getList() != null) {
                    FavoriteMng mng = new FavoriteMng();

                    for (FavoriteTransaction tran : result.getList()) {
                        try {
                            if (tran.getAction() != null && tran.getTourId() != null) {
                                if (tran.getAction().equals("add")) {
                                    mng.updateFavorite(tran.getTourId(), accountId);
                                } else if (tran.getAction().equals("remove")) {
                                    mng.removeFavorite(tran.getTourId(), accountId);
                                }//end add/remove favorite
                            }//end check fields in tran != null
                        } catch (Exception e) {
                            log("Exception when add/remove favorite: " + e.getMessage());
                        }

                    }//end for list transaction

                    ListFavorite lst = new ListFavorite(accountId, mng.getFavoriteByAccount(accountId));

                    HttpSession session = request.getSession();
                    session.setAttribute("LIST_FAVORITE", lst.toString());
                }

            }

            out.print("true");

        } catch (JAXBException ex) {
            log("JAXB Exception: " + ex.getMessage());
            ex.printStackTrace();
            out.print("false");
        } catch (SAXException ex) {
            log("SAX Parser Exception: " + ex.getMessage());
            ex.printStackTrace();
            out.print("false");
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
