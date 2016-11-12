/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import com.controller.MindBodyConstants;
import com.intbittech.mindbody.MindBodyClass;
import com.intbittech.mindbody.MindBodyDataMapper;
import com.intbittech.mindbody.MindBodyDuration;
import com.intbittech.mindbody.MindBodyProcessedData;
import com.mindbody.source.RevenueCategoryResponse;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import com.mindbodyonline.clients.api._0_5.GetLocationsResult;
import com.mindbodyonline.clients.api._0_5.GetProgramsResult;
import com.mindbodyonline.clients.api._0_5Class.ArrayOfClass;
import com.mindbodyonline.clients.api._0_5Class.ArrayOfClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.ClassDescription;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsResult;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import com.mindbodyonline.clients.api._0_5Sale.GetServicesResult;
import com.mindbodyonline.clients.api._0_5Staff.ArrayOfStaff;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffResult;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.JSONException;
import org.json.simple.JSONArray;

/**
 *
 * @author AR
 */
public class MindbodyExternalContentFactory extends ExternalContentFactory {

    private final int[] siteIds;
    private final MindBodyClass mindBodyClass;
    private static final SimpleDateFormat newFormat = new SimpleDateFormat("MMMMM dd h:mm a");
    private static final SimpleDateFormat newEnrollmentFormat = new SimpleDateFormat("MMMMM dd yyyy");
    private static final SimpleDateFormat existingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public MindbodyExternalContentFactory(Integer companyID) throws SQLException {
        super(companyID);
        Integer studioId = sqlMethods.getStudioID(companyID);
        siteIds = new int[]{studioId};
        mindBodyClass = new MindBodyClass(siteIds);
    }

    @Override
    public Boolean isActivated() {
        return mindBodyClass.isActivated();
    }

    @Override
    public String getActivationLink() {
        GetActivationCodeResult result = mindBodyClass.getActivationCode();
        return result.getActivationLink();
    }

    @Override
    public ExternalSourceProcessedData getListData(String query) throws JSONException, ParseException {
        ExternalSourceProcessedData mind_body_processed_data = null;
        if (query.equalsIgnoreCase(MindBodyConstants.kPromote_new_staff_query)) {
            GetStaffResult staffResult = mindBodyClass.getStaff();
            mind_body_processed_data = getMindBodyProcessedStaffData(staffResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_staff_spotlight_query)) {
            GetStaffResult staffResult = mindBodyClass.getStaff();
            mind_body_processed_data = getMindBodyProcessedStaffData(staffResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mindBodyClass.getEnrollments(MindBodyDuration.All);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_todays_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mindBodyClass.getEnrollments(MindBodyDuration.Today);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_upcoming_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mindBodyClass.getEnrollments(MindBodyDuration.All);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_class_query)) {
            GetClassesResult classResult = mindBodyClass.getClasses(MindBodyDuration.Thirty);
            mind_body_processed_data = getMindBodyProcessedClassData(classResult);
        } else if (query.equalsIgnoreCase(MindBodyConstants.kPromote_todays_class_query)) {
            GetClassesResult classResult = mindBodyClass.getClasses(MindBodyDuration.Today);
            mind_body_processed_data = getMindBodyProcessedClassData(classResult);
        }
        return mind_body_processed_data;
    }

    private MindBodyProcessedData getMindBodyProcessedStaffData(GetStaffResult result) throws JSONException {
        HashMap<String, Object> hash_map = new HashMap<>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();
        ArrayOfStaff array_of_staff = result.getStaffMembers();
        JSONArray json_data_array = new JSONArray();

        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Teacher Name");

        if (array_of_staff != null && array_of_staff.getStaff() != null) {
            List<com.mindbodyonline.clients.api._0_5Staff.Staff> staffList = array_of_staff.getStaff();
            for (int i = 0; i < staffList.size(); i++) {

                com.mindbodyonline.clients.api._0_5Staff.Staff staffInstance = staffList.get(i);
                String staff_id = String.valueOf(staffInstance.getID().getValue());

                hash_map.put(staff_id, staffInstance);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", staffInstance.getName());
                newJSONObject.put("column2", " ");
                newJSONObject.put("column3", " ");
                newJSONObject.put("id", staff_id);

                json_data_array.add(newJSONObject);

            }

            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a teacher to promote:");

        } else {
            mind_body_process_data.setJsonData(json_data_array);

            mind_body_process_data.setTitle("There are no teachers to promote");

        }
        return mind_body_process_data;
    }

