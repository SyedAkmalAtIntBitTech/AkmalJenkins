/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.PhantomImageConverter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author intbit
 */
public class Layout {
    private static final Logger logger = Logger.getLogger(Layout.class.getName());
    public SqlMethods sqlmethods;
    String Style_image_name = "";
    
    public Layout() {
        this.sqlmethods = new SqlMethods();
    }
    
    public String checkAvailability(String model_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        
        String check = "no";
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_model where model_name='" + model_name + "'";
            
            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = "yes";
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
            
        }
        
        return check;
    }
    
//    
//    public String getFileName(Integer brand_id) {
//        String query_string = "";
//        PreparedStatement prepared_statement = null;
//        ResultSet result_set = null;
//        
//        String fileName = "";
//        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
//            query_string = "Select * from tbl_brand_personality where id=" + brand_id + "";
//            
//            prepared_statement = connection.prepareStatement(query_string);
//            result_set = prepared_statement.executeQuery();
//            
//            if (result_set.next()) {
//                fileName = result_set.getString("image");
//            }
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "", e);
//        } finally {
//            sqlmethods.close(result_set, prepared_statement);
//            
//        }
//        
//        return fileName;
//    }
    
    public void addLayouts(Integer organization_id, Integer user_id, Integer category_id, String layout, String model, boolean email, boolean social, Integer sub_category_id, Integer brand_id, Integer block_id, String Style_image_name, String file_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_model (organization_id, user_id, category_id, layout_file_name, model_file_name, email, social, sub_category_id, brand_id, block_id,image_file_name, model_name) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setInt(2, user_id);
            prepared_statement.setInt(3, category_id);
            prepared_statement.setString(4, layout);
            prepared_statement.setString(5, model);
            prepared_statement.setBoolean(6, email);
            prepared_statement.setBoolean(7, social);
            prepared_statement.setInt(8, sub_category_id);
            prepared_statement.setInt(9, brand_id);
            prepared_statement.setInt(10, block_id);
            prepared_statement.setString(11, Style_image_name);
            prepared_statement.setString(12, file_name);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
            
        }
        
    }
    
