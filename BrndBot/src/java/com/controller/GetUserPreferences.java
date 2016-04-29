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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author intbit
 */
public class GetUserPreferences extends BrndBotBaseHttpServlet {

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
        getSqlMethodsInstance().session = request.getSession();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        PGobject pgobject = new PGobject();
        Object object = new Object();
        Integer brand_id = 0, font_theme_id;
        JSONObject user_preferences = new JSONObject();
        JSONObject json_colors = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray json_font_names = new JSONArray();

        Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            String query_string = "Select * from tbl_user_brands where user_id="+user_id+"";
                prepared_statement = connection.prepareStatement(query_string);

                result_set = prepared_statement.executeQuery();

                while (result_set.next()) {
                    brand_id = result_set.getInt("brand_id");
                
//                    pgobject = (PGobject)object;
//                    pgobject.setType("json");
//                    String obj = object.toString();
//                    json_colors = (JSONObject) parser.parse(obj);

        /*---------------------------------- script to get the font names from the database ----------------------------*/
//                    brand_id = rs2.getInt(1);
                    Statement stmt3 = connection.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id="+brand_id+"");
                    JSONObject json_font;
                    if (rs3.next()){
                        Integer font_id1 = rs3.getInt("font_id1");
                        json_font_names = getFontsList(connection, font_id1, json_font_names);
                        Integer font_id2 = rs3.getInt("font_id2");
                        json_font_names = getFontsList(connection, font_id2, json_font_names);
                        Integer font_id3 = rs3.getInt("font_id3");
                        json_font_names = getFontsList(connection, font_id3, json_font_names);
                        Integer font_id4 = rs3.getInt("font_id4");
                        json_font_names = getFontsList(connection, font_id4, json_font_names);
                        Integer font_id5 = rs3.getInt("font_id5");
                        json_font_names = getFontsList(connection, font_id5, json_font_names);
                        Integer font_id6 = rs3.getInt("font_id6");
                        json_font_names = getFontsList(connection, font_id6, json_font_names);
                        Integer font_id7 = rs3.getInt("font_id7");
                        json_font_names = getFontsList(connection, font_id7, json_font_names);
                        Integer font_id8 = rs3.getInt("font_id8");
                        json_font_names = getFontsList(connection, font_id8, json_font_names);
                        Integer font_id9 = rs3.getInt("font_id9");
                        json_font_names = getFontsList(connection, font_id9, json_font_names);
                        Integer font_id10 = rs3.getInt("font_id10");
                        json_font_names = getFontsList(connection, font_id10, json_font_names);
                        Integer font_id11 = rs3.getInt("font_id11");
                        json_font_names = getFontsList(connection, font_id11, json_font_names);
                        Integer font_id12 = rs3.getInt("font_id12");
                        json_font_names = getFontsList(connection, font_id12, json_font_names);
                        Integer font_id13 = rs3.getInt("font_id13");
                        json_font_names = getFontsList(connection, font_id13, json_font_names);
                        Integer font_id14 = rs3.getInt("font_id14");
                        json_font_names = getFontsList(connection, font_id14, json_font_names);
                        Integer font_id15 = rs3.getInt("font_id15");
                        json_font_names = getFontsList(connection, font_id15, json_font_names);

                    }
            }

        query_string = "Select * from tbl_user_preferences where user_id="+user_id+"";

        prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                object = result_set.getObject("user_preferences");
            }
                
            pgobject = (PGobject)object;
            pgobject.setType("json");
            String obj = object.toString();
            json_colors = (JSONObject) parser.parse(obj);
            
            user_preferences.put("user_colors", json_colors);
            user_preferences.put("user_font_names", json_font_names);
            
        String json = new Gson().toJson(user_preferences);
        response.setContentType("application/json");
        response.getWriter().write(json);

        }catch (Exception e){
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while getting user preferences:", getSqlMethodsInstance().error));
        } finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
        }
    }
    
    public JSONArray getFontsList(Connection connection, 
            Integer id, JSONArray json_font_names)throws SQLException{
            ResultSet rs = null;
            Statement stmt = null;
        try {
            JSONObject json_font = new JSONObject();
            
            stmt = connection.createStatement();
            rs = stmt.executeQuery("Select * From tbl_font_family where id="+id+"");
                if (rs.next()){
                    String font_name5 = rs.getString("font_name");
                    String font_family_name5 = rs.getString("font_family_name");
                    font_family_name5 = font_family_name5 + "," + rs.getString("file_name");

                    json_font.put("font_name", font_name5);
                    json_font.put("font_family_name", font_family_name5);
                }
            json_font_names.add(json_font);
        }catch (Exception e){
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while getting user preferences:", getSqlMethodsInstance().error));
        }finally {
            rs.close();
            stmt.close();
        }
        return json_font_names;
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
