/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.mandrill;

import com.controller.ForgotSendEmail;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mohamed
 */
public class MandrillApiHandler {
    
    private static final Logger logger = Logger.getLogger(MandrillApiHandler.class.getName());
    
    public static Map<String, Object> getEmailDetails(String mandrillEmailId) throws URISyntaxException, IOException{
        Map<String,Object> emailDetails = new HashMap<>();
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://mandrillapp.com/api/1.0/messages/info.json");
        URI uri = new URIBuilder(httpGet.getURI())
                .addParameter("key", ForgotSendEmail.MANDRILL_KEY)
                .addParameter("id", mandrillEmailId)
                .build();
        logger.info("Getting Email details: " + uri.toString());
        Gson gson = new Gson();
        httpGet.setURI(uri);
        
        //Execute and get the response.
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();
        if ( responseEntity != null){
            String jsonContent = EntityUtils.toString(responseEntity);
            logger.info(jsonContent);
            // Create a Reader from String
            Reader stringReader = new StringReader(jsonContent);

            // Pass the string reader to JsonReader constructor
            JsonReader reader = new JsonReader(stringReader);
            reader.setLenient(true);
            Map<String, Object> mandrillEmailDetails = gson.fromJson(reader, Map.class);
            emailDetails.put("sent_on", mandrillEmailDetails.get("ts"));
            emailDetails.put("mandrill_id", mandrillEmailDetails.get("_id"));
            emailDetails.put("email_state", mandrillEmailDetails.get("state"));
            emailDetails.put("subject", mandrillEmailDetails.get("subject"));
            emailDetails.put("email", mandrillEmailDetails.get("email"));
            emailDetails.put("tags", mandrillEmailDetails.get("tags"));
            emailDetails.put("opens", mandrillEmailDetails.get("opens"));
            emailDetails.put("clicks", mandrillEmailDetails.get("clicks"));
            emailDetails.put("sender", mandrillEmailDetails.get("sender"));
            
        }
        
        return emailDetails;
    }
    
    public static List<Map<String, Object>> getTags(){
        List<Map<String, Object>> mandrillTagList = new ArrayList<>();
        
        try {
            
            HttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://mandrillapp.com/api/1.0/tags/list.json");
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("key", ForgotSendEmail.MANDRILL_KEY)
                    .build();
            logger.info("Getting mandrill tags: " + uri.toString());
            Gson gson = new Gson();
            httpGet.setURI(uri);
            
            //Execute and get the response.
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if ( responseEntity != null){
                String jsonContent = EntityUtils.toString(responseEntity);
//                logger.info(jsonContent);
                logger.info("Tags list in MandrillApiHandler.getTags()");
                // Create a Reader from String
                Reader stringReader = new StringReader(jsonContent);
                
                // Pass the string reader to JsonReader constructor
                JsonReader reader = new JsonReader(stringReader);
                reader.setLenient(true);
                mandrillTagList = gson.fromJson(reader, List.class);
                
            }
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(MandrillApiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MandrillApiHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MandrillApiHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mandrillTagList;
    }
    
}
