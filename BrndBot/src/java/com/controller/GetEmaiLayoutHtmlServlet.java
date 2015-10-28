/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblModel;

/**
 *
 * @author sandeep-kumar
 */
public class GetEmaiLayoutHtmlServlet extends BrndBotBaseHttpServlet {

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
        PrintWriter out = response.getWriter();
        JSONArray json_arr = new JSONArray();
        String html="";
        JSONObject Htmljson = new JSONObject();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        Integer tbl_model_id = Integer.parseInt(request.getParameter("id"));
        String model_name = request.getParameter("layout");
        
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            String query = "Select * from tbl_model where id=" + tbl_model_id + "";
            prepared_statement = connection.prepareStatement(query);
            result_set = prepared_statement.executeQuery();
       if(result_set.next()){
            html = result_set.getString("emailhtmldata");
            
       }
        Htmljson.put("htmldata",html);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(AppConstants.GSON.toJson(Htmljson));
        response.getWriter().flush();

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

        } finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
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
