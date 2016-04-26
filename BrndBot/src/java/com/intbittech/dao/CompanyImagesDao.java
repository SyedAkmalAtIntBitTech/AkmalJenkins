/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.model.CompanyImages;
import java.util.List;

/**
 * <code> {@link CompanyDao} </code> Interface to get Company details
 * from Company table
 *
 * @author Ajit
 */
public interface CompanyImagesDao {

    public List<CompanyImages> getAllCompanyImages(Integer companyId);

    public Integer save(CompanyImages companyImage);

    public void delete(CompanyImages companyImages);

    public CompanyImages getById(Integer companyImagesId);
    
    
}
