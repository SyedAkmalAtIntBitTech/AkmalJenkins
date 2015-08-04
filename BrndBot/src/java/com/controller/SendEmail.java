/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import email.mandrill.Attachment;
import email.mandrill.Message;
import email.mandrill.Recipient;
import email.mandrill.RecipientMetadata;
import email.mandrill.SendMail;
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
public class SendEmail extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    GenerateHashPassword generate_hash_password = new GenerateHashPassword();
    boolean check = false;
    PreparedStatement prepared_statement = null;
    ResultSet result_set = null;
    StringBuffer string_buffer = new StringBuffer();

    private final static String MANDRILL_KEY = "4jd3wIMvBAmJt9H0FcEb1w";
    SendMail send_email = new SendMail();

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
        sqlmethods.session = request.getSession(true);
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject joUser = null;
            joUser = (org.json.simple.JSONObject) parser.parse(string_buffer.toString());

            String email_id = (String) joUser.get("emailid");

            sqlmethods.setDatabaseConnection();
            check = sqlmethods.checkEmailAvailability(email_id);
            Integer user_id = sqlmethods.getUserID(email_id);
            Date expdate = new Date();
            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            java.sql.Time tdat = new java.sql.Time(expdate.getHours(), expdate.getMinutes(), expdate.getSeconds());

            if (check) {

                String randomVal = email_id + String.valueOf(user_id) + sdat + tdat;

                String hashURL = generate_hash_password.hashURL(randomVal);

                sqlmethods.setUserDetails(user_id, hashURL, sdat, tdat);

                Message message = new Message();

                message.setKey(MANDRILL_KEY);
//                String url=request.getRequestURL().toString().replace("SendEmail","");  
                message.setHtml("<html><body>http://production.brndbot.intbittech.com:8080/BrndBot/changepassword.jsp?userid=" + hashURL + "</body></html>");
                message.setText("text");
                message.setSubject("your password changing link for our account");
                message.setFrom_email("intbit@intbittech.com");
                message.setFrom_name("Intbit Tech");
                message.setAsync(true);

                ArrayList<String> tags = new ArrayList<String>();
                tags.add("1");
                tags.add("2");

                message.setTags(tags);

                ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

                Recipient recipient = new Recipient();
                recipient.setEmail(email_id);
                recipient.setName("Syed Muzamil");
                recipient.setType("to");

                messageToList.add(recipient);

                message.setMessageTo(messageToList);

                RecipientMetadata recipientMetadata = new RecipientMetadata();
                recipientMetadata.setRcpt(email_id);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("key", "value");
                recipientMetadata.setValues(map);

                ArrayList<RecipientMetadata> metadataList = new ArrayList<RecipientMetadata>();
                metadataList.add(recipientMetadata);
                metadataList.add(recipientMetadata);

                message.setRecipient_metadata(metadataList);
                send_email.checkPingPong();
                send_email.sendMail(message);
                out.write("true");
            } else {
                out.write("false");
            }
            sqlmethods.con.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(sqlmethods.error);
        }finally {
            out.close();
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
        }
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
        }
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
