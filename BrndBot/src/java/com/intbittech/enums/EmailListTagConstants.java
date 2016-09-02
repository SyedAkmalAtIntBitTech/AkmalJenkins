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
public enum EmailListTagConstants {
    General(1),Newsletter(2);
    
    private Integer emailListTag;
    private EmailListTagConstants(Integer emailListTag) {
        this.emailListTag = emailListTag;
    }

    public Integer getEmailListTag() {
        return emailListTag;
    }
}
