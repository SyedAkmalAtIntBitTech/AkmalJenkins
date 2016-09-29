/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.AddressDao;
import com.intbittech.dao.CompanyPreferencesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Address;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.AddressDetails;
import com.intbittech.services.AddressService;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilyas
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class AddressServiceImpl implements AddressService {
    
    private static Logger logger = Logger.getLogger(AddressServiceImpl.class);
    
    @Autowired
    private AddressDao addressDao;
    
    @Autowired
    private CompanyPreferencesDao companyPreferencesDao;
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public Address getByAddressId(Integer addressId) throws ProcessFailed {
        Address address = addressDao.getByAddressId(addressId);
        if (address == null) {
            throw new ProcessFailed(messageSource.getMessage("address_not_found",new String[]{}, Locale.US));
        }
        return address;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(AddressDetails addressDetails, Company company) throws ProcessFailed {
        CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
        Address address = new Address();
        Integer savedAddressId = 0;
        if(companyPreferences.getFkAddressId() != null) {
            savedAddressId = companyPreferences.getFkAddressId().getAddressId();
            address = addressDao.getByAddressId(savedAddressId);
        }
        address.setAddressLine1(addressDetails.getAddressLine1());
        address.setAddressLine2(addressDetails.getAddressLine2());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setZipcode(addressDetails.getZipcode());
        address.setCountry(addressDetails.getCountry());
        if (companyPreferences.getFkAddressId() == null) {
        //Save address
            savedAddressId =  addressDao.save(address);
            //Save addressId in companyPreferences  
            address = new Address();
            address.setAddressId(savedAddressId);
            companyPreferences.setFkAddressId(address);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        }
        else {
        //Update address
            addressDao.update(address);
            
        }
        
        return savedAddressId;
    }

    /**
     * {@inheritDoc}
     */
    public void update(Address address) throws ProcessFailed {
        addressDao.update(address);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer addressId) throws ProcessFailed {
        Address address = addressDao.getByAddressId(addressId);
        if(address == null)
            throw new ProcessFailed(messageSource.getMessage("address_not_found",new String[]{}, Locale.US));
        addressDao.delete(address);
    }
    
}
