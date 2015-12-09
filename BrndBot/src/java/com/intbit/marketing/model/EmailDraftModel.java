/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.model;

/**
 *
 * @author development
 */
public class EmailDraftModel {
    private Integer categoryid;
    private Integer subcategoryid;
    private String subcategoryname;
    private String emailsubject;
    private String htmlbodystring;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(Integer subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

    public String getEmailsubject() {
        return emailsubject;
    }

    public void setEmailsubject(String emailsubject) {
        this.emailsubject = emailsubject;
    }

    public String getHtmlbodystring() {
        return htmlbodystring;
    }

    public void setHtmlbodystring(String htmlbodystring) {
        this.htmlbodystring = htmlbodystring;
    }


}
