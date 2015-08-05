/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import java.io.PrintWriter;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

/**
 *
 * @author Syed
 */
public class UploadImages extends BrndBotBaseHttpServlet {
    File file;
    int maxFileSize = 30000 * 1024;
    int maxMemSize = 30000 * 1024;
    String filePath = "";
    String slno,title,author,topic,act,fileName,fieldName,dat;
    Integer user_id;
    int pages, IDNo;
    RequestDispatcher rd;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String upload_path;
    
    public void init()throws ServletException{
        this.upload_path = getServletConfig().getInitParameter(upload_path);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            
            String uploadPath = getServletContext().getInitParameter("file-upload") +  File.separator + "Gallery" + File.separator + user_id;
//            upload_path = "images" + File.separator + "Gallery" + File.separator + user_id;
            
//            String uploadPath = getServletContext().getRealPath("")
//                + File.separator + upload_path;
            // creates the directory if it does not exist

            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("c://temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );        
        
            // Parse the request to get file items.
             List fileItems = upload.parseRequest(request);

             // Process the uploaded file items
             Iterator i = fileItems.iterator();

             while ( i.hasNext () ) 
             {
               FileItem fi = (FileItem)i.next();
                if ( !(fi.isFormField ()))	
                {
                    // Get the uploaded file parameters
                    fieldName = fi.getFieldName();
                    fileName = fi.getName();
//                        fileName = slno + fileName; 
                    if (fileName != ""){
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

//                        int inStr = fileName.indexOf(".");
//                        String Str = fileName.substring(0, inStr);
//
//                        fileName = user_id + "_" + Str; 
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        // Write the file
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        fi.write( storeFile ) ;
                        
                        getSqlMethodsInstance().AddImages(user_id, fileName);
                    }
                }
             }
            }catch(Exception ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
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
