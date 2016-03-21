/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbit.AppConstants;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class EmailUploadPhoto {

    private static final Logger logger = Logger.getLogger(EmailUploadPhoto.class.getName());

    private static final int maxFileSize = 30000 * 1024;
    private static final int maxMemSize = 30000 * 1024;
    
    public static String uploadFile(String uploadPath,
            HttpServletRequest request) throws FileUploadException, Exception {
        logger.info("EmailUploadPhoto::Entering EmailUploadPhoto#uploadFile");
        
        String fileName = null;
        logger.info("EmailUploadPhoto::Upload path without filename: " + uploadPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File(AppConstants.EMAIL_TEMPLATES));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        List fileItems = upload.parseRequest(request);

        Iterator i = fileItems.iterator();

        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!(fi.isFormField())) {
                fileName = fi.getName();
                if (!"".equals(fileName)) {
                    File uploadDir = new File(uploadPath);
                    boolean result = false;
                    if (!uploadDir.exists()) {
                        result = uploadDir.mkdirs();
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    logger.info("EmailUploadPhoto::Upload path with filename" + filePath);
                    File storeFile = new File(filePath);
                    fi.write(storeFile);
                    logger.info("EmailUploadPhoto::File Uploaded successfully");

                }else{
                    throw new IllegalArgumentException("Filename of uploded file cannot be empty");
                }
            }
        }
        return fileName;
    }

}
