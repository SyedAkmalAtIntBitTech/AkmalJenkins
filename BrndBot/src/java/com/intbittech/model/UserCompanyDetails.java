/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class UserCompanyDetails {
    private Integer userId;
    private Integer companyId;
    private String companyName;
    private String roleName;
    private Integer roleId;
    private String logourl;
    private String accountStatus;
    private String userEmailId;
    private String userFirstName;
    private String userLastName;
    private String userHashId;

    public String getUserHashId() {
        return userHashId;
    }

    public void setUserHashId(String userHashId) {
        this.userHashId = userHashId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    
    public UserCompanyDetails() {
    }

    public UserCompanyDetails(Integer companyId, String companyName, String userEmailId,
            String roleName, Integer roleId, String logourl, String accountStatus, 
            String userFirstName, String userLastName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.roleName = roleName;
        this.roleId = roleId;
        this.logourl = logourl;
        this.accountStatus = accountStatus;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailId = userEmailId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userId) {
        this.userEmailId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    
}
