/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.mindbody;

/**
 *
 * @author intbit
 */
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.mindbody.source.MindBody;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import com.mindbodyonline.clients.api._0_5.XMLDetailLevel;
import com.mindbodyonline.clients.api._0_5Class.ArrayOfClass;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.ClassDescription;
import com.mindbodyonline.clients.api._0_5Class.GetClassesRequest;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class MindBodyTest {
    private static final Logger logger = Logger.getLogger(MindBodyTest.class.getName());
    
    private static String sourcePassword = "WBQ2o/mat0gOfT1WeoXDKP1eH8Y=";
    private static String sourceName = "BrndbotLLC";

    public static void main(String args[]) {
        int[] siteIds = new int[]{7335};
        MindBody mindBody = new MindBody(sourceName, sourcePassword, siteIds);
        GetActivationCodeResult result = MindBody.getActivationCode(sourceName, sourcePassword, siteIds);
        logger.log(Level.INFO, result.getActivationCode());

        GetClassesRequest classesRequest = new GetClassesRequest();
        classesRequest.setXMLDetail(XMLDetailLevel.FULL);
        classesRequest.setCurrentPageIndex(0);

        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(new Date());
        XMLGregorianCalendar calendarStartDate = null;
        try {
            calendarStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        classesRequest.setStartDateTime(calendarStartDate);

        GregorianCalendar nextTenDays = new GregorianCalendar();
        nextTenDays.add(Calendar.DATE, 10);

        XMLGregorianCalendar calendarDateNextTenDays = null;
        try {
            calendarDateNextTenDays = DatatypeFactory.newInstance().newXMLGregorianCalendar(nextTenDays);
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        classesRequest.setEndDateTime(calendarDateNextTenDays);
        classesRequest.setHideCanceledClasses(false);
        GetClassesResult classesResult = mindBody.getClasses(classesRequest);

        ArrayOfClass arrayOfClasses = classesResult.getClasses();
        if (arrayOfClasses != null && arrayOfClasses.getClazz() != null) {
            List<Class> classesList = arrayOfClasses.getClazz();
            for (int i = 0; i < classesList.size(); i++) {
                Class classInstance = classesList.get(i);

                JAXBElement<XMLGregorianCalendar> calendarStart =  classInstance.getStartDateTime();
                JAXBElement<XMLGregorianCalendar> calendarEnd =  classInstance.getEndDateTime();
                Staff staff = classInstance.getStaff();
                
                ClassDescription class_description = classInstance.getClassDescription();
                String name = class_description.getName();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar)calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar)calendarEnd.getValue();
                
                logger.log(Level.INFO, i +" " + name + " " + staff.getFirstName() + " " + staff.getLastName() + " " + calendarStartDateTime);
                }
        }
    }

}
