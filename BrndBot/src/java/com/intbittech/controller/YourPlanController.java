/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.ApplicationContextListener;
import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.util.ServletUtil;
import com.intbittech.dao.impl.ScheduleDAO;
import com.intbittech.AppConstants;
import com.intbittech.dao.impl.ScheduleSocialPostDAO;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import social.controller.PostToFacebook;
import social.controller.PostToTwitter;

/**
 *
 * @author ilyas
 */
@Controller
public class YourPlanController {
    private Logger logger = Logger.getLogger(YourPlanController.class);
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    @RequestMapping(value = "/GetScheduledEntities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void GetScheduledEntities(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try{
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        List<String> errorMsgs = new ArrayList<>();
            
            if ( StringUtils.isEmpty(request.getParameter("from")) ){
                errorMsgs.add("from date parameter is missing");
            }
            if ( StringUtils.isEmpty(request.getParameter("to")) ){
                errorMsgs.add("to date parameter is missing");
            }
            
            if ( !errorMsgs.isEmpty() ){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("error", errorMsgs);
                response.getWriter().write(AppConstants.GSON.toJson(responseMap));
                response.getWriter().flush();
                return;
            }
            LocalDate fromDate = null;
            LocalDate toDate = null;
            //Dates have to follow the format: 2011-12-03
            fromDate = LocalDate.parse(request.getParameter("from"));
            toDate = LocalDate.parse(request.getParameter("to"));
            
            JSONObject scheduledEntities = 
                     ScheduleDAO.getScheduledEntities(companyId, fromDate, toDate);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(AppConstants.GSON.toJson(scheduledEntities));
            response.getWriter().flush();
            
            }catch(Throwable throwable){
                logger.error(throwable);
            }
        
    }
    
    @RequestMapping(value = "/ChangeSchedule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void ChangeSchedule(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String type = (String) requestBodyMap.get("type");
            if (type.equalsIgnoreCase("updatesocial")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                String metadataString = requestBodyMap.get("metadata").toString();

                String schedule_desc = (String) requestBodyMap.get("schedule_desc");

                ScheduleSocialPostDAO.updateScheduleSocialPostDetails(companyId,
                        Integer.parseInt(schedule_id),
                        Integer.parseInt(entity_id),
                        schedule_title,
                        scheduleTimeStamp,
                        schedule_desc,
                        com.intbit.AppConstants.GSON.fromJson(metadataString, Map.class)
                );
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("updateemail")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());

                ScheduleDAO.updateScheduleEmailDetails(companyId,
                        Integer.parseInt(schedule_id),
                        Integer.parseInt(entity_id),
                        requestBodyMap.get("email_subject").toString(),
                        requestBodyMap.get("from_email_address").toString(),
                        requestBodyMap.get("email_list").toString(),
                        requestBodyMap.get("reply_to_email_address").toString(),
                        requestBodyMap.get("to_email_addresses").toString().split(","),
                        schedule_title,
                        scheduleTimeStamp
                );
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();

            } else if (type.equalsIgnoreCase("deleteSelected")) {
                String schedule_ids = (String) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");

                ScheduleDAO.deleteSchedules(companyId, schedule_ids);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("delete")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.deleteSchedule(companyId, schedule_ids.intValue());
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("removetemplate")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.removeSavedTemplate(companyId, schedule_ids.intValue());
                
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase(ScheduledEntityType.Reminder.toString())) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                String schedule_desc = (String) requestBodyMap.get("schedule_desc");
                String status = (String) requestBodyMap.get("status");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                ScheduleDAO.updateNoteDetails(companyId, Integer.parseInt(schedule_id),
                        schedule_title, schedule_desc, status, scheduleTimeStamp);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("updateSchedule")) {
                String schedule_id = (String) requestBodyMap.get("scheduleid");
                String entity_id = (String) requestBodyMap.get("entityid");

                try (Connection conn = connectionManager.getConnection()) {
                    Integer id = ScheduleDAO.updateScheduleEntityList(Integer.parseInt(entity_id),
                            Integer.parseInt(schedule_id),
                            TemplateStatus.complete.toString(), conn);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("true");
                    response.getWriter().flush();
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }catch(Throwable throwable){
                logger.error(throwable);
        }
        
    }
    
    @RequestMapping(value = "/AddAction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void AddAction(HttpServletRequest request, HttpServletResponse response) {
        try {
        response.setContentType("application/json");
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        if ( requestBodyMap == null){
            ServletUtil.printIllegalArgumentError(response, "Request body is missing");
            return;
        }
        
        
        try(Connection conn = ConnectionManager.getInstance().getConnection()){
            conn.setAutoCommit(false);
            String type = (String)requestBodyMap.get("type");
            if (type.equalsIgnoreCase("save")){
                String templateStatus = TemplateStatus.no_template.toString();
                if ( ScheduledEntityType.Reminder.toString().equals(requestBodyMap.get("actiontype").toString().toLowerCase())){
                    templateStatus = TemplateStatus.incomplete.toString();
                }
                try{
                        Double tempmarketingType=new Double(requestBodyMap.get("marketingType").toString().trim());
                        Double tempDays = new Double(requestBodyMap.get("days").toString().trim());
                        Integer marketingType=tempmarketingType.intValue();
                        Integer days = tempDays.intValue();
                        int scheduleId = ScheduleDAO.addToScheduleEntityList(null,
                        requestBodyMap.get("title").toString(),
                        marketingType,
                        requestBodyMap.get("description").toString(),
                        new Timestamp(Double.valueOf(requestBodyMap.get("action_date").toString()).longValue()), 
                        requestBodyMap.get("actiontype").toString(), 
                        days.toString().trim(),
                        templateStatus,
                        companyId,
                        conn
                    );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    ServletUtil.printSuccessData(response, data);
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    ServletUtil.printInternalException(response, ex.getMessage());
                }                
            }else if (type.equalsIgnoreCase("update")){
                String scheduleID = (String)requestBodyMap.get("schedule_id");
                try{
                    int scheduleId = ScheduleDAO.updateScheduledEntity(Integer.parseInt(scheduleID),
                        requestBodyMap.get("title").toString(),
                        requestBodyMap.get("description").toString(),
                        new Timestamp(Double.valueOf(requestBodyMap.get("action_date").toString()).longValue()), 
                        requestBodyMap.get("actiontype").toString(), 
                        companyId,
                        Integer.parseInt(requestBodyMap.get("days").toString()),
                        conn
                    );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    ServletUtil.printSuccessData(response, data);
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
                } catch (SQLException ex) {
                     logger.error(ex);
                    conn.rollback();
                    ServletUtil.printInternalException(response, ex.getMessage());
                }                
//                ApplicationContextListener.refreshAllSchedulers();
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
            }
            else if (type.equalsIgnoreCase("updatenotesyourplan")){
                String scheduleID = (String)requestBodyMap.get("schedule_id");
                try{
                    int scheduleId = ScheduleDAO.updateDescriptionScheduledEntity(Integer.parseInt(scheduleID),
                        requestBodyMap.get("description").toString(),
                        conn
                    );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    ServletUtil.printSuccessData(response, data);
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    ServletUtil.printInternalException(response, ex.getMessage());
                }                
//                ApplicationContextListener.refreshAllSchedulers();
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
            }
            else if (type.equalsIgnoreCase("updatenotes")){
                double scheduleID1 = (double)requestBodyMap.get("schedule_id");
                int scheduleID=(int)scheduleID1;
                try{
                    int scheduleId = ScheduleDAO.updateDescriptionScheduledEntity(scheduleID,
                        requestBodyMap.get("description").toString(),
                        conn
                    );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    ServletUtil.printSuccessData(response, data);
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    ServletUtil.printInternalException(response, ex.getMessage());
                }                
//                ApplicationContextListener.refreshAllSchedulers();
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
            }

        } catch (SQLException ex) {
            logger.error(ex);
            ServletUtil.printInternalException(response, ex.getMessage());
        }
        
        
        
        
        }catch(Throwable throwable){
            logger.error(throwable);
        }
    }
    
    @RequestMapping(value = "/GetScheduledEmailDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void GetScheduledEmailDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            if ( StringUtils.isEmpty(request.getParameter("schedule_id")) ){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Schedule id is missing");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
        
            try{
                Integer scheduleEmailId = Integer.parseInt(request.getParameter("schedule_id"));
                Map<String, Object> scheduleEmailDetails = 
                        ScheduleDAO.getScheduleEmailDetails(companyId, scheduleEmailId);
                response.getWriter().write(AppConstants.GSON.toJson(scheduleEmailDetails));
                response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_OK);
            }catch (ParseException parse){
                logger.error(parse);
            }catch(NumberFormatException ex){
                logger.error(ex);
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Schedule id cannot be parsed to integer");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }catch(Throwable throwable){
            logger.error(throwable);
        }
    }
    
    @RequestMapping(value = "/GetScheduledSocialPostDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void GetScheduledSocialPostDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            
            String scheduleId = request.getParameter("schedule_id");
            if ( StringUtils.isEmpty(scheduleId)){
                ServletUtil.printIllegalArgumentError(response, "Schedule id is missing");
                return;
            }
            
            Map<String, Object> schedulePostDetails =
                    ScheduleSocialPostDAO.getScheduleSocialPostDetails(companyId, Integer.parseInt(scheduleId));
            response.getWriter().write(AppConstants.GSON.toJson(schedulePostDetails));
            response.getWriter().flush();
        
            
            
        }catch(Throwable throwable){
            logger.error(throwable);
        }
    }
    
    @RequestMapping(value = "/PostToSocial", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void PostToSocial(HttpServletRequest request, HttpServletResponse response) {
        boolean face = false;
        boolean twit = false;
        try {
            response.setContentType("application/json;charset=UTF-8");
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String htmlString = "";
            String isFacebook = request.getParameter("isFacebook");
            String isTwitter = request.getParameter("isTwitter");
            String getImageFile = request.getParameter("imageToPost");
            String getFile = request.getParameter("imagePost");
            String url = request.getParameter("url");
            String imageType = request.getParameter("imageType");
            String file_image_path = "";
            String returnMessage = "";
            boolean status = true;
            if (imageType.equals("layout")){
                file_image_path = com.intbit.AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;
            }else if (imageType.equals("gallery")) {
                file_image_path = com.intbit.AppConstants.USER_IMAGE_HOME + File.separator + companyId + File.separator + getImageFile;
            }else if (imageType.equals("url")){
                file_image_path = getImageFile;
            }

//            String file_image_path = getServletContext().getRealPath("") + "/temp/"+getImageFile;
            String imagePostURL = ServletUtil.getServerName(request.getServletContext());
            //String imagePostURL = AppConstants.LAYOUT_IMAGES_HOME + getImageFile;
            if (isFacebook.equalsIgnoreCase("true")) {

                String accessToken = request.getParameter("accesstoken");
                String posttext = request.getParameter("postText");
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String url1 = request.getParameter("url");
                returnMessage = PostToFacebook.postStatus(accessToken, title, 
                        file_image_path, posttext, imagePostURL, getImageFile, url1, 
                        description, imageType, companyId, htmlString);
            }if (isTwitter.equalsIgnoreCase("true")) {

                String twitterAccessToken = request.getParameter("twittweraccestoken");
                String twitterTokenSecret = request.getParameter("twitterTokenSecret");
                String text = request.getParameter("text");
                String shortURL = request.getParameter("shorturl");
                PrintWriter out1 = response.getWriter();
                returnMessage = PostToTwitter.postStatus(twitterAccessToken, twitterTokenSecret, 
                        imageType, text, shortURL, file_image_path, companyId, htmlString, getImageFile);
                out1.println(returnMessage);
            }
            logger.log(Priority.DEBUG, "message while facebook post:"+returnMessage);

            if (returnMessage.equals("success") && imageType.equals("layout")){
                file_image_path = com.intbit.AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;
                File deleteFile = new File(file_image_path);
                status = deleteFile.delete();
            }
            logger.log(Priority.DEBUG, "message after social post:"+status);
            
        }catch(Throwable throwable){
            logger.error(throwable);
        }
    }
    
    
    @RequestMapping(value = "/user/testRes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void testRes(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
//            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            response.getWriter().write("true");
            response.getWriter().flush();
        
            
            
        }catch(Throwable throwable){
            logger.error(throwable);
        }
    }
    
    @RequestMapping(value = "/getColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColors(HttpServletRequest request,
            HttpServletResponse response) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        TransactionResponse transactionResponse = new TransactionResponse();
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        JSONObject colorsJson = new JSONObject();
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            String query_string = "Select company_preferences from company_preferences where fk_company_id=" + companyId + "";
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
             if (result_set.next()) {
            PGobject pgobject = (PGobject) result_set.getObject("company_preferences");
                        pgobject.setType("json");
                        String obj = pgobject.getValue();
            
                colorsJson.put("colors", obj);
             }
            String json = new Gson().toJson(colorsJson);
            response.setContentType("application/json");
            response.getWriter().write(json);

        } catch (Exception e) {
            logger.error(e);
        } finally {
            //close connection if required
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
