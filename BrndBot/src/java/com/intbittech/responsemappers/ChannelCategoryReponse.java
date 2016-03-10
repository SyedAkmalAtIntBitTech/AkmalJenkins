/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.responsemappers;

import com.intbittech.modelmappers.ChannelDetails;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ajit
 */
public class ChannelCategoryReponse extends BaseResponse implements Serializable {

    public ChannelCategoryReponse() {
        super();
        operationStatus = new OperationStatus();

    }

    private OperationStatus operationStatus;

    private List<ChannelDetails> channelDetailsList;
    

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public List<ChannelDetails> getChannelDetailsList() {
        return channelDetailsList;
    }

    public void setChannelDetailsList(List<ChannelDetails> channelDetailsList) {
        this.channelDetailsList = channelDetailsList;
    }

}
