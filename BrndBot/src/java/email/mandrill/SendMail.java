/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.mandrill;

import java.io.InputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

/**
 *
 * @author intbit
 */
public class SendMail {

    public final static String MANDRILL_KEY = "4jd3wIMvBAmJt9H0FcEb1w";

    public static void sendMail(Message message) {
        try {
            System.out.println("Message:" + message.toJSONString());

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

                    System.out.println(theString);
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    System.out.println(theString);
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
