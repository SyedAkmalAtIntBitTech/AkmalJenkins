/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @OneToMany(mappedBy = "inviteSentBy")
    private Set<CompanyInvite> companyInviteSet;
    @OneToMany(mappedBy = "inviteSentTo")
    private Set<CompanyInvite> companyInviteSet1;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "userId")

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "fk_company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company fkCompanyId;
    @JoinColumn(name = "fk_user_role_id", referencedColumnName = "user_role_id")
    @ManyToOne(optional = false)
    private UserRole fkUserRoleId;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Company getFkCompanyId() {
        return fkCompanyId;
    }

    public void setFkCompanyId(Company fkCompanyId) {
        this.fkCompanyId = fkCompanyId;
    }

    public UserRole getFkUserRoleId() {
        return fkUserRoleId;
    }

    public void setFkUserRoleId(UserRole fkUserRoleId) {
        this.fkUserRoleId = fkUserRoleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlTransient
    public Set<CompanyInvite> getCompanyInviteSet() {
        return companyInviteSet;
    }

    public void setCompanyInviteSet(Set<CompanyInvite> companyInviteSet) {
        this.companyInviteSet = companyInviteSet;
    }

    @XmlTransient
    public Set<CompanyInvite> getCompanyInviteSet1() {
        return companyInviteSet1;
    }

    public void setCompanyInviteSet1(Set<CompanyInvite> companyInviteSet1) {
        this.companyInviteSet1 = companyInviteSet1;
    }

}
