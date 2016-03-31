/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.AppConstants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class FileHandlerUtil {

    public static String saveAdminEmailTemplatesImage(String fileNameWithExtension, String base64ImageString) throws Throwable {

        String filePath = getAdminEmailTemplatesImageFilePath();
        String fileName = saveImage(fileNameWithExtension, base64ImageString, filePath);
        return fileName;
    }

    public static String saveAdminEmailBlockModelImage(String fileNameWithExtension, String base64ImageString) throws Throwable {

        String filePath = getAdminEmailBlockModelImageFilePath();
        String fileName = saveImage(fileNameWithExtension, base64ImageString, filePath);
        return fileName;
    }

    public static String saveImage(String fileNameWithExtension, String base64ImageString, String filePath) throws Throwable {
        String base64ImageData = extractOnlyBase64ImageData(base64ImageString);
        BufferedImage imageData = DataConverterUtil.convertBase64ToBufferedImage(base64ImageData);
        String fileName = getStorableFileNameWithExtension(fileNameWithExtension);
        writeImageFile(imageData, fileName, filePath);

        return fileName;
    }
    
    public static void deleteAdminEmailTemplatesImage(String fileNameWithExtension) throws Throwable {
        String filePath = getAdminEmailTemplatesImageFilePath();
        deleteImage(fileNameWithExtension, filePath);
    }
    
     public static void deleteAdminEmailBlockModelImage(String fileNameWithExtension) throws Throwable {
        String filePath = getAdminEmailBlockModelImageFilePath();
        deleteImage(fileNameWithExtension, filePath);
    }

    public static void deleteImage(String fileNameWithExtension, String filePath) throws Throwable {
        File dir = new File(filePath);
        File file = new File(dir, fileNameWithExtension);
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new Throwable("Security Manager does not allow for delete permission.");
        }

    }

    private static void writeImageFile(BufferedImage imageData, String fileNameWithExtension, String filePath) throws Throwable {
        File outputFileDir = new File(filePath);
        if (!outputFileDir.exists()) {
            try {
                if (!outputFileDir.mkdirs()) {
                    throw new Throwable("Not able to create directory.");
                }
            } catch (SecurityException se) {
                throw new Throwable("Security Manager does not allow for write permission.");
            }

        }
        File outputFile = new File(outputFileDir, fileNameWithExtension);
        try {
            if (!ImageIO.write(imageData, getFileType(fileNameWithExtension), outputFile)) {
                throw new Throwable("Not found any appropriate writer.");
            }

        } catch (IllegalArgumentException e) {
            throw new Throwable("Illegal arguments.");
        } catch (IOException e) {
            throw new Throwable("Image could not be written.");
        }
    }

    public static String getBaseUploadFilePath() {

        return AppConstants.BASE_UPLOAD_PATH;

    }

    public static String getBaseUploadAdminFilePath() {

        return getBaseUploadFilePath() + File.separator + "admin";

    }

    public static String getBaseUploadAdminImageFilePath() {

        return getBaseUploadAdminFilePath() + File.separator + "images";

    }

    public static String getAdminEmailTemplatesImageFilePath() {

        return getBaseUploadAdminImageFilePath() + File.separator + "emailtemplates";

    }

    public static String getAdminEmailBlockModelImageFilePath() {

        return getBaseUploadAdminImageFilePath() + File.separator + "emailblockmodels";

    }

    public static String getFileIdentifier() {

        return "" + System.currentTimeMillis();

    }

    public static String getStorableFileNameWithExtension(String srcFileNameWithExtension) {
        String arr[] = srcFileNameWithExtension.split("\\.");
        String fileName = "";
        if (arr.length == 2) {
            fileName = getFileIdentifier() + "." + arr[1];
        }

        return fileName;
    }

    private static String getFileType(String fileNameWithExtension) {
        String arr[] = fileNameWithExtension.split("\\.");
        String fileType = "";
        if (arr.length == 2) {
            fileType = arr[1];
        }

        return fileType;
    }

    private static String extractOnlyBase64ImageData(String base64ImageString) {
        String finaldata = base64ImageString.split(",")[1];
        return finaldata;
    }

}
