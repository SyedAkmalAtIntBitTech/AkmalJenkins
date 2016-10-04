/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.sendgrid.models;

import com.intbittech.responsemappers.OperationStatus;

/**
 *
 * @author ar
 */
public class BaseSendGridModel {
    private OperationStatus operationStatus;

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
    
}
