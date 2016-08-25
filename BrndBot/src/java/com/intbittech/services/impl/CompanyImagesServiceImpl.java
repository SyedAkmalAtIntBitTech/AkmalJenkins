/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyImagesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyImages;
import com.intbittech.services.CompanyImagesService;
import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CompanyImagesServiceImpl implements CompanyImagesService {

    @Autowired
    private CompanyImagesDao companyImagesDao;
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<CompanyImages> getAllImagesForCompany(Integer companyId) throws ProcessFailed {
        List<CompanyImages> companyList = companyImagesDao.getAllCompanyImages(companyId);
        if (companyList == null) {
            throw new ProcessFailed(messageSource.getMessage("company_list_not_found", new String[]{}, Locale.US));
        }
        return companyList;
    }

    @Override
    public Integer save(CompanyImages companyImage) throws ProcessFailed {
        return companyImagesDao.save(companyImage);
    }

    @Override
    public void delete(Integer companyImagesId) throws ProcessFailed {
        CompanyImages companyImages = companyImagesDao.getById(companyImagesId);
        Integer companyId = companyImages.getFkCompanyId().getCompanyId();
        String image_name = companyImages.getImageName();
        String deletePath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + image_name;
        File deleteFile = new File(deletePath);
        deleteFile.delete();
        companyImagesDao.delete(companyImages);
    }

    @Override
    public void save(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPath(Integer companyId) {
        return AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId +File.separator+AppConstants.GALLERY_FOLDERNAME;
    }

    @Override
    public String getLink(String fileName, Integer companyId, String imageURL) {
        return ""+imageURL+"downloadImage?imageType=GALLERY&companyId=" + companyId + "&imageName=" + fileName;
    }

    public CompanyImages getCompanyImagesByCompany(Company company) throws ProcessFailed {
        CompanyImages companyImages = companyImagesDao.getCompanyImagesByCompany(company);
        if(companyImages == null)
        {
             throw new ProcessFailed(messageSource.getMessage("company_not_found",new String[]{}, Locale.US));
        }
              return companyImages;
    }
    
}