    private MindBodyProcessedData getMindBodyProcessedEnrollmentData(GetEnrollmentsResult result) throws JSONException, ParseException {
        HashMap<String, Object> hash_map = new HashMap<>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();

        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Workshop");
        json_column_header_array.add(1, "Teacher");
        json_column_header_array.add(2, "Date");

        ArrayOfClassSchedule array_of_enrollments = result.getEnrollments();
        JSONArray json_data_array = new JSONArray();
        if (array_of_enrollments != null && array_of_enrollments.getClassSchedule() != null) {
            List<ClassSchedule> enrollmentsList = array_of_enrollments.getClassSchedule();
            for (int i = 0; i < enrollmentsList.size(); i++) {

                ClassSchedule enrollmentInstance = enrollmentsList.get(i);
                String enrollment_id = String.valueOf(enrollmentInstance.getID().getValue());

                hash_map.put(enrollment_id, enrollmentInstance);

                JAXBElement<XMLGregorianCalendar> calendarStart = enrollmentInstance.getStartDate();
                JAXBElement<XMLGregorianCalendar> calendarEnd = enrollmentInstance.getEndDate();

                ClassDescription class_description = enrollmentInstance.getClassDescription();

                String name = class_description.getName();
                Staff staff = enrollmentInstance.getStaff();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                Date newDate = existingFormat.parse(calendarStartDateTime.toString());
                String newDateString = newEnrollmentFormat.format(newDate);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", staff.getName());
                newJSONObject.put("column3", newDateString);
                newJSONObject.put("id", enrollment_id);
                json_data_array.add(newJSONObject);

            }

            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a work shop to promote:");
        } else {
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setTitle("There are no workshops to promote.");
        }
        return mind_body_process_data;
    }

    private MindBodyProcessedData getMindBodyProcessedClassData(GetClassesResult result) throws JSONException, ParseException {
        HashMap<String, Object> hash_map = new HashMap<>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();

        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Class");
        json_column_header_array.add(1, "Teacher");
        json_column_header_array.add(2, "Date");

        ArrayOfClass array_of_classes = result.getClasses();
        JSONArray json_data_array = new JSONArray();
        if (array_of_classes != null && array_of_classes.getClazz() != null) {
            List<com.mindbodyonline.clients.api._0_5Class.Class> classesList = array_of_classes.getClazz();
            for (int i = 0; i < classesList.size(); i++) {

                com.mindbodyonline.clients.api._0_5Class.Class classInstance = classesList.get(i);
                String class_id = String.valueOf(classInstance.getID().getValue());

                hash_map.put(class_id, classInstance);

                JAXBElement<XMLGregorianCalendar> calendarStart = classInstance.getStartDateTime();
                JAXBElement<XMLGregorianCalendar> calendarEnd = classInstance.getEndDateTime();
                Staff staff = classInstance.getStaff();

                ClassDescription class_description = classInstance.getClassDescription();

                String name = class_description.getName();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                Date newDate = existingFormat.parse(calendarStartDateTime.toString());
                String newDateString = newFormat.format(newDate);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", staff.getName());
                newJSONObject.put("column3", newDateString);
                newJSONObject.put("id", class_id);

                json_data_array.add(newJSONObject);

            }

            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a class to promote:");
        } else {
            mind_body_process_data.setTitle("There are no classes to promote");
            mind_body_process_data.setJsonData(json_data_array);
        }
        return mind_body_process_data;
    }

    @Override
    public Map<String,String> getDetailData(String query, Object selected_object) throws JSONException {
        Map<String,String> mapped_json_object = new HashMap<>();
        query = query.toLowerCase();
        if (query.contains("class")) {
            com.mindbodyonline.clients.api._0_5Class.Class mindbody_class = (com.mindbodyonline.clients.api._0_5Class.Class) selected_object;
            mapped_json_object = MindBodyDataMapper.mapClassDataRaw(mindbody_class);

        } else if (query.contains("work shop") || query.contains("workshop")) {
            ClassSchedule mindbody_enrollments = (ClassSchedule) selected_object;
            mapped_json_object = MindBodyDataMapper.mapEnrollmentDataRaw(mindbody_enrollments);
        } else if (query.contains("staff")) {
            com.mindbodyonline.clients.api._0_5Staff.Staff mindbody_staff = (com.mindbodyonline.clients.api._0_5Staff.Staff) selected_object;
            mapped_json_object = MindBodyDataMapper.mapStaffDataRaw(mindbody_staff);
        }
        return mapped_json_object;

    }

    @Override
    public String getExternalSourceName() {
        return "Mindbody";
    }

    @Override
    public void searchEmailAndUpdateEmailOptIn(List<String> unsubscribeEmailList) {
        mindBodyClass.searchEmailAndUpdateEmailOptIn(unsubscribeEmailList);
    }
    
    public RevenueCategoryResponse getRevenueCategories(String revenueCategory) throws IOException {
        RevenueCategoryResponse revenueCategoryResponse = mindBodyClass.getRevenueCategories(revenueCategory);
        return revenueCategoryResponse;
    }

    public GetProgramsResult getServiceCategories(String scheduleType, Boolean onlineOnly) {
        GetProgramsResult programsResult = mindBodyClass.getServiceCategories(scheduleType, onlineOnly);
        return programsResult;
    }

    public GetServicesResult getPricingOptions(String[] programIds) {
        GetServicesResult servicesResult = mindBodyClass.getPricingOptions(programIds);
        return servicesResult;
    }

    public GetLocationsResult getSiteLocations() {
        GetLocationsResult locationsResult = mindBodyClass.getSiteLocations();
        return locationsResult;
    }
}
