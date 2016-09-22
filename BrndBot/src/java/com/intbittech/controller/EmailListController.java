/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.model.Contact;
import com.intbittech.model.EmailList;
import com.intbittech.model.EmailListTagLookup;
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
import com.intbittech.utility.EmailValidator;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.util.ArrayList;
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

    @RequestMapping(value = "/createEmailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> createEmailList(@RequestBody AddEmailListDetails addEmailListDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            if (!emailListService.checkUniqueness(addEmailListDetails.getCompanyId(), addEmailListDetails.getEmailListName())) {
                throw new ProcessFailed(messageSource.getMessage("email_list_not_unique", new String[]{}, Locale.US));
            }

            Integer emailListId = emailListService.save(addEmailListDetails);
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
            for (Integer emailListId : deleteIdsDetails.getIds()) {
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
            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(contactDetails.getEmailAddress())) {
                throw new ProcessFailed(messageSource.getMessage("contact_not_valid", new String[]{}, Locale.US));
            }

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
            for (int i = 0; i < emailAddressesSplit.length; i++) {
                EmailValidator emailValidator = new EmailValidator();
                if (emailValidator.validate(emailAddressesSplit[i])) {
                    contactDetails.setEmailAddress(emailAddressesSplit[i]);
                    contactsService.addContact(contactDetails);
                }
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("contact_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    //IDS need to be contactLookupId and not contactId
    @RequestMapping(value = "/deleteContactList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteContactList(@RequestBody DeleteIdsDetails deleteIdsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            for (Integer contactId : deleteIdsDetails.getIds()) {
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
            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(contactDetails.getEmailAddress())) {
                throw new ProcessFailed(messageSource.getMessage("contact_not_valid", new String[]{}, Locale.US));
            }
            Contact contacts = new Contact();
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
        GenericResponse<ContactEmailListLookup> genericResponse = new GenericResponse<ContactEmailListLookup>();
        try {
            List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailListId);
            genericResponse.setDetails(contactsList);
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
            for (EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                addEmailListDetails.setEmailListId(emailList.getEmailListId());
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
            List<EmailList> emailLists = emailListService.getEmailListByCompanyIdAndType(companyId, EmailListTypeConstants.General.getEmailListType());
            for (EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                addEmailListDetails.setEmailListType(emailList.getFkTypeId().getTypeName());
                addEmailListDetails.setEmailListId(emailList.getEmailListId());
                addEmailListDetails.setCreatedDate(emailList.getCreatedDate());
                //Send back tags
                List<EmailListTagLookup> emailListTagLookups = emailListTagLookupService.getByEmailListTagLookupByEmailListId(emailList.getEmailListId());
                if (emailListTagLookups != null) {
                    List<String> emailListTags = new ArrayList<>();
                    for (EmailListTagLookup emailListTagLookup : emailListTagLookups) {
                        emailListTags.add(emailListTagLookup.getFkEmailListTagId().getTagName());
                    }
                    if (emailListTags.size() > 0) {
                        addEmailListDetails.setEmailListTags(emailListTags);
                    }
                }
                List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailList.getEmailListId());
                Integer numberOfContacts = 0;
                if (contactsList != null) {
                    numberOfContacts = contactsList.size();
                }
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
            List<EmailList> emailLists = emailListService.getEmailListByCompanyIdAndType(companyId, EmailListTypeConstants.Mindbody.getEmailListType());
            for (EmailList emailList : emailLists) {
                AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                addEmailListDetails.setEmailListName(emailList.getEmailListName());
                addEmailListDetails.setEmailListType(emailList.getFkTypeId().getTypeName());
                addEmailListDetails.setEmailListId(emailList.getEmailListId());
                addEmailListDetails.setCreatedDate(emailList.getCreatedDate());

                //Send back tags
                List<EmailListTagLookup> emailListTagLookups = emailListTagLookupService.getByEmailListTagLookupByEmailListId(companyId);
                if (emailListTagLookups != null) {
                    List<String> emailListTags = new ArrayList<>();
                    for (EmailListTagLookup emailListTagLookup : emailListTagLookups) {
                        emailListTags.add(emailListTagLookup.getFkEmailListTagId().getTagName());
                    }
                    if (emailListTags.size() > 0) {
                        addEmailListDetails.setEmailListTags(emailListTags);
                    }
                }

                List<ContactEmailListLookup> contactsList = contactEmailListLookupService.getContactsByEmailListId(emailList.getEmailListId());
                Integer numberOfContacts = 0;
                if (contactsList != null) {
                    numberOfContacts = contactsList.size();
                }
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
