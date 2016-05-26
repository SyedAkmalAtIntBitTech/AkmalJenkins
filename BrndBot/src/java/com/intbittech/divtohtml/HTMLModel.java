/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.divtohtml;

/**
 *
 * @author AR
 */
public class HTMLModel {
    private String id;
    private String html;

    HTMLModel(String elementType, String cleanItemString) {
        id = elementType;
        html = cleanItemString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
    
}
