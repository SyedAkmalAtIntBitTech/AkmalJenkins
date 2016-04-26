/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.responsemappers;

import com.intbittech.modelmappers.CategoryDetails;
import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class CategoryResponse extends BaseResponse implements Serializable {

    public CategoryResponse() {
        super();
        operationStatus = new OperationStatus();
    }

    private CategoryDetails categoryDetails;
    private OperationStatus operationStatus;

    public CategoryDetails getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(CategoryDetails categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

}
