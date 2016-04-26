/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.AppConstants;
import com.intbit.TemplateStatus;
import com.intbittech.dao.impl.ScheduleDAO;
import com.intbit.util.AuthenticationUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author development
 */
public class ScheduleEmailActionsServlet extends HttpServlet {

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
        response.setContentType("application/json");
        try {
            if ( !AuthenticationUtil.isUserLoggedIn(request)){
                AuthenticationUtil.printAuthErrorToResponse(response);
                return;
            }
            Integer userId = AuthenticationUtil.getUUID(request);
            
            Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            if ( requestBodyMap == null){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Request body is missing");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
            
            List<String> errorMsgs = validateRequestBody(requestBodyMap);
            if ( !errorMsgs.isEmpty()){
                Map<String, Object> error = new HashMap<>();
                error.put("error", errorMsgs);
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
            //As of now schedule description is not yet mandatory.
            String schedule_id = (String)requestBodyMap.get("schedule_id");
            String scheduleDesc = requestBodyMap.containsKey("schedule_desc") ? 
                    String.valueOf(requestBodyMap.get("schedule_desc")):null;
            
            //Added by Syed Ilyas 27 Nov 2015 - email body from iframe
            String html_text = "";
            String path = "";
            if (requestBodyMap.get("iframeName").toString().trim() != null){
                String iframeName=requestBodyMap.get("iframeName").toString().trim();
                path=AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH+File.separator+iframeName+".html";
                File file = new File(path);
                html_text = FileUtils.readFileToString(file, "UTF-8");
            }
            
            
            
            Map<String, Integer> idMap = ScheduleDAO.updatetoScheduledEmailList(
                    userId,
                    Integer.parseInt(schedule_id),
                    requestBodyMap.get("email_subject").toString(),
                    html_text,
                    requestBodyMap.get("from_email_address").toString(),
                    requestBodyMap.get("email_list").toString(),
                    requestBodyMap.get("from_name").toString(),
                    requestBodyMap.get("reply_to_email_address").toString(),
                    requestBodyMap.get("to_email_addresses").toString().split(","),
                    scheduleDesc, 
                    TemplateStatus.template_saved.toString()
            );
            
            if (!path.equals("")){
                File IframeDelete=new File(path);
                IframeDelete.delete();
            }
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(AppConstants.GSON.toJson(idMap));
            response.getWriter().flush();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex){
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Invalid format for schedule time.");
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().flush();
        } catch (Exception ex){
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private List<String> validateRequestBody(Map<String, Object> requestBodyMap){
        List<String> errorMsgs = new ArrayList<>();
        if ( !mapContainsKey(requestBodyMap, "email_subject")){
            errorMsgs.add("Email subject is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "to_email_addresses")){
            errorMsgs.add("To email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "email_body")){
            errorMsgs.add("Email body is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "email_list")){
            errorMsgs.add("Email List name is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "reply_to_email_address")){
            errorMsgs.add("Reply to email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "from_email_address")){
            errorMsgs.add("From email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "from_name")){
            errorMsgs.add("From name is missing");
        }
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
