/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author intbit
 */
@Entity
@Table(name = "tbl_email_draft")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEmailDraft.findAll", query = "SELECT t FROM TblEmailDraft t"),
    @NamedQuery(name = "TblEmailDraft.findById", query = "SELECT t FROM TblEmailDraft t WHERE t.id = :id"),
    @NamedQuery(name = "TblEmailDraft.findByEmailId", query = "SELECT t FROM TblEmailDraft t WHERE t.emailId = :emailId"),
    @NamedQuery(name = "TblEmailDraft.findByCategory", query = "SELECT t FROM TblEmailDraft t WHERE t.category = :category"),
    @NamedQuery(name = "TblEmailDraft.findBySubCategory", query = "SELECT t FROM TblEmailDraft t WHERE t.subCategory = :subCategory"),
    @NamedQuery(name = "TblEmailDraft.findBySubCategoryName", query = "SELECT t FROM TblEmailDraft t WHERE t.subCategoryName = :subCategoryName"),
    @NamedQuery(name = "TblEmailDraft.findByEmailSubject", query = "SELECT t FROM TblEmailDraft t WHERE t.emailSubject = :emailSubject"),
    @NamedQuery(name = "TblEmailDraft.findByEmailAddress", query = "SELECT t FROM TblEmailDraft t WHERE t.emailAddress = :emailAddress"),
    @NamedQuery(name = "TblEmailDraft.findByEmailList", query = "SELECT t FROM TblEmailDraft t WHERE t.emailList = :emailList"),
    @NamedQuery(name = "TblEmailDraft.findBySessionValue", query = "SELECT t FROM TblEmailDraft t WHERE t.sessionValue = :sessionValue"),
    @NamedQuery(name = "TblEmailDraft.findBySessionKey", query = "SELECT t FROM TblEmailDraft t WHERE t.sessionKey = :sessionKey"),
    @NamedQuery(name = "TblEmailDraft.findBySessionIframeKey", query = "SELECT t FROM TblEmailDraft t WHERE t.sessionIframeKey = :sessionIframeKey"),
    @NamedQuery(name = "TblEmailDraft.findBySessionIframeValue", query = "SELECT t FROM TblEmailDraft t WHERE t.sessionIframeValue = :sessionIframeValue")})
public class TblEmailDraft implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "category")
    private String category;
    @Column(name = "sub_category")
    private String subCategory;
    @Column(name = "sub_category_name")
    private String subCategoryName;
    @Column(name = "email_subject")
    private String emailSubject;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "email_list")
    private String emailList;
    @Column(name = "session_value")
    private String sessionValue;
    @Column(name = "session_key")
    private String sessionKey;
    @Column(name = "session_iframe_key")
    private String sessionIframeKey;
    @Column(name = "session_iframe_value")
    private String sessionIframeValue;

    public TblEmailDraft() {
    }

    public TblEmailDraft(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailList() {
        return emailList;
    }

    public void setEmailList(String emailList) {
        this.emailList = emailList;
    }

    public String getSessionValue() {
        return sessionValue;
    }

    public void setSessionValue(String sessionValue) {
        this.sessionValue = sessionValue;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionIframeKey() {
        return sessionIframeKey;
    }

    public void setSessionIframeKey(String sessionIframeKey) {
        this.sessionIframeKey = sessionIframeKey;
    }

    public String getSessionIframeValue() {
        return sessionIframeValue;
    }

    public void setSessionIframeValue(String sessionIframeValue) {
        this.sessionIframeValue = sessionIframeValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEmailDraft)) {
            return false;
        }
        TblEmailDraft other = (TblEmailDraft) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intbit.marketing.model.TblEmailDraft[ id=" + id + " ]";
    }
    
}
