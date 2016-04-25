/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.ApplicationContextListener;
import com.controller.GenerateHashPassword;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.Utility;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    private Logger logger = Logger.getLogger(DownloadController.class);

    @RequestMapping(value = "/HTML/{fileName}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public void forgotSendEMail(@PathVariable(value = "fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (fileName == null || "".equals(fileName)) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Name of the image is missing");
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            //response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "inline;filename=" + fileName);
            String xmlPath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + fileName;
            File file = new File(xmlPath);
            response.setContentLength((int) file.length());
            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                try (OutputStream out = response.getOutputStream()) {
                    int i;
                    while ((i = fileInputStream.read(buf)) >= 0) {
                        out.write(buf, 0, i);
                    }
                    out.flush();
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
