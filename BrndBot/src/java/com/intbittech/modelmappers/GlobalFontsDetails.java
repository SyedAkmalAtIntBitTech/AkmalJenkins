/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ilyas
 */
public class GlobalFontsDetails implements Serializable {
    
    private Integer globalFontsId;
    private String fontName;
    private String fontFamilyName;
    private String fileName;

    public Integer getGlobalFontsId() {
        return globalFontsId;
    }

    public void setGlobalFontsId(Integer globalFontsId) {
        this.globalFontsId = globalFontsId;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontFamilyName() {
        return fontFamilyName;
    }

    public void setFontFamilyName(String fontFamilyName) {
        this.fontFamilyName = fontFamilyName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
}
