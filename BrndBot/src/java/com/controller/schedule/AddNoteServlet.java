/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityStatus;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.dao.ScheduleDAO;
import com.intbit.util.AuthenticationUtil;
import com.intbit.util.ServletUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "AddNoteServlet", urlPatterns = {"/AddNote"})
public class AddNoteServlet extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        if ( !AuthenticationUtil.isUserLoggedIn(request)){
            AuthenticationUtil.printAuthErrorToResponse(response);
            return;
        }
        
        Integer userId = AuthenticationUtil.getUUID(request);
        
        Map<String, Object> requestBodyMap = ServletUtil.getJsonFromRequestBody(request);
        if ( requestBodyMap == null){
            ServletUtil.printIllegalArgumentError(response, "Request body is missing");
            return;
        }
        
        List<String> errorMsgs = validateRequestBody(requestBodyMap);
        if ( ! errorMsgs.isEmpty()){
            ServletUtil.printIllegalArgumentError(response, errorMsgs);
            return;
        }
        
         try(Connection conn = ConnectionManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            try{
                int scheduleId = ScheduleDAO.addToScheduleEntityList(null,
                    requestBodyMap.get("title").toString(),
                    requestBodyMap.get("description").toString(),
                    new Timestamp(Double.valueOf(requestBodyMap.get("schedule_time").toString()).longValue()), 
                    requestBodyMap.get("type").toString(), 
                    ScheduledEntityStatus.scheduled.toString(), 
                    userId, 
                    TemplateStatus.na.toString(),
                    conn
                );
                conn.commit();
                Map<String, Object> data = new HashMap<>();
                data.put("schedule_entity_id", scheduleId);
                ServletUtil.printSuccessData(response, data);
            } catch (SQLException ex) {
                Logger.getLogger(AddNoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                conn.rollback();
                ServletUtil.printInternalException(response, ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            ServletUtil.printInternalException(response, ex.getMessage());
        }
        
        
    }
    
    private List<String> validateRequestBody(Map<String, Object> requestBodyMap){
        List<String> errorMsgs = new ArrayList<>();
        if ( !ServletUtil.mapContainsKey(requestBodyMap, "title")){
            errorMsgs.add("title is missing");
        }
        
        if ( !ServletUtil.mapContainsKey(requestBodyMap, "description")){
            errorMsgs.add("description is missing");
        }
        
        if ( !ServletUtil.mapContainsKey(requestBodyMap, "type")){
            errorMsgs.add("type is missing");
        }else{
            try{
                ScheduledEntityType type = 
                        ScheduledEntityType.valueOf(requestBodyMap.get("type").toString());
                if ( ScheduledEntityType.note != type){
                    errorMsgs.add("Only type = note is supported while adding new notes");
                }
            }catch(IllegalArgumentException ex){
                errorMsgs.add("type: " + requestBodyMap.get("type").toString() +" is invalid");
            }
        }
        
        if ( !ServletUtil.mapContainsKey(requestBodyMap, "schedule_time")){
            errorMsgs.add("schedule_time is missing");
        }
        return errorMsgs;
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
