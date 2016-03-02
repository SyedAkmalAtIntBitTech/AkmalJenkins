/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.controller;

import com.intbittech.model.Category;
import com.intbittech.requestmappers.CategoryDetails;
import com.intbittech.responsemappers.CategoryResponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.services.CategoryService;
import com.intbittech.utility.ErrorHandlingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ajit
 */
@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "getCategoryByCategoryId/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCategoryByCategoryId (@PathVariable("categoryId") Integer categoryId) {
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            Category category = categoryService.getByCategoryId(categoryId);
            CategoryDetails categoryDetails = new CategoryDetails();
            categoryDetails.setCategoryId(category.getCategoryId());
            categoryDetails.setCategoryName(category.getCategoryName());
            categoryResponse.setCategoryDetails(categoryDetails);
            
            categoryResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Category retrieved successfully"));
           

        } catch (Throwable ex) {
            
            categoryResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage())); 
        }
        return new ResponseEntity<>(new ContainerResponse(categoryResponse), HttpStatus.ACCEPTED);
    }

}
