/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.util.CustomStyles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import util.Utility;

/**
 *
 * @author sandeep-kumar
 */
public class GetColorsServlet extends BrndBotBaseHttpServlet {

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

        JSONArray userColor = getColorUserPreferences(request, response);
        JSONArray customColor = getCustomColorFromFile(request);
        JSONArray combineColors=new JSONArray();
        combineColors.addAll(userColor);
        combineColors.addAll(customColor);
     
        response.setContentType("application/json");
        response.getWriter().write(combineColors.toString());

    }

    private JSONArray getColorUserPreferences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processRequest(request, response);
        getSqlMethodsInstance().session = request.getSession(true);
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        PGobject pgobject = new PGobject();
        Object object = new Object();
        Integer brand_id = 0, font_theme_id;
        JSONObject user_preferences = new JSONObject();
        JSONObject json_colors = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray userColorsArray = null;
        Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            String query_string = "Select * from tbl_user_preferences where user_id=" + user_id + "";
            prepared_statement = connection.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                object = result_set.getObject("user_preferences");
                brand_id = result_set.getInt("brand_id");
                font_theme_id = result_set.getInt("font_theme_id");
            }
            String obj = object.toString();
            json_colors = (JSONObject) parser.parse(obj);

            userColorsArray = new JSONArray();
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color1")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color2")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color3")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color4")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color5")));
            userColorsArray.add(Utility.rgbToHex((String) json_colors.get("color6")));

        } catch (SQLException ex) {
            Logger.getLogger(GetColorsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GetColorsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userColorsArray;
    }

    private JSONArray getCustomColorFromFile(HttpServletRequest request) {
        JSONObject colorFromFile = CustomStyles.getCustomColorsJson(request);
        return (JSONArray)colorFromFile.get("admincustomcolors");
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
