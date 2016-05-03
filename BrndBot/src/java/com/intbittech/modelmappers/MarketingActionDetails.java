/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

/**
 *
 * @author intbit
 */
public class MarketingActionDetails {
    
    private String title;
    private String type;
    private String days ;
    private String description;
    private String time;
    private Boolean is_recuring;
    private String tilldate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getIs_recuring() {
        return is_recuring;
    }

    public void setIs_recuring(Boolean is_recuring) {
        this.is_recuring = is_recuring;
    }

    public String getTilldate() {
        return tilldate;
    }

    public void setTilldate(String tilldate) {
        this.tilldate = tilldate;
    }
    
    
    
}
