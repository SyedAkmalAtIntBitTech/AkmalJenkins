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
    private String layoutFileName;
    private String modelFileName;
    private String imageFileName;

    public String getLayoutFileName() {
        return layoutFileName;
    }

    public void setLayoutFileName(String layoutFileName) {
        this.layoutFileName = layoutFileName;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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
