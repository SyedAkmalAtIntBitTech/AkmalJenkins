/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author AR
 */
public interface MindBodyConstants {
    
    //These should match with the subcategory names. Exact match (except upper/lower case)
    //Make change in site.js, MindBodyDataServlet and MindBodyConstants
    public final static String kPromote_new_staff_query = "promote new staff";
    public final static String kPromote_staff_spotlight_query = "promote staff spotlight";

    public final static String kPromote_workshop_query = "promote new workshop";
    public final static String kPromote_todays_workshop_query = "promote todays workshop";
    public final static String kPromote_upcoming_workshop_query = "promote upcoming workshops";

    public final static String kPromote_class_query = "promote new class";
    public final static String kPromote_todays_class_query = "promote todays class";
    
}