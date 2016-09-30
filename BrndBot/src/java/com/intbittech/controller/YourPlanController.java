/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbit.ConnectionManager;
import com.intbittech.enums.ScheduledEntityType;
import com.intbittech.enums.TemplateStatus;
import com.intbittech.utility.ServletUtil;
import com.intbittech.dao.impl.ScheduleDAO;
import com.intbittech.AppConstants;
import com.intbittech.dao.impl.ScheduleSocialPostDAO;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.intbittech.social.PostToFacebook;
import com.intbittech.social.PostToTwitter;
import com.intbittech.utility.Utility;
import java.time.LocalDateTime;
//import java.sql.Date;


/**
 *
 * @author ilyas
 */
@Controller
public class YourPlanController {
    
    private Logger logger = Logger.getLogger(YourPlanController.class);
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "/GetScheduledEntities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> GetScheduledEntities(HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try{
            List<String> messageList = new ArrayList<>();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
 
        List<String> errorMsgs = new ArrayList<>();
            
            if ( StringUtils.isEmpty(request.getParameter("from")) ){
                errorMsgs.add("from date parameter is missing");
            }
            if ( StringUtils.isEmpty(request.getParameter("to")) ){
                errorMsgs.add("to date parameter is missing");
            }
            
            if ( !errorMsgs.isEmpty() ){
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("error", errorMsgs);
                
                throw new ProcessFailed(AppConstants.GSON.toJson(responseMap));
            }
            LocalDate fromDate = null;
            LocalDate toDate = null;
            //Dates have to follow the format: 2011-12-03
            fromDate = LocalDate.parse(request.getParameter("from"));
            toDate = LocalDate.parse(request.getParameter("to"));
            Integer companyId= Integer.parseInt(request.getParameter("companyId"));
            JSONObject scheduledEntities = 
                     ScheduleDAO.getScheduledEntitiesWithUser(companyId, fromDate, toDate);
            messageList.add(AppConstants.GSON.toJson(scheduledEntities));
            genericResponse.setDetails(messageList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
            }catch(Throwable throwable){
                logger.error(throwable);
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(AppConstants.GSON.toJson(throwable.getMessage())));
                
            }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/ChangeSchedule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> ChangeSchedule(HttpServletRequest request, HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            String type = (String) requestBodyMap.get("type");
            String messageStatus = "";
            if (type.equalsIgnoreCase("updatesocial")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                String metadataString = requestBodyMap.get("metadata").toString();

                String schedule_desc = (String) requestBodyMap.get("schedule_desc");

                ScheduleSocialPostDAO.updateScheduleSocialPostDetails(userCompanyIds.getCompanyId(),
                        Integer.parseInt(schedule_id),
                        Integer.parseInt(entity_id),
                        schedule_title,
                        scheduleTimeStamp,
                        schedule_desc,
                        AppConstants.GSON.fromJson(metadataString, Map.class)
                );
                
                messageStatus = "true";
            } else if (type.equalsIgnoreCase("updateemail")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());

                ScheduleDAO.updateScheduleEmailDetails(userCompanyIds.getCompanyId(),
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
                messageStatus = "true";

            } else if (type.equalsIgnoreCase("deleteSelected")) {
                String schedule_ids = (String) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");

                ScheduleDAO.deleteSchedules(userCompanyIds.getCompanyId(), schedule_ids);
                messageStatus = "true";
            } else if (type.equalsIgnoreCase("delete")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.deleteSchedule(userCompanyIds.getCompanyId(), schedule_ids.intValue());
                messageStatus="true";
            } else if (type.equalsIgnoreCase("removetemplate")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.removeSavedTemplate(userCompanyIds.getCompanyId(), schedule_ids.intValue());
                
                messageStatus = "true";
            } else if (type.equalsIgnoreCase(ScheduledEntityType.Reminder.toString())) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                String schedule_desc = (String) requestBodyMap.get("schedule_desc");
                String status = (String) requestBodyMap.get("status");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                ScheduleDAO.updateNoteDetails(userCompanyIds.getCompanyId(), Integer.parseInt(schedule_id),
                        schedule_title, schedule_desc, status, scheduleTimeStamp);
                messageStatus = "true";
            } else if (type.equalsIgnoreCase("updateSchedule")) {
                String schedule_id = (String) requestBodyMap.get("scheduleid");
                String entity_id = (String) requestBodyMap.get("entityid");

                try (Connection conn = connectionManager.getConnection()) {
                    Integer id = ScheduleDAO.updateScheduleEntityList(Integer.parseInt(entity_id),
                            Integer.parseInt(schedule_id),
                            TemplateStatus.complete.toString(), conn);
                    messageStatus = "true";
                } catch (Exception e) {
                    logger.error(e);
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(e.getMessage()));
                }
            }
            transactionResponse.setMessage(messageStatus);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
        }catch(Throwable throwable){
                logger.error(throwable);
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/AddAction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> AddAction(HttpServletRequest request, HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
        Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
        if ( requestBodyMap == null){
            throw new ProcessFailed(messageSource.getMessage("something_wrong",new String[]{}, Locale.US));
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
                        Double TempUserAssignToId = new Double(requestBodyMap.get("userAssignToId").toString().trim());
                        Integer userAssignToId = TempUserAssignToId.intValue();
                        int scheduleId = ScheduleDAO.addToScheduleEntityList(null,
                            requestBodyMap.get("title").toString(),
                            marketingType,
                            requestBodyMap.get("description").toString(),
                            new Timestamp(Double.valueOf(requestBodyMap.get("action_date").toString()).longValue()), 
                            requestBodyMap.get("actiontype").toString(), 
                            days.toString().trim(),
                            templateStatus,
                            userCompanyIds.getCompanyId(),userCompanyIds.getUserId(),userAssignToId,
                            conn
                        );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    
                    transactionResponse.setMessage(AppConstants.GSON.toJson(data));
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
                }                
            }else if (type.equalsIgnoreCase("update")){
                String scheduleID = (String)requestBodyMap.get("schedule_id");
                try{
                    int scheduleId = ScheduleDAO.updateScheduledEntity(Integer.parseInt(scheduleID),
                        requestBodyMap.get("title").toString(),
                        requestBodyMap.get("description").toString(),
                        new Timestamp(Double.valueOf(requestBodyMap.get("action_date").toString()).longValue()), 
                        requestBodyMap.get("actiontype").toString(), 
                        userCompanyIds.getCompanyId(),
                        Integer.parseInt(requestBodyMap.get("days").toString()),
                        conn
                    );
                    conn.commit();
                    Map<String, Object> data = new HashMap<>();
                    data.put("schedule_entity_id", scheduleId);
                    transactionResponse.setMessage(AppConstants.GSON.toJson(data));
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
                } catch (SQLException ex) {
                     logger.error(ex);
                    conn.rollback();
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
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
                    transactionResponse.setMessage(AppConstants.GSON.toJson(data));
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
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
                    transactionResponse.setMessage(AppConstants.GSON.toJson(data));
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
                } catch (SQLException ex) {
                    logger.error(ex);
                    conn.rollback();
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
                }                
//                ApplicationContextListener.refreshAllSchedulers();
//                    Logger.getLogger(AddActionServlet.class.getName()).log(Level.SEVERE, "Log while updating the Actions");
            }

        } catch (Exception ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        
        
        
        
        }catch(Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/GetScheduledEmailDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> GetScheduledEmailDetail(HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            List<String> messageList = new ArrayList<>();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
 
            
            if ( StringUtils.isEmpty(request.getParameter("schedule_id")) ){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Schedule id is missing");
                throw new ProcessFailed(AppConstants.GSON.toJson(error));
            }
        
            try{
                Integer scheduleEmailId = Integer.parseInt(request.getParameter("schedule_id"));
                Integer companyId= Integer.parseInt(request.getParameter("companyId"));
                Map<String, Object> scheduleEmailDetails = 
                        ScheduleDAO.getScheduleEmailDetails(companyId, scheduleEmailId);
                messageList.add(AppConstants.GSON.toJson(scheduleEmailDetails));
                genericResponse.setDetails(messageList);
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
            }catch (ParseException parse){
                logger.error(parse);
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(parse.getMessage()));
            }catch(NumberFormatException ex){
                logger.error(ex);
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Schedule id cannot be parsed to integer");
                throw new ProcessFailed(AppConstants.GSON.toJson(error));
                } catch (SQLException ex) {
                logger.error(ex);
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
            }
        }catch(Throwable throwable){
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/GetScheduledSocialPostDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> GetScheduledSocialPostDetail(HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            List<String> messageList = new ArrayList<>();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
 
            
            
            String scheduleId = request.getParameter("schedule_id");
            if ( StringUtils.isEmpty(scheduleId)){
                throw new ProcessFailed("Schedule id is missing");
            }
            
            Map<String, Object> schedulePostDetails =
                    ScheduleSocialPostDAO.getScheduleSocialPostDetails(Integer.parseInt(request.getParameter("companyId")), Integer.parseInt(scheduleId));
            
            messageList.add(AppConstants.GSON.toJson(schedulePostDetails));
            genericResponse.setDetails(messageList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
        }catch(Throwable throwable){
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/PostToSocial", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> PostToSocial(HttpServletRequest request, HttpServletResponse response) {
        boolean face = false;
        boolean twit = false;
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
 
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
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
//                file_image_path = com.intbit.AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;
            }else if (imageType.equals("gallery")) {
                file_image_path = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + userCompanyIds.getCompanyId() + File.separator + AppConstants.GALLERY_FOLDERNAME + File.separator + getImageFile;
            }else if (imageType.equals("url")){
                file_image_path = getImageFile;
            }

            String imagePostURL = ServletUtil.getServerName(request.getServletContext());
            if (isFacebook.equalsIgnoreCase("true")) {

                String posttext = request.getParameter("postText");
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String url1 = request.getParameter("url");
                returnMessage = PostToFacebook.postStatus(title, 
                        file_image_path, posttext, imagePostURL, getImageFile, url1, 
                        description, imageType, userCompanyIds.getCompanyId(), htmlString);
            }if (isTwitter.equalsIgnoreCase("true")) {

                String text = request.getParameter("text");
                String shortURL = request.getParameter("shorturl");
                PrintWriter out1 = response.getWriter();
                returnMessage = PostToTwitter.postStatus( 
                        imageType, text, shortURL, file_image_path, userCompanyIds.getCompanyId(), htmlString, getImageFile);
                transactionResponse.setMessage(returnMessage);
            }
            logger.log(Priority.DEBUG, "message while facebook post:"+returnMessage);

//            if (returnMessage.equals("success") && imageType.equals("layout")){
//                file_image_path = com.intbit.AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;
//                File deleteFile = new File(file_image_path);
//                status = deleteFile.delete();
//            }
            logger.log(Priority.DEBUG, "message after social post:"+status);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success",new String[]{}, Locale.US)));
        }catch(Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
}
