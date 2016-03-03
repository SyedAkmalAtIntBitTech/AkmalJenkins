/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class CategoryDetails implements Serializable {

    private Integer categoryId;
    private String categoryName;
    private Integer channelId;
    private Integer orgnizationId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getOrgnizationId() {
        return orgnizationId;
    }

    public void setOrgnizationId(Integer orgnizationId) {
        this.orgnizationId = orgnizationId;
    }

}
