/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.output.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.*;

/**
 *
 * @author intbit
 */
public class ServletAddCategories extends HttpServlet {

    String filePath;
    String file_name, field_name, upload_path;
    Categories categories = new Categories();
    RequestDispatcher request_dispatcher;
    SqlMethods sqlmethods = new SqlMethods();
    String category_name, organization_id;
    boolean check = false;
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

        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        try {

            upload_path = getServletContext().getRealPath("") + "/images/Organizations/Categories";

            // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("c://temp"));

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
                        if (field_name.equals("organization")) {
                            organization_id = fi.getString();
                            upload_path = upload_path + File.separator + organization_id;
                        }

                    } else {
                        
                        field_name = fi.getFieldName();
                        file_name = fi.getName();
                        if (file_name != "") {

                            sqlmethods.setDatabaseConnection();
                            check = categories.checkAvailability(category_name, Integer.parseInt(organization_id));
                            if (check == false){
                                File uploadDir = new File(upload_path);
                                if (!uploadDir.exists()) {
                                    uploadDir.mkdirs();
                                }

                                int inStr = file_name.indexOf(".");
                                String Str = file_name.substring(0, inStr);

                                file_name = category_name + "_" + Str + ".jpeg";
                                boolean isInMemory = fi.isInMemory();
                                long sizeInBytes = fi.getSize();

                                String filePath = upload_path + File.separator + file_name;
                                File storeFile = new File(filePath);

                                fi.write(storeFile);
                                categories.addCategories(Integer.parseInt(organization_id), category_name, file_name);

                                out.println("Uploaded Filename: " + filePath + "<br>");
                                response.sendRedirect(request.getContextPath() + "/admin/categories.jsp");
                            }else {
                                response.sendRedirect(request.getContextPath() + "/admin/categories.jsp?exist=exist");
                            }
                        }
                    }
                }
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
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            out.println(sqlmethods.error);
        } finally {
            try {
                        out.close();
                        sqlmethods.con.close();
            }catch (Exception e){}        }

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
