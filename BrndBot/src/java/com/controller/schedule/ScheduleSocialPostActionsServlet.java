/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.dao.ScheduleSocialPostDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author development
 */
public class ScheduleSocialPostActionsServlet extends HttpServlet {

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
        try {
            response.setContentType("application/json");
            HttpSession session = request.getSession();
            if (session.getAttribute("UID") == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "User is not logged in");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().flush();
                response.setContentType("application/json");
                return;
            }
            Integer userId = Integer.parseInt(session.getAttribute("UID").toString());
            List<Map<String, Object>> requestBodyList
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), List.class);
            if (requestBodyList == null || requestBodyList.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Request body is missing");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
            List<String> errorMessages = validateRequestBodyList(requestBodyList);
            if (!errorMessages.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", errorMessages);
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
            
        for(Map<String, Object> requestBodyMap : requestBodyList) {
            String tokenDataString = requestBodyMap.get("token_data").toString();
            String type = requestBodyMap.get("type").toString();
            errorMessages.addAll(validateTokenData(tokenDataString, type));
            String metadataString = requestBodyMap.get("metadata").toString();
            errorMessages.addAll(validateMetadata(metadataString, type));
        }

        if (!errorMessages.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", errorMessages);
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().flush();
            return;
        }
        List<Map<String, Integer>> daoResponseList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try {
                for (Map<String, Object> requestBodyMap : requestBodyList) {
                    String tokenDataString = requestBodyMap.get("token_data").toString();
                    String metadataString = requestBodyMap.get("metadata").toString();
                    String schedule_id = (String)requestBodyMap.get("schedule_id");
                    Map<String, Integer> daoResponse = ScheduleSocialPostDAO.updateActionsToScheduleSocialPost(
                            userId,
                            Integer.parseInt(schedule_id),
                            requestBodyMap.get("image_name").toString(),
                            AppConstants.GSON.fromJson(tokenDataString, Map.class),
                            AppConstants.GSON.fromJson(metadataString, Map.class),
                            requestBodyMap.get("type").toString(),
                            TemplateStatus.template_saved.toString(),
                            conn);
                    daoResponseList.add(daoResponse);
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(AppConstants.GSON.toJson(daoResponseList));
            response.getWriter().flush();

        } catch (SQLException ex) {
            Logger.getLogger(ScheduleSocialPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        }catch (Exception e){
            Logger.getLogger(ScheduleSocialPostServlet.class.getName()).log(Level.SEVERE, null, e);
            out.println(e);
        }
    }
    
    private List<String> validateRequestBodyList(List<Map<String,Object>> requestBodyList){
        List<String> errorMessages = new ArrayList<>();
        
        for ( Map<String, Object> requestBody : requestBodyList){
            errorMessages.addAll(validateRequestBody(requestBody));
        }
        
        return errorMessages;
    }
    
    private List<String> validateRequestBody(Map<String, Object> requestBody){
        List<String> errorMsgs = new ArrayList<>();
        if (!mapContainsKey(requestBody, "image_name")){
            errorMsgs.add("image_name is missing");
        }
        
        if ( !mapContainsKey(requestBody, "type")){
            errorMsgs.add("type is missing");
        }else{
            try{
                ScheduledEntityType.valueOf(requestBody.get("type").toString());
            }catch(IllegalArgumentException ex){
                errorMsgs.add("Unsupported type value: " + requestBody.get("type").toString());
            }
        }
        
        if ( !mapContainsKey(requestBody, "token_data")){
            errorMsgs.add("token_data JSON is missing");
        }
        
        if ( !mapContainsKey(requestBody, "metadata")){
            errorMsgs.add("metadata JSON is missing");
        }
        
        return errorMsgs;
    }
    
    private List<String> validateTokenData(String tokenDataString, String postType){
        
        List<String> errorMsgs = validateJsonData(tokenDataString,
                " is missing in token data JSON Object" , ()->{
            Set<String> requiredKeys = new HashSet<>();
            if (postType.contains(ScheduledEntityType.Facebook.toString())){
                requiredKeys.add("access_token");
            }else if(postType.contains(ScheduledEntityType.Twitter.toString())){
                requiredKeys.add("access_token");
                requiredKeys.add("token_secret");
            }
            return requiredKeys;
        });
        
        return errorMsgs;
    }
    
    private List<String> validateMetadata(String metadataString, String postType){
        List<String> errorMsgs = validateJsonData(metadataString,  
                " is missing in metadata JSON Object", ()->{
            Set<String> requiredKeys = new HashSet<>();
            if (postType.contains(ScheduledEntityType.Facebook.toString())){
                requiredKeys.add("post_text");
                requiredKeys.add("url");
                requiredKeys.add("description");
            }else if(postType.contains(ScheduledEntityType.Twitter.toString())){
                requiredKeys.add("text");
            }
            return requiredKeys;
        });
        
        return errorMsgs;
    }
    
    private List<String> validateJsonData(String jsonDataString, 
            String errorMsgSuffix,
            Supplier<Set<String>> requiredKeysBuilder){
        List<String> errorMsgs = new ArrayList<>();
        
        Map<String, Object> jsonDataMap = 
                AppConstants.GSON.fromJson(jsonDataString, Map.class);
        
        Set<String> requiredKeys = requiredKeysBuilder.get();
        
        Set<String> keySet = jsonDataMap.keySet();
        requiredKeys.stream().filter((key) -> (!keySet.contains(key))).forEach((key) -> {
            errorMsgs.add(key + errorMsgSuffix);
        });
        
        return errorMsgs;
    }
    
     private boolean mapContainsKey(Map<String, Object> requestBodyMap, String key){
        if ( !requestBodyMap.containsKey(key) || 
                requestBodyMap.get(key) == null ||
                StringUtils.isEmpty(requestBodyMap.get(key).toString())){
            return false;
        }
        return true;
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
