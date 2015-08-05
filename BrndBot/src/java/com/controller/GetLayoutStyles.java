/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblModel;

/**
 *
 * @author intbit
 */
public class GetLayoutStyles extends BrndBotBaseHttpServlet {

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
        JSONArray json_arr = new JSONArray();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String image_name,  layout_file_name, model_file_name;
        Integer user_id, id, organization_id;
        String category_id, sub_category_id;
         HashMap<Integer, Object> hash_map = new HashMap<Integer, Object>();

        getSqlMethodsInstance().session = request.getSession(true);
        
        try {
            
//          hash_map = (HashMap<Integer, Object>)getSqlMethodsInstance().session.getAttribute("Mind_Body_Data");
            user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
            category_id = (String)getSqlMethodsInstance().session.getAttribute("category_id");
            sub_category_id = (String)getSqlMethodsInstance().session.getAttribute("sub_category_id");
            
            String query = "Select * from tbl_model where organization_id="+organization_id+" and user_id="+user_id+" and category_id="+category_id+" and social="+true+" and sub_category_id="+sub_category_id;
            prepared_statement = getSqlMethodsInstance().getConnection().prepareStatement(query);
            result_set = prepared_statement.executeQuery();
            
            while(result_set.next()){
                TblModel layout_model = new TblModel();
                JSONObject json_ob = new JSONObject();
                id = result_set.getInt("id");
                layout_file_name = result_set.getString("layout_file_name");
                model_file_name = result_set.getString("model_file_name");
                
                layout_model.setId(id);
                layout_model.setLayout_file_name(layout_file_name);
//                json_ob.put("id", id);
//                json_ob.put("layout_file_name", layout_file_name);
                json_arr.add(layout_model);
            }

        String json = new Gson().toJson(json_arr);
        response.setContentType("application/json");
        response.getWriter().write(json);
            
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
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
