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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "email_block_model_lookup")
public class EmailBlockModelLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "email_block_model_lookup_id")
    private Integer emailBlockModelLookupId;
    @JoinColumn(name = "fk_email_block_id", referencedColumnName = "email_block_id")
    @ManyToOne
    private EmailBlock fkEmailBlockId;
    @JoinColumn(name = "fk_email_block_model_id", referencedColumnName = "email_block_model_id")
    @ManyToOne
    private EmailBlockModel fkEmailBlockModelId;

    public EmailBlockModelLookup() {
    }

    public EmailBlockModelLookup(Integer emailBlockModelLookupId) {
        this.emailBlockModelLookupId = emailBlockModelLookupId;
    }

    public Integer getEmailBlockModelLookupId() {
        return emailBlockModelLookupId;
    }

    public void setEmailBlockModelLookupId(Integer emailBlockModelLookupId) {
        this.emailBlockModelLookupId = emailBlockModelLookupId;
    }

    public EmailBlock getFkEmailBlockId() {
        return fkEmailBlockId;
    }

    public void setFkEmailBlockId(EmailBlock fkEmailBlockId) {
        this.fkEmailBlockId = fkEmailBlockId;
    }

    public EmailBlockModel getFkEmailBlockModelId() {
        return fkEmailBlockModelId;
    }

    public void setFkEmailBlockModelId(EmailBlockModel fkEmailBlockModelId) {
        this.fkEmailBlockModelId = fkEmailBlockModelId;
    }
}
