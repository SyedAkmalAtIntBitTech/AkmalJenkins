/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbit.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "global_fonts")
public class GlobalFonts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "global_fonts_id")
    private Integer globalFontsId;
    @Column(name = "font_name")
    private String fontName;
    @Column(name = "font_family_name")
    private String fontFamilyName;
    @Column(name = "file_name")
    private String fileName;

    public GlobalFonts() {
    }

    public GlobalFonts(Integer globalFontsId) {
        this.globalFontsId = globalFontsId;
    }

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
