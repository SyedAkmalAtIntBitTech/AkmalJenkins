/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import model.ClassImages;
import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Syed
 */
    public class GetImageGallery extends BrndBotBaseHttpServlet {
        private static final Logger logger = Logger.getLogger(GetImageGallery.class.getName());
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
        String image_name;
        Integer user_id, id;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            user_id = (Integer)request.getSession().getAttribute("UID");

            String query = "Select * from tbl_user_images where user_id=?";
            prepared_statement = connection.prepareStatement(query);
            prepared_statement.setInt(1, user_id);
            result_set = prepared_statement.executeQuery();
            
            while(result_set.next()){
                ClassImages cli = new ClassImages();
                id = result_set.getInt("id");
                user_id = result_set.getInt("user_id");
                image_name = result_set.getString("image_name");
                cli.setId(id);
                cli.setUser_id(user_id);
                cli.setImage_name(image_name);
                json_arr.add(cli);
            }
//        String jsonText = obj.toJSONString();        
        String json = new Gson().toJson(json_arr);
        logger.info(json);
        response.setContentType("application/json");
        response.getWriter().write(json);
            
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while getting gallery images", e);
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

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
