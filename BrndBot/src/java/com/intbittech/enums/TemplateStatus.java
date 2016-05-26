/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.enums;

/**
 *
 * @author Mohamed
 */
public enum TemplateStatus {
    no_template("No Template"), template_saved("Template Saved"), 
    incomplete("Incomplete"), complete("Complete"), approved("Approved");
    
    private TemplateStatus(String displayName) {
        this.displayName = displayName;
    }
    
    private String displayName;
    
    public String getDisplayName(){
        return this.displayName;
    }
}
