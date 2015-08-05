/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import email.mandrill.Message;
import email.mandrill.Recipient;
import email.mandrill.RecipientMetadata;
import email.mandrill.SendMail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;

import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
@WebServlet(name = "sendEmail", urlPatterns = {"/sendEmail"})
public class SendEmail extends BrndBotBaseHttpServlet {

    GenerateHashPassword generate_hash_password;
    boolean check = false;
    PreparedStatement prepared_statement = null;
    ResultSet result_set = null;
    StringBuffer string_buffer;

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
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        getSqlMethodsInstance().session = request.getSession(true);
        generate_hash_password = new GenerateHashPassword();
        string_buffer = new StringBuffer();
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

            check = getSqlMethodsInstance().checkEmailAvailability(email_id);
            Integer user_id = getSqlMethodsInstance().getUserID(email_id);
            Date expdate = new Date();
            java.sql.Date sdat = new java.sql.Date(expdate.getYear(), expdate.getMonth(), expdate.getDate());
            java.sql.Time tdat = new java.sql.Time(expdate.getHours(), expdate.getMinutes(), expdate.getSeconds());

            if (check) {

                String randomVal = email_id + String.valueOf(user_id) + sdat + tdat;

                String hashURL = generate_hash_password.hashURL(randomVal);

                getSqlMethodsInstance().setUserDetails(user_id, hashURL, sdat, tdat);

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
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(getSqlMethodsInstance().error);
        }finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
            getSqlMethodsInstance().closeConnection();
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
