/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.divtohtml.ConvertDivToHTML;
import com.intbit.AppConstants;
import com.intbit.util.ServletUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ilyas
 */
public class PreviewServlet extends BrndBotBaseHttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            super.processRequest(request, response);
            response.setContentType("text/html;charset=UTF-8");

//            ConvertDivToHTML convertDivToHTML = new ConvertDivToHTML(request);

            String htmlString = request.getParameter("htmlString");
            String iframeName=request.getParameter("iframeName");
//            htmlString = convertDivToHTML.getResponsiveHTMLFromDiv(htmlString);
            String htmlHeader = "";
            
            htmlHeader = ServletUtil.getEmailHeader();
            htmlString = htmlHeader + htmlString + "</body></html>";
            File htmlFolder = new File(AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH);
            if (!htmlFolder.exists()) {
                htmlFolder.mkdirs();
            }
            File emailTemplateFile = new File(AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator +iframeName+".html");
            if(!emailTemplateFile.exists()) {
                    emailTemplateFile.createNewFile();
                } 
            FileWriter emailTemplateWriter = new FileWriter(emailTemplateFile, false); // true to append
            // false to overwrite.
            emailTemplateWriter.write(htmlString);
            emailTemplateWriter.close();


            response.getWriter().write(htmlString);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(ex, "Exception while creating the responsive html:", getSqlMethodsInstance().error));
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
