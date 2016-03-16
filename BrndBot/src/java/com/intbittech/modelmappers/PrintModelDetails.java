/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author development
 */
public class PrintModelDetails {
    private Integer subCategoryPrintModelId;
    private Integer printModelId;
    private String printModelName;

    private Integer subCategoryId;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    
    public Integer getSubCategoryPrintModelId() {
        return subCategoryPrintModelId;
    }

    public void setSubCategoryPrintModelId(Integer subCategoryPrintModelId) {
        this.subCategoryPrintModelId = subCategoryPrintModelId;
    }

    public Integer getPrintModelId() {
        return printModelId;
    }

    public void setPrintModelId(Integer printModelId) {
        this.printModelId = printModelId;
    }

    public String getPrintModelName() {
        return printModelName;
    }

    public void setPrintModelName(String printModelName) {
        this.printModelName = printModelName;
    }
    
    
    
    
}
