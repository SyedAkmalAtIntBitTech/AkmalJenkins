/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletSubCategory extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    SubCategories sub_categories = new SubCategories();

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
        PreparedStatement preparestatement = null;
        StringBuffer string_buffer = new StringBuffer();
        sqlmethods.session = request.getSession(true);

        try {

            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject jo_category = null;
            jo_category = (JSONObject) parser.parse(string_buffer.toString());

            String type = (String) jo_category.get("type");
            sqlmethods.setDatabaseConnection();
            if (type.equals("delete")) {
                Long category_id = (Long) jo_category.get("category_id");
                sub_categories.delete(category_id.intValue());

                out.write("true");
            } else if (type.equals("add")) {
                String sub_category_name = (String) jo_category.get("sub_category_name");
                String external_source = (String) jo_category.get("external_source");
                String category_id = (String) jo_category.get("category");

                String category = sub_categories.getCategoryName(Integer.parseInt(category_id));
                sub_categories.addSubCategories(external_source, category, sub_category_name, category_id);
                out.write("true");
            } else if (type.equals("update")) {
                String sub_category_id = (String) jo_category.get("sub_category_id");
                String sub_category_name = (String) jo_category.get("sub_category_name");
                String external_source = (String) jo_category.get("external_source");
                String category_id = (String) jo_category.get("category");

                String category = sub_categories.getCategoryName(Integer.parseInt(category_id));
                sub_categories.editSubCategories(Integer.parseInt(sub_category_id), external_source, category, sub_category_name, category_id);
                out.write("true");
            }
            sqlmethods.con.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(sqlmethods.error);
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
