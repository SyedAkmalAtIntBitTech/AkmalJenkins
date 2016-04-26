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
@Table(name = "global_colors")
public class GlobalColors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "global_colors_id")
    private Integer globalColorsId;
    @Column(name = "color_name")
    private String colorName;
    @Column(name = "color_1")
    private String color1;
    @Column(name = "color_2")
    private String color2;
    @Column(name = "color_3")
    private String color3;
    @Column(name = "color_4")
    private String color4;

    public GlobalColors() {
    }

    public GlobalColors(Integer globalColorsId) {
        this.globalColorsId = globalColorsId;
    }

    public Integer getGlobalColorsId() {
        return globalColorsId;
    }

    public void setGlobalColorsId(Integer globalColorsId) {
        this.globalColorsId = globalColorsId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    
    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor4() {
        return color4;
    }

    public void setColor4(String color4) {
        this.color4 = color4;
    }

}
