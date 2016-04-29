/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.mandrill;

import com.controller.SqlMethods;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import static social.controller.SendAnEmail.MANDRILL_KEY;

/**
 *
 * @author intbit
 */
public class SendMail {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(SendMail.class));

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
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h:mm a");
        String formattedDate = sdf.format(new Date());
        String tag =  subject + " - " + formattedDate;
        return tag;
    }

}
