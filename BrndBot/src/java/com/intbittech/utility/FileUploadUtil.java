/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import com.intbittech.AppConstants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Mohamed Sanaulla
 */
public class FileUploadUtil {

    private static final Logger logger = Logger.getLogger(FileUploadUtil.class.getName());

    private static final int maxFileSize = 30000 * 1024;
    private static final int maxMemSize = 30000 * 1024;

    public static String uploadFile(String uploadPath,
            HttpServletRequest request) throws FileUploadException, Exception {
        logger.info("FileUploadUtil::Entering FileUploadUtil#uploadFile");

        String fileName = null;
        logger.info("FileUploadUtil::Upload path without filename: " + uploadPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(AppConstants.TMP_FOLDER));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!(fi.isFormField())) {
                // Get the uploaded file parameters
                fileName = fi.getName();
                if (!"".equals(fileName)) {
                    File uploadDir = new File(uploadPath);
                    boolean result = false;
                    if (!uploadDir.exists()) {
                        result = uploadDir.mkdirs();
                    }
                    // Write the file
                    String filePath = uploadPath + File.separator + fileName;
                    logger.info("FileUploadUtil::Upload path with filename" + filePath);
                    File storeFile = new File(filePath);
                    fi.write(storeFile);
                    logger.info("FileUploadUtil::File Uploaded successfully");

                } else {
                    throw new IllegalArgumentException("Filename of uploded file cannot be empty");
                }
            }
        }
        return fileName;
    }

    public static String uploadLogo(String uploadPath,
            HttpServletRequest request) throws FileUploadException, Exception {
        logger.info("FileUploadUtil::Entering FileUploadUtil#uploadFile");
        String fileName = null;
        logger.info("FileUploadUtil::Upload path without filename: " + uploadPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(AppConstants.TMP_FOLDER));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!(fi.isFormField())) {
                // Get the uploaded file parameters
                fileName = fi.getName();
                if (!"".equals(fileName)) {
                    File uploadDir = new File(uploadPath);
                    boolean result = false;
                    if (!uploadDir.exists()) {
                        result = uploadDir.mkdirs();
                    }
                    // Write the file
                    String filePath = uploadPath + File.separator + AppConstants.COMPANY_LOGO_FILENAME;
                    logger.info("FileUploadUtil::Upload path with filename" + filePath);
                    File storeFile = new File(filePath);
                    if (storeFile.exists()) {
                        storeFile.delete();
                    }
                    fi.write(storeFile);
                    logger.info("FileUploadUtil::File Uploaded successfully");

                } else {
                    throw new IllegalArgumentException("Filename of uploded file cannot be empty");
                }
            }
        }
        return fileName;
    }

    public static String uploadImageFromUrl(String FolderLocation, String folderName, Map<String, Object> requestBodyMap) {
        String imageName = null;
        BufferedImage image = null;
        try {
            URL url = new URL(requestBodyMap.get("imageUrl").toString());
            // read the url
            image = ImageIO.read(url);
            File file = new File(FolderLocation + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }
            imageName = FilenameUtils.getName(requestBodyMap.get("imageUrl").toString());
            logger.info(imageName);
            String filePath = FolderLocation + File.separator + folderName + File.separator+ imageName;
            File storeFile = new File(filePath);
            if(storeFile.exists())
            {
            storeFile.delete();
            }
            ImageIO.write(image, "png", storeFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageName;
    }

}