//    public void editBrands(Integer brand_id, String brand_name, Integer look_id, String image) throws SQLException {
//        String query_string = "";
//        PreparedStatement prepared_statement = null;
//        ResultSet result_set = null;
//        
//        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
//            query_string = "UPDATE tbl_brand_personality"
//                    + " SET brand_name='" + brand_name + "', look_id=" + look_id + ", image='" + image + "'  WHERE id='" + brand_id + "'";
//            
//            prepared_statement = connection.prepareStatement(query_string);
//            prepared_statement.executeUpdate();
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "", e);
//        } finally {
//            sqlmethods.close(result_set, prepared_statement);
//            
//        }
//        
//    }
//    
//    public void deleteBrands(Integer org_id) throws SQLException {
//        String query_string = "";
//        PreparedStatement prepared_statement = null;
//        ResultSet result_set = null;
//        
//        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
//            query_string = "Delete From tbl_brand_personality"
//                    + " WHERE id='" + org_id + "'";
//            
//            prepared_statement = connection.prepareStatement(query_string);
//            prepared_statement.executeUpdate();
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "", e);
//        } finally {
//            sqlmethods.close(result_set, prepared_statement);
//            
//        }
//        
//    }
    
    public String createImage(String layoutfilename, ServletContext servletContext) throws SAXException {
//        throw new UnsupportedOperationException("Not supported yet.");
        String uploadPath = AppConstants.BASE_XML_UPLOAD_PATH;
        try {
            StringBuffer htmldata = new StringBuffer();
            String backgroundimage, margin_left, Blend_mode, margin_top, blend_mode, opacity, width, height, background_repeat, background_size;
            String textData, huerotate, font_weight, Drop_shadow_color, font_style, text_align, font_size, font_color, text_shadow, line_height, letter_spacing, webkit_transform, h_shadow, v_shadow, Blur;
            double grayscale, sepia, saturate, invert, brightness, contrast;
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            Document doc = docBuilder.parse(new File(uploadPath  + File.separator + layoutfilename + ".xml"));
            Element docEle = doc.getDocumentElement();
            // normalize text representation
            doc.getDocumentElement().normalize();
//            logger.log(Level.INFO, "Root element of the doc is " + doc.getDocumentElement().getNodeName());
            
            NodeList listOfModels = docEle.getElementsByTagName("element");
            NodeList listOfContent = docEle.getElementsByTagName("container");
            int totalModels = listOfModels.getLength();
             Node firstContentNode = listOfContent.item(0);
             Element modelElementContent = (Element) firstContentNode;
             String containerWidth=modelElementContent.getAttribute("Width");
            String containerHeight=modelElementContent.getAttribute("Height");
            logger.log(Level.INFO, "Total no of models : " + totalModels);
            
            for (int s = 0; s < listOfModels.getLength(); s++) {
                
                Node firstModelNode = listOfModels.item(s);
                Element modelElement = (Element) firstModelNode;
                
                logger.log(Level.INFO, modelElement.getAttribute("tag"));
                
                if (modelElement.getAttribute("tag").equalsIgnoreCase("image")) {
                    String filter = "";
                    logger.log(Level.INFO, modelElement.getAttribute("brightness"));
                    if (modelElement.getAttribute("blur")=="null" || modelElement.getAttribute("brightness") == " ") {
                        h_shadow = modelElement.getAttribute("h-shadow");
                        v_shadow = modelElement.getAttribute("v-shadow");
                        Blur = modelElement.getAttribute("blur");
                        Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                        filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                        logger.log(Level.INFO, filter);
                    } else {
                        Blur = modelElement.getAttribute("blur");
                        grayscale = (Double.parseDouble(modelElement.getAttribute("grayscale"))) * 100;
                        sepia = (Double.parseDouble(modelElement.getAttribute("sepia"))) * 100;
                        saturate = (Double.parseDouble(modelElement.getAttribute("saturate"))) * 100;
                        huerotate = modelElement.getAttribute("huerotate");
                        invert = (Double.parseDouble(modelElement.getAttribute("invert"))) * 100;
                        brightness = (Double.parseDouble(modelElement.getAttribute("brightness"))) * 100;
                        contrast = (Double.parseDouble(modelElement.getAttribute("contrast"))) * 100;
//                        blur(2px) grayscale(2%) sepia(2%) saturate(102%) hue-rotate(1deg) invert(1%) brightness(100%) contrast(101%) 
                        
                        filter = "blur(" + Blur + ") grayscale(" + (int) grayscale + "%) sepia(" + (int) sepia + "%) saturate(" + (int) saturate + "%) hue-rotate(" + huerotate + ") invert(" + (int) invert + "%) brightness(" + (int) brightness + "%) contrast(" + (int) contrast + "%)";                        
                        logger.log(Level.INFO, filter);
                    }
                    
                    backgroundimage = modelElement.getAttribute("background-image");
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    Blend_mode = modelElement.getAttribute("Blend");
                    blend_mode = modelElement.getAttribute("blend-background-color");
                    opacity = modelElement.getAttribute("opacity");
                    width = modelElement.getAttribute("width");
                    height = modelElement.getAttribute("height");
                    background_repeat = "no-repeat";
                    background_size = "contain";
                    htmldata.append("<div style='position: absolute; width:" + width + "; height:" + height + "; background-blend-mode:" + Blend_mode + "; background-color:" + blend_mode + "; background-image:" + backgroundimage + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-repeat:" + background_repeat + "; -webkit-background-size:" + background_size + ";-webkit-filter:" + filter + "; '></div>");
                    logger.log(Level.INFO, htmldata.toString());
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("text")) {
                    textData = modelElement.getAttribute("type");
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    width=modelElement.getAttribute("width");
                    height=modelElement.getAttribute("height");
                    font_weight = modelElement.getAttribute("font-weight");
                    font_style = modelElement.getAttribute("font-style");                    
                    text_align = modelElement.getAttribute("text-align");
                    font_size = modelElement.getAttribute("font-size");                    
                    font_color = modelElement.getAttribute("font-color");
                    h_shadow = modelElement.getAttribute("H-shadow");
                    v_shadow = modelElement.getAttribute("V-shadow");
                    Blur = modelElement.getAttribute("Blur");
                    text_shadow = modelElement.getAttribute("text-shadow");
                    line_height = modelElement.getAttribute("line-height");
                    letter_spacing = modelElement.getAttribute("letter-spacing");
                    opacity = modelElement.getAttribute("opacity");
                    webkit_transform = modelElement.getAttribute("webkit-transform");
                    
                    htmldata.append("<textarea style='position: absolute; font-weight:" + font_weight + "; width: "+width+"; height:"+height+"; font-style:" + font_style + "; resize:none ; outline:0 ;text-align:" + text_align + "; margin-left:" + margin_left +"px; margin-top:" + margin_top +"px; font-size:" + font_size + "; color:" + font_color + ";text-shadow: " + h_shadow + " " + v_shadow + " " + Blur + " " + text_shadow + "; line-height: " + line_height + ";letter-spacing: " + letter_spacing + "; opacity:" + opacity + ";-webkit-transform: rotate(" + webkit_transform + "deg);background-color: inherit; border:none;'>" + textData + "</textarea>");
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("button")) {
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    backgroundimage = modelElement.getAttribute("src").replace("url(", "").replace(")", "");
                    htmldata.append("<img style='position: absolute; margin-left: " + margin_left + "; margin-top:" + margin_top + ";' src='"+backgroundimage+"'/>");
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("logo")) {
                    String filter = "";
                    logger.log(Level.INFO, modelElement.getAttribute("brightness"));
                    if (modelElement.getAttribute("blur")=="null" || modelElement.getAttribute("brightness") == " ") {
                        h_shadow = modelElement.getAttribute("h-shadow");
                        v_shadow = modelElement.getAttribute("v-shadow");
                        Blur = modelElement.getAttribute("blur");
                        Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                        filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                        logger.log(Level.INFO, filter);
                    } else {
                        Blur = modelElement.getAttribute("blur");
                        grayscale = (Double.parseDouble(modelElement.getAttribute("grayscale"))) * 100;
                        sepia = (Double.parseDouble(modelElement.getAttribute("sepia"))) * 100;
                        saturate = (Double.parseDouble(modelElement.getAttribute("saturate"))) * 100;
                        huerotate = modelElement.getAttribute("huerotate");
                        invert = (Double.parseDouble(modelElement.getAttribute("invert"))) * 100;
                        brightness = (Double.parseDouble(modelElement.getAttribute("brightness"))) * 100;
                        contrast = (Double.parseDouble(modelElement.getAttribute("contrast"))) * 100;
//                        blur(2px) grayscale(2%) sepia(2%) saturate(102%) hue-rotate(1deg) invert(1%) brightness(100%) contrast(101%) 
                        
                        filter = "blur(" + Blur + ") grayscale(" + (int) grayscale + "%) sepia(" + (int) sepia + "%) saturate(" + (int) saturate + "%) hue-rotate(" + huerotate + ") invert(" + (int) invert + "%) brightness(" + (int) brightness + "%) contrast(" + (int) contrast + "%)";                        
                        logger.log(Level.INFO, filter);
                    }
                    
                    backgroundimage = modelElement.getAttribute("background-image");
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    Blend_mode = modelElement.getAttribute("Blend");
                    blend_mode = modelElement.getAttribute("blend-background-color");
                    opacity = modelElement.getAttribute("opacity");
                    width = modelElement.getAttribute("width");
                    height = modelElement.getAttribute("height");
                    background_repeat = "no-repeat";
                    background_size = "contain";
                    htmldata.append("<div style='position: absolute; width:" + width + "; height:" + height + "; background-blend-mode:" + Blend_mode + "; background-color:" + blend_mode + "; background-image:" + backgroundimage + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-repeat:" + background_repeat + "; -webkit-background-size:" + background_size + ";-webkit-filter:" + filter + "; '></div>");
                    logger.log(Level.INFO, htmldata.toString());
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("block")) {
                    String filter = "";
                    
                    h_shadow = modelElement.getAttribute("H-shadow");
                    v_shadow = modelElement.getAttribute("V-shadow");
                    Blur = modelElement.getAttribute("blur");
                    Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                    filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                    logger.log(Level.INFO, filter);
                    
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    
                    width = modelElement.getAttribute("width");
                    height = modelElement.getAttribute("height");
                    opacity = modelElement.getAttribute("opacity");
                    String background_color = modelElement.getAttribute("background-color");
                    htmldata.append("<div style='position: absolute; width:" + width + "; height:" + height + ";opacity:" + opacity + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-color:" + background_color + "; -webkit-filter:" + filter + ";  '></div>");
                }
                
                logger.log(Level.INFO, htmldata.toString());
            }
            PhantomImageConverter phantomImageConverter = new PhantomImageConverter(servletContext);
            File imagePngFile = phantomImageConverter.getImage(htmldata.toString(),containerWidth, containerHeight, "0", "0");
            Style_image_name = imagePngFile.getName();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        return Style_image_name;
    }
    
}
