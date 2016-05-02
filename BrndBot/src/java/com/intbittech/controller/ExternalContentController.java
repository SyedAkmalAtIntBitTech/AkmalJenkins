/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.google.gson.Gson;
import com.intbit.util.ServletUtil;
import com.intbittech.externalcontent.ExternalContentProcessor;
import com.intbittech.externalcontent.ExternalContentSession;
import com.intbittech.externalcontent.ExternalSourceProcessedData;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.model.SubCategoryExternalSource;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.EmailBlockExternalSourceService;
import com.intbittech.services.EmailBlockModelLookupService;
import com.intbittech.services.EmailModelService;
import com.intbittech.services.ExternalSourceKeywordLookupService;
import com.intbittech.services.SubCategoryEmailModelService;
import com.intbittech.services.SubCategoryExternalSourceService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    private SubCategoryExternalSourceService subCategoryExternalSourceService;
    @Autowired
    private ExternalSourceKeywordLookupService externalSourceKeywordLookupService;
    @Autowired
    private EmailModelService emailModelService;
    @Autowired
    private EmailBlockModelLookupService emailBlockModelLookupService;
    @Autowired
    private EmailBlockExternalSourceService emailBlockExternalSourceService;
    @Autowired
    private SubCategoryEmailModelService subCategoryEmailModelService;

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
            String keyname = externalContentProcessor.getExternalSourceMapKeyName(query);
            String jsonn = new Gson().toJson(externalSourceProcessedData.getJSON());
            genericResponse.addDetail(jsonn);
            externalContentSession.addSessionKeyValue(keyname, externalSourceProcessedData.getData_hash_map());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getLayoutEmailModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getLayoutEmailModelById(@RequestParam("emailModelId") Integer modelId,
            @RequestParam("isBlock") Boolean isBlock,
            @RequestParam("externalDataId") Integer externalDataId,
            HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            String hostURL = ServletUtil.getServerName(request.getServletContext());
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            Map<String, Object> data = new HashMap<>();
            ExternalSourceKeywordLookup externalSourceKeywordLookup = null;
            externalContentProcessor = new ExternalContentProcessor(companyId);
            Integer externalKeywordId = null;
            Integer emailOrBlockModel = 0;
            Integer blockOrSubcategoryId = 0;
            if (isBlock) {
                EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupService.getByEmailModelId(modelId);
                emailOrBlockModel = emailBlockModelLookup.getFkEmailBlockModelId().getEmailBlockModelId();
                blockOrSubcategoryId = emailBlockModelLookup.getFkEmailBlockId().getEmailBlockId();
            } else {
                SubCategoryEmailModel subCategoryEmailModel = subCategoryEmailModelService.getBySubCategoryEmailModelId(modelId);
                emailOrBlockModel = subCategoryEmailModel.getFkEmailModelId().getEmailModelId();
                blockOrSubcategoryId = subCategoryEmailModel.getFkSubCategoryId().getSubCategoryId();
            }
            if (externalDataId != 0) {
                if (isBlock) {
                    //Now emailModelId is actually email_block_model_lookup_id 
                    //get email_block_id from email_block_model_lookup then 
                    //get external_source_keyword_lookup_id from email_block_external_source
//                    EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupService.getByEmailModelId(modelId);
                    EmailBlockExternalSource emailBlockExternalSource = emailBlockExternalSourceService.getByEmailBlockId(blockOrSubcategoryId);
                    externalKeywordId = emailBlockExternalSource.getFkExternalSourceKeywordLookupId().getExternalSourceKeywordLookupId();

                } else {
                    //Now emailModelId is actually sub_caterogy_email_model_id
                    //get sub_caterogy_id from sub_caterogy_email_model 
                    //get external_source_keyword_lookup_id from sub_caterogy_external_source
//                    SubCategoryEmailModel subCategoryEmailModel = subCategoryEmailModelService.getBySubCategoryEmailModelId(modelId);
                    SubCategoryExternalSource subCategoryExternalSource = subCategoryExternalSourceService.getBySubCategoryId(blockOrSubcategoryId);
                    externalKeywordId = subCategoryExternalSource.getFkExternalSourceKeywordLookupId().getExternalSourceKeywordLookupId();

                }
                //Defensive code
                if (externalKeywordId != null || externalKeywordId != 0) {
                    externalSourceKeywordLookup = externalSourceKeywordLookupService.getByExternalSourceKeywordLookupId(externalKeywordId);
                    String query = externalSourceKeywordLookup.getFkExternalSourceKeywordId().getExternalSourceKeywordName();
                    String keyname = externalContentProcessor.getExternalSourceMapKeyName(query);
                    data = (Map<String, Object>) externalContentSession.getSessionKeyValue(keyname);
                }

            }
            String html = emailModelService.getLayoutEmail(isBlock,emailOrBlockModel, hostURL, companyId, externalSourceKeywordLookup, externalDataId, data);
            genericResponse.addDetail(html);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

}
