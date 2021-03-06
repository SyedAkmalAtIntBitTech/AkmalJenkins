/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.AppConstants;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ServletAddLooks extends BrndBotBaseHttpServlet {
    private static final Logger logger = Logger.getLogger(ServletAddLooks.class.getName());
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
        
        super.processRequest(request, response);
            String filePath;
            String fileName, fieldName;
            Looks look;
            RequestDispatcher request_dispatcher;
            String look_name = null;
            Integer organization_id = 0;
            boolean check;
            look = new Looks();
            check = false;

        response.setContentType("text/html;charset=UTF-8");
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        try {
            String uploadPath = AppConstants.LOOK_IMAGES_HOME;

            // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("/tmp"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(maxFileSize);

                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    
                    if (fi.isFormField()) {
                        // Get the uploaded file parameters
                        fieldName = fi.getFieldName();
                        try {
                            if (fieldName.equals("lookname")) {
                                look_name = fi.getString();
                            }
                            if (fieldName.equals("organization")) {
                                organization_id = Integer.parseInt(fi.getString());
                            }
                        }catch (Exception e){
                            logger.log(Level.SEVERE, 
                                    "Exception while getting the look_name and organization_id", e);
                        }
                    } else {
                        check = look.checkAvailability(look_name, organization_id);

                        if (check == false){
                            fieldName = fi.getFieldName();
                            fileName = fi.getName();

                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }

//                            int inStr = fileName.indexOf(".");
//                            String Str = fileName.substring(0, inStr);
//
//                            fileName = look_name + "_" + Str + ".png";
                            fileName = look_name + "_" + fileName;
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);

                            fi.write(storeFile);

                            look.addLooks(look_name, fileName, organization_id);
                            response.sendRedirect(request.getContextPath() + "/admin/looks.jsp");
                        }else {
                            response.sendRedirect(request.getContextPath() + "/admin/looks.jsp?exist=exist");
                        }
                        
                    }
                }

            } else {
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception while uploading the Looks image", ex);
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
