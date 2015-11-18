/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblModel;

/**
 *
 * @author intbit
 */
public class GetLayoutStyles extends BrndBotBaseHttpServlet {

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
        JSONArray json_arr = new JSONArray();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String image_name,  layout_file_name, model_file_name,image_file_name,image_url,layout_html_data;
        Integer user_id, id, organization_id, block_id = 0;
        String category_id, sub_category_id, editorType;
        boolean mediadefault=false;
        String socialEditorType="media="+ mediadefault +"";
        Boolean isEmail = false, media = false, print = false, download = false;
         HashMap<Integer, Object> hash_map = new HashMap<Integer, Object>();

        getSqlMethodsInstance().session = request.getSession(true);
        
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            String queryParameter = request.getParameter("query");

            user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
            category_id = (String)getSqlMethodsInstance().session.getAttribute("category_id");
            sub_category_id = (String)getSqlMethodsInstance().session.getAttribute("sub_category_id");

            editorType = request.getParameter("editorType");
            
            if (editorType.equalsIgnoreCase("social") && request.getParameter("media_type")!= null){
                if (request.getParameter("media_type").equalsIgnoreCase("social")){
                    media=true;
                    socialEditorType="media="+ media +"";
                }
                if (request.getParameter("media_type").equalsIgnoreCase("print")){
                    print=true;
                    socialEditorType="print="+ print +"";
                }
                if (request.getParameter("media_type").equalsIgnoreCase("image")){
                    download=true;
                    socialEditorType="download="+ download +"";
                }
               
                
            }
            if (editorType.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                isEmail = true;//Else it is initialized as false -- social
            }

            if (queryParameter != null && queryParameter.equalsIgnoreCase("block")) {
                block_id = Integer.parseInt(request.getParameter("block_id"));
                sub_category_id = "0";//Since its a block
                category_id = "0";//Since its a block
            }
            

            String query = "Select * from tbl_model where organization_id="+organization_id+" "
                    + "and (user_id="+user_id+" or user_id=0) and "
                    + "category_id="+category_id+" and social="+!isEmail+" "
                    + "and email="+isEmail+" and "
                    + "sub_category_id="+sub_category_id+" "
                    + "and block_id="+block_id+" and "+socialEditorType+"";
            prepared_statement = connection.prepareStatement(query);
            result_set = prepared_statement.executeQuery();
            
            while(result_set.next()){
                TblModel layout_model = new TblModel();
                id = result_set.getInt("id");
                layout_file_name = result_set.getString("layout_file_name");
                model_file_name = result_set.getString("model_file_name");
                image_file_name = result_set.getString("image_file_name");
                image_url ="data:image/png;base64," + Base64.encode(result_set.getBytes("email_style_image")) ;
               
                layout_html_data=result_set.getString("emailhtmldata");
                layout_model.setId(id);
                layout_model.setLayout_file_name(layout_file_name);
                layout_model.setImage_file_name(image_file_name);
                layout_model.setImage_url(image_url);
                layout_model.setLayout_html_data(layout_html_data);
                json_arr.add(layout_model);
            }

        String json = new Gson().toJson(json_arr);
        response.setContentType("application/json");
        response.getWriter().write(json);
            
        }catch (Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

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
