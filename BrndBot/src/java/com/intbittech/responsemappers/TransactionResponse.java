/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.responsemappers;

import java.io.Serializable;

/**
 *
 * @author Ajit
 */
public class TransactionResponse extends BaseResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1613083572078767809L;
    private String id;
    private String message;
    private OperationStatus operationStatus;

    public TransactionResponse() {
        this.operationStatus = new OperationStatus();
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
