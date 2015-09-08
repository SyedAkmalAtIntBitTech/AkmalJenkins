/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import static com.controller.BrndBotBaseHttpServlet.logger;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ilyas
 */
public class ServletDisplayLayoutHTML extends BrndBotBaseHttpServlet {

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
        
        response.setContentType("application/json");
        String modelname = request.getParameter("modelname");
        
        try (PrintWriter out = response.getWriter()) {
            JSONArray json_arr = new JSONArray();
            JSONObject json_ob = new JSONObject();
              File divFile = new File(AppConstants.LAYOUT_HTML_HOME+File.separator+modelname+".html");
              String layoutHTMLDiv = "";
              
              if(divFile.exists())
                 layoutHTMLDiv = FileUtils.readFileToString(divFile, "UTF-8");
              json_ob.put("div",layoutHTMLDiv);
              String layoutHTMLTable = "";
              File tableFile = new File(AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH+File.separator+modelname+".html");
              if(tableFile.exists())
                layoutHTMLTable = FileUtils.readFileToString(tableFile, "UTF-8");
              json_ob.put("table",layoutHTMLTable);
              
              json_arr.add(json_ob);
            out.write(new Gson().toJson(json_arr));
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception:", e.getMessage()));

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
