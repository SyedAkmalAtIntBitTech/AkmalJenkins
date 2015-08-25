/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author sandeep-kumar
 */
public class CropImage extends BrndBotBaseHttpServlet {
    private Object dateFormat;

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
           Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ssSSS");
        PrintWriter out = response.getWriter();
         logger.log(Level.INFO, "enter in servlet");
//        sqlmethods.session = request.getSession(true);
        StringBuffer string_buffer = new StringBuffer();
        boolean check = true;
        try {

            String imageData = request.getParameter("image");
            
            imageData = imageData.replaceAll("^data:image[^;]+;base64,", "");
            logger.log(Level.INFO, getServletContext().getRealPath(""));
            byte[] data = Base64.decodeBase64(imageData);
             try (OutputStream stream = new FileOutputStream(getServletContext().getRealPath("")+"/images/temp_image/"+dateFormat.format(date)+".png")) {
                stream.write(data);
                 response.setContentType("text/plain");
                  response.getWriter().write(dateFormat.format(date)+".png");
             } 
//            String imageString=
//   
//            BASE64Decoder decoder = new BASE64Decoder();
//            imageByte = decoder.decodeBuffer(imageString);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//            image = ImageIO.read(bis);
//            bis.close();


        }
        catch(Exception e){
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

        } finally {
            out.close();
            getSqlMethodsInstance().closeConnection();
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
