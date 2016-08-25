/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Address;
import com.intbittech.modelmappers.AddressDetails;

/**
 * <code> {@link AddressService} </code> Interface to get Address details from
 * Address Dao layer
 *
 * @author ilyas
 */
public interface AddressService {
    
    /**
     * This method pass id as input and get the {@link Address} from DAO layer.
     *
     * @param addressId the addressId
     * @return {@link Address}
     * @throws ProcessFailed the process failed
     */
    public Address getByAddressId(Integer addressId) throws ProcessFailed;
    
    /**
     * This method save {@link Address} into the DAO layer.
     *
     * @param address the Address
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(AddressDetails addressDetails) throws ProcessFailed;

    /**
     * This method update {@link Address} updates existing data from the
     * DAO layer.
     *
     * @param address the address
     * @throws ProcessFailed the process failed
     */
    public void update(Address address) throws ProcessFailed;

    /**
     * This method delete particular {@link Address} based on the
     * organization from the DAO layer.
     *
     * @param address the address
     * @throws ProcessFailed the process failed
     */
    public void delete(Address address) throws ProcessFailed;
    
}
