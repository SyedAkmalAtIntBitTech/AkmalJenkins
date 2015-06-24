/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.mandrill.attachment;
import com.mandrill.message;
import com.mandrill.recipient;
import com.mandrill.recipientMetadata;
import com.mandrill.sendMail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author intbit
 */
@WebServlet(name = "sendEmail", urlPatterns = {"/sendEmail"})
public class sendEmail extends HttpServlet {
    sqlMethods SM = new sqlMethods();
    generateHashPassword HP = new generateHashPassword();

    private final static String MANDRILL_KEY = "4jd3wIMvBAmJt9H0FcEb1w";
    sendMail SE = new sendMail();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        generateHashPass hash = new generateHashPass("ABCDEFGHI");
        boolean check = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        SM.session = request.getSession(true);
        
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            try 
            {
              BufferedReader reader = request.getReader();
             String line = null;
              while ((line = reader.readLine()) != null)
              {
                sb.append(line);
              }
            } catch (Exception e) { e.printStackTrace(); }

            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject joUser = null;
            try
            {
              joUser = (org.json.simple.JSONObject) parser.parse(sb.toString());
            } catch (ParseException e) { e.printStackTrace(); }
            
            String email_id = (String)joUser.get("emailid");
            
            SM.setConnection();
            check = SM.checkEmailAvailability(email_id);
            Integer user_id = SM.getUserID(email_id);
            Date expdate = new Date();
//            Date udat = new Date(expdate.getYear(),expdate.getMonth(),expdate.getDay(),expdate.getHours(),expdate.getMinutes(),expdate.getSeconds());
            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            java.sql.Time tdat = new java.sql.Time(expdate.getHours(), expdate.getMinutes(), expdate.getSeconds());
            
            if(check){ 
                
                    String randomVal = email_id + String.valueOf(user_id) + sdat +tdat;

                    String hashURL = HP.hashURL(randomVal);

                    SM.setUserDetails(user_id, hashURL, sdat, tdat);

                    message message = new message();

                    message.setKey(MANDRILL_KEY);
                    
                    message.setHtml("<html><body>http://localhost:8084/BrndBot/changePassword.jsp?userid="+hashURL+"</body></html>");
                    message.setText("text");
                    message.setSubject("your password for our account");
                    message.setFrom_email("intbit@intbittech.com");
                    message.setFrom_name("Intbit Tech");
                    message.setAsync(true);

                    ArrayList<String> tags = new ArrayList<String> ();
                    tags.add("1");
                    tags.add("2");

                    message.setTags(tags);

                    attachment attachment = new attachment();
                    attachment.setContent("1234");
                    attachment.setName("myfile.txt");
                    attachment.setType("text/plain");

                    ArrayList<attachment> attachments = new ArrayList<attachment> ();
                    attachments.add(attachment);
                    attachments.add(attachment);

                    message.setAttachments(attachments);

                    ArrayList<recipient> messageToList = new ArrayList<recipient> ();

                    recipient recipient = new recipient();
                    recipient.setEmail(email_id);
                    recipient.setName("Syed Muzamil");
                    recipient.setType("to");

                    messageToList.add(recipient);

                    message.setMessageTo(messageToList);

                    recipientMetadata recipientMetadata = new recipientMetadata();
                    recipientMetadata.setRcpt(email_id);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("key", "value");
                    recipientMetadata.setValues(map);

                    ArrayList<recipientMetadata> metadataList = new ArrayList<recipientMetadata>();
                    metadataList.add(recipientMetadata);
                    metadataList.add(recipientMetadata);

                    message.setRecipient_metadata(metadataList);
                    SE.checkPingPong();
                    SE.sendMail(message);
                    out.write("true");
            }else {
                    out.write("false");
            } 
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch (Exception e){}
        }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch (Exception e){}
        }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
