/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.PhantomImageConverter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sandeep-kumar
 */
public class AdminHtmlToImageServlet extends BrndBotBaseHttpServlet {

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
        try{
             String htmlString = request.getParameter("htmlString");
             String width= request.getParameter("containerWidth").replace("px", "");
            String height= request.getParameter("containerHeight").replace("px", "");
           PhantomImageConverter phantomImageConverter = new PhantomImageConverter(getServletContext());
           File imagePngFile = phantomImageConverter.getImage(htmlString, width, height, "0", "0");
        
        String filename=imagePngFile.getName();
        System.err.println(filename);
        
         response.setContentType("text/plain");
        response.getWriter().write(filename);
        }
        catch(Exception e){
        response.setContentType("text/html;charset=UTF-8");
           StringBuffer sb = new StringBuffer();
           out = response.getWriter();
           sb.append("<html><body><h2>");
           sb.append(e.getLocalizedMessage());
           sb.append("</body></html>");
           out.println(sb);
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
