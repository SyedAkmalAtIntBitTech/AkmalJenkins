/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.SendEmail;
import com.intbit.dao.EmailHistoryDAO;
import com.intbit.marketing.model.TblUserPreferences;
import com.intbit.marketing.service.UserPreferencesService;
import email.mandrill.Message;
import email.mandrill.MessageResponses;
import email.mandrill.Recipient;
import email.mandrill.RecipientMetadata;
import email.mandrill.SendMail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ajit
 */
public class SendAnEmail {

    @Autowired
    public UserPreferencesService userPreferencesService;

    public static String sendEmail(String html_text, String email_subject, String to_email_addresses, String emaillist_name, Integer user_id, String reply_to_address, String from_email_address, String from_name) {
        String returnMessage = "";
        try {
            SendMail send_email = new SendMail();
            Message message = new Message();
            message.setKey(SendEmail.MANDRILL_KEY);
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

            String emailids[] = to_email_addresses.split(",");

            for (int i = 0; i < emailids.length; i++) {

                String email = emailids[i];
                Recipient rec = new Recipient();

                rec.setEmail(email);
                rec.setName(email);
                rec.setType("to");
                messageToList.add(rec);
                message.setMessageTo(messageToList);
                RecipientMetadata recipientMetadata1 = new RecipientMetadata();
                recipientMetadata1.setRcpt(email);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("key", "value");
                recipientMetadata1.setValues(map);

                ArrayList<RecipientMetadata> metadataList1 = new ArrayList<RecipientMetadata>();
                metadataList1.add(recipientMetadata1);
                message.setRecipient_metadata(metadataList1);

            }
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
            Logger.getLogger(SendAnEmail.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger(SendAnEmail.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return returnMessage;
    }

    public String getAllEmailAddressesForEmailList(Integer userId, Integer days, String emailListName) throws Throwable {
        TblUserPreferences userPreferences = userPreferencesService.getById(userId);
        String userPreferencesJson = userPreferences.getUserPreferences();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(userPreferencesJson);
        org.json.simple.JSONArray allEmailListJSONArray = (org.json.simple.JSONArray) jsonObject.get("emailLists");
        org.json.simple.JSONArray jSONArray = null;
         for (Object emaiListObject : allEmailListJSONArray) {
                JSONObject emailListJSONObject = (JSONObject) emaiListObject;
                String emailListNameInUserPreferences = (String) emailListJSONObject.get("emailListName");
                if (emailListNameInUserPreferences.equals(emailListName)) {
                 jSONArray = (JSONArray)emailListJSONObject.get("emailAddresses");
                 break;
             }
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String to_email_addresses = "";
        for (Integer i = 0; i < jSONArray.size(); i++) {
            JSONObject json = (JSONObject) jSONArray.get(i);

            String createDate = json.get("addedDate").toString();
            Date date = formatter.parse(createDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, days);
            String postDate = formatter.format(cal.getTime());
            Date todayDate = Calendar.getInstance().getTime();
            String currentDateString = formatter.format(todayDate);
            if (postDate.equalsIgnoreCase(currentDateString)) {
                to_email_addresses = json.get("emailAddress").toString();

                if ((i + 1) < jSONArray.size()) {
                    to_email_addresses += ",";
                }

            }
        }

        return to_email_addresses;
    }

}
