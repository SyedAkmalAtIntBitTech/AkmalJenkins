/**
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
@Table(name = "email_block")
public class EmailBlock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_block_id")
    private Integer emailBlockId;
    @Column(name = "email_block_name")
    private String emailBlockName;

    public EmailBlock() {
    }

    public EmailBlock(Integer emailBlockId) {
        this.emailBlockId = emailBlockId;
    }

    public Integer getEmailBlockId() {
        return emailBlockId;
    }

    public void setEmailBlockId(Integer emailBlockId) {
        this.emailBlockId = emailBlockId;
    }

    public String getEmailBlockName() {
        return emailBlockName;
    }

    public void setEmailBlockName(String emailBlockName) {
        this.emailBlockName = emailBlockName;
    }
}
