/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;

/**
 *
 * @author development
 */
public class SentEmailDetails extends UserCompanyIds implements Serializable {
     
    private Double openRate;
    private Double clickThroughRate;
    private Integer unSubscribes;
    private Integer sentNumbers;
    private Integer hardBounces;
    private long sentDateTime;
    private String subjectLine;
    private String fromName;
    private String replyToAddress;
    private String htmlData;

    public Double getOpenRate() {
        return openRate;
    }

    public void setOpenRate(Double openRate) {
        this.openRate = openRate;
    }

    public Double getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(Double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public Integer getUnSubscribes() {
        return unSubscribes;
    }

    public void setUnSubscribes(Integer unSubscribes) {
        this.unSubscribes = unSubscribes;
    }

    public Integer getSentNumbers() {
        return sentNumbers;
    }

    public void setSentNumbers(Integer sentNumbers) {
        this.sentNumbers = sentNumbers;
    }

    public Integer getHardBounces() {
        return hardBounces;
    }

    public void setHardBounces(Integer hardBounces) {
        this.hardBounces = hardBounces;
    }

    public long getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(long sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

}
