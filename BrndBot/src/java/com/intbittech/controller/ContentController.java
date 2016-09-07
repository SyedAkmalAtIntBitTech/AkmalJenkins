/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/defaultContent")
public class ContentController {

    private final static Logger logger = Logger.getLogger(ContentController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/generic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getLayoutEmailModelById(@RequestParam("companyId") Integer companyId,
            @RequestParam("emailModelId") Integer emailModelId,
            HttpServletRequest request, HttpServletResponse response) {
        //TODO - Ilyas this is copy of the Generic Announcement Servlet. 
        //I did some of it but kinda confused with the category and sub cat. Please look into this.
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
//            Map<String, Object> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
//            String hostURL = ServletUtil.getServerName(request.getServletContext());
//            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
//            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
//            
//            Integer organization_id = 0, block_id = 0;
//            String editor_type = (String)requestBodyMap.get("editor_type");;
//            Integer model_mapper_id = 0;
//
//            String category_id = (String) getSqlMethodsInstance().session.getAttribute("category_id");
//            String sub_category_id = (String) getSqlMethodsInstance().session.getAttribute("sub_category_id");
//            String sub_category_name = (String) getSqlMethodsInstance().session.getAttribute("sub_category_name");
//            
//            if (MapUtility.mapContainsKey(requestBodyMap, "model_mapper_id")){
//                model_mapper_id = Integer.parseInt((String)requestBodyMap.get("model_mapper_id"));
//            }
//            if (MapUtility.mapContainsKey(requestBodyMap, "block_id")){
//                block_id = Integer.parseInt((String)requestBodyMap.get("block_id"));            
//            }
//            
//            
//            organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
//            
//            String mapperFileName = getSqlMethodsInstance().getMapperFile(user_id, organization_id, Integer.parseInt(category_id), Integer.parseInt(sub_category_id), model_mapper_id, block_id, editor_type);
//            String editor_mapper_file_name = AppConstants.BASE_XML_UPLOAD_PATH + File.separator + mapperFileName + ".xml";
//            JSONObject mapped_json_object = null;
//            sub_category_name = sub_category_name.toLowerCase();
//
//            mapped_json_object = GenericAnnouncementDataMapper.mapAnnouncements(editor_mapper_file_name);
//            if (mapped_json_object != null) {
//                response.setContentType("application/json");
//                response.getWriter().write(mapped_json_object.toString());
//            }
//            
//            String html = emailModelService.getLayoutEmail(emailModelId, hostURL, companyId, requestBodyMap);
//            genericResponse.addDetail(html);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }  
    
    
}
