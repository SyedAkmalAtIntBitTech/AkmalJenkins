/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import mindbody.controller.*;
import com.divtohtml.StringUtil;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.Program;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
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
public class GenericAnnouncementDataMapper {

    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(GenericAnnouncementDataMapper.class));

    public static JSONObject mapAnnouncements(String socialEditorLayoutFileName) throws JSONException {
        JSONObject json_announcements = new JSONObject();

        try {

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

                        json_announcements.put(element, defaultValue);


                }//end of for loop with s var

            }
        } catch (SAXParseException err) {
            logger.log(Level.SEVERE, util.Utility.logMessage(err, "Exception while reading the xml file:", null));

        } catch (SAXException e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while reading the xml file:", null));

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while reading the xml file:", null));
        }
        //System.exit (0);        

        return json_announcements;
    }
    
}
