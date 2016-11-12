/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.mindbody;

import com.mindbody.source.MindBody;
import com.mindbody.source.MindBodyRevenueType;
import com.mindbody.source.RevenueCategoryResponse;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import com.mindbodyonline.clients.api._0_5.GetLocationsResult;
import com.mindbodyonline.clients.api._0_5.GetProgramsResult;
import com.mindbodyonline.clients.api._0_5.ScheduleType;
import com.mindbodyonline.clients.api._0_5.XMLDetailLevel;
import com.mindbodyonline.clients.api._0_5Class.GetClassesRequest;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsRequest;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsResult;
import com.mindbodyonline.clients.api._0_5Client.Client;
import com.mindbodyonline.clients.api._0_5Sale.GetServicesResult;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffRequest;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author intbit
 */
public class MindBodyClass {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(MindBodyClass.class));

    private static String sourcePassword = "WBQ2o/mat0gOfT1WeoXDKP1eH8Y=";
    private static String sourceName = "BrndbotLLC";
    private MindBody mindBody;
    int[] siteIds = new int[]{};

    public MindBodyClass(int[] siteIds) {

        mindBody = new MindBody(sourceName, sourcePassword, siteIds);
        this.siteIds = siteIds;

    }

    public GetActivationCodeResult getActivationCode() {
        GetActivationCodeResult result = MindBody.getActivationCode(sourceName, sourcePassword, siteIds);
        return result;
    }

    public GetStaffResult getStaff() {
        GetStaffResult staffResult = null;
        try {
            GetStaffRequest staffRequest = new GetStaffRequest();
            staffRequest.setXMLDetail(XMLDetailLevel.FULL);
            staffRequest.setCurrentPageIndex(0);

            staffResult = mindBody.getStaff(staffRequest);

        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while getting staff:", null));

        }
        return staffResult;
    }

    public GetEnrollmentsResult getEnrollments(MindBodyDuration mindBodyDuration) {

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

            enrollmentsRequest.setEndDate(getEndDate(mindBodyDuration));
            enrollmentsResult = mindBody.getEnrollments(enrollmentsRequest);

        } catch (DatatypeConfigurationException D) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(D, "Exception while  getting enrollments:", null));
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while getting enrollments:", null));
        }
        return enrollmentsResult;
    }

    public GetClassesResult getClasses(MindBodyDuration mindBodyDuration) {
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
            classesRequest.setEndDateTime(getEndDate(mindBodyDuration));
            classesRequest.setHideCanceledClasses(true);
            classesResult = mindBody.getClasses(classesRequest);
        } catch (DatatypeConfigurationException D) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(D, "Exception while getting classes:", null));
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while getting classes:", null));

        }
        return classesResult;
    }

    private static XMLGregorianCalendar getEndDate(MindBodyDuration duration) throws DatatypeConfigurationException {
        GregorianCalendar nextTenDays = new GregorianCalendar();
        nextTenDays.add(Calendar.DATE, duration.getDuration());

        XMLGregorianCalendar calendarDateNextTenDays = null;

        calendarDateNextTenDays = DatatypeFactory.newInstance().newXMLGregorianCalendar(nextTenDays);
        return calendarDateNextTenDays;
    }
    
    public HashMap<String, List<Client>> getAllClientIndexes() {
        return mindBody.getEmailLists();
    }

    public boolean isActivated() {
        boolean value = mindBody.isActivated();
        return value;
    }   
    
    public void searchEmailAndUpdateEmailOptIn(List<String> unsubscribeEmailList) {
        mindBody.searchEmailAndUpdateEmailOptIn(unsubscribeEmailList);
    }
    
    public RevenueCategoryResponse getRevenueCategories(String revenueCategory) throws IOException {
        MindBodyRevenueType revenueType = MindBodyRevenueType.valueOf(revenueCategory);
        return mindBody.getRevenueCategories(revenueType);
    }

    public GetServicesResult getPricingOptions(String[] programIds) {
        List<Integer> list = new ArrayList<>();
        for (String value : programIds) {
            list.add(Integer.parseInt(value));
        }
        GetServicesResult servicesResult = mindBody.getPricingOptions(list);
        return servicesResult;
    }

    public GetProgramsResult getServiceCategories(String scheduleType, Boolean onlineOnly) {
        ScheduleType scheduleType1 = ScheduleType.fromValue(scheduleType);
        return mindBody.getServiceCategories(scheduleType1, onlineOnly);
    }

    public GetLocationsResult getSiteLocations() {
        GetLocationsResult locationsResult = mindBody.getLocations();
        return locationsResult;
    }

}
