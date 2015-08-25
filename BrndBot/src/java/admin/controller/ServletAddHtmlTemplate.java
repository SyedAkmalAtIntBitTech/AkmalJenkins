/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.intbit.AppConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author development
 */
public class ServletAddHtmlTemplate extends HttpServlet {

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
        String fileName = null,filePath = null, fieldName = null, uploadPath = null, uploadType = null;
        StringBuffer string_buffer = new StringBuffer();

        try {
            /* TODO output your page here. You may use following sample code. */
            uploadPath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH;
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            
            JSONParser parser = new JSONParser();
            JSONObject json_html_template = null;
            json_html_template = (JSONObject) parser.parse(string_buffer.toString());
            String type = (String) json_html_template.get("type");
            
//            BufferedReader txtfile = new BufferedReader(new FileReader("c:\\test.txt"));
            String model_name = (String)json_html_template.get("model_name");
            String html_content = (String)json_html_template.get("html_content");
            
            filePath = uploadPath + File.separator + model_name + ".html";
            
            OutputStream htmlfile= new FileOutputStream(new File(filePath));
            PrintStream printhtml = new PrintStream(htmlfile);
            
//            String[] txtbyLine = new String[500];
//            String temp = "";
//            String txtfiledata = "";

//            String htmlheader="<html><head>";
//            htmlheader+="<title>Equivalent HTML</title>";
//            htmlheader+="</head><body>";
//            String htmlfooter="</body></html>";
//            int linenum = 0 ;
//
//            htmlheader = htmlheader + txtfile;
            
//            while ((txtfiledata = txtfile.readLine())!= null)
//               {
//                    txtbyLine[linenum] = txtfiledata;
//                    linenum++;
//               } 
//            for(int i=0;i<linenum;i++)
//                {
//                    if(i == 0)
//                    {
//                        temp = htmlheader + txtbyLine[0];
//                        txtbyLine[0] = temp;
//                    }
//                    if(linenum == i+1)
//                    {
//                        temp = txtbyLine[i] + htmlfooter;
//                        txtbyLine[i] = temp;
//                    }
//                    printhtml.println(txtbyLine[i]);
//                }
                printhtml.print(html_content);
                
        printhtml.close();
        htmlfile.close();
//        txtfile.close();
            
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
