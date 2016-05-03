/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Category;
import com.intbittech.model.Channel;
import com.intbittech.model.OrganizationCategoryLookup;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.CategoryDetails;
import com.intbittech.modelmappers.ChannelDetails;
import com.intbittech.modelmappers.OrganizationCompanyDetails;
import com.intbittech.modelmappers.SubCategoryDetails;
import com.intbittech.responsemappers.CategoryResponse;
import com.intbittech.responsemappers.ChannelCategoryReponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CategoryService;
import com.intbittech.services.ChannelService;
import com.intbittech.services.CompanyService;
import com.intbittech.services.OrganizationCategoryLookupService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajit
 */
@RestController
public class CategoryController {

    private static Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private OrganizationCategoryLookupService organizationCategoryLookupService;

    @RequestMapping(value = "getCategoryByCategoryId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCategoryByCategoryId(@RequestParam("categoryId") Integer categoryId) {
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            Category category = categoryService.getByCategoryId(categoryId);
            CategoryDetails categoryDetails = new CategoryDetails();
            categoryDetails.setCategoryId(category.getCategoryId());
            categoryDetails.setCategoryName(category.getCategoryName());
            categoryResponse.setCategoryDetails(categoryDetails);

            categoryResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category retrieved successfully"));

        } catch (Throwable ex) {
            logger.error(ex);
            categoryResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(categoryResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getAllOrganizationCategoryByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllOrganizationCategoryByOrganizationId(@RequestParam("organizationId") Integer organizationId) {
        ChannelCategoryReponse channelCategoryReponse = new ChannelCategoryReponse();

        List<ChannelDetails> channelDetailsList = new ArrayList<>();
        try {
            List<Channel> channelList = channelService.getAllChannels();
            for (Channel channel : channelList) {
                ChannelDetails channelDetails = new ChannelDetails();
                channelDetails.setChannelName(channel.getChannelName());

                List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupService.getAllOrganizationCategoryLookup(organizationId, channel.getChannelId());
                List<CategoryDetails> categoryDetailList = new ArrayList<>();
                if (OrganizationCategoryList != null) {
                    for (OrganizationCategoryLookup organizationCategoryLookup : OrganizationCategoryList) {

                        CategoryDetails categoryDetails = new CategoryDetails();
                        categoryDetails.setCategoryId(organizationCategoryLookup.getFkCategoryId().getCategoryId());
                        categoryDetails.setCategoryName(organizationCategoryLookup.getFkCategoryId().getCategoryName());
                        categoryDetailList.add(categoryDetails);
                    }
                }
                channelDetails.setCategoryDetailsList(categoryDetailList);
                channelDetailsList.add(channelDetails);

            }
            channelCategoryReponse.setChannelDetailsList(channelDetailsList);

            channelCategoryReponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category retrieved successfully"));

        } catch (Throwable ex) {
            logger.error(ex);
            channelCategoryReponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(channelCategoryReponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCategory(@RequestBody CategoryDetails categoryDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            categoryService.saveCategory(categoryDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateCategory(@RequestBody CategoryDetails categoryDetails, @RequestParam("categoryId") Integer categoryId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Category category = categoryService.getByCategoryId(categoryId);
            category.setCategoryName(categoryDetails.getCategoryName());
            Channel channel = new Channel();
            channel.setChannelId(categoryDetails.getChannelId());
            category.setFkChannelId(channel);
            categoryService.update(category);

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category updated successfully"));

        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteCategory(@RequestParam("categoryId") Integer categoryId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            categoryService.delete(categoryId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllCompanyCategories",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCompanyCategories(@RequestParam("channelId") Integer channelId) {
        GenericResponse<CategoryDetails> genericResponse = new GenericResponse<CategoryDetails>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationsByCompanyId(companyId);
            List<OrganizationCompanyDetails> organizationCompanyDetailsList = new ArrayList<>();
            Integer organizationCompanySize = 1;
            if(organizationCompanyDetail!=null)
                organizationCompanySize = organizationCompanyDetail.size()+1;
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i =0;
            organizationIds[i++] = 0;
            if(organizationCompanyDetail!=null)
            {
                for(OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }
            List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupService.getAllOrganizationCategoryLookupByIds(organizationIds, channelId);
                List<CategoryDetails> categoryDetailList = new ArrayList<>();
                if (OrganizationCategoryList != null) {
                    for (OrganizationCategoryLookup organizationCategoryLookup : OrganizationCategoryList) {

                        CategoryDetails categoryDetails = new CategoryDetails();
                        categoryDetails.setCategoryId(organizationCategoryLookup.getFkCategoryId().getCategoryId());
                        categoryDetails.setCategoryName(organizationCategoryLookup.getFkCategoryId().getCategoryName());
                        categoryDetails.setChannelId(organizationCategoryLookup.getFkCategoryId().getFkChannelId().getChannelId());
                        categoryDetails.setOrganizationId(organizationCategoryLookup.getFkOrganizationId().getOrganizationId());
                        categoryDetails.setOrganizationName(organizationCategoryLookup.getFkOrganizationId().getOrganizationName());
                        categoryDetailList.add(categoryDetails);
                    }
                }
            genericResponse.setDetails(categoryDetailList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_get",new String[]{}, Locale.US)));   
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

}
