/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.*;

/**
 *
 * @author intbit
 */
public class changeLogo extends BrndBotBaseHttpServlet {


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
        getSqlMethodsInstance().session = request.getSession();
    String filePath = null;
    String fileName = null, fieldName = null, uploadPath = null, uploadType = null;
    RequestDispatcher request_dispatcher = null;

        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        try {
            uploadPath = AppConstants.USER_LOGO;

            // Verify the content type
            String contentType = request.getContentType();
            
                    if ((contentType.indexOf("multipart/form-data") >= 0)) {

                        DiskFileItemFactory factory = new DiskFileItemFactory();
                        // maximum size that will be stored in memory
                        factory.setSizeThreshold(maxMemSize);
                        // Location to save data that is larger than maxMemSize.
                        factory.setRepository(new File(AppConstants.TMP_FOLDER));

                        // Create a new file upload handler
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        // maximum file size to be uploaded.
                        upload.setSizeMax(maxFileSize);

                        // Parse the request to get file items.
                        List fileItems = upload.parseRequest(request);

                        // Process the uploaded file items
                        Iterator i = fileItems.iterator();

                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>JSP File upload</title>");
                        out.println("</head>");
                        out.println("<body>");
                        while (i.hasNext()) {
                            FileItem fi = (FileItem) i.next();
                            if (fi.isFormField()) {
                                fieldName = fi.getFieldName();
                                if (fieldName.equals("upload")) {
                                    uploadType = fi.getString();
                                }
                                
                            }else{    
                                // Get the uploaded file parameters
                                fieldName = fi.getFieldName();
                                fileName = fi.getName();

                                Integer UID = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
                                
                                uploadPath = uploadPath + File.separator + UID + File.separator + "logo";

                                File uploadDir = new File(uploadPath);
                                if (!uploadDir.exists()) {
                                    uploadDir.mkdirs();
                                }

//                                int inStr = fileName.indexOf(".");
//                                String Str = fileName.substring(0, inStr);
//
//                                fileName = Str + "_" + UID + ".jpeg";
                                fileName = fileName + "_" + UID;
                                getSqlMethodsInstance().session.setAttribute("ImageFileName", fileName);
                                boolean isInMemory = fi.isInMemory();
                                long sizeInBytes = fi.getSize();
                                
                                if (uploadType.equals("update")){
                                    String file_name_to_delete = getSqlMethodsInstance().getLogofileName(UID);
                                    String filePath_to_delete = uploadPath + File.separator + file_name_to_delete;

                                    File deletefile = new File(filePath_to_delete);
                                    deletefile.delete();
                                }
                                
                                filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                fi.write(storeFile);

                                getSqlMethodsInstance().updateUsers(UID, fileName);
                                out.println("Uploaded Filename: " + filePath + "<br>");
                            
                                response.sendRedirect(request.getContextPath() + "/settings.jsp");

                            }
                            
                        }
                    }
                
            
        } catch (Exception ex) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Exception while updating org name:", getSqlMethodsInstance().error));

            out.println(getSqlMethodsInstance().error);
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
