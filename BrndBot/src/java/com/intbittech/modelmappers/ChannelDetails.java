/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.modelmappers;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ajit
 */
public class ChannelDetails implements Serializable {

    private Integer channelId;
    private String channelName;
    List<CategoryDetails> categoryDetailsList;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<CategoryDetails> getCategoryDetailsList() {
        return categoryDetailsList;
    }

    public void setCategoryDetailsList(List<CategoryDetails> categoryDetailsList) {
        this.categoryDetailsList = categoryDetailsList;
    }
    

}
