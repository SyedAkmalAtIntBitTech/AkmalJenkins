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
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.*;

/**
 *
 * @author intbit
 */
public class ServletEditCategories extends BrndBotBaseHttpServlet {

    private static final Logger logger = Logger.getLogger(ServletEditCategories.class.getName());
    String filePath;
    String file_name, field_name, upload_path;
    Categories categories;
    RequestDispatcher request_dispatcher;
    String category_name, organization_id, category_id;

     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            categories = new Categories();
        } catch (NamingException ex) {
            Logger.getLogger(BrndBotBaseHttpServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        PrintWriter out = response.getWriter();

        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        try {

            upload_path = AppConstants.ORG_CATEGORIES_HOME;

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
                        // Get the uploaded file parameters
                        field_name = fi.getFieldName();
                        if (field_name.equals("category_name")) {
                            category_name = fi.getString();
                        }
                        if (field_name.equals("category_id")) {
                            category_id = fi.getString();
                        }
                        if (field_name.equals("organization")) {
                            organization_id = fi.getString();
                            upload_path = upload_path + File.separator + organization_id;
                        }

                    } else {
                        field_name = fi.getFieldName();
                        file_name = fi.getName();

                        if (file_name != "") {
                            File uploadDir = new File(upload_path);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }

//                            int inStr = file_name.indexOf(".");
//                            String Str = file_name.substring(0, inStr);
//
//                            file_name = category_name + "_" + Str + ".jpeg";
                            file_name = category_name + "_" + file_name;
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            String filePath = upload_path + File.separator + file_name;
                            File storeFile = new File(filePath);

                            fi.write(storeFile);

                            out.println("Uploaded Filename: " + filePath + "<br>");
                        }
                    }
                }
                categories.editCategories(Integer.parseInt(category_id), Integer.parseInt(organization_id), category_name, file_name);
                response.sendRedirect(request.getContextPath() + "/admin/categories.jsp");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet upload</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>No file uploaded</p>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception while editing categories", ex);
        } finally {
        try {
            out.close();
            }catch (Exception e){}
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
