/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.util.ServletUtil;
import com.intbittech.dao.impl.EmailHistoryDAO;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.services.SendEmailService;
import email.mandrill.MandrillApiHandler;
import email.mandrill.Message;
import email.mandrill.MessageResponses;
import email.mandrill.Recipient;
import email.mandrill.RecipientMetadata;
import email.mandrill.SendMail;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static social.controller.SendAnEmail.MANDRILL_KEY;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SendEmailServiceImpl implements SendEmailService {

    private final static Logger logger = Logger.getLogger(SendEmailServiceImpl.class);

    @Override
    public void sendMail(Map<String, Object> requestBodyMap, Integer companyId) throws Exception {
        SendMail send_email = new SendMail();
        String html_text = "";
        String email_subject = (String) requestBodyMap.get("email_subject");

        String to_email_addresses = (String) requestBodyMap.get("email_addresses");
        if ((String) requestBodyMap.get("htmldata") != null) {
            html_text = (String) requestBodyMap.get("htmldata");
        }
        //
        String emaillist_name = (String) requestBodyMap.get("email_list");

        String reply_to_address = (String) requestBodyMap.get("reply_to_email_address");
        String from_email_address = (String) requestBodyMap.get("from_email_address");
        String from_name = (String) requestBodyMap.get("from_name");
        String path = "";
        if ((String) requestBodyMap.get("iframeName") != null) {
            String iframeName = (String) requestBodyMap.get("iframeName");
            path = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html";
            File file = new File(path);
            html_text = FileUtils.readFileToString(file, "UTF-8");
        }
        Message message = new Message();

        message.setKey(MANDRILL_KEY);

        message.setHtml(html_text);
        message.setSubject(email_subject);
        message.setFrom_email(from_email_address);
        message.setFrom_name(from_name);
        message.setAsync(true);
        message.setReply_to(reply_to_address);

        //For Billing purposes.
        ArrayList<String> tags = new ArrayList<>();
        tags.add(SendMail.getTag(email_subject));
        logger.info("Mandrill Tag: " + SendMail.getTag(email_subject));
        message.setTags(tags);

        ArrayList<Recipient> messageToList = new ArrayList<>();

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
        }

        MessageResponses mandrillResponse = send_email.sendMail(message);

        //Added by Syed Ilyas 27 Nov 2015 - changed to NOT
        if (!path.equals("")) {
            File IframeDelete = new File(path);
            IframeDelete.delete();
        }

        int lastUpdateId = EmailHistoryDAO.addToEmailHistory(companyId,
                html_text, from_email_address, emaillist_name,
                to_email_addresses, email_subject, SendMail.getTag(email_subject));
        if (mandrillResponse != null && lastUpdateId != -1) {
            EmailHistoryDAO.insertMandrillEmailId(mandrillResponse, lastUpdateId);
        }
    }

    @Override
    public String getTags(Integer userId) throws Exception {
        List<Map<String, Object>> tagsFromMandrill = MandrillApiHandler.getTags();
        List<Map<String, Object>> tagsFromMandrillForUser = new ArrayList<>();

        Set<String> tagsForUser = EmailHistoryDAO.getTagsForUser(userId);
        for (Map<String, Object> mTag : tagsFromMandrill) {
            if (mTag.get("tag") != null) {
                if (tagsForUser.contains(mTag.get("tag").toString())) {
                    tagsFromMandrillForUser.add(mTag);
                }
            }
        }
        return new Gson().toJson(tagsFromMandrillForUser);
    }

    //TODO Ilyas to check the path.
    @Override
    public String previewEmail(Integer companyId, Map<String, Object> requestBodyMap) {
        try {
            String htmlString = (String) requestBodyMap.get("htmlString");
            String iframeName = (String) requestBodyMap.get("iframeName");
            String htmlHeader = "";

            htmlHeader = ServletUtil.getEmailHeader();
            htmlString = htmlHeader + htmlString + "</body></html>";
            File htmlFolder = new File(AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH);
            if (!htmlFolder.exists()) {
                htmlFolder.mkdirs();
            }
            File emailTemplateFile = new File(AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html");
            if (!emailTemplateFile.exists()) {
                emailTemplateFile.createNewFile();
            }
            FileWriter emailTemplateWriter = new FileWriter(emailTemplateFile, false); // true to append
            // false to overwrite.
            emailTemplateWriter.write(htmlString);
            emailTemplateWriter.close();
            return htmlString;
        } catch (Throwable th) {
            logger.error(th);
            throw new ProcessFailed("Unable to create the preview");
        }
    }
}
