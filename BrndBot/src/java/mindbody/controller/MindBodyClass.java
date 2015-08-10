/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import com.mindbody.source.MindBody;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import com.mindbodyonline.clients.api._0_5.XMLDetailLevel;
import com.mindbodyonline.clients.api._0_5Class.GetClassesRequest;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsRequest;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsResult;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffRequest;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffResult;

/**
 *
 * @author intbit
 */

public class MindBodyClass {
            private static String sourcePassword = "WBQ2o/mat0gOfT1WeoXDKP1eH8Y=";
            private static String sourceName = "BrndbotLLC";
            private MindBody mindBody;
           int[] siteIds = new int[]{};
            
    public MindBodyClass(int[] siteIds) {
        
        mindBody = new MindBody(sourceName, sourcePassword, siteIds);        
        this.siteIds = siteIds;
        
    }
    
    public GetActivationCodeResult getActivationCode()
    {
        
        GetActivationCodeResult result = MindBody.getActivationCode(sourceName, sourcePassword, siteIds);
        return result;

    }    
    public GetClassesResult getClasses()throws Exception{
                GetClassesRequest classesRequest = new GetClassesRequest();
                classesRequest.setXMLDetail(XMLDetailLevel.FULL);
                classesRequest.setCurrentPageIndex(0);

                GregorianCalendar startDate = new GregorianCalendar();
                startDate.setTime(new Date());
                XMLGregorianCalendar calendarStartDate = null;
                calendarStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);

                classesRequest.setStartDateTime(calendarStartDate);

                GregorianCalendar nextTenDays =new GregorianCalendar();
                nextTenDays.add(Calendar.DATE, 10);

                XMLGregorianCalendar calendarDateNextTenDays = null;

                calendarDateNextTenDays = DatatypeFactory.newInstance().newXMLGregorianCalendar(nextTenDays);

                classesRequest.setEndDateTime(calendarDateNextTenDays);
                classesRequest.setHideCanceledClasses(false);
                GetClassesResult classesResult = mindBody.getClasses(classesRequest);
                
                return classesResult;
    }
    
    public GetClassesResult getTodaysClass() {
        GetClassesResult classesResult = null;
        try {

            GetClassesRequest classesRequest = new GetClassesRequest();
            classesRequest.setXMLDetail(XMLDetailLevel.FULL);
            classesRequest.setCurrentPageIndex(0);

            GregorianCalendar startDate = new GregorianCalendar();
            startDate.setTime(new Date());
            XMLGregorianCalendar calendarStartDate = null;
            calendarStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);

            classesRequest.setStartDateTime(calendarStartDate);

            classesRequest.setEndDateTime(calendarStartDate);
            classesRequest.setHideCanceledClasses(true);
            classesResult = mindBody.getClasses(classesRequest);
        } catch (DatatypeConfigurationException D) {
            System.out.println(D.getCause());
            System.out.println(D.getMessage());
            D.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return classesResult;
    }
    
    public GetEnrollmentsResult getTodaysEnrollments() {
        GetEnrollmentsResult enrollmentsResult = null;
        try {

            GregorianCalendar startDate = new GregorianCalendar();
            startDate.setTime(new Date());
            XMLGregorianCalendar calendarStartDate = null;
            calendarStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);

            GetEnrollmentsRequest enrollmentsRequest = new GetEnrollmentsRequest();
            enrollmentsRequest.setXMLDetail(XMLDetailLevel.FULL);
            enrollmentsRequest.setCurrentPageIndex(0);

            enrollmentsRequest.setStartDate(calendarStartDate);
            enrollmentsRequest.setEndDate(calendarStartDate);
            enrollmentsResult = mindBody.getEnrollments(enrollmentsRequest);

        } catch (DatatypeConfigurationException D) {
            System.out.println(D.getCause());
            System.out.println(D.getMessage());
            D.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return enrollmentsResult;
    }

    public GetStaffResult getStaff() {
        GetStaffResult staffResult = null;
        try {

            GregorianCalendar startDate = new GregorianCalendar();
            startDate.setTime(new Date());
            XMLGregorianCalendar calendarStartDate = null;
            calendarStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate);

            GetStaffRequest staffRequest = new GetStaffRequest();
            staffRequest.setXMLDetail(XMLDetailLevel.FULL);
            staffRequest.setCurrentPageIndex(0);

            staffResult = mindBody.getStaff(staffRequest);

        } catch (DatatypeConfigurationException D) {
            System.out.println(D.getCause());
            System.out.println(D.getMessage());
            D.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return staffResult;
    }


}