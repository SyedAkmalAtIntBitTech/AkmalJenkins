/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindbody.controller;

import com.controller.SqlMethods;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.Program;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.JSONObject;
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

    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(MindBodyDataMapper.class));

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
                        logger.log(Level.INFO, modelElement.getAttribute("option"));

                        if (class_model_option.equalsIgnoreCase("EnrollmentID")) {
                            if (mindbody_enrollments.getID().getValue() != null) {
                                json_mindbody_enrollment_data.put(element, mindbody_enrollments.getID().getValue());
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentName")) {
                            if (mindbody_enrollments.getClassDescription().getName() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(mindbody_enrollments.getClassDescription().getName()));
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentDescription")) {
                            if (mindbody_enrollments.getClassDescription().getDescription() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(mindbody_enrollments.getClassDescription().getDescription()));
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        Program program = mindbody_enrollments.getClassDescription().getProgram();

                        if (class_model_option.equalsIgnoreCase("EnrollmentProgramName")) {
                            if (program.getName() != null) {
                                json_mindbody_enrollment_data.put(element, Jsoup.parse(program.getName()));
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

                        if (class_model_option.equalsIgnoreCase("EnrollmentStartDate")) {

                            JAXBElement<XMLGregorianCalendar> calendarStart = mindbody_enrollments.getStartDate();

                            XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();

                            if (calendarStartDateTime != null) {
                                json_mindbody_enrollment_data.put(element, calendarStartDateTime);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentStartTime")) {

                            JAXBElement<XMLGregorianCalendar> calendarStart = mindbody_enrollments.getStartTime();

                            XMLGregorianCalendar calendarStartTime = (XMLGregorianCalendar) calendarStart.getValue();

                            if (calendarStartTime != null) {
                                json_mindbody_enrollment_data.put(element, calendarStartTime);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("EnrollmentEndTime")) {

                            JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_enrollments.getEndTime();

                            XMLGregorianCalendar calendarEndTime = (XMLGregorianCalendar) calendarEnd.getValue();

                            if (calendarEndTime != null) {
                                json_mindbody_enrollment_data.put(element, calendarEndTime);
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

                        if (class_model_option.equalsIgnoreCase("ClassEndDate")) {

                            JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_enrollments.getEndDate();

                            XMLGregorianCalendar calendarEndDate = (XMLGregorianCalendar) calendarEnd.getValue();

                            if (calendarEndDate != null) {
                                json_mindbody_enrollment_data.put(element, calendarEndDate);
                            } else {
                                json_mindbody_enrollment_data.put(element, defaultValue);
                            }

                        }

                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, util.Utility.logMessage(err, "Exception while updating org name:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_enrollment_data;
    }

    public static JSONObject mapTodaysClassData(Class mindbody_class, String socialEditorLayoutFileName) throws JSONException {
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
                        logger.log(Level.INFO, modelElement.getAttribute("option"));

                        if (class_model_option.equalsIgnoreCase("ClassID")) {
                            if (mindbody_class.getID().getValue() != null) {
                                json_mindbody_class_data.put(element, mindbody_class.getID().getValue());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassName")) {
                            if (mindbody_class.getClassDescription().getName() != null) {

                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getName()));
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassDescription")) {
                            if (mindbody_class.getClassDescription().getDescription() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getDescription()));
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }
                        Program program = mindbody_class.getClassDescription().getProgram();

                        if (class_model_option.equalsIgnoreCase("ClassProgramName")) {
                            if (program.getName() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(program.getName()));
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

                            if (calendarStartDateTime != null) {
                                json_mindbody_class_data.put(element, calendarStartDateTime);
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
                                json_mindbody_class_data.put(element, Jsoup.parse(Name));
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassEndDateTime")) {

                            JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_class.getEndDateTime();

                            XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                            if (calendarEndDateTime != null) {
                                json_mindbody_class_data.put(element, calendarEndDateTime);
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, util.Utility.logMessage(err, "Exception while updating org name:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_class_data;
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
                        logger.log(Level.INFO, modelElement.getAttribute("option"));

                        if (class_model_option.equalsIgnoreCase("ClassID")) {
                            if (mindbody_class.getID().getValue() != null) {
                                json_mindbody_class_data.put(element, mindbody_class.getID().getValue());
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassName")) {
                            if (mindbody_class.getClassDescription().getName() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getName()));
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassDescription")) {
                            if (mindbody_class.getClassDescription().getDescription() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getDescription()));
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassProgramName")) {
                            if (mindbody_class.getClassDescription().getProgram() != null) {
                                json_mindbody_class_data.put(element, Jsoup.parse(mindbody_class.getClassDescription().getProgram().getName()));
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

                            if (calendarStartDateTime != null) {
                                json_mindbody_class_data.put(element, calendarStartDateTime);
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
                                json_mindbody_class_data.put(element, Name);
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                        if (class_model_option.equalsIgnoreCase("ClassEndDateTime")) {

                            JAXBElement<XMLGregorianCalendar> calendarEnd = mindbody_class.getEndDateTime();

                            XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                            if (calendarEndDateTime != null) {
                                json_mindbody_class_data.put(element, calendarEndDateTime);
                            } else {
                                json_mindbody_class_data.put(element, defaultValue);
                            }

                        }

                    }//end of if clause
                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, util.Utility.logMessage(err, "Exception while updating org name:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        } catch (Throwable t) {
        }
        //System.exit (0);        

        return json_mindbody_class_data;
    }
}
