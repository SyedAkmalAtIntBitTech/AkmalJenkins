/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.intbittech.utility.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author development
 */
public class GenericAnnouncementServlet extends BrndBotBaseHttpServlet {

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
        getSqlMethodsInstance().session = request.getSession(true);

        try {
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            Integer organization_id = 0, block_id = 0;
            String editor_type = null;
            Integer model_mapper_id = 0;

            String category_id = (String) getSqlMethodsInstance().session.getAttribute("category_id");
            String sub_category_id = (String) getSqlMethodsInstance().session.getAttribute("sub_category_id");
            String sub_category_name = (String) getSqlMethodsInstance().session.getAttribute("sub_category_name");
            if (request.getParameter("editor_type") != null){
                editor_type = request.getParameter("editor_type");
            }
            if (request.getParameter("model_mapper_id") != null){
                model_mapper_id = Integer.parseInt(request.getParameter("model_mapper_id"));
            }
            if (request.getParameter("block_id") != null){
                block_id = Integer.parseInt(request.getParameter("block_id"));            
            }
            organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
            
            String mapperFileName = getSqlMethodsInstance().getMapperFile(user_id, organization_id, Integer.parseInt(category_id), Integer.parseInt(sub_category_id), model_mapper_id, block_id, editor_type);
            String editor_mapper_file_name = AppConstants.BASE_XML_UPLOAD_PATH + File.separator + mapperFileName + ".xml";
            JSONObject mapped_json_object = null;
            sub_category_name = sub_category_name.toLowerCase();

            mapped_json_object = GenericAnnouncementDataMapper.mapAnnouncements(editor_mapper_file_name);
            if (mapped_json_object != null) {
                response.setContentType("application/json");
                response.getWriter().write(mapped_json_object.toString());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while displaying the annoucement data:", getSqlMethodsInstance().error));
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
