/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.controller;

import com.intbittech.model.Category;
import com.intbittech.model.Channel;
import com.intbittech.model.OrganizationCategoryLookup;
import com.intbittech.modelmappers.CategoryDetails;
import com.intbittech.modelmappers.ChannelDetails;
import com.intbittech.responsemappers.CategoryResponse;
import com.intbittech.responsemappers.ChannelCategoryReponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.services.CategoryService;
import com.intbittech.services.ChannelService;
import com.intbittech.services.OrganizationCategoryLookupService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ajit
 */
@Controller
public class CategoryController {

    private static Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ChannelService channelService;
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
        List<CategoryDetails> categoryDetailList = new ArrayList<>();
        List<ChannelDetails> channelDetailsList = new ArrayList<>();
        try {
            List<Channel> channelList = channelService.getAllChannels();
            for (Channel channel : channelList) {
                ChannelDetails channelDetails = new ChannelDetails();
                channelDetails.setChannelName(channel.getChannelName());

                List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupService.getAllOrganizationCategoryLookup(organizationId, channel.getChannelId());
                for (OrganizationCategoryLookup organizationCategoryLookup : OrganizationCategoryList) {
                    CategoryDetails categoryDetails = new CategoryDetails();
                    categoryDetails.setCategoryId(organizationCategoryLookup.getFkCategoryId().getCategoryId());
                    categoryDetails.setCategoryName(organizationCategoryLookup.getFkCategoryId().getCategoryName());
                    categoryDetailList.add(categoryDetails);
                }
                channelDetails.setCategoryDetailsList(categoryDetailList);
                channelDetailsList.add(channelDetails);
                channelCategoryReponse.setChannelDetailsList(channelDetailsList);

            }

            channelCategoryReponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category retrieved successfully"));

        } catch (Throwable ex) {
            logger.error(ex);
            channelCategoryReponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(channelCategoryReponse), HttpStatus.ACCEPTED);
    }

}
