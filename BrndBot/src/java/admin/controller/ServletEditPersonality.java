/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
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
public class ServletEditPersonality extends BrndBotBaseHttpServlet {

    String filePath;
    String fileName, fieldName, uploadPath, deletePath, file_name_to_delete;
    Brands brand;
    RequestDispatcher request_dispatcher;
    String brandname, brandid, lookid;

     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            brand = new Brands();
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

            uploadPath = getServletContext().getRealPath("") + "/images/Brandimages";
            deletePath = getServletContext().getRealPath("") + "/images/Brandimages";
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
                        fieldName = fi.getFieldName();
                        if (fieldName.equals("brandname")) {
                            brandname = fi.getString();
                        }
                        if (fieldName.equals("brandid")) {
                            brandid = fi.getString();
                        }
                        if (fieldName.equals("look")) {
                            lookid = fi.getString();
                        }

                        file_name_to_delete = brand.getFileName(Integer.parseInt(brandid));
                    } else {
                        fieldName = fi.getFieldName();
                        fileName = fi.getName();

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        int inStr = fileName.indexOf(".");
                        String Str = fileName.substring(0, inStr);

                        fileName = brandname + "_" + Str + ".jpeg";
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        String file_path = uploadPath + File.separator + fileName;
                        String delete_path = deletePath + File.separator + file_name_to_delete;
                        File deleteFile = new File(delete_path);
                        deleteFile.delete();
                        File storeFile = new File(file_path);
                        fi.write(storeFile);
                        out.println("Uploaded Filename: " + filePath + "<br>");
                    }
                }
                brand.editBrands(Integer.parseInt(brandid), brandname, Integer.parseInt(lookid), fileName);
                response.sendRedirect(request.getContextPath() + "/admin/brandpersonality.jsp");
//                        request_dispatcher = request.getRequestDispatcher("/admin/looks.jsp");
//                        request_dispatcher.forward(request, response);
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
        } finally {
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
