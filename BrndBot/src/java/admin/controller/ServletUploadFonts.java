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
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author intbit
 */
public class ServletUploadFonts extends BrndBotBaseHttpServlet {
    private static Logger logger = Logger.getLogger(ServletUpdateFonts.class.getName());
    
    String filePath;
    String file_name, field_name;
    Fonts fonts;
    boolean check;
    
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            fonts = new Fonts();
            check = false;
        } catch (NamingException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
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
        String filePath;
        String file_name = null, field_name, upload_path;
        RequestDispatcher request_dispatcher;
        String font_name = "", look_id;
        String font_family_name = "";
        PrintWriter out = response.getWriter();
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        
        try {

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

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()) {
                        // Get the uploaded file parameters
                        field_name = fi.getFieldName();
                        if (field_name.equals("fontname")){
                            font_name = fi.getString();
                        }
                        if (field_name.equals("fontstylecss")){
                            font_family_name = fi.getString();
                        }

                    }else {
                        
//                        check = fonts.checkAvailability(font_name);
//                        if (check == false){
                            
                            field_name = fi.getFieldName();
                            file_name = fi.getName();

                            if (file_name != ""){
                                File uploadDir = new File(AppConstants.BASE_FONT_UPLOAD_PATH);
                                if (!uploadDir.exists()) {
                                    uploadDir.mkdirs();
                                }

                                boolean isInMemory = fi.isInMemory();
                                long sizeInBytes = fi.getSize();

                                filePath = AppConstants.BASE_FONT_UPLOAD_PATH + File.separator + file_name;
                                File storeFile = new File(filePath);

                                fi.write(storeFile);

                                out.println("Uploaded Filename: " + filePath + "<br>");
                            }
                                fonts.addFont(font_name, file_name,font_family_name );
                                response.sendRedirect(request.getContextPath() + "/admin/fontsfamily.jsp");

//                            }else {
//                                response.sendRedirect(request.getContextPath() + "/admin/fontsfamily.jsp?exist=exist");
//                            }
                        }
                    }
            }
            
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while uploading fonts", e);
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
