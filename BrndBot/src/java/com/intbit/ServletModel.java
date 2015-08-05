/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import admin.controller.Looks;
import com.controller.BrndBotBaseHttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author intbit
 */
public class ServletModel extends BrndBotBaseHttpServlet {
    String filePath;
    String fileName, fieldName, uploadPath, uploadXmlPath;
    Looks look;
    RequestDispatcher request_dispatcher;
    String lookName, organization;

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

            look = new Looks();
//            uploadXmlPath = getServletContext().getRealPath("") + "/model";
            uploadPath = getServletContext().getRealPath("") + "/model";

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
                        if (fieldName.equals("organization")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("users")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("categories")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("mapper")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("layout")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("mail")) {
                            lookName = fi.getString();
                        }
                        if (fieldName.equals("socialmedia")) {
                            lookName = fi.getString();
                        }

                        if (fieldName.equals("textstyle")) {
                            lookName = fi.getString();
                        }

                        if (fieldName.equals("containerstyle")) {
                            lookName = fi.getString();
                        }

                        if (fieldName.equals("element")) {
                            lookName = fi.getString();
                        }

                        String textstyleinfo = request.getParameter("textstyle");
                    String containerstyle = request.getParameter("containerstyle");
                    String mapfiledata = request.getParameter("element");
                    String textstylearray[] = textstyleinfo.split(",");
                    String containerstylearray[] = containerstyle.split(" ");
                    String mapfiledataarray[] = mapfiledata.split(",");
            //        String image = request.getParameter("image");
                    System.out.println(containerstyle);

                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                        // root elements
                        Document doc = docBuilder.newDocument();
                        Element rootElement = doc.createElement("layout");
                        doc.appendChild(rootElement);

                        Document doc1 = docBuilder.newDocument();
                        Element rootElement1 = doc1.createElement("models");
                        doc1.appendChild(rootElement1);

                        Element container = doc.createElement("container");
                        rootElement.appendChild(container);

//                        for (int i = 0; i <= containerstylearray.length - 1; i++) {
//                            String v[] = containerstylearray[i].split(":");
//                            Attr attr = doc.createAttribute(v[0]);
//                            attr.setValue("" + v[1]);
//                            container.setAttributeNode(attr);
//                        }
//
//                        // staff elements
//                        for (int i = 0; i <= textstylearray.length - 1; i++) {
//                            Element element = doc.createElement("element");
//                            rootElement.appendChild(element);
//                            String field1[] = textstylearray[i].split(" ");
//                            for (int j = 0; j <= field1.length - 1; j++) {
//                                String field2[] = field1[j].split(":");
//                                for (int k = 0; k < field2.length - 1; k++) {
//                                    Attr attr = doc.createAttribute(field2[0]);
//                                    attr.setValue("" + field2[1]);
//                                    element.setAttributeNode(attr);
//                                }
//                            }
//                        }
//
//            //            for mapper xml file
//                        for (int i = 0; i <= mapfiledataarray.length - 1; i++) {
//                            Element element1 = doc1.createElement("model");
//                            rootElement1.appendChild(element1);
//                            String field1[] = mapfiledataarray[i].split(" ");
//                            for (int j = 0; j <= field1.length - 1; j++) {
//                                String field2[] = field1[j].split(":");
//                                for (int k = 0; k < field2.length - 1; k++) {
//                                    Attr attr = doc1.createAttribute(field2[k]);
//                                    attr.setValue("" + field2[1]);
//                                    element1.setAttributeNode(attr);
//                                }
//                            }
//                        }

                        // write the content into xml file
//                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                        Transformer transformer = transformerFactory.newTransformer();
//                        DOMSource source = new DOMSource(doc);
//                        StreamResult result = new StreamResult(new File(uploadPath +File.separator + layoutfilename + ".xml"));
//
//                        TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
//                        Transformer transformer1 = transformerFactory1.newTransformer();
//                        DOMSource source1 = new DOMSource(doc1);
//                        StreamResult result1 = new StreamResult(new File(uploadPath +File.separator + mapperfilename + ".xml"));

                        // Output to console for testing
                        // StreamResult result = new StreamResult(System.out);
//                        transformer.transform(source, result);
//                        transformer1.transform(source1, result1);
//                        layout.addLayouts(organization_id , user_id, category_id, layoutfilename, mapperfilename, type_email, type_social);
                        
                    } else {
                        fieldName = fi.getFieldName();
                        fileName = fi.getName();

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        int inStr = fileName.indexOf(".");
                        String Str = fileName.substring(0, inStr);

                        fileName = lookName + "_" + Str + ".png";
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        fi.write(storeFile);

                        out.println("Uploaded Filename: " + filePath + "<br>");
                    }
                }
                look.addLooks(lookName, fileName);
                response.sendRedirect(request.getContextPath() + "/admin/looks.jsp");
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
