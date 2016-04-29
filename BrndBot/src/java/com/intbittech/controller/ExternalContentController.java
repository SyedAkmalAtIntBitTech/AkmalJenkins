/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.google.gson.Gson;
import com.intbittech.externalcontent.ExternalContentProcessor;
import com.intbittech.externalcontent.ExternalContentSession;
import com.intbittech.externalcontent.ExternalSourceProcessedData;
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.ExternalSourceKeywordLookupService;
import com.intbittech.services.ExternalSourceService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.sql.SQLException;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/externalContent")
public class ExternalContentController {

    private final static Logger logger = Logger.getLogger(ExternalContentController.class);

    ExternalContentProcessor externalContentProcessor;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ExternalContentSession externalContentSession;
    @Autowired
    private ExternalSourceService externalSourceService;
    @Autowired
    private ExternalSourceKeywordLookupService externalSourceKeywordLookupService;
    
    @RequestMapping(value = "/isActivated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> isActivated() throws SQLException {
        GenericResponse<Boolean> genericResponse = new GenericResponse<>();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyID = userProfile.getUser().getFkCompanyId().getCompanyId();
        externalContentProcessor = new ExternalContentProcessor(companyID);
        genericResponse.addDetail(externalContentProcessor.isActivated());
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getActivationLink", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getActivationLink() throws SQLException {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyID = userProfile.getUser().getFkCompanyId().getCompanyId();
        externalContentProcessor = new ExternalContentProcessor(companyID);
        genericResponse.addDetail(externalContentProcessor.getActivationLink());
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    //TODO Ilyas needs to explain whats coming here.
    @RequestMapping(value = "/getListData/{externalSourceKeywordLookupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getListData(@PathVariable("externalSourceKeywordLookupId") Integer externalSourceKeywordLookupId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyID = userProfile.getUser().getFkCompanyId().getCompanyId();
            //TODO what if the user has multiple external sources.
            externalContentProcessor = new ExternalContentProcessor(companyID);
            ExternalSourceKeywordLookup externalSourceKeywordLookup = externalSourceKeywordLookupService.getByExternalSourceKeywordLookupId(externalSourceKeywordLookupId);
            String query = externalSourceKeywordLookup.getFkExternalSourceKeywordId().getExternalSourceKeywordName();
            ExternalSourceProcessedData externalSourceProcessedData = externalContentProcessor.getListData(query);
            String jsonn = new Gson().toJson(externalSourceProcessedData.getJSON());
            genericResponse.addDetail(jsonn);
            externalContentSession.addSessionKeyValue(query + companyID, externalSourceProcessedData.getData_hash_map());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

//    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ContainerResponse> getDetail(HttpServletRequest request,
//            HttpServletResponse response) {
//        GenericResponse<String> genericResponse = new GenericResponse<>();
//        try {
//            Map<String, Object> mindbodyHashMap = null;
//            Map<String, Object> requestBodyMap
//                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
//
//            String categoryId = (String) requestBodyMap.get("category_id");
//            String subCategoryId = (String) requestBodyMap.get("sub_category_id");
//            String subCategoryName = (String) requestBodyMap.get("sub_category_name");
//            String mindbodyId = (String) requestBodyMap.get("mindbody_id");
//            String modelMapperId = (String) requestBodyMap.get("model_mapper_id");
//            String editoryType = (String) requestBodyMap.get("editor_type");
//            String query = (String) requestBodyMap.get("query");
//            String mindbodyQuery = (String) requestBodyMap.get("mindbody_query");
//            Integer blockId = Integer.valueOf(requestBodyMap.get("block_id").toString());
//            
//             if (query != null && !query.equalsIgnoreCase("null") && query.equalsIgnoreCase("block")) {
//                mindbodyHashMap = (Map<String, Object>) externalContentSession.getSessionKeyValue(mindbodyQuery);
//                subCategoryName = mindbodyQuery;//doing this since its a block and we are checking against the query to send appropriate file
//            } else {
//                mindbodyHashMap = (Map<String, Object>) externalContentSession.getSessionKeyValue(k_mind_body);
//            }
//
//            
//            
//            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
//            Integer companyID = userProfile.getUser().getFkCompanyId().getCompanyId();
//            externalContentProcessor = new ExternalContentProcessor(companyID);
//            
//            ExternalSourceProcessedData externalSourceProcessedData = externalContentProcessor.getDetailData(requestBodyMap, id);
//            String jsonn = new Gson().toJson(externalSourceProcessedData.getJSON());
//            genericResponse.addDetail(jsonn);
//            externalContentSession.addSessionKeyValue(query + companyID, externalSourceProcessedData.getData_hash_map());
//            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));
//
//        } catch (Throwable throwable) {
//            logger.error(throwable);
//            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
//        }
//        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
//    }
}
