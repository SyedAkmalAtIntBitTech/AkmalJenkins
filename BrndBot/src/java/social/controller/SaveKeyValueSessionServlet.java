/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sandeep-kumar
 */
public class SaveKeyValueSessionServlet extends BrndBotBaseHttpServlet {
    SqlMethods sqlmethods = new SqlMethods();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String process = request.getParameter("process");
        String send="";
        if(process.equals("save"))
        {
            try
            {
                getSqlMethodsInstance().session = request.getSession(true);
                String sessionValue = request.getParameter("sessionValue");
                String sessionKey = request.getParameter("sessionKey");
                String sessionIframeKey = request.getParameter("sessionIframeKey");
                String sessionIframevalue = request.getParameter("sessionIframevalue");
                getSqlMethodsInstance().session.setAttribute(sessionKey, sessionValue);
                getSqlMethodsInstance().session.setAttribute(sessionIframeKey, sessionIframevalue);          
            }
            catch (Exception e)
            {
                 logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));
            }
        }
        else if(process.equals("draft"))
        {send=process+"..";
            try
            {
                getSqlMethodsInstance().session = request.getSession(true);
                String sessionValue = request.getParameter("sessionValue");
                String sessionKey = request.getParameter("sessionKey");
                String sessionIframeKey = request.getParameter("sessionIframeKey");
                String sessionIframevalue = request.getParameter("sessionIframevalue");
                
                getSqlMethodsInstance().session.setAttribute(sessionKey, sessionValue);
                getSqlMethodsInstance().session.setAttribute(sessionIframeKey, sessionIframevalue); 
                String emailSubject=(String)sqlmethods.session.getAttribute("email_subject");
                String subCategoryName=(String)sqlmethods.session.getAttribute("sub_category_name");
                String subCategoryId=(String)sqlmethods.session.getAttribute("sub_category_id");
                String categoryId=(String)sqlmethods.session.getAttribute("category_id");
                String emailAddresses=(String)sqlmethods.session.getAttribute("email_addresses");
                String emailList=(String)sqlmethods.session.getAttribute("email_list");
                
                send+="emailList - "+emailList;
            }
            catch (Exception e)
            {
                 logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));
            }
        }
       out.print(send);
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
