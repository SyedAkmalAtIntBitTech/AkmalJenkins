/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.AppConstants;
import com.intbittech.divtohtml.StringUtil;
import com.intbittech.enums.APIKeyType;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.responsemappers.OperationStatus;
import com.intbittech.responsemappers.OperationStatusType;
import com.intbittech.sendgrid.models.APIKey;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.sendgrid.models.Scopes;
import com.intbittech.sendgrid.models.SendGridAPIDetails;
import com.intbittech.sendgrid.models.SendGridCNameValidity;
import com.intbittech.sendgrid.models.SendGridError;
import com.intbittech.sendgrid.models.SendGridStatsList;
import com.intbittech.sendgrid.models.SendGridUser;
import com.intbittech.sendgrid.models.SendGridUsers;
import com.intbittech.sendgrid.models.SubUserAPIKey;
import com.intbittech.sendgrid.models.Subuser;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.SendGridSubUserDetailsService;
import com.intbittech.utility.IConstants;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.MailSettings;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author AR
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailServiceProviderServiceImpl implements EmailServiceProviderService {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private static Logger logger = Logger.getLogger(EmailServiceProviderServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SendGridSubUserDetailsService sendGridSubUserDetailsService;

    private Map<String, String> generateQueryParams(String key,
            List<String> categories) {
        Map<String, String> map = new HashMap<String, String>();
        if (categories != null && categories.size() > 0) {
            for (String category : categories) {
                map.put(key, category);
            }
        }
        return map;
    }

    private static String concatenateString(String key, List<String> categories) {
        if (categories != null && categories.size() > 0) {
            StringBuilder sbStr = new StringBuilder();
            for (int j = 0; j < categories.size(); j++) {
                if (j != 0) {
                    sbStr.append("&");
                }
                sbStr.append(key + "=" + categories.get(j));
            }

            return sbStr.toString();
        }
        return "";

    }

    @Override
    public SendGridUsers getAllSubusers() throws ProcessFailed {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SendGridUsers sendGridUsers = new SendGridUsers();
            SendGrid sg = new SendGrid(AppConstants.KSendGridAPIKey);
            Request request = new Request();
            request.method = Method.GET;
            request.endpoint = "whitelabel/domains";
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("exclude_subusers", "false");
            queryParams.put("limit", "100");
            queryParams.put("offset", "0");
            request.queryParams = queryParams;
            logRequest(request);
            Response response = sg.api(request);
            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                List sendGridUserList = mapper.readValue(response.body, List.class);
                List<SendGridUser> sendGridUsersList = new ArrayList<>();
                for (Object object : sendGridUserList) {
                    sendGridUsersList.add(mapper.convertValue(object, SendGridUser.class));
                }
                sendGridUsers.setSendGridUsers(sendGridUserList);
            } else {
                sendGridUsers.setOperationStatus(mapError(response));
            }
            return sendGridUsers;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    @Override
    public SendGridUser getSubuserDetails(String userName) throws ProcessFailed {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SendGridUser sendGridUser = new SendGridUser();
            SendGrid sg = new SendGrid(AppConstants.KSendGridAPIKey);
            Request request = new Request();
            request.method = Method.GET;
            request.endpoint = "whitelabel/domains";
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("exclude_subusers", "false");
            queryParams.put("limit", "100");
            queryParams.put("offset", "0");
            if (!StringUtil.isEmpty(userName)) {
                queryParams.put("username", userName);
            }
            request.queryParams = queryParams;

            logRequest(request);
            Response response = sg.api(request);
            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                sendGridUser = mapper.convertValue(response.body, SendGridUser.class);
            } else {
                sendGridUser.setOperationStatus(mapError(response));
            }
            return sendGridUser;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    @Override
    public SendGridCNameValidity validateCNAME(String sendGridUserId) throws ProcessFailed {
        try {
            SendGridCNameValidity sendGridCNameValidity = new SendGridCNameValidity();
            ObjectMapper mapper = new ObjectMapper();
            SendGrid sg = new SendGrid(AppConstants.KSendGridAPIKey);
            Request request = new Request();
            request.method = Method.POST;
            request.endpoint = "whitelabel/domains/" + sendGridUserId + "/validate";
            logRequest(request);
            Response response = sg.api(request);
            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                sendGridCNameValidity = mapper.convertValue(response.body, SendGridCNameValidity.class);
            } else {
                sendGridCNameValidity.setOperationStatus(mapError(response));
            }
            return sendGridCNameValidity;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    public SendGrid getSendGridWithAPI(APIKeyType apiKeyType, Integer companyId) {
        SendGrid sendGrid = null;
        if (apiKeyType.equals(APIKeyType.Main)) {
            sendGrid = new SendGrid(AppConstants.KSendGridAPIKey);
        }
        if (apiKeyType.equals(APIKeyType.SubUser)) {
            //Get API key from db for current company
            SendGridAPIDetails sendGridAPIDetails = sendGridSubUserDetailsService.getSendGridAPIDetailsByCompanyId(companyId);
            sendGrid = new SendGrid(sendGridAPIDetails.getApiKey());
        }

        return sendGrid;
    }

//    getAPIKey(typeKey = email stats, emailtype = )
    @Override
    public OperationStatus sendEmail(Mail mail, EmailType emailType, Integer companyId, String fromName) throws ProcessFailed {
        try {
            if (companyId == 0){
                String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
                mail = formatTo(mail, emailType, companyName);
            }else {
                mail = formatTo(mail, emailType, fromName);
            }

            OperationStatus operationStatus = new OperationStatus();
            MailSettings mailSettings = new MailSettings();
            mail.setMailSettings(mailSettings);
            SendGrid sendGrid = null;
            if (emailType.equals(EmailType.BrndBot_NoReply)) {
                sendGrid = getSendGridWithAPI(APIKeyType.Main, companyId);
            } else {
                sendGrid = getSendGridWithAPI(APIKeyType.SubUser, companyId);
            }
            Request request = new Request();
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            logRequest(request);
            Response response = sendGrid.api(request);
            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            operationStatus.setStatusCode(type);

            if (type != OperationStatusType.Success) {
                operationStatus = mapError(response);
            } else {
            }
            return operationStatus;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (Throwable throwable){
            logger.error(throwable.getMessage());
            throw new ProcessFailed(throwable.getMessage());
        }

    }

    @Override
    public SendGridStatsList getStatsByCategory(String userId, List<String> categories, Date startDate, Date endDate, Integer companyId) throws ProcessFailed {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SendGridStatsList sendGridStats = new SendGridStatsList();

            SendGrid sg = getSendGridWithAPI(APIKeyType.Main, 0);
            SendGridSubUserDetails sendGridSubUserDetails = sendGridSubUserDetailsService.getByCompanyId(companyId);
            sg.addRequestHeader("on-behalf-of", sendGridSubUserDetails.getSendGridUserId());

            startDate = getStartDate(startDate);
            if (endDate == null) {
                endDate = new Date();
            }
            String startDateString = dateFormatter.format(startDate);
            String endDateString = dateFormatter.format(endDate);

            Map<String, String> queryParams = generateQueryParams("categories", categories);

            Request request = new Request();
            request.method = Method.GET;
            request.endpoint = "categories/stats";
            queryParams.put("start_date", startDateString);
            request.queryParams = queryParams;
            queryParams.put("end_date", endDateString);
            queryParams.put("aggregated_by", "month");
            logRequest(request);
            Response response = sg.api(request);

            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                sendGridStats = mapper.readValue("{\"sendGridStats\":" + response.body + "}", SendGridStatsList.class);
            } else {
                sendGridStats.setOperationStatus(mapError(response));
            }
            return sendGridStats;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    @Override
    public SendGridUser addSubuser(Subuser subuser) throws ProcessFailed {
        try {
            SendGridUser sendGridUser = new SendGridUser();
            ObjectMapper mapper = new ObjectMapper();

            SendGrid sg = getSendGridWithAPI(APIKeyType.Main, 0);
            Request request = new Request();
            request.method = Method.POST;
            request.endpoint = "subusers";
            request.body = subuser.build();
            logRequest(request);
            Response response = sg.api(request);
            logResponse(response);
            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                sendGridUser = mapper.readValue(response.body, SendGridUser.class);
            } else {
                sendGridUser.setOperationStatus(mapError(response));
            }
            return sendGridUser;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }

    }

    public Scopes getScopes(List<String> scopeList) {
        Scopes scopes = new Scopes();
        for (String scope : scopeList) {
            scopes.setScopes(scope);
        }

        return scopes;
    }

    public List<String> getSubUserScopes() {
        List<String> subUserScopeList = new ArrayList<>();
        subUserScopeList.add("api_keys.create");
        subUserScopeList.add("api_keys.delete");
        subUserScopeList.add("api_keys.read");
        subUserScopeList.add("api_keys.update");
        subUserScopeList.add("mail.batch.create");
        subUserScopeList.add("mail.batch.delete");
        subUserScopeList.add("mail.batch.read");
        subUserScopeList.add("mail.batch.update");
        subUserScopeList.add("mail.send");
        Scopes subUserScopes = getScopes(subUserScopeList);
        return subUserScopes.getScopes();
    }

    public APIKey getSubUserDataObject(String keyName) {
        APIKey apiKey = new APIKey();
        apiKey.setName(keyName);
        apiKey.setScopes(getSubUserScopes());

        return apiKey;
    }

    @Override
    public SubUserAPIKey createSubUserAPIKey(String subUserId) throws ProcessFailed {
        try {
            SubUserAPIKey subUserAPIKey = new SubUserAPIKey();
            ObjectMapper mapper = new ObjectMapper();

            SendGrid sg = getSendGridWithAPI(APIKeyType.Main, 0);
            sg.addRequestHeader("on-behalf-of", subUserId);
            Request request = new Request();
            request.method = Method.POST;
            request.endpoint = "api_keys";
            String keyName = subUserId + AppConstants.EMAIL_API_KEY_NAME;
            request.body = getSubUserDataObject(keyName).build();

            logRequest(request);
            Response response = sg.api(request);
            logResponse(response);

            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.Success) {
                subUserAPIKey = mapper.readValue(response.body, SubUserAPIKey.class);
            } else {
                subUserAPIKey.setOperationStatus(mapError(response));
            }

            return subUserAPIKey;
        } catch (JsonProcessingException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    @Override
    public Boolean changePassword(String subUserId, String newPassword, String oldPassword) throws ProcessFailed {
        try {
            Boolean returnStatus = true;
            SendGrid sg = getSendGridWithAPI(APIKeyType.Main, 0);
            sg.addRequestHeader("on-behalf-of", subUserId);
            Request request = new Request();
            request.method = Method.PUT;
            request.endpoint = "/user/password";
            request.body = "{\"new_password\": \"" + newPassword + "\", \"old_password\": \"" + oldPassword + "\"}";

            logRequest(request);
            Response response;

            response = sg.api(request);

            logResponse(response);

            OperationStatusType type = SendGridError.parseStatusCode(response.statusCode);
            if (type == OperationStatusType.DataError || type == OperationStatusType.RequestError) {
                returnStatus = false;
            }
            return returnStatus;
        } catch (IOException ex) {
            logger.error(ex);
            throw new ProcessFailed(ex.getMessage());
        }
    }

    private void logRequest(Request request) {
        logger.error("Request Endpoint:" + request.endpoint + "  Request Body:" + request.body);
    }

    private void logResponse(Response response) {
        logger.error("Response Status:" + response.statusCode + "  Response Body:" + response.body + "  Response Headers:" + response.headers);

    }

    private OperationStatus mapError(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        SendGridError sendGridError = mapper.convertValue(response.body, SendGridError.class);
        OperationStatus operationStatus = new OperationStatus();
        List<String> messages = new ArrayList<>();
        for (com.intbittech.sendgrid.models.Error error : sendGridError.getErrors()) {
            messages.add(error.getMessage());
        }
        operationStatus.setMessages(messages);
        operationStatus.setStatusCode(OperationStatusType.DataError);
        return operationStatus;
    }

    private Date getStartDate(Date startDate) {
        if (startDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -150);//Going all the way back to 6 months
            Date pastDate = cal.getTime();
            return pastDate;

        }
        return startDate;
    }

    private Mail formatTo(Mail mail, EmailType emailType, String fromEmailId) {
        Email fromObject;
        if (emailType == EmailType.BrndBot_NoReply) {
            if (fromEmailId.equals('0')){
                String companyName = messageSource.getMessage("companyName", new String[]{}, Locale.US);
                fromObject = new Email(IConstants.kNoReplyBrndbot, companyName);
            }else {
                fromObject = new Email(IConstants.kNoReplyBrndbot, fromEmailId);
            }
            mail.setFrom(fromObject);
        }
        return mail;
    }
}
