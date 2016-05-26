/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.mindbody;

import com.intbittech.divtohtml.StringUtil;
import com.intbittech.utility.MapUtility;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.Program;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author intbit
 */
public class MindBodyDataMapper {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(MindBodyDataMapper.class));
    private static DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static DateFormat enrollmentInputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

    public static JSONObject mapEnrollmentData(ClassSchedule mindbody_enrollments, String socialEditorLayoutFileName) {
        JSONObject json_mindbody_enrollment_data = new JSONObject();
        try {
            if (mindbody_enrollments != null) {

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document doc = docBuilder.parse(new File(socialEditorLayoutFileName));

                // normalize text representation
                doc.getDocumentElement().normalize();
                logger.log(Level.INFO, "Root element of the doc is " + doc.getDocumentElement().getNodeName());

                NodeList listOfModels = doc.getElementsByTagName("model");
                int totalModels = listOfModels.getLength();
                logger.log(Level.INFO, "Total no of models : " + totalModels);

                for (int s = 0; s < listOfModels.getLength(); s++) {

                    Node firstModelNode = listOfModels.item(s);
                    if (firstModelNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element modelElement = (Element) firstModelNode;
                        //-------
                        String element = modelElement.getAttribute("element");
                        String class_model_option = modelElement.getAttribute("option");
                        String defaultValue = modelElement.getAttribute("default");
                        String epochValue = modelElement.getAttribute("epoch");

                        logger.log(Level.INFO, modelElement.getAttribute("option"));
                        if (class_model_option.equalsIgnoreCase("Select")
                                && (element.toLowerCase().contains("button")
                                || element.toLowerCase().contains("body")
                                || element.toLowerCase().contains("header")
                                || element.toLowerCase().contains("footer"))) {
                            json_mindbody_enrollment_data.put(element, defaultValue);
                        }
                        if (class_model_option.equalsIgnoreCase("EnrollmentID")) {
                            if (mindbody_enrollments.getID().getValue() != null) {
                                json_mindbody_enrollment_data.put(element, mindbody_enrollments.getID().getValue());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentName")) {
                            if (mindbody_enrollments.getClassDescription().getName() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(mindbody_enrollments.getClassDescription().getName()).text());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentDescription")) {
                            if (mindbody_enrollments.getClassDescription().getDescription() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(mindbody_enrollments.getClassDescription().getDescription()).text());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        Program program = mindbody_enrollments.getClassDescription().getProgram();

                        if (class_model_option.equalsIgnoreCase("EnrollmentProgramName")) {
                            if (program.getName() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(program.getName()).text());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentImageURL")) {
                            if (mindbody_enrollments.getClassDescription().getImageURL() != null) {
                                json_mindbody_enrollment_data.put(element, mindbody_enrollments.getClassDescription().getImageURL());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentStartDateTime")) {

                            JAXBElement<XMLGregorianCalendar> jcalendarStartDate = mindbody_enrollments.getStartDate();
                            JAXBElement<XMLGregorianCalendar> jcalendarStartTime = mindbody_enrollments.getStartTime();

                            XMLGregorianCalendar xmlcalendarStartDate = (XMLGregorianCalendar) jcalendarStartDate.getValue();
                            XMLGregorianCalendar xmlcalendarStartTime = (XMLGregorianCalendar) jcalendarStartTime.getValue();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd yyyy");
                            String formattedDate = "";
                            if (!StringUtil.isEmpty(epochValue)) {
                                dateFormat = new SimpleDateFormat(epochValue);
                            }

                            if (xmlcalendarStartDate != null) {

                                Date finalDate = combineDateTime(xmlcalendarStartDate, xmlcalendarStartTime);
                                Date date = parseEnrollmentDateToDate(finalDate.toString());
                                try {
                                    formattedDate = dateFormat.format(date);
                                } catch (Exception e) {
                                    //This is double make sure that the client doesn't enter garbage values
                                    dateFormat = new SimpleDateFormat("EEEEE dd yyyy");
                                    formattedDate = dateFormat.format(date);
                                }

                                json_mindbody_enrollment_data.put(element, formattedDate);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentEndDateTime")) {

                            JAXBElement<XMLGregorianCalendar> jcalendarEndDate = mindbody_enrollments.getEndDate();
                            JAXBElement<XMLGregorianCalendar> jcalendarEndTime = mindbody_enrollments.getEndTime();

                            XMLGregorianCalendar xmlcalendarEndDate = (XMLGregorianCalendar) jcalendarEndDate.getValue();
                            XMLGregorianCalendar xmlcalendarEndTime = (XMLGregorianCalendar) jcalendarEndTime.getValue();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd yyyy");
                            String formattedDate = "";
                            if (!StringUtil.isEmpty(epochValue)) {
                                dateFormat = new SimpleDateFormat(epochValue);
                            }

                            if (xmlcalendarEndDate != null) {

                                Date finalDate = combineDateTime(xmlcalendarEndDate, xmlcalendarEndTime);
                                Date date = parseEnrollmentDateToDate(finalDate.toString());
                                try {
                                    formattedDate = dateFormat.format(date);
                                } catch (Exception e) {
                                    //This is double make sure that the client doesn't enter garbage values
                                    dateFormat = new SimpleDateFormat("EEEEE dd yyyy");
                                    formattedDate = dateFormat.format(date);
                                }
                                json_mindbody_enrollment_data.put(element, formattedDate);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("StaffName")) {

                            Staff staff = mindbody_enrollments.getStaff();

                            String First_name = staff.getFirstName();
                            String Last_name = staff.getLastName();
                            String Name = First_name + " " + Last_name;

                            if (Name != null) {
                                json_mindbody_enrollment_data.put(element, Name);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }
                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(err, "Exception while mapping enrollment:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while mapping enrollment:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_enrollment_data;
    }

    public static JSONObject mapClassData(Class mindbody_class, String socialEditorLayoutFileName) throws JSONException {
        JSONObject json_mindbody_class_data = new JSONObject();

        try {
            if (mindbody_class != null) {

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document doc = docBuilder.parse(new File(socialEditorLayoutFileName));

                // normalize text representation
                doc.getDocumentElement().normalize();
                logger.log(Level.INFO, "Root element of the doc is " + doc.getDocumentElement().getNodeName());

                NodeList listOfModels = doc.getElementsByTagName("model");
                int totalModels = listOfModels.getLength();
                logger.log(Level.INFO, "Total no of models : " + totalModels);

                for (int s = 0; s < listOfModels.getLength(); s++) {

                    Node firstModelNode = listOfModels.item(s);
                    if (firstModelNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element modelElement = (Element) firstModelNode;
                        //-------
                        String element = modelElement.getAttribute("element");
                        String class_model_option = modelElement.getAttribute("option");
                        String defaultValue = modelElement.getAttribute("default");
                        String epochValue = modelElement.getAttribute("epoch");
                        logger.log(Level.INFO, modelElement.getAttribute("option"));

                        if (class_model_option.equalsIgnoreCase("Select")
                                && (element.toLowerCase().contains("button")
                                || element.toLowerCase().contains("body")
                                || element.toLowerCase().contains("header")
                                || element.toLowerCase().contains("footer"))) {
                            json_mindbody_class_data.put(element, defaultValue);
                        }
                        if (class_model_option.equalsIgnoreCase("ClassID")) {
                            if (mindbody_class.getID().getValue() != null) {
                                json_mindbody_class_data.put(element, mindbody_class.getID().getValue());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassName")) {
                            if (mindbody_class.getClassDescription().getName() != null) {

                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getName()).text());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassDescription")) {
                            if (mindbody_class.getClassDescription().getDescription() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getDescription()).text());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }
                        Program program = mindbody_class.getClassDescription().getProgram();

                        if (class_model_option.equalsIgnoreCase("ClassProgramName")) {
                            if (program.getName() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(program.getName()).text());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassImageURL")) {
                            if (mindbody_class.getClassDescription().getImageURL() != null) {
                                json_mindbody_class_data.put(element, mindbody_class.getClassDescription().getImageURL());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassStartDateTime")) {
                            JAXBElement<XMLGregorianCalendar> calendarStart = mindbody_class.getStartDateTime();

                            XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE h:mm a");
                            String formattedDate = "";
                            if (!StringUtil.isEmpty(epochValue)) {
                                dateFormat = new SimpleDateFormat(epochValue);
                            }

                            if (calendarStartDateTime != null) {
                                Date date = parseMindBodyDateToDate(calendarStartDateTime.toString());
                                try {
                                    formattedDate = dateFormat.format(date);
                                } catch (Exception e) {
                                    //This is double make sure that the client doesn't enter garbage values                                    
                                    dateFormat = new SimpleDateFormat("EEEEE h:mm a");
                                    formattedDate = dateFormat.format(date);
                                }

                                json_mindbody_class_data.put(element, formattedDate);
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("StaffName")) {

                            Staff staff = mindbody_class.getStaff();

                            String First_name = staff.getFirstName();
                            String Last_name = staff.getLastName();
                            String Name = First_name + " " + Last_name;

                            if (Name != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(Name).text());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassEndDateTime")) {

                            SimpleDateFormat dateFormat = null;
                            String formattedDate = "";
                            if (!StringUtil.isEmpty(epochValue)) {
                                dateFormat = new SimpleDateFormat(epochValue);
                            }

                            JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_class.getEndDateTime();

                            XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                            if (calendarEndDateTime != null) {
                                Date date = parseMindBodyDateToDate(calendarEndDateTime.toString());
                                try {
                                    formattedDate = dateFormat.format(date);
                                } catch (Exception e) {
                                    dateFormat = new SimpleDateFormat("EEEEE h:mm a");
                                    formattedDate = dateFormat.format(date);
                                }

                                json_mindbody_class_data.put(element, formattedDate);
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(err, "Exception while mapping class:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while mapping class:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_class_data;
    }

    private static Date combineDateTime(XMLGregorianCalendar xmlcalendarStartDate, XMLGregorianCalendar xmlcalendarStartTime) throws DatatypeConfigurationException {

        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(toDate(xmlcalendarStartDate));
        Calendar calendarB = Calendar.getInstance();
        if (xmlcalendarStartTime == null) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            xmlcalendarStartTime = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            xmlcalendarStartTime.setHour(0);
            xmlcalendarStartTime.setMinute(0);
            xmlcalendarStartTime.setSecond(0);
            xmlcalendarStartTime.setMillisecond(0);
        }
        calendarB.setTime(toDate(xmlcalendarStartTime));

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

        Date result = calendarA.getTime();
        return result;
    }

    public static JSONObject mapStaffData(com.mindbodyonline.clients.api._0_5Staff.Staff mindbody_staff, String socialEditorLayoutFileName) throws JSONException {
        JSONObject json_mindbody_staff_data = new JSONObject();

        try {
            if (mindbody_staff != null) {

                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

                Document doc = docBuilder.parse(new File(socialEditorLayoutFileName));

                // normalize text representation
                doc.getDocumentElement().normalize();
                logger.log(Level.INFO, "Root element of the doc is " + doc.getDocumentElement().getNodeName());

                NodeList listOfModels = doc.getElementsByTagName("model");
                int totalModels = listOfModels.getLength();
                logger.log(Level.INFO, "Total no of models : " + totalModels);

                for (int s = 0; s < listOfModels.getLength(); s++) {

                    Node firstModelNode = listOfModels.item(s);
                    if (firstModelNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element modelElement = (Element) firstModelNode;
                        //-------
                        String element = modelElement.getAttribute("element");
                        String class_model_option = modelElement.getAttribute("option");
                        String defaultValue = modelElement.getAttribute("default");
                        String epochValue = modelElement.getAttribute("epoch");
                        logger.log(Level.INFO, modelElement.getAttribute("option"));
                        if (class_model_option.equalsIgnoreCase("Select")
                                && (element.toLowerCase().contains("button")
                                || element.toLowerCase().contains("body")
                                || element.toLowerCase().contains("header")
                                || element.toLowerCase().contains("footer"))) {
                            json_mindbody_staff_data.put(element, defaultValue);
                        }
                        if (class_model_option.equalsIgnoreCase("Name")) {
                            if (mindbody_staff.getName() != null) {
                                json_mindbody_staff_data.put(element, mindbody_staff.getName());
                            } else {
                                json_mindbody_staff_data.put(element, defaultValue);
                            }
                        }

                        if (class_model_option.equalsIgnoreCase("ImageURL")) {
                            if (mindbody_staff.getImageURL() != null) {

                                json_mindbody_staff_data.put(element, mindbody_staff.getImageURL());
                            } else {
                                json_mindbody_staff_data.put(element, defaultValue);
                            }
                        }

                        if (class_model_option.equalsIgnoreCase("Bio")) {
                            if (!StringUtil.isEmpty(mindbody_staff.getBio())) {
                                json_mindbody_staff_data.put(element, Jsoup.parse(mindbody_staff.getBio()).text());
                            } else {
                                json_mindbody_staff_data.put(element, defaultValue);
                            }
                        }

                        if (class_model_option.equalsIgnoreCase("Email")) {
                            if (mindbody_staff.getEmail() != null) {
                                json_mindbody_staff_data.put(element, Jsoup.parse(mindbody_staff.getEmail()).text());
                            } else {
                                json_mindbody_staff_data.put(element, defaultValue);
                            }
                        }

                        if (class_model_option.equalsIgnoreCase("MobilePhone")) {
                            if (mindbody_staff.getMobilePhone() != null) {
                                json_mindbody_staff_data.put(element, mindbody_staff.getMobilePhone());
                            } else {
                                json_mindbody_staff_data.put(element, defaultValue);
                            }
                        }
                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(err, "Exception mapping staff:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception mapping staff:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_staff_data;
    }

    public static Map<String, String> mapStaffDataRaw(com.mindbodyonline.clients.api._0_5Staff.Staff mindbody_staff) {
        Map<String, Object> mapData = new HashMap<>();

        try {
            if (mindbody_staff != null) {

                if (mindbody_staff.getName() != null) {
                    mapData.put("name", mindbody_staff.getName());
                }

                if (mindbody_staff.getImageURL() != null) {
                    mapData.put("imageurl", mindbody_staff.getImageURL());
                }
                if (!StringUtil.isEmpty(mindbody_staff.getBio())) {
                    mapData.put("bio", Jsoup.parse(mindbody_staff.getBio()).text());
                }
                if (mindbody_staff.getEmail() != null) {
                    mapData.put("email", Jsoup.parse(mindbody_staff.getEmail()).text());
                }

                if (mindbody_staff.getMobilePhone() != null) {
                    mapData.put("mobilephone", mindbody_staff.getMobilePhone());
                }
            }
        } catch (Throwable t) {

        }
        //System.exit (0);        

        return MapUtility.convertObjectToStringMap(mapData);
    }

    public static Map<String, String> mapEnrollmentDataRaw(ClassSchedule mindbody_enrollments) {
        Map<String, Object> mapData = new HashMap<>();
        try {
            if (mindbody_enrollments != null) {
                if (mindbody_enrollments.getID().getValue() != null) {
                    mapData.put("enrollmentid", mindbody_enrollments.getID().getValue());
                }

                if (mindbody_enrollments.getClassDescription().getName() != null) {
                    mapData.put("enrollmentname", Jsoup.parse(mindbody_enrollments.getClassDescription().getName()).text());
                }

                if (mindbody_enrollments.getClassDescription().getDescription() != null) {
                    mapData.put("enrollmentdescription", Jsoup.parse(mindbody_enrollments.getClassDescription().getDescription()).text());
                }

                Program program = mindbody_enrollments.getClassDescription().getProgram();

                if (program.getName() != null) {
                    mapData.put("enrollmentprogramname", Jsoup.parse(program.getName()).text());
                }

                if (mindbody_enrollments.getClassDescription().getImageURL() != null) {
                    mapData.put("enrollmentimageurl", mindbody_enrollments.getClassDescription().getImageURL());
                }

                JAXBElement<XMLGregorianCalendar> jcalendarStartDate = mindbody_enrollments.getStartDate();
                JAXBElement<XMLGregorianCalendar> jcalendarStartTime = mindbody_enrollments.getStartTime();

                XMLGregorianCalendar xmlcalendarStartDate = (XMLGregorianCalendar) jcalendarStartDate.getValue();
                XMLGregorianCalendar xmlcalendarStartTime = (XMLGregorianCalendar) jcalendarStartTime.getValue();

                Date combinedStartDate = combineDateTime(xmlcalendarStartDate, xmlcalendarStartTime);
                if (combinedStartDate != null) {
                    mapData.put("enrollmentstartdatetime", combinedStartDate);
                }

                JAXBElement<XMLGregorianCalendar> jcalendarEndDate = mindbody_enrollments.getEndDate();
                JAXBElement<XMLGregorianCalendar> jcalendarEndTime = mindbody_enrollments.getEndTime();

                XMLGregorianCalendar xmlcalendarEndDate = (XMLGregorianCalendar) jcalendarEndDate.getValue();
                XMLGregorianCalendar xmlcalendarEndTime = (XMLGregorianCalendar) jcalendarEndTime.getValue();

                Date combinedEndDate = combineDateTime(xmlcalendarEndDate, xmlcalendarEndTime);
                if (combinedEndDate != null) {
                    mapData.put("enrollmentenddatetime", combinedEndDate);
                }

                Staff staff = mindbody_enrollments.getStaff();

                String First_name = staff.getFirstName();
                String Last_name = staff.getLastName();
                String Name = First_name + " " + Last_name;

                if (Name != null) {
                    mapData.put("staffname", Name);
                }

            }//end of for loop with s var

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return MapUtility.convertObjectToStringMap(mapData);
    }

    public static Map<String, String> mapClassDataRaw(Class mindbody_class) throws JSONException {
        Map<String, Object> map_mindbody_class_data = new HashMap<>();

        try {
            if (mindbody_class != null) {

                if (mindbody_class.getID().getValue() != null) {
                    map_mindbody_class_data.put("classid", mindbody_class.getID().getValue());
                }

                if (mindbody_class.getClassDescription().getName() != null) {

                    map_mindbody_class_data.put("classname", Jsoup.parse(mindbody_class.getClassDescription().getName()).text());
                }
                if (mindbody_class.getClassDescription().getDescription() != null) {
                    map_mindbody_class_data.put("classdescription", Jsoup.parse(mindbody_class.getClassDescription().getDescription()).text());
                }
                Program program = mindbody_class.getClassDescription().getProgram();

                if (program.getName() != null) {
                    map_mindbody_class_data.put("classprogramname", Jsoup.parse(program.getName()).text());
                }
                if (mindbody_class.getClassDescription().getImageURL() != null) {
                    map_mindbody_class_data.put("classimageurl", mindbody_class.getClassDescription().getImageURL());
                }
                JAXBElement<XMLGregorianCalendar> calendarStart = mindbody_class.getStartDateTime();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();

                if (calendarStartDateTime != null) {
                    Date date = parseMindBodyDateToDate(calendarStartDateTime.toString());

                    map_mindbody_class_data.put("classstartdatetime", date);
                }
                Staff staff = mindbody_class.getStaff();

                String First_name = staff.getFirstName();
                String Last_name = staff.getLastName();
                String Name = First_name + " " + Last_name;

                if (Name != null) {
                    map_mindbody_class_data.put("staffname", Jsoup.parse(Name).text());
                }

                JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_class.getEndDateTime();

                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                if (calendarEndDateTime != null) {
                    Date date = parseMindBodyDateToDate(calendarEndDateTime.toString());
                    map_mindbody_class_data.put("classenddatetime", date);
                }

            }
        } catch (Throwable t) {
        }
        //System.exit (0);        
        return MapUtility.convertObjectToStringMap(map_mindbody_class_data);

    }

    private static Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    private static Date parseMindBodyDateToDate(String dateTimeString) throws ParseException {

        Date date = inputFormat.parse(dateTimeString);
        return date;
    }

    private static Date parseEnrollmentDateToDate(String dateTimeString) throws ParseException {

//        Jan 14, 2016 12:00:00 AM
        Date date = enrollmentInputFormat.parse(dateTimeString);
        return date;
    }

}
