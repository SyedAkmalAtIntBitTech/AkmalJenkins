/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author AR
 */
public class Utility {

    public static String getClassName(Class c) {
        String name = null;
        Class<?> enclosingClass = c.getEnclosingClass();
        if (enclosingClass != null) {
            name = enclosingClass.getName();
        } else {
            name = c.getName();

        }
        return name;
    }
    
    public static String logMessage(Exception e,String message,String dbMessage) {
        String newMessage = message.concat(e.toString());
        if(dbMessage!= null){
            newMessage.concat(dbMessage);
        }
        return newMessage;
    }
}
