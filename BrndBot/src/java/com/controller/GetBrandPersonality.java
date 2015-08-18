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
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.brandpersonality;

/**
 *
 * @author intbit
 */
public class GetBrandPersonality extends BrndBotBaseHttpServlet {

    String query_string = "", Query1 = "";

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
        JSONObject jsonobject = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONArray jsonarray1 = new JSONArray();
        JSONObject json_brand = new JSONObject();
    PreparedStatement prepared_statement = null;
    ResultSet result_set = null;

        getSqlMethodsInstance().session = request.getSession();
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            
            if  (request.getParameter("type") != null){

                    String type = request.getParameter("type");

                    if (type.equalsIgnoreCase("selected")){
                        
                        Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
                        json_brand = getSqlMethodsInstance().getUserPreferencesBrand(user_id);
                        String json = new Gson().toJson(json_brand);
                        response.setContentType("application/json");
                        out.write(json);
                    }else if (type.equalsIgnoreCase("all")){

                        Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
                        Integer look_id = getSqlMethodsInstance().getLookID(user_id);
                        query_string = "Select * from tbl_brand_personality where look_id='" + look_id + "'";
                        prepared_statement = connection.prepareStatement(query_string);

                        result_set = prepared_statement.executeQuery();
                        while (result_set.next()) {
                            brandpersonality brand_personality = new brandpersonality();

                            int id = result_set.getInt("id");
                            String brand_name = result_set.getString("brand_name");
                            String image_file_name = result_set.getString("image");
                            brand_personality.setId(id);
                            brand_personality.setBrand_name(brand_name);
                            brand_personality.setImage_name(image_file_name);
                            jsonarray.add(brand_personality);
                        }
                        jsonobject.put("first", jsonarray);
                        String json = new Gson().toJson(jsonobject);
                        response.setContentType("application/json");
                        out.write(json);
                        
                    }
                    
            }else {
                String look_id = (String) getSqlMethodsInstance().session.getAttribute("LookID");

                query_string = "Select * from tbl_brand_personality where look_id='" + look_id + "'";
                prepared_statement = connection.prepareStatement(query_string);

                result_set = prepared_statement.executeQuery();
                while (result_set.next()) {
                    brandpersonality brand_personality = new brandpersonality();

                    int id = result_set.getInt("id");
                    String brand_name = result_set.getString("brand_name");
                    String image_file_name = result_set.getString("image");
                    brand_personality.setId(id);
                    brand_personality.setBrand_name(brand_name);
                    brand_personality.setImage_name(image_file_name);
                    jsonarray.add(brand_personality);
                }
                jsonobject.put("first", jsonarray);
                String json = new Gson().toJson(jsonobject);
                response.setContentType("application/json");
                out.write(json);
                
            }
        } catch (Exception e) {
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Getting brand  personality", getSqlMethodsInstance().error));

            out.write(getSqlMethodsInstance().error);
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
