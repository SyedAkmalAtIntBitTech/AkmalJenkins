package com.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblBrandColorTheme;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class GetColorPalettes extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    String query = "";
    String theme = "";

    Integer i = 1;
    PreparedStatement prepared_statement, ps1;
    ResultSet result_set, rs1;

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
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        int page = 1;
        int recordsPerPage = 4;
        
        try {
            sqlmethods.setDatabaseConnection();
            
            query = "Select color1, color2, color3, color4, color5, color6, theme_name from tbl_brand_color_theme order by id Asc";
            prepared_statement = sqlmethods.con.prepareStatement(query);
            result_set = prepared_statement.executeQuery();
            Integer num = 1;
            Integer num1 = 0;
            num1 = num;
            Integer themeNum = 1;
            String theme = "";
            while (result_set.next()) {
                TblBrandColorTheme color_theme = new TblBrandColorTheme();
                TblColors colors = new TblColors();
                JSONArray jarr = new JSONArray();
                
                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(1)), num1);
                jarr.add(colors);
                
                if (themeNum == 5){
                    themeNum = 1;
                }
                
                num1 = num1 +1;
                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(2)), num1);
                jarr.add(colors);

                num1 = num1 +1;
                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(3)), num1);
                jarr.add(colors);

                num1 = num1 +1;
                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(4)), num1);
                jarr.add(colors);
                num1 = num1 +1;

                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(5)), num1);
                jarr.add(colors);
                num1 = num1 +1;

                colors = sqlmethods.getColors(String.valueOf(result_set.getInt(6)), num1);
                jarr.add(colors);
                if (num1 == 24){
                    num1 = 0;
                }
                num1 = num1 +1;
                theme = "theme" + themeNum;

                color_theme.setId(theme);
                color_theme.setTheme_name(result_set.getString(7));
                jarr.add(color_theme);
                json1.put(result_set.getString(7), jarr);
                jsonarray.add(jarr);
                themeNum++;
                i++;
            }
            String jsonn = new Gson().toJson(jsonarray);
            response.setContentType("application/json");
            out.write(jsonn);

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(sqlmethods.error);
        }finally {
            out.close();
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
