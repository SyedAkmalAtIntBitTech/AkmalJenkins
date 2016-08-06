/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.FranchiseCompanyLookupDao;
import com.intbittech.dao.FranchiseDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.FranchiseDetails;
import com.intbittech.services.FranchiseService;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void activateCompanyAsFranchise(Integer companyId, Integer franchiseId) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if(franchiseCompanyLookup != null) {
            throw new ProcessFailed("This company already has a head quarter");
        }
       
        franchiseCompanyLookup = new FranchiseCompanyLookup();
        franchiseCompanyLookup.setCreatedAt(new Date());
        franchiseCompanyLookup.setFkAddedByUserId(new Users(1));//Always gonna be admin
        franchiseCompanyLookup.setFkCompanyId(new Company(companyId));
        franchiseCompanyLookup.setFkFranchiseId(new Franchise(franchiseId));
        franchiseCompanyLookup.setIsHeadQuarter(true);
        franchiseCompanyLookupDao.save(franchiseCompanyLookup);    
        //TODO - Muzamil - Send out an email to the user that he has been added as a Franchise.
    }

    @Override
    public void associateCompanyToFranchise(Integer companyId, Users user, Integer franchiseId ) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if(franchiseCompanyLookup != null) {
            throw new ProcessFailed("Company is already associated with Franchise");
        }
        franchiseCompanyLookup = new FranchiseCompanyLookup();
        franchiseCompanyLookup.setCreatedAt(new Date());
        franchiseCompanyLookup.setFkAddedByUserId(user);
        franchiseCompanyLookup.setFkCompanyId(new Company(companyId));
        franchiseCompanyLookup.setFkFranchiseId(new Franchise(franchiseId));
        franchiseCompanyLookup.setIsHeadQuarter(false);
        franchiseCompanyLookupDao.save(franchiseCompanyLookup);
        //TODO Muzamil - send out an email , you are part of the franchise.
    }

    @Override
    public void removeCompanyFromFranchise(Integer companyId, Integer franchiseId ) throws ProcessFailed {
        FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookupDao.getFranchiseLookup(companyId, franchiseId);
        if (franchiseCompanyLookup == null) {
            throw new ProcessFailed("Company not found in the franchise.");
        }
        franchiseCompanyLookupDao.delete(franchiseCompanyLookup);
    }
    
      @Override
    public List<Company> getCompaniesForFranchises(Integer franchiseId ) throws ProcessFailed {
        List<Company> companies = franchiseCompanyLookupDao.getCompanyForFranchiseId(new Franchise(franchiseId));
        if (companies == null) {
            throw new ProcessFailed("No Companies with franchise id" + companies + ".");
        }
        return companies;
    }

    @Override
    public List<Franchise> getFranchisesForCompanyId(Integer companyId ) throws ProcessFailed {
        List<Franchise> franchises = franchiseCompanyLookupDao.getFranchiseForCompanyId(new Company(companyId));
        if (franchises == null) {
            throw new ProcessFailed("No Franchises with company id" + companyId + ".");
        }
        return franchises;
    }

    @Override
    public List<Franchise> getAllFranchises( ) throws ProcessFailed {
        List<Franchise> franchises = franchiseDao.getAllFranchise();
        if (franchises == null) {
            throw new ProcessFailed("No Franchises Present");
        }
        return franchises;
    }

    @Override
    public void deleteFranchise(Integer franchiseId ) throws ProcessFailed {
        Franchise franchise = franchiseDao.getByFranchiseId(franchiseId);
        if (franchise == null) {
            throw new ProcessFailed("No Franchise with id" + franchiseId + ".");
        }
        franchiseDao.delete(franchise);
    }

    @Override
    public void updateFranchise(FranchiseDetails franchiseDetails, Integer franchiseId ) throws ProcessFailed {
        Franchise franchise = franchiseDao.getByFranchiseId(franchiseId);
        if (franchise == null) {
            throw new ProcessFailed("No Franchise with id" + franchiseId + ".");
        }
        Franchise franchiseDeserialized = FranchiseDetails.deserialize(franchiseDetails);
        franchiseDeserialized.setFranchiseId(franchiseId);
        franchiseDao.update(franchise);
    }

    @Override
    public void saveFranchise(FranchiseDetails franchiseDetails ) throws ProcessFailed {
        Franchise franchiseDeserialized = FranchiseDetails.deserialize(franchiseDetails);
        franchiseDao.save(franchiseDeserialized);
    }
}
  
