/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.EmailDataDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.SendEmailService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/email")
public class EmailController {

    private final static Logger logger = Logger.getLogger(EmailController.class);

    @Autowired
    SendEmailService sendEmailService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    UsersService usersService;
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> sendEMail(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            String html_text = "";
            String email_subject = (String) requestBodyMap.get("email_subject");
            if ((String) requestBodyMap.get("htmldata") != null) {
                html_text = (String) requestBodyMap.get("htmldata");
            }
            String emaillist_name = (String) requestBodyMap.get("email_list");
            String reply_to_address = (String) requestBodyMap.get("reply_to_email_address");
            String from_email_address = (String) requestBodyMap.get("from_email_address");
            String from_name = (String) requestBodyMap.get("from_name");
            String path = "";
            if ((String) requestBodyMap.get("iframeName") != null) {
                String iframeName = (String) requestBodyMap.get("iframeName");
                path = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html";
                File file = new File(path);
                html_text = FileUtils.readFileToString(file, "UTF-8");
            }
            EmailDataDetails emailDataDetails = new EmailDataDetails();
            emailDataDetails.setCompanyId(userCompanyIds.getCompanyId());
            emailDataDetails.setEmailListName(emaillist_name);
            emailDataDetails.setEmailSubject(email_subject);
            emailDataDetails.setFromEmailAddress(from_email_address);
            emailDataDetails.setFromName(from_name);
            emailDataDetails.setHtmlData(html_text);
            emailDataDetails.setReplyToEmailAddress(reply_to_address);
            emailDataDetails.setIsRecurring(Boolean.FALSE);
            sendEmailService.sendMail(emailDataDetails);
            //Added by Syed Ilyas 27 Nov 2015 - changed to NOT
            if (!path.equals("")) {
                File IframeDelete = new File(path);
                IframeDelete.delete();
            }
            transactionResponse.setMessage("true");
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> get(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("userId") Integer userId, @RequestParam("companyId") Integer companyId) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Company company = companyService.getCompanyById(companyId);
            String data = sendEmailService.getTags(company.getCompanyId());
            transactionResponse.addDetail(data);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/previewServlet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> previewEmail(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            String data = sendEmailService.previewEmail(userCompanyIds.getCompanyId(), requestBodyMap);
            transactionResponse.addDetail(data);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
