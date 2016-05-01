/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.IConstants;
import com.controller.SqlMethods;
import com.intbittech.dao.impl.EmailHistoryDAO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EmailInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Ajit
 */
public class SendAnEmail {
    public final static String MANDRILL_KEY = "lymaWyyktPr4YFkyZAzcQQ";//Test key

    SqlMethods sqlMethods = new SqlMethods();
    
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
            Logger.getLogger(SendAnEmail.class.getName()).log(Level.SEVERE, "Exception while sending an email", e);
            Logger.getLogger(SendAnEmail.class.getName()).log(Level.SEVERE, "Exception while sending an email", e.getMessage());
        }
        return returnMessage;
    }

    public JSONArray getAllEmailAddressesForEmailList(Integer userId, Integer days, String emailListName) throws Throwable {
        
        JSONObject userPreferences = null; //(JSONObject)sqlMethods.getJSONUserPreferences(userId);
        JSONArray userPreferencesJson = (JSONArray)userPreferences.get(IConstants.kEmailAddressUserPreferenceKey);
        org.json.simple.JSONArray jSONArray = null;
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
        JSONArray jsonArrayEmailClient = new JSONArray();
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
