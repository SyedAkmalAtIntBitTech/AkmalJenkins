/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import admin.controller.Categories;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblOrganization;
import com.intbit.marketing.service.MarketingCategoryService;
import com.intbit.util.ServletUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author intbit-6
 */
@Controller
public class MarketingCategoryController {
    private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
    @Autowired
    private MarketingCategoryService marketingCategoryService;
   @RequestMapping(value="/allmarketingCategory", method = RequestMethod.GET)
   public @ResponseBody String getAllMarketingCategories()throws ServletException, IOException, Throwable {
//       List<TblMarketingCategory> marketingCategorysList = null;
       String json = "test";
       JSONArray json_array = new JSONArray();
       JSONObject json_obj = new JSONObject();
       JSONObject jsonObject= new JSONObject();
       try {

                List<TblMarketingCategory> marketingCategorysList = marketingCategoryService.getAllMarketingCategory();
                     
                org.json.JSONArray marketingCategoryJsonArray = new org.json.JSONArray();
                Integer i = 1;
                for (TblMarketingCategory marketingCategoryobject : marketingCategorysList) {
                    org.json.JSONObject json_object = new org.json.JSONObject();
                   json_object.put("id", i);
                   json_object.put("category_id", marketingCategoryobject.getId());
                   json_object.put("name", marketingCategoryobject.getName());
                   json_object.put("order", marketingCategoryobject.getCategoryOrder().toString());
                   json_object.put("image", ServletUtil.bytesTo64(marketingCategoryobject.getImage()));
                   json_object.put("organization_id", marketingCategoryobject.getTblOrganization().getId());
                   marketingCategoryJsonArray.put(json_object);
                   i++;
                }
                   
                    jsonObject.put("marketingData",marketingCategoryJsonArray);  
                    System.out.println(marketingCategoryJsonArray);
                    return jsonObject.toString();
                                    
       }catch (Exception e){
           logger.log(Level.SEVERE, "Exception while getting the categories", e);
       }
       return jsonObject.toJSONString();
   }

   @RequestMapping(value="/deleteMarketingCategory", method = RequestMethod.POST)
   public @ResponseBody String deleteMarketingCategories(HttpServletRequest request, 
                HttpServletResponse response)throws ServletException, IOException, Throwable {

       try {

           Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
           Double category_id = (Double)requestBodyMap.get("category_id");
           
           marketingCategoryService.delete(category_id.intValue());
            
       }catch (Exception e){
           logger.log(Level.SEVERE, "Exception while deleting the categories", e);
       }
       return "true";
   }   
//   public ModelAndView getAllMarketingCategory() {
//		ModelAndView mav = new ModelAndView("admin/marketingcategories");
//		try {
//                    System.out.println("hii");
//			List<TblMarketingCategory> marketingCategorysList = marketingCategoryService.getAllMarketingCategory();
//			mav.addObject("MARKETING_DETAILS_RESULTS_KEY", marketingCategorysList);
//                        System.err.println("bye");
//		}
//		
//		catch (Throwable throwable) {
//			logger.log(Level.SEVERE, null, throwable);
//			mav.addObject("mainError", throwable.getMessage());
//		}
//		return mav;
//    
//    }
    @RequestMapping(value="/setMarketingCategory", method = RequestMethod.POST)
	public @ResponseBody void setMarketingCategory(HttpServletRequest request, 
                HttpServletResponse response)throws ServletException, IOException, Throwable {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        String filePath;
        String file_name, field_name;
        Categories categories;
        RequestDispatcher request_dispatcher;
        String category_name = "", organization_id = "", category_order = "";
        boolean check = false;
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        try {

            String uploadPath = AppConstants.MARKETING_ORG_CATEGORIES_HOME;

            // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

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

                out.println("<html>");
                out.println("<head>");
                out.println("<title>JSP File upload</title>");
                out.println("</head>");
                out.println("<body>");
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()) {
                        // Get the uploaded file parameters
                        field_name = fi.getFieldName();
                        if (field_name.equals("category_name")) {
                            category_name = fi.getString();
                        }
                        if (field_name.equals("organization")) {
                            organization_id = fi.getString();
                        }
                        if (field_name.equals("category_order")) {
                            category_order = fi.getString();
                        }
                        

                    } else {
                        
                    field_name = fi.getFieldName();
                    file_name = fi.getName();
                    InputStream fis = fi.getInputStream();
                    if (file_name != "") {
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }
                            file_name = category_name + "_" + file_name;
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            filePath = uploadPath + File.separator + file_name;
                            File storeFile = new File(filePath);
                            fi.write(storeFile);
                            
                            TblOrganization organization = new TblOrganization();
                            organization.setId(Integer.parseInt(organization_id));
                            TblMarketingCategory marketing_category = new TblMarketingCategory();
                            marketing_category.setId(0);
                            marketing_category.setName(category_name);
                            marketing_category.setCategoryOrder(Integer.parseInt(category_order));
                            marketing_category.setImage(ServletUtil.extractBytes2(filePath));
                            marketing_category.setTblOrganization(organization);
                            marketing_category.getTblOrganization().setId(Integer.parseInt(organization_id));
                            marketingCategoryService.save(marketing_category);
                            storeFile.delete();
                            out.println("Uploaded Filename: " + filePath + "<br>");
                            response.sendRedirect(request.getContextPath() + "/admin/marketingcategories.jsp");
                        }else {
                            response.sendRedirect(request.getContextPath() + "/admin/marketingcategories.jsp?exist=exist");
                        }
                    }
                }
            } else {
                out.write("true");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception while adding categories", ex);
        } finally {
            out.close();
        }
    
        }
}
