/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.google.gson.Gson;
import com.intbittech.AppConstants;
import com.intbittech.utility.ServletUtil;
import com.intbittech.dao.impl.EmailHistoryDAO;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.services.SendEmailService;
import com.intbittech.email.mandrill.MandrillApiHandler;
import com.intbittech.email.mandrill.SendMail;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.EmailDataDetails;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SendEmailServiceImpl implements SendEmailService {

    private final static Logger logger = Logger.getLogger(SendEmailServiceImpl.class);
    
    @Autowired
    private ContactEmailListLookupService contactEmailListLookupService;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    EmailServiceProviderService emailServiceProviderService;

    @Override
    public void sendMail(EmailDataDetails emailDataDetails) throws Exception {
        Mail mail = new Mail();
        List<ContactEmailListLookup>  toEmailIds = new ArrayList<>();
        if(emailDataDetails.getIsRecurring())
            toEmailIds = contactEmailListLookupService.getContactsByEmailListNameAndCompanyIdForToday(emailDataDetails.getEmailListName(), emailDataDetails.getCompanyId(),emailDataDetails.getDays());
        else
            toEmailIds = contactEmailListLookupService.getContactsByEmailListNameAndCompanyId(emailDataDetails.getEmailListName(), emailDataDetails.getCompanyId());

        
        Email emailFrom = new Email(emailDataDetails.getFromEmailAddress(), emailDataDetails.getFromName());
        mail.setFrom(emailFrom);
        Email emailReplyTo = new Email(emailDataDetails.getReplyToEmailAddress());
        mail.setReplyTo(emailReplyTo);
        mail.setSubject(emailDataDetails.getEmailSubject());
        
        Content content = new Content(IConstants.kContentHTML, emailDataDetails.getHtmlData());
        mail.addContent(content);
        
        //TODO category
//        mail.addCategory("category goes here");
        //TODO preheader
//        mail.addHeader("preHeader", preheader);

        for (ContactEmailListLookup currectContact : toEmailIds) {
            Personalization personalization = new Personalization();
            Users user = new Users();
            user.setFirstName(currectContact.getFkContactId().getFirstName());
            user.setLastName(currectContact.getFkContactId().getLastName());
            Email emailToObject = new Email(currectContact.getFkContactId().getEmailAddress(), Utility.combineUserName(user));
            personalization.addTo(emailToObject);
            
            personalization.addSubstitution(IConstants.kEmailClientFirstName, user.getFirstName());
            personalization.addSubstitution(IConstants.kEmailClientFirstName.toLowerCase(), user.getFirstName());
            
            personalization.addSubstitution(IConstants.kEmailClientLastName, user.getLastName());
            personalization.addSubstitution(IConstants.kEmailClientLastName.toLowerCase(), user.getLastName());
            
            personalization.addSubstitution(IConstants.kEmailClientFullName, Utility.combineUserName(user));
            personalization.addSubstitution(IConstants.kEmailClientFullName.toLowerCase(), Utility.combineUserName(user));
            
            mail.addPersonalization(personalization);
        }
        emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_SubUserRegularEmail);

        
        //TODO need to check and change this
        int lastUpdateId = EmailHistoryDAO.addToEmailHistory(emailDataDetails.getCompanyId(),
                emailDataDetails.getHtmlData(), emailDataDetails.getFromEmailAddress(), emailDataDetails.getEmailListName(),
                "", emailDataDetails.getEmailSubject(), "TODO add tag/category here");
        //TODO check this and remove insertMandrillEmailId
//        if (mandrillResponse != null && lastUpdateId != -1) {
//            EmailHistoryDAO.insertMandrillEmailId(mandrillResponse, lastUpdateId);
//        }
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

    @Override
    public String previewEmail(Integer companyId, Map<String, Object> requestBodyMap) {
        try {
            String htmlString = (String) requestBodyMap.get("htmlString");
            String iframeName = (String) requestBodyMap.get("iframeName");
            String htmlHeader = "";
            htmlString = htmlString.replace("contenteditable=\"true\"", "contenteditable=\"false\"");
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
