/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class DataConverterUtil {

    public static BufferedImage convertBase64ToBufferedImage(String base64ImageData) throws IOException {
        BufferedImage bufferedImage = null;
        byte[] imageByte;
        @SuppressWarnings("restriction")
        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(base64ImageData);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        bufferedImage = ImageIO.read(bis);
        bis.close();
        return bufferedImage;
    }

    public static String convertImageToBase64(File imageFile) throws IOException {
        String base64String = null;
        FileInputStream fis = new FileInputStream(imageFile);
        byte imageData[] = new byte[(int) imageFile.length()];
        fis.read(imageData);
        base64String = Base64.encodeBase64URLSafeString(imageData);
        return base64String;
    }
}
