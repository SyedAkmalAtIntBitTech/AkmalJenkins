/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author intbit
 */
public class brandpersonality {
    public int id = 0;
    public String brand_name = "";
    public int look_id = 0;
    public String image_name = "";

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getLook_id() {
        return look_id;
    }

    public void setLook_id(int look_id) {
        this.look_id = look_id;
    }
    
    
}
