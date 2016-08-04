/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class TaskDetails {
    public List<Integer> roles = new ArrayList<Integer>();
    public String task;

    public TaskDetails(String task, List roles) {
        this.task = task;
        this.roles = roles;
    }

    
    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Integer> roles) {
        this.roles = roles;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
