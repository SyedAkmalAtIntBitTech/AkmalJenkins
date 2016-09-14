/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.email.mandrill;

import com.intbittech.component.SpringContextBridge;
import com.intbittech.dao.impl.EmailHistoryDAO;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.EmailInfo;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.utility.IConstants;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class SendMail {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(SendMail.class));
//    public final static String MANDRILL_KEY = "qiy8dbHXdGU46qX53MvGdQ";//Test key
    public final static String MANDRILL_KEY = "UMOD7RKLNPwoGrCL7XnvTg";//Prod key changed 17 june from new account - BrndBot1
    
    public static MessageResponses sendMail(Message message) {
        try {
            logger.log(Level.INFO, "Message:" + message.toJSONString());

            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("https://mandrillapp.com/api/1.0/messages/send.json");

            String request = message.toJSONString();
            HttpEntity entity = new ByteArrayEntity(request.getBytes("UTF-8"));
            httppost.setEntity(entity);
            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                InputStream instream = responseEntity.getContent();
                try {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(instream, writer, "UTF-8");
                    String theString = writer.toString();

                    MessageResponses messageResponses = new MessageResponses(theString);
                    //Do whateveer is needed with the response.
                    logger.log(Level.INFO, theString);
                    return messageResponses;
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while sending an email via mandrill:", null));
        }
        return null;
    }

    public static void checkPingPong() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("https://mandrillapp.com/api/1.0/users/ping.json");

            JSONObject obj = new JSONObject();
            obj.put("key", MANDRILL_KEY);
            String request = obj.toString();
            HttpEntity entity = new ByteArrayEntity(request.getBytes("UTF-8"));
            httppost.setEntity(entity);
            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                InputStream instream = responseEntity.getContent();
                try {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(instream, writer, "UTF-8");
                    String theString = writer.toString();
                    logger.log(Level.INFO, theString);
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while updating org name:", null));
        }
    }
    
    public static String getTag(String subject){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h mm a");
        String formattedDate = sdf.format(new Date());
        String tag =  subject + " - " + formattedDate;
        return tag;
    }
    
    public static String sendEmail(String html_text, String email_subject, String to_email_addresses, String emaillist_name, Integer user_id, String reply_to_address, String from_email_address, String from_name, String to_name) {
        String returnMessage = "";
        try {
            SendMail send_email = new SendMail();
            Message message = new Message();
            message.setKey(MANDRILL_KEY);
            message.setHtml(html_text);
            message.setSubject(email_subject);
            message.setFrom_email(from_email_address);
            message.setFrom_name(from_name);
            message.setAsync(true);
            message.setReply_to(reply_to_address);

            //For Billing purposes.
            ArrayList<String> tags = new ArrayList<String>();
            tags.add(SendMail.getTag(email_subject));
            logger.info("Mandrill Tag: " + SendMail.getTag(email_subject));
            message.setTags(tags);

            ArrayList<Recipient> messageToList = new ArrayList<Recipient>();

            Recipient rec = new Recipient();

            rec.setEmail(to_email_addresses);
            if (to_name != null){
                rec.setName(to_name);
            }else{
                rec.setName(to_email_addresses);
            }
            rec.setType("to");
            messageToList.add(rec);
            message.setMessageTo(messageToList);
            RecipientMetadata recipientMetadata1 = new RecipientMetadata();
            recipientMetadata1.setRcpt(to_email_addresses);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("key", "value");
            recipientMetadata1.setValues(map);

            ArrayList<RecipientMetadata> metadataList1 = new ArrayList<RecipientMetadata>();
            metadataList1.add(recipientMetadata1);
            message.setRecipient_metadata(metadataList1);

            MessageResponses mandrillResponse = send_email.sendMail(message);
            int lastUpdateId = EmailHistoryDAO.addToEmailHistory(user_id,
                    html_text, from_email_address, emaillist_name,
                    to_email_addresses, email_subject, SendMail.getTag(email_subject));
            if (mandrillResponse != null && lastUpdateId != -1) {
                EmailHistoryDAO.insertMandrillEmailId(mandrillResponse, lastUpdateId);
            }
            returnMessage = "success";
        } catch (Exception e) {
            returnMessage = "Email Exception: " + e.getMessage();
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, "Exception while sending an email", e);
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, "Exception while sending an email", e.getMessage());
        }
        return returnMessage;
    }

    public JSONArray getAllEmailAddressesForEmailList(Integer companyId, Integer days, String emailListName) throws Throwable {
        
        CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
        CompanyPreferences companyPreferences = companyPreferencesService.getByCompanyId(companyId);
        JSONParser parser = new JSONParser();
        JSONObject companyPreferencesEmailList = (JSONObject) parser.parse(companyPreferences.getEmailList());
        JSONArray userPreferencesJson = (JSONArray)companyPreferencesEmailList.get(IConstants.kEmailListUserKey);
        JSONArray jSONArray = null;
        JSONObject jsonObject = new JSONObject();
        List listemailInfos = new ArrayList();
        for (Object emaiListObject : userPreferencesJson) {
                JSONObject emailListJSONObject = (JSONObject) emaiListObject;
                String emailListNameInUserPreferences = (String) emailListJSONObject.get("emailListName");
                if (emailListNameInUserPreferences.equals(emailListName)) {
                    
                    jSONArray = (JSONArray)emailListJSONObject.get("emailAddresses");
                    for (int i = 0; i < jSONArray.size(); i++){

                        EmailInfo emailinfo = new EmailInfo().fromJSON(jSONArray.get(i).toString());
                        listemailInfos.add(emailinfo);
                    }
                    break;
                }
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArrayEmailAddresses = new JSONArray();
        for (Integer i = 0; i < listemailInfos.size(); i++) {
            EmailInfo emailInfo = (EmailInfo)listemailInfos.get(i);
            String createDate = emailInfo.getAddedDate().toString();
            Date date = formatter.parse(createDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, days);
            String postDate = formatter.format(cal.getTime());
            Date todayDate = Calendar.getInstance().getTime();
            String currentDateString = formatter.format(todayDate);
            if (postDate.equalsIgnoreCase(currentDateString)) {
                jsonArrayEmailAddresses.add(emailInfo);
            }
        }
        jsonObject.put(IConstants.kEmailClientName, jsonArrayEmailAddresses);

        return jsonArrayEmailAddresses;
    }
}
