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
import java.sql.Statement;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        PGobject pgobject = new PGobject();
        Integer brand_id = 0, font_theme_id;
        JSONObject user_preferences = new JSONObject();
        JSONObject json_colors = new JSONObject();
        JSONParser parser = new JSONParser();

        Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
        try {
                String query_string = "Select * from tbl_user_preferences where user_id="+user_id+"";
                prepared_statement = getSqlMethodsInstance().getConnection().prepareStatement(query_string);

                result_set = prepared_statement.executeQuery();

                if (result_set.next()) {
                    pgobject = (PGobject) result_set.getObject("user_preferences");
                    brand_id = result_set.getInt("brand_id");
                    font_theme_id = result_set.getInt("font_theme_id");
                }
                
            pgobject.setType("json");
            String obj = pgobject.getValue();

            json_colors = (JSONObject) parser.parse(obj);


        /*---------------------------------- script to get the font names from the database ----------------------------*/
//                    brand_id = rs2.getInt(1);
                    Statement stmt3 = getSqlMethodsInstance().getConnection().createStatement();
                    ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id="+brand_id+"");
                    JSONArray json_font_names = new JSONArray();
                    JSONArray json_font_sizes = new JSONArray();

                    if (rs3.next()){
                            Integer font_id1 = rs3.getInt("font_id1");
                            Statement stmt4 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs4 = stmt4.executeQuery("Select * From tbl_font_family where id="+font_id1+"");
                                if (rs4.next()){
                                    String font_name1 = rs4.getString("font_name");
                                    json_font_names.add(font_name1);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_id2 = rs3.getInt("font_id2");
                            Statement stmt5 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs5 = stmt5.executeQuery("Select * From tbl_font_family where id="+font_id2+"");
                                if (rs5.next()){
                                    String font_name2 = rs5.getString("font_name");
                                    json_font_names.add(font_name2);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_id3 = rs3.getInt("font_id3");

                            Statement stmt6 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs6 = stmt6.executeQuery("Select * From tbl_font_family where id="+font_id3+"");
                                if (rs6.next()){
                                    String font_name3 = rs6.getString("font_name");
                                    json_font_names.add(font_name3);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_id4 = rs3.getInt("font_id4");

                            Statement stmt7 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs7 = stmt7.executeQuery("Select * From tbl_font_family where id="+font_id4+"");
                                if (rs7.next()){
                                    String font_name4 = rs7.getString("font_name");
                                    json_font_names.add(font_name4);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_id5 = rs3.getInt("font_id5");

                            Statement stmt8 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs8 = stmt8.executeQuery("Select * From tbl_font_family where id="+font_id5+"");
                                if (rs8.next()){
                                    String font_name5 = rs8.getString("font_name");
                                    json_font_names.add(font_name5);
                                }
                            rs4.close();
                            stmt4.close();

        /*-------------------------------script to get the font sizes from the database ----------------------------*/

                            Integer font_size_id1 = rs3.getInt("font_size1");
                            Statement stmt9 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs9 = stmt9.executeQuery("Select * From tbl_font_size where id="+font_size_id1+"");
                                if (rs9.next()){
                                    String font_size1 = rs9.getString("font_size");
                                    font_size1 = font_size1 + "px";
                                    json_font_sizes.add(font_size1);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_size_id2 = rs3.getInt("font_size2");
                            Statement stmt10 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs10 = stmt10.executeQuery("Select * From tbl_font_size where id="+font_size_id2+"");
                                if (rs10.next()){
                                    String font_size2 = rs10.getString("font_size");
                                    font_size2 = font_size2 + "px";
                                    json_font_sizes.add(font_size2);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_size_id3 = rs3.getInt("font_size3");
                            Statement stmt11 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs11 = stmt11.executeQuery("Select * From tbl_font_size where id="+font_size_id3+"");
                                if (rs11.next()){
                                    String font_size3 = rs11.getString("font_size");
                                    font_size3 = font_size3 + "px";
                                    json_font_sizes.add(font_size3);
                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_size_id4 = rs3.getInt("font_size4");
                            Statement stmt12 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs12 = stmt12.executeQuery("Select * From tbl_font_size where id="+font_size_id4+"");
                                if (rs12.next()){
                                    String font_size4 = rs12.getString("font_size");
                                    font_size4 = font_size4 + "px";
                                    json_font_sizes.add(font_size4);

                                }
                            rs4.close();
                            stmt4.close();
                            Integer font_size_id5 = rs3.getInt("font_size5");
                            Statement stmt13 = getSqlMethodsInstance().getConnection().createStatement();
                            ResultSet rs13 = stmt13.executeQuery("Select * From tbl_font_size where id="+font_size_id5+"");
                                if (rs13.next()){
                                    String font_size5 = rs13.getString("font_size");
                                    font_size5 = font_size5 + "px";
                                    json_font_sizes.add(font_size5);
                                }
                            rs4.close();
                            stmt4.close();

                    }
                
            user_preferences.put("user_colors", json_colors);
            user_preferences.put("user_font_names", json_font_names);
            user_preferences.put("user_font_sizes", json_font_sizes);
            
        String json = new Gson().toJson(user_preferences);
        response.setContentType("application/json");
        response.getWriter().write(json);

        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
