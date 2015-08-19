/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import email.mandrill.Message;
import email.mandrill.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;


/**
 *
 * @author intbit
 */
@WebServlet(name = "SendEmailServlet", urlPatterns = {"/SendEmailServlet"})
public class SendEmailServlet extends BrndBotBaseHttpServlet {

    private final static String MANDRILL_KEY = "4jd3wIMvBAmJt9H0FcEb1w";

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

//        SqlMethods sqlmethods = new SqlMethods();
        SendMail send_email = new SendMail();
        response.setContentType("text/html;charset=UTF-8");
        getSqlMethodsInstance().session = request.getSession(true);
        PrintWriter out = response.getWriter();
        try {
            String email_subject = request.getParameter("email_subject");
            String email_addresses = request.getParameter("email_addresses");
            String html_text = (String) getSqlMethodsInstance().session.getAttribute("htmldata");
            String emaillist_name = (String) getSqlMethodsInstance().session.getAttribute("email_list");
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            
            String reply_to_address = request.getParameter("reply_to_email_address");
            String from_email_address = request.getParameter("from_email_address");
            String from_name = request.getParameter("from_name");
            Message message = new Message();

//            message.setKey(MANDRILL_KEY);
//
//            message.setHtml(html_text);
//            message.setSubject(email_subject);
//            message.setFrom_email(from_email_address);
//            message.setFrom_name(from_name);
//            message.setAsync(true);
//            message.setReply_to(reply_to_address);
//
//            //For Billing purposes.
//            ArrayList<String> tags = new ArrayList<String>();
//            tags.add(String.valueOf(user_id));
//
//            message.setTags(tags);
//
//            ArrayList<Recipient> messageToList = new ArrayList<Recipient>();
//
//            String emailids[] = email_addresses.split(",");
//
//            for (int i = 0; i < emailids.length; i++) {
//
//                String email = emailids[i];
//                Recipient rec = new Recipient();
//
//                rec.setEmail(email);
//                rec.setName(email);
//                rec.setType("to");
//                messageToList.add(rec);
//                message.setMessageTo(messageToList);
//                RecipientMetadata recipientMetadata1 = new RecipientMetadata();
//                recipientMetadata1.setRcpt(email);
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("key", "value");
//                recipientMetadata1.setValues(map);
//
//                ArrayList<RecipientMetadata> metadataList1 = new ArrayList<RecipientMetadata>();
//                metadataList1.add(recipientMetadata1);
//                message.setRecipient_metadata(metadataList1);
//
//            }
            
            getSqlMethodsInstance().setEmailSentHistory(user_id, html_text, from_email_address, emaillist_name);
            send_email.sendMail(message);
            out.write("true");
        } catch (Exception e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

            out.write(getSqlMethodsInstance().error);
        } finally {
            out.close();
            getSqlMethodsInstance().closeConnection();;
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
