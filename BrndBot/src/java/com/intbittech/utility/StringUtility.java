/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

/**
 *
 * @author AR
 */
public class StringUtility {

    public static String safeBoolean(boolean valid) {
        return valid ? "true" : "false";
    }

    public static boolean isEmpty(final String value) {
        return (value == null || (value.trim().length() == 0));
    }

}
