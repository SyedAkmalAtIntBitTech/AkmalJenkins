/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.mindbody;

/**
 *
 * @author AR
 */
public enum MindBodyDuration {
 
    Today(0),
    All(999),
    Thirty(30);
    
    private int value;
    private MindBodyDuration(int value) {
        this.value = value;
    }
    
    public int getDuration() {
        return this.value;
    }
}
