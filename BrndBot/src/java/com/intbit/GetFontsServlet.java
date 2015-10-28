/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import com.google.gson.Gson;
import com.intbit.util.CustomStyles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;

/**
 *
 * @author sandeep-kumar
 */
public class GetFontsServlet extends BrndBotBaseHttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject customUserFonts=null;

        JSONObject customFonts = getCustomFontsFromFile(request);
        try {
            customUserFonts = getUserFonts(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(GetFontsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GetFontsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


        response.setContentType("application/json");
        response.getWriter().write(customUserFonts.toString());
    }
    
    private JSONObject getUserFonts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException{
    super.processRequest(request, response);
      getSqlMethodsInstance().session = request.getSession();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        PGobject pgobject = new PGobject();
        Object object = new Object();
        Integer brand_id = 0, font_theme_id;
        JSONObject user_preferences = new JSONObject();
        JSONArray json_font_names = new JSONArray();
        Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            
            String query_string = "Select * from tbl_user_preferences where user_id="+user_id+"";
                prepared_statement = connection.prepareStatement(query_string);

                result_set = prepared_statement.executeQuery();

                if (result_set.next()) {
                    brand_id = result_set.getInt("brand_id");
                }
                
        /*---------------------------------- script to get the font names from the database ----------------------------*/
                    Statement stmt3 = connection.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id="+brand_id+"");

                    JSONObject json_font;
                    if (rs3.next()){
                            Integer font_id1 = rs3.getInt("font_id1");
                            json_font = new JSONObject();
                            Statement stmt4 = connection.createStatement();
                            ResultSet rs4 = stmt4.executeQuery("Select * From tbl_font_family where id="+font_id1+"");
                                if (rs4.next()){
                                    String font_name1 = rs4.getString("font_name");
                                    String font_family_name1 = rs4.getString("font_family_name");
                                    font_family_name1 = font_family_name1 + "," + rs4.getString("file_name");
                                    
                                    user_preferences.put(font_family_name1,font_name1);
                                    
                                }
                                json_font_names.add(json_font);
                            rs4.close();
                            stmt4.close();
                            Integer font_id2 = rs3.getInt("font_id2");
                            json_font = new JSONObject();
                            Statement stmt5 = connection.createStatement();
                            ResultSet rs5 = stmt5.executeQuery("Select * From tbl_font_family where id="+font_id2+"");
                                if (rs5.next()){
                                    String font_name2 = rs5.getString("font_name");
                                    String font_family_name2 = rs5.getString("font_family_name");
                                    font_family_name2 = font_family_name2 + "," + rs5.getString("file_name");
                                    
                                    user_preferences.put(font_family_name2,font_name2);
                                 
                                }
                                json_font_names.add(json_font);
                            rs4.close();
                            stmt4.close();
                            Integer font_id3 = rs3.getInt("font_id3");
                            json_font = new JSONObject();
                            Statement stmt6 = connection.createStatement();
                            ResultSet rs6 = stmt6.executeQuery("Select * From tbl_font_family where id="+font_id3+"");
                                if (rs6.next()){
                                    String font_name3 = rs6.getString("font_name");
                                    String font_family_name3 = rs6.getString("font_family_name");
                                    font_family_name3 = font_family_name3 + "," + rs6.getString("file_name");
                                    
                                    user_preferences.put(font_family_name3,font_name3);
                                }
                            json_font_names.add(json_font);

                            rs4.close();
                            stmt4.close();
                            Integer font_id4 = rs3.getInt("font_id4");
                            json_font = new JSONObject();

                            Statement stmt7 = connection.createStatement();
                            ResultSet rs7 = stmt7.executeQuery("Select * From tbl_font_family where id="+font_id4+"");
                                if (rs7.next()){
                                    String font_name4 = rs7.getString("font_name");
                                    String font_family_name4 = rs7.getString("font_family_name");
                                    font_family_name4 = font_family_name4 + "," + rs7.getString("file_name");

                                    user_preferences.put(font_family_name4,font_name4);
                                }
                            json_font_names.add(json_font);
                            rs4.close();
                            stmt4.close();
                            Integer font_id5 = rs3.getInt("font_id5");
                            json_font = new JSONObject();

                            Statement stmt8 = connection.createStatement();
                            ResultSet rs8 = stmt8.executeQuery("Select * From tbl_font_family where id="+font_id5+"");
                                if (rs8.next()){
                                    String font_name5 = rs8.getString("font_name");
                                    String font_family_name5 = rs8.getString("font_family_name");
                                    font_family_name5 = font_family_name5 + "," + rs8.getString("file_name");
                                    
                                    user_preferences.put(font_family_name5,font_name5);
                                }
                            json_font_names.add(json_font);
                            rs4.close();
                            stmt4.close();
//            user_preferences.put("user_font_names", json_font_names);
            
            
       
    }
        }
        return user_preferences;
    }

    private JSONObject getCustomFontsFromFile(HttpServletRequest request) {
        JSONObject colorFromFile = CustomStyles.getCustomFontsJson(request);
        return colorFromFile;
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
