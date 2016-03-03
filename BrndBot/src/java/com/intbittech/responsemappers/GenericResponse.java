/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.responsemappers;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ilyas
 */
public class GenericResponse<T>  extends BaseResponse implements Serializable {
    
     public GenericResponse() {
        super();
        operationStatus = new OperationStatus();
    }

    private List<T> details;
    private OperationStatus operationStatus;

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
    
}
