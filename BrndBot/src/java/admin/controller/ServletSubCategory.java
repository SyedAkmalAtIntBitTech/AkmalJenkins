/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ServletSubCategory extends BrndBotBaseHttpServlet {

    SubCategories sub_categories;
    boolean check = false;
    
     public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            sub_categories = new SubCategories();
        } catch (NamingException ex) {
            Logger.getLogger(ServletSubCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        getSqlMethodsInstance().session = request.getSession(true);

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
            if (type.equals("delete")) {
                Long category_id = (Long) jo_category.get("category_id");
                sub_categories.delete(category_id.intValue());

                out.write("true");
            } else if (type.equals("add")) {
                String sub_category_name = (String) jo_category.get("sub_category_name");
                String external_source = (String) jo_category.get("external_source");
                String category_id = (String) jo_category.get("category");

                String category = sub_categories.getCategoryName(Integer.parseInt(category_id));
                check = sub_categories.checkAvailability(external_source, category, sub_category_name, category_id);
                
                if (check == false){
                    sub_categories.addSubCategories(external_source, category, sub_category_name, category_id);
                    out.write("true");
                }else {
                    out.write("false");
                }
                } else if (type.equals("update")) {
                String sub_category_id = (String) jo_category.get("sub_category_id");
                String sub_category_name = (String) jo_category.get("sub_category_name");
                String external_source = (String) jo_category.get("external_source");
                String category_id = (String) jo_category.get("category");

                String category = sub_categories.getCategoryName(Integer.parseInt(category_id));
                check = sub_categories.checkAvailability(external_source, category, sub_category_name, category_id);
                if (check == false){
                    sub_categories.editSubCategories(Integer.parseInt(sub_category_id), external_source, category, sub_category_name, category_id);
                    out.write("true");
                }else {
                    out.write("false");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(getSqlMethodsInstance().error);
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
