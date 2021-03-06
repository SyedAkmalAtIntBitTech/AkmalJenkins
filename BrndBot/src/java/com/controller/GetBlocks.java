/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ilyas
 */
public class GetBlocks extends BrndBotBaseHttpServlet {

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
        JSONArray json_arr = new JSONArray();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String mindbody_query = null, blockname = null;
        Integer user_id, id, brand_id;
        Integer sub_category_id;

        getSqlMethodsInstance().session = request.getSession(true);
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            
            brand_id = getSqlMethodsInstance().getBrandID(user_id);

            getSqlMethodsInstance().session.setAttribute("brandID", brand_id);
            
            String query = "Select * from tbl_blocks where brand_id="+brand_id;
            
            prepared_statement = connection.prepareStatement(query);
            result_set = prepared_statement.executeQuery();
           
            while(result_set.next()){
                JSONObject json_ob = new JSONObject();
                id = result_set.getInt("id");
                mindbody_query = result_set.getString("mindbody_query");
                sub_category_id = result_set.getInt("sub_category_id");
                blockname = result_set.getString("name");  
                json_ob.put("block_name", blockname);
                json_ob.put("block_id", id);
                json_ob.put("mindbody_query", mindbody_query);
                json_ob.put("sub_category_id", sub_category_id);
                
                json_arr.add(json_ob);
            }

        String json = new Gson().toJson(json_arr);
        response.setContentType("application/json");
        response.getWriter().write(json);
        
        //sample
        //        {
        //  [
        //    "block_name": "block1",
        //    "block_id": "1",
        //    "mindbody_query": "promote",
        //    "subcategory_id": "1"
        //  ]
        //}
            
        }catch (Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while getting blocks", getSqlMethodsInstance().error));

        }finally {
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
