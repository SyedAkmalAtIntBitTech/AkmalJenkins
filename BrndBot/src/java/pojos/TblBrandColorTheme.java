/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author intbit
 */
public class TblBrandColorTheme {
    private String theme_id;
    private Integer brand_id;
    private Integer color1;
    private Integer color2;
    private Integer color3;
    private Integer color4;
    private Integer color5;
    private Integer color6;
    private boolean predefined;
    private String theme_name;

    public String getId() {
        return theme_id;
    }

    public void setId(String id) {
        this.theme_id = id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getColor1() {
        return color1;
    }

    public void setColor1(Integer color1) {
        this.color1 = color1;
    }

    public Integer getColor2() {
        return color2;
    }

    public void setColor2(Integer color2) {
        this.color2 = color2;
    }

    public Integer getColor3() {
        return color3;
    }

    public void setColor3(Integer color3) {
        this.color3 = color3;
    }

    public Integer getColor4() {
        return color4;
    }

    public void setColor4(Integer color4) {
        this.color4 = color4;
    }

    public Integer getColor5() {
        return color5;
    }

    public void setColor5(Integer color5) {
        this.color5 = color5;
    }

    public Integer getColor6() {
        return color6;
    }

    public void setColor6(Integer color6) {
        this.color6 = color6;
    }

    public boolean isPredefined() {
        return predefined;
    }

    public void setPredefined(boolean predefined) {
        this.predefined = predefined;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }
}
