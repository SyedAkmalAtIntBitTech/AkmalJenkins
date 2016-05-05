/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.AppConstants;
import com.intbittech.exception.ProcessFailed;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class FileHandlerUtil {
    
    public static String saveCompanyLogo(String filePath, String imageFile, String base64ImageString) throws Throwable {
        String fileNameWithExtension = imageFile;
        saveImageWithOrignalName(fileNameWithExtension, base64ImageString, filePath);
        return fileNameWithExtension;
    }
    
    public static String saveAdminGlobalFont(String filePathWithFileName, String fontFileType, String base64ImageString) throws Throwable {
        saveFontWithOrignalName(filePathWithFileName, base64ImageString);
        return "true";
    }
    
    public static String updateAdminGlobalFont(String oldFileNameWithExtension,String filePathWithFileName, String fontFileType, String base64ImageString) throws Throwable {
        if(oldFileNameWithExtension != null){
            deleteAdminGlobalFont(oldFileNameWithExtension);
             saveFontWithOrignalName(filePathWithFileName, base64ImageString);
        }
        saveFontWithOrignalName(filePathWithFileName, base64ImageString);
        return "true";
    }

    // CRUD operation for Admin Global image with File System.
    /**
     * 
     * @param imageFileName
     * @param imageFileType
     * @param base64ImageString
     * @return Stored File Name with extension i.e File name together with file type.
     * @throws Throwable 
     */
    public static String saveAdminGlobalImage(String imageFileName, String imageFileType, String base64ImageString) throws Throwable {
        String filePath = AppConstants.BASE_ADMIN_FONT_UPLOAD_PATH;
        String fileNameWithExtension = imageFileName + "." + imageFileType;
        saveImageWithOrignalName(fileNameWithExtension, base64ImageString, filePath);
        return fileNameWithExtension;
    }
    /**
     * 
     * @param oldFileNameWithExtension
     * @param imageFileName
     * @param imageFileType
     * @param base64ImageString
     * @return Updated File Name with extension i.e File name together with file type. Otherwise null if no file supplied for update. 
     * @throws Throwable 
     */
    public static String updateAdminGlobalImage(String oldFileNameWithExtension, String imageFileName, String imageFileType, String base64ImageString) throws Throwable {
        String fileNameWithExtension = null;
        if( imageFileType != null && base64ImageString != null){
            deleteAdminGlobalImage(oldFileNameWithExtension);
            fileNameWithExtension = saveAdminGlobalImage(imageFileName,imageFileType,base64ImageString);
        }
        else{
            // In case if user not uploaded any file but rename it.
           if( imageFileType == null || base64ImageString == null ){
               if(!getFileNameOnly(oldFileNameWithExtension).equals(imageFileName)){
                   fileNameWithExtension = imageFileName + getFileType(oldFileNameWithExtension);
                   if(!renameFile(oldFileNameWithExtension, fileNameWithExtension, getAdminGlobalImageFilePath())){
                       throw new Throwable();
                   }
               }
           }
        }
        return fileNameWithExtension;
    }
    
     public static void deleteAdminGlobalImage(String imageFileNameWithextension) throws Throwable {
       String filePath = getAdminGlobalImageFilePath();
       deleteFile(imageFileNameWithextension, filePath);
    }
     
     public static void deleteAdminGlobalFont(String fontFileNameWithextension) throws Throwable {
       String filePath = AppConstants.BASE_ADMIN_FONT_UPLOAD_PATH;
       deleteFile(fontFileNameWithextension, filePath);
    }

    //.....................................        End       ................................................................
  
    /**
     *
     * @param fileNameWithExtension
     * @param base64ImageString
     * @return
     * @throws Throwable
     */
    public static String saveAdminEmailTemplatesImage(String fileNameWithExtension, String base64ImageString) throws Throwable {

        String filePath = getAdminEmailTemplatesImageFilePath();
        String fileName = saveImageWithGeneratedName(fileNameWithExtension, base64ImageString, filePath);
        return fileName;
    }

    public static String saveAdminEmailBlockModelImage(String fileNameWithExtension, String base64ImageString) throws Throwable {

        String filePath = getAdminEmailBlockModelImageFilePath();
        String fileName = saveImageWithGeneratedName(fileNameWithExtension, base64ImageString, filePath);
        return fileName;
    }

    public static String saveImageWithGeneratedName(String fileNameWithExtension, String base64ImageString, String filePath) throws Throwable {
        String targetFileNameWithExtension = getStorableFileNameWithExtension(fileNameWithExtension);
        saveImageWithOrignalName(targetFileNameWithExtension, base64ImageString, filePath);
        return targetFileNameWithExtension;
    }
    
     public static void saveImageWithOrignalName(String fileNameWithExtension, String base64ImageString, String filePath) throws Throwable {
        String base64ImageData = extractOnlyBase64ImageData(base64ImageString);
        BufferedImage imageData = DataConverterUtil.convertBase64ToBufferedImage(base64ImageData);
        writeImageFile(imageData, fileNameWithExtension, filePath);
    }
     
    public static void saveFontWithOrignalName(String fileWithExtension, String base64ImageString) throws Throwable {
        String base64ImageData = extractOnlyBase64ImageData(base64ImageString);
        File outputFileDir = new File(AppConstants.BASE_ADMIN_FONT_UPLOAD_PATH);
        if (!outputFileDir.exists()) {
            try {
                if (!outputFileDir.mkdirs()) {
                    throw new Throwable("Not able to create directory.");
                }
            } catch (SecurityException se) {
                throw new Throwable("Security Manager does not allow for write permission.");
            }
        }
        File ttfFile = new File(fileWithExtension);
        try (FileOutputStream output = new FileOutputStream(ttfFile)) {
            byte[] binary = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64ImageData);
            output.write(binary);
        } catch (IOException e) {
            throw new Throwable(e.getMessage());
        }
    }

    public static void deleteAdminEmailTemplatesImage(String fileNameWithExtension) throws Throwable {
        String filePath = getAdminEmailTemplatesImageFilePath();
        deleteFile(fileNameWithExtension, filePath);
    }

    public static void deleteAdminEmailBlockModelImage(String fileNameWithExtension) throws Throwable {
        String filePath = getAdminEmailBlockModelImageFilePath();
        deleteFile(fileNameWithExtension, filePath);
    }

    public static void deleteFile(String fileNameWithExtension, String filePath) throws Throwable {
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

        return AppConstants.BASE_ADMIN_UPLOAD_PATH;

    }

    public static String getBaseUploadAdminImageFilePath() {

        return AppConstants.BASE_ADMIN_IMAGE_UPLOAD_PATH;

    }

    public static String getAdminGlobalImageFilePath() {

        return AppConstants.BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH;

    }

    public static String getAdminEmailTemplatesImageFilePath() {

        return AppConstants.BASE_ADMIN_EMAIL_TEMPLATE_IMAGE_UPLOAD_PATH;

    }

    public static String getAdminEmailBlockModelImageFilePath() {

        return AppConstants.BASE_ADMIN_EMAIL_TEMPLATE_IMAGE_UPLOAD_PATH;

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
    
      private static String getFileNameOnly(String fileNameWithExtension) {
        String arr[] = fileNameWithExtension.split("\\.");
        String fileName = "";
        if (arr.length == 2) {
            fileName = arr[0];
        }

        return fileName;
    }

    private static String extractOnlyBase64ImageData(String base64ImageString) {
        String finaldata = base64ImageString.split(",")[1];
        return finaldata;
    }
    
    
     private static boolean renameFile(String oldFileNameWithExtension, String newFileNameWithExtension, String filePath) throws Throwable{
        boolean sucess = false;
        File dir = new File(filePath);
        File oldFile = new File(dir, oldFileNameWithExtension);
        File newFile = new File(dir, newFileNameWithExtension);
        sucess = oldFile.renameTo(newFile);
        return sucess;
    }
     
    public static String getAdminGlobalImageBase64(String imageFileNameWithExtension) throws Throwable{
        String filePath = getAdminGlobalImageFilePath();
        return getBase64ImageData(imageFileNameWithExtension,filePath);
    } 
    
     public static String getAdminEmailTemplateImageBase64(String imageFileNameWithExtension) throws Throwable{
        String filePath = getAdminEmailTemplatesImageFilePath();
        return getBase64ImageData(imageFileNameWithExtension,filePath);
    } 
     
     public static String getAdminEmailBlockModelImageBase64(String imageFileNameWithExtension) throws Throwable{
        String filePath = getAdminEmailBlockModelImageFilePath();
        return getBase64ImageData(imageFileNameWithExtension,filePath);
    } 
     
     private static String getBase64ImageData(String fileNameWithExtension, String filePath) throws Throwable{
        File dir = new File(filePath);
        File imageFile = new File(dir, fileNameWithExtension);
        return DataConverterUtil.convertImageToBase64(imageFile);
    } 
    
    
}
