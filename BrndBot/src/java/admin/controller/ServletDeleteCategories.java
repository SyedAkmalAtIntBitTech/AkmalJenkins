/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.AppConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ServletDeleteCategories extends BrndBotBaseHttpServlet {
    Categories category;
    String delete_path, file_name_to_delete;
    
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            category = new Categories();
        } catch (NamingException ex) {
            Logger.getLogger(BrndBotBaseHttpServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer string_buffer = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null){
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonCategory = null;
            jsonCategory = (JSONObject) parser.parse(string_buffer.toString());
            Long category_id = (Long) jsonCategory.get("category_id");
            String deletePath = AppConstants.ORG_CATEGORIES_HOME;

            Integer organization_id = category.getOrganizationID(category_id.intValue());
            file_name_to_delete = category.getFileName(category_id.intValue());
            if (file_name_to_delete != ""){
                deletePath += File.separator + organization_id + File.separator + file_name_to_delete;
                File deleteFile = new File(deletePath);
                deleteFile.delete();
            }            
            category.delete(category_id.intValue());
            out.write("true");
        }catch(Exception e){
            logger.log(Level.SEVERE, "", e);
        }finally {
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
