/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.enums.EmailListTagConstants;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.model.Company;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.model.Contacts;
import com.intbittech.model.EmailList;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.model.EmailListType;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.modelmappers.AddEmailListDetails;
import com.intbittech.modelmappers.ContactDetails;
import com.intbittech.modelmappers.DeleteIdsDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.ContactsService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.EmailListTagLookupService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/emaillist")
public class EmailListController {
    
    private final Logger logger = Logger.getLogger(EmailListController.class);
    
    @Autowired
    EmailListService emailListService;
    
    @Autowired
    EmailListTagLookupService emailListTagLookupService;
    
    @Autowired
    ContactEmailListLookupService contactEmailListLookupService;
    
    @Autowired
    ContactsService contactsService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailList(@RequestParam("emailListName") String emailListName, HttpServletRequest request,
            HttpServletResponse response,@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        
        try {
            String queryParameter = (String) request.getParameter("update");
            String data = emailListService.getEmailList(queryParameter, companyId, emailListName);
            transactionResponse.addDetail(data);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("list_success", new String[]{}, Locale.US)));
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> setEmailList(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            Boolean result = emailListService.setEmailList(requestBodyMap, userCompanyIds.getCompanyId());
            if (result) {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("save_success", new String[]{}, Locale.US)));
            } else {
                logger.debug("Unable to save email list");
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(messageSource.getMessage("save_fail", new String[]{}, Locale.US)));
            }
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/createEmailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> createEmailList(@RequestBody AddEmailListDetails addEmailListDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailList emailList = new EmailList();
            emailList.setCreatedDate(new Date());
            emailList.setEmailListName(addEmailListDetails.getEmailListName());
            emailList.setDescription(addEmailListDetails.getEmailListDescription());
            emailList.setDefaultFromAddress(addEmailListDetails.getDefaultFromAddress());
            
            EmailListType emailListType = new EmailListType();
            emailListType.setTypeId(EmailListTypeConstants.valueOf(addEmailListDetails.getEmailListType()).getEmailListType());
            emailList.setFkTypeId(emailListType);
            
            Company company = new Company();
            company.setCompanyId(addEmailListDetails.getCompanyId());
            emailList.setFkCompanyId(company);
            
            Integer emailListId = emailListService.save(emailList);
            
            emailList = emailListService.getByEmailListId(emailListId);
            
            for(String tag : addEmailListDetails.getEmailListTags()) {
                EmailListTagLookup emailListTagLookup = new EmailListTagLookup();
                emailListTagLookup.setFkEmailListId(emailList);
                EmailListTag emailListTag = new EmailListTag();
                emailListTag.setTagId(EmailListTagConstants.valueOf(tag).getEmailListTag());
                emailListTagLookupService.save(emailListTagLookup);
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("email_list_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/deleteEmailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailList(@RequestBody DeleteIdsDetails deleteIdsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            for(Integer emailListId : deleteIdsDetails.getIds()) {
                emailListService.delete(emailListId);
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("email_list_delete", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    //TODO validate email
    @RequestMapping(value = "/addContact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> addContact(@RequestBody ContactDetails contactDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            contactsService.addContact(contactDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contact_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    //TODO validate emails
    @RequestMapping(value = "/addContactList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> addContactList(@RequestBody ContactDetails contactDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            String emailAddressesSplit[] = contactDetails.getEmailAddress().split(",");
            for(int i=0;i<emailAddressesSplit.length;i++)
            {
                contactDetails.setEmailAddress(emailAddressesSplit[i]);
                contactsService.addContact(contactDetails);
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contact_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    //IDS need to contactEmailListLookupId and not contactId
    @RequestMapping(value = "/deleteContactList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteContactList(@RequestBody DeleteIdsDetails deleteIdsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            for(Integer contactId : deleteIdsDetails.getIds()) {
                contactEmailListLookupService.delete(contactId);
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contact_delete", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    //TODO validate email
    @RequestMapping(value = "/editContact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> editContact(@RequestBody ContactDetails contactDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Contacts contacts = new Contacts();
            contacts = contactsService.getByContactsId(contactDetails.getContactId());
            contacts.setEmailAddress(contactDetails.getEmailAddress());
            contacts.setFirstName(contactDetails.getFirstName());
            contacts.setLastName(contactDetails.getLastName());
            contactsService.update(contacts);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contact_edit", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getContactsOfEmailList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getContactsOfEmailList(@RequestParam Integer emailListId) {
        GenericResponse<ContactDetails> genericResponse = new GenericResponse<ContactDetails>();
        try {
            List<ContactDetails> contactDetailsList = new ArrayList<>();
            List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailListId);
            if(contactsList !=null) {
                for(ContactEmailListLookup contact:contactsList) {
                    ContactDetails contactDetails = new ContactDetails();
                    contactDetails.setContactId(contact.getFkContactId().getContactId());
                    contactDetails.setEmailAddress(contact.getFkContactId().getEmailAddress());
                    contactDetails.setFirstName(contact.getFkContactId().getFirstName());
                    contactDetails.setLastName(contact.getFkContactId().getLastName());
                    contactDetails.setContactEmailListLookupId(contact.getContactLookupId());
                    contactsList.add(contact);
                }
            }
            genericResponse.setDetails(contactDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contacts_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllEmailListNames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListNames(@RequestParam Integer companyId) {
        GenericResponse<AddEmailListDetails> genericResponse = new GenericResponse<AddEmailListDetails>();
        try {
            List<AddEmailListDetails> allEmailListNames = new ArrayList<>();
            List<EmailList> emailLists = emailListService.getEmailListByCompanyId(companyId);
            for(EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                allEmailListNames.add(addEmailListDetails);
            }
            genericResponse.setDetails(allEmailListNames);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailList_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllEmailListWithNoOfContactsForUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListWithNoOfContacts(@RequestParam Integer companyId) {
        GenericResponse<AddEmailListDetails> genericResponse = new GenericResponse<AddEmailListDetails>();
        try {
            List<AddEmailListDetails> allEmailListNames = new ArrayList<>();
            List<EmailList> emailLists = emailListService.getEmailListByCompanyIdAndType(companyId,EmailListTypeConstants.General.getEmailListType());
            for(EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                addEmailListDetails.setEmailListType(emailList.getFkTypeId().getTypeName());
                
                //Send back tags
                List<EmailListTagLookup> emailListTagLookups = emailListTagLookupService.getByEmailListTagLookupByEmailListId(companyId);
                if(emailListTagLookups!=null)
                {
                    List<String> emailListTags = new ArrayList<>();
                    for(EmailListTagLookup emailListTagLookup:emailListTagLookups) {
                        emailListTags.add(emailListTagLookup.getFkEmailListTagId().getTagName());
                    }
                    if(emailListTags.size()>0)
                        addEmailListDetails.setEmailListTags(emailListTags);
                }
                List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailList.getEmailListId());
                Integer numberOfContacts = 0;
                if(contactsList != null)
                    numberOfContacts = contactsList.size();
                addEmailListDetails.setNumberOfContacts(numberOfContacts);
                allEmailListNames.add(addEmailListDetails);
            }
            genericResponse.setDetails(allEmailListNames);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailList_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllEmailListWithNoOfContactsForMindBody", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListWithNoOfContactsForMindBody(@RequestParam Integer companyId) {
        GenericResponse<AddEmailListDetails> genericResponse = new GenericResponse<AddEmailListDetails>();
        try {
            List<AddEmailListDetails> allEmailListNames = new ArrayList<>();
            List<EmailList> emailLists = emailListService.getEmailListByCompanyIdAndType(companyId,EmailListTypeConstants.Mindbody.getEmailListType());
            for(EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                addEmailListDetails.setEmailListType(emailList.getFkTypeId().getTypeName());
                
                //Send back tags
                List<EmailListTagLookup> emailListTagLookups = emailListTagLookupService.getByEmailListTagLookupByEmailListId(companyId);
                if(emailListTagLookups!=null)
                {
                    List<String> emailListTags = new ArrayList<>();
                    for(EmailListTagLookup emailListTagLookup:emailListTagLookups) {
                        emailListTags.add(emailListTagLookup.getFkEmailListTagId().getTagName());
                    }
                    if(emailListTags.size()>0)
                        addEmailListDetails.setEmailListTags(emailListTags);
                }
                
                List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailList.getEmailListId());
                Integer numberOfContacts = 0;
                if(contactsList != null)
                    numberOfContacts = contactsList.size();
                addEmailListDetails.setNumberOfContacts(numberOfContacts);
                allEmailListNames.add(addEmailListDetails);
            }
            genericResponse.setDetails(allEmailListNames);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailList_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
