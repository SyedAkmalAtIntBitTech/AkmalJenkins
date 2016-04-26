/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "external_source")
public class ExternalSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "external_source_id")
    private Integer externalSourceId;
    @Column(name = "external_source_name")
    private String externalSourceName;

    public ExternalSource() {
    }

    public ExternalSource(Integer externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public Integer getExternalSourceId() {
        return externalSourceId;
    }

    public void setExternalSourceId(Integer externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public String getExternalSourceName() {
        return externalSourceName;
    }

    public void setExternalSourceName(String externalSourceName) {
        this.externalSourceName = externalSourceName;
    }
}
