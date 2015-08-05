/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import admin.controller.Layout;
import com.controller.BrndBotBaseHttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Sandeep Kumar at IntBit Technologies.
 */
public class Model extends BrndBotBaseHttpServlet {

    Layout layout;
    String filePath;
    String fileName, fieldName, uploadPath;
    boolean type_email = false;
    boolean type_social = false;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String upload_path = "";

//    public void init(ServletConfig servletConfig) throws ServletException{
//      this.upload_path  = servletConfig.getInitParameter("uploadpath");
//    }

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try {
            layout = new Layout();
            response.setContentType("text/html;charset=UTF-8");
//            uploadPath = getServletContext().getRealPath("") + "/xml";
            uploadPath = getServletContext().getInitParameter("file-upload") + File.separator + "xml";
    //        uploadPath = getServletContext().getContextPath() + "/xml";

            Integer organization_id = Integer.parseInt(request.getParameter("organization"));
            Integer user_id = Integer.parseInt(request.getParameter("users"));
            Integer category_id = Integer.parseInt(request.getParameter("categories"));
            Integer sub_category_id = Integer.parseInt(request.getParameter("subcategories"));
            String mapperfilename = request.getParameter("mapper");
            String layoutfilename = request.getParameter("layout");

            String email = request.getParameter("mail");

            if (email != null){
                type_email = true;
            }
            String social = request.getParameter("socialmedia");

            if (social != null){
                type_social = true;
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

            for (int i = 0; i <= containerstylearray.length - 1; i++) {
                String v[] = containerstylearray[i].split("!");
                Attr attr = doc.createAttribute(v[0]);
                attr.setValue("" + v[1]);
                container.setAttributeNode(attr);
            }

            // staff elements
            for (int i = 0; i <= textstylearray.length - 1; i++) {
                Element element = doc.createElement("element");
                rootElement.appendChild(element);
                String field1[] = textstylearray[i].split(" ");
                
                for (int j = 0; j <= field1.length - 1; j++) {
                    String field2[] = field1[j].split("!");

                    for (int k = 0; k < field2.length - 1; k++) {
                        Attr attr = doc.createAttribute(field2[0]);
                        attr.setValue("" + field2[1]);
                        element.setAttributeNode(attr);
                    }

                }

            }

//            for mapper xml file
            for (int i = 0; i <= mapfiledataarray.length - 1; i++) {
                Element element1 = doc1.createElement("model");
                rootElement1.appendChild(element1);
                String field1[] = mapfiledataarray[i].split(" ");
                for (int j = 0; j <= field1.length - 1; j++) {
                    String field2[] = field1[j].split("!");
                    for (int k = 0; k < field2.length - 1; k++) {
                        Attr attr = doc1.createAttribute(field2[k]);
                        attr.setValue("" + field2[1]);
                        element1.setAttributeNode(attr);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(uploadPath +File.separator + layoutfilename + ".xml"));

            TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
            Transformer transformer1 = transformerFactory1.newTransformer();
            DOMSource source1 = new DOMSource(doc1);
            StreamResult result1 = new StreamResult(new File(uploadPath +File.separator + mapperfilename + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            transformer1.transform(source1, result1);
            layout.addLayouts(organization_id , user_id, category_id, layoutfilename, mapperfilename, type_email, type_social, sub_category_id);
            System.out.println("File saved!");
            response.sendRedirect(request.getContextPath() + "/admin/layoutmodel.jsp");
            
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getCause());
            System.out.println(pce.getMessage());
            System.out.println(pce.getStackTrace());

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            System.out.println(tfe.getCause());
            System.out.println(tfe.getMessage());
            System.out.println(tfe.getStackTrace());
        } catch (SQLException s){
            System.out.println(s.getCause());
            System.out.println(s.getMessage());
            System.out.println(s.getStackTrace());
        } catch (NamingException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
