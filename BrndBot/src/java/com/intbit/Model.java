/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class Model extends HttpServlet {

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
        
        String mapperfilename = request.getParameter("mapper");
        String layoutfilename = request.getParameter("layout");
        String textstyleinfo = request.getParameter("textstyle");
        String containerstyle=request.getParameter("containerstyle");
        String mapfiledata=request.getParameter("element");
        String textstylearray[] = textstyleinfo.split(",");
        String containerstylearray[]=containerstyle.split(" ");
        String mapfiledataarray[]=mapfiledata.split(",");
        System.out.println(containerstyle);
        
      
        
        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("layout");
            doc.appendChild(rootElement);
            
            
            Document doc1 = docBuilder.newDocument();
            Element rootElement1 = doc1.createElement("models");
            doc1.appendChild(rootElement1);
            
            
            
            
            Element container=doc.createElement("container");
            rootElement.appendChild(container);
            
           for(int i=0;i<=containerstylearray.length-1;i++){
            String v[]=containerstylearray[i].split(":");
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
                    String field2[] = field1[j].split(":");
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
                    String field2[] = field1[j].split(":");
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
            StreamResult result = new StreamResult(new File("/home/development/NetBeansProjects/BrndBot/BrndBot/web/CSS/"+mapperfilename+".xml"));

            TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
            Transformer transformer1 = transformerFactory1.newTransformer();
            DOMSource source1 = new DOMSource(doc1);
            StreamResult result1 = new StreamResult(new File("/home/development/NetBeansProjects/BrndBot/BrndBot/web/CSS/"+layoutfilename+".xml"));



            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            transformer1.transform(source1, result1);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Model</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + textstyleinfo +  "</h1>");
            out.println("</body>");
            out.println("</html>");
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
