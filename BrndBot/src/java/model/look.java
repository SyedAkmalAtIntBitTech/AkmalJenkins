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
public class look {
    public int id = 0;
    public String look_name = "";
    public String file_name = "";

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLook_name() {
        return look_name;
    }

    public void setLook_name(String look_name) {
        this.look_name = look_name;
    }
    
}
