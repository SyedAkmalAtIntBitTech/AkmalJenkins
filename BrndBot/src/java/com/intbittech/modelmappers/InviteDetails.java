/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.util.ArrayList;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class InviteDetails {
    String emailaddress;
    public ArrayList<Integer> roles = new ArrayList<Integer>();
    public String task;

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getTask() {
        return task;
    }

    public ArrayList<Integer> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Integer> roles) {
        this.roles = roles;
    }

    public void setTask(String task) {
        this.task = task;
    }

   
    
}
