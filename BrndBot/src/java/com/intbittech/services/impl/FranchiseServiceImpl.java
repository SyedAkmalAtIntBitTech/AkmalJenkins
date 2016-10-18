/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.EmailListTagLookupDao;
import com.intbittech.dao.FranchiseCompanyLookupDao;
import com.intbittech.dao.FranchiseDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.FranchiseDetails;
import com.intbittech.sendgrid.models.EmailType;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.FranchiseService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author AR
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class FranchiseServiceImpl implements FranchiseService {

    private static Logger logger = Logger.getLogger(FranchiseServiceImpl.class);
    @Autowired
    private FranchiseDao franchiseDao;
    @Autowired
    private FranchiseCompanyLookupDao franchiseCompanyLookupDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmailListTagLookupDao emailListTagLookupDao;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UsersService usersService;
    @Autowired
    EmailServiceProviderService emailServiceProviderService;

    @Override
    public boolean activateCompanyAsFranchise(Integer companyId, Integer franchiseId) throws ProcessFailed {
        boolean returnValue = false;
        try {
            FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseHeadquarter(franchiseId);

            if (franchiseCompanyLookup != null) {
                franchiseCompanyLookup.setIsHeadQuarter(false);
                franchiseCompanyLookupDao.update(franchiseCompanyLookup);

                FranchiseCompanyLookup franchiseCompanyLookupToMakeHeadquarter = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
                franchiseCompanyLookupToMakeHeadquarter.setIsHeadQuarter(true);
                franchiseCompanyLookupDao.update(franchiseCompanyLookupToMakeHeadquarter);
            } else {
                FranchiseCompanyLookup franchiseCompanyLookupToMakeHeadquarter = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
                franchiseCompanyLookupToMakeHeadquarter.setIsHeadQuarter(true);
                franchiseCompanyLookupDao.update(franchiseCompanyLookupToMakeHeadquarter);
            }
            returnValue = true;
        } catch (Throwable throwable) {
            throw new ProcessFailed("No headquarter found.");
        }
        return returnValue;
        //TODO - Muzamil - Send out an email to the user that he has been added as a Franchise.
    }

    @Override
    public void associateCompanyToFranchise(Integer companyId, Users user, Integer franchiseId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if (franchiseCompanyLookup != null) {
            throw new ProcessFailed("Company is already associated with Franchise");
        }
        franchiseCompanyLookup = new FranchiseCompanyLookup();
        franchiseCompanyLookup.setCreatedAt(new Date());
        franchiseCompanyLookup.setFkAddedBy(user);
        franchiseCompanyLookup.setFkCompanyId(new Company(companyId));
        franchiseCompanyLookup.setFkFranchiseId(new Franchise(franchiseId));
        franchiseCompanyLookup.setIsHeadQuarter(false);
        franchiseCompanyLookupDao.save(franchiseCompanyLookup);
        //TODO Muzamil - send out an email , you are part of the franchise.
    }

    @Override
    public void removeCompanyFromFranchise(Integer companyId, Integer franchiseId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if (franchiseCompanyLookup == null) {
            throw new ProcessFailed("Company not found in the franchise.");
        }
        franchiseCompanyLookupDao.delete(franchiseCompanyLookup);
    }

    @Override
    public List<Company> getCompaniesForFranchises(Integer franchiseId) throws ProcessFailed {
        List<Company> companies = franchiseCompanyLookupDao.getCompanyForFranchiseId(new Franchise(franchiseId));
        if (companies == null) {
            throw new ProcessFailed("No Companies with franchise id" + companies + ".");
        }
        return companies;
    }

    @Override
    public List<Franchise> getFranchisesForCompanyId(Integer companyId) throws ProcessFailed {
        List<Franchise> franchises = franchiseCompanyLookupDao.getFranchiseForCompanyId(new Company(companyId));
        if (franchises == null) {
            throw new ProcessFailed("No Franchises with company id" + companyId + ".");
        }
        return franchises;
    }

    @Override
    public List<Franchise> getAllFranchises() throws ProcessFailed {
        List<Franchise> franchises = franchiseDao.getAllFranchise();
        if (franchises == null) {
            throw new ProcessFailed("No Franchises Present");
        }
        return franchises;
    }

    @Override
    public void deleteFranchise(Integer franchiseId) throws ProcessFailed {
        Franchise franchise = franchiseDao.getByFranchiseId(franchiseId);
        if (franchise == null) {
            throw new ProcessFailed("No Franchise with id" + franchiseId + ".");
        }
        franchiseDao.delete(franchise);
    }

    @Override
    public boolean updateFranchise(FranchiseDetails franchiseDetails, Integer franchiseId) throws ProcessFailed {
        Franchise franchise = franchiseDao.getByFranchiseId(franchiseId);
        if (franchise == null) {
            throw new ProcessFailed("No Franchise with id" + franchiseId + ".");
        }
        boolean franchiseExist = isFranchiseExist(franchiseDetails.getFranchiseName());
        if (franchiseExist) {
            return false;
        } else {
            franchise.setFranchiseName(franchiseDetails.getFranchiseName());
            franchiseDao.update(franchise);
            return true;
        }
    }

    @Override
    public boolean saveFranchise(FranchiseDetails franchiseDetails) throws ProcessFailed {
        Franchise franchiseDeserialized = FranchiseDetails.deserialize(franchiseDetails);
        if (franchiseDeserialized.getCreatedAt() == null) {
            franchiseDeserialized.setCreatedAt(new Date());
        }
        boolean franchiseExist = isFranchiseExist(franchiseDeserialized.getFranchiseName());
        if (franchiseExist) {
            return false;
        } else {
            franchiseDao.save(franchiseDeserialized);
            return true;
        }
    }

    @Override
    public FranchiseCompanyLookup getFranchiseLookup(Integer companyId, Integer franchiseId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if (franchiseCompanyLookup != null) {
            return franchiseCompanyLookup;
        } else {
            return null;
        }
    }

    @Override
    public String getFranchiseHeadquarter(Integer franchiseId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseHeadquarter(franchiseId);

        if (franchiseCompanyLookup != null) {
            Company company = companyDao.getCompanyById(franchiseCompanyLookup.getFkCompanyId().getCompanyId());
            return company.getCompanyName();
        } else {
            return null;
        }
    }

    @Override
    public boolean isFranchiseExist(String FranchiseName) throws ProcessFailed {
        Franchise franchise = franchiseDao.getByFranchiseName(FranchiseName);

        if (franchise != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FranchiseCompanyLookup getFranchiseByCompanyId(Integer companyId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseByCompanyId(companyId);
//        if (franchiseCompanyLookup == null) {
//            throw new ProcessFailed("No Franchise with company id" + companyId + ".");
//        }
        return franchiseCompanyLookup;
    }

    @Override
    public Boolean isEmailListTagAssociateToCompany(Integer emailListTagId, Integer companyId) throws ProcessFailed {
        Boolean isEmailList = false;
        EmailListTagLookup emailListTagLookup = emailListTagLookupDao.getEmailListTagLookupByEmailListTagIdAndCompanyId(emailListTagId, companyId);
        if (emailListTagLookup != null) {
            isEmailList = true;
        }
        return isEmailList;
    }

    @Override
    public Boolean requestToAddCompanies(String companiesName, String franchiseName, Integer userId) throws ProcessFailed {
        Users users = usersDao.getUserById(userId);
        String fromName = users.getFirstName() + " " + users.getLastName();
        return sendRequestToAddCompaniesEmail(users.getUserName(), fromName, IConstants.SUPPORT_BRNDBOT_EMAIL_ID, companiesName, franchiseName);
    }
    
    //TODO Ilyas this needs to corrected, its wrong right now sendTO goes to support???
    public Boolean sendRequestToAddCompaniesEmail(String fromEmailId, String fromName, String sendTo, String companyName, String franchiseName) throws ProcessFailed {
        try {

            String htmlBody = messageSource.getMessage("requestToAddCompany", new String[]{}, Locale.US);
            String formattedBody = String.format(htmlBody, companyName, franchiseName);
            Content content = new Content(IConstants.kContentHTML, formattedBody);
            String subject = "Request to add Company";
            Users  user = usersService.getUserByEmailId(fromEmailId);
            Email emailTo = new Email(sendTo);
            Email emailFrom = new Email(fromEmailId, Utility.combineUserName(user));
            Mail mail = new Mail(null, subject, emailTo, content);
            mail.setFrom(emailFrom);
            emailServiceProviderService.sendEmail(mail, EmailType.BrndBot_NoReply);
            return true;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("mail_send_problem", new String[]{}, Locale.US));
        }
    }
}
