/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.enums;

/**
 *
 * @author ilyas
 */
public enum EmailListTypeConstants {
    General(1),Mindbody(2);
    
    private Integer emailListType;
    private EmailListTypeConstants(Integer emailListType) {
        this.emailListType = emailListType;
    }

    public Integer getEmailListType() {
        return emailListType;
    }
}
