/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.look;

/**
 *
 * @author intbit
 */
public class GetLooks extends BrndBotBaseHttpServlet {

    
    public static Integer ll1 = 4;

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
        String query = "", query1 = "";
        JSONObject jsonobject = new JSONObject();
        JSONArray jsonarr = new JSONArray();
        JSONArray jsonarr1 = new JSONArray();

        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        getSqlMethodsInstance().session = request.getSession(true);

//          String type = request.getParameter("type");
          try(Connection connection = ConnectionManager.getInstance().getConnection()) {
        
            if  (request.getParameter("type") != null){
                    Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

                    jsonobject = getSqlMethodsInstance().getUserPreferencesLook(user_id);
            }else {

                        query = "Select * from tbl_look limit 4";
                        prepared_statement = connection.prepareStatement(query);
                        result_set = prepared_statement.executeQuery();
                        while (result_set.next()) {
                            look lk = new look();
                            int id = result_set.getInt("id");
                            String look_name = result_set.getString("look_name");
                            lk.setId(id);
                            lk.setLook_name(look_name);
                            String file_name = result_set.getString("file_name");
                            lk.setFile_name(file_name);
                            jsonarr.add(lk);
                        }
//                        result_set.close();  
                        prepared_statement.close();
                        jsonobject.put("first", jsonarr);

                        query1 = "Select * from tbl_look limit 4 OFFSET 4";
                        prepared_statement = connection.prepareStatement(query1);
                        result_set = prepared_statement.executeQuery();
                        while (result_set.next()) {
                            look lk = new look();
                            int id = result_set.getInt("id");
                            String look_name = result_set.getString("look_name");
                            lk.setId(id);
                            lk.setLook_name(look_name);
                            String file_name = result_set.getString("file_name");
                            lk.setFile_name(file_name);
                            jsonarr1.add(lk);
                        }
                        jsonobject.put("second", jsonarr1);

            }
            
            String json = new Gson().toJson(jsonobject);
            response.setContentType("application/json");
            response.getWriter().write(json);
            
            
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

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
