/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.PhantomImageConverter;
import com.intbit.util.ServletUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.apache.struts.chain.commands.servlet.CreateAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    JSONArray json_font_names = new JSONArray();
    public JSONArray json_font_list = new JSONArray();
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
    
    public void addLayouts(Integer organization_id, Integer user_id, Integer category_id, String layout, String model, boolean email, boolean social, Integer sub_category_id, Integer brand_id, Integer block_id, String Style_image_name, String file_name,boolean isSocial,boolean isPrint,boolean isDownload,String emailHtmldata,String imagefilePath) throws SQLException, FileNotFoundException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_model (organization_id, user_id, category_id, layout_file_name, model_file_name, email, social, sub_category_id, brand_id, block_id,image_file_name, model_name,media,print,download,emailhtmldata) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
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
            prepared_statement.setBoolean(13, isSocial);
            prepared_statement.setBoolean(14, isPrint);
            prepared_statement.setBoolean(15, isDownload);
            prepared_statement.setString(16, emailHtmldata);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
            
        }
        
    }
     public void addEmailLayouts(Integer organization_id, Integer user_id, Integer category_id, String layout, String model, boolean email, boolean social, Integer sub_category_id, Integer brand_id, Integer block_id, String Style_image_name, String file_name,boolean isSocial,boolean isPrint,boolean isDownload,String emailHtmldata,String imagefilePath) throws SQLException, FileNotFoundException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        
            File file = new File(AppConstants.ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME + File.separator+ imagefilePath);
            FileInputStream fis = new FileInputStream(file);
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_model (organization_id, user_id, category_id, layout_file_name, model_file_name, email, social, sub_category_id, brand_id, block_id,image_file_name, model_name,media,print,download,emailhtmldata,email_style_image) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
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
            prepared_statement.setBoolean(13, isSocial);
            prepared_statement.setBoolean(14, isPrint);
            prepared_statement.setBoolean(15, isDownload);
            prepared_statement.setString(16, emailHtmldata);
            prepared_statement.setBinaryStream(17, fis, file.length());
            prepared_statement.executeUpdate();
            if (imagefilePath !=null){
                 file.delete();
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
            
        }
        
    }
    
    public void editModel(Integer model_id, String html_file_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_model"
                    + " SET html_file_name ='" + html_file_name + "' WHERE id='" + model_id + "'";
            
            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }
        
    }
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
    public JSONArray getFontList(Integer brandId){
        JSONArray json_font_sizes = new JSONArray();
                    
    try (Connection connection = ConnectionManager.getInstance().getConnection()){    
        Statement stmt3 = connection.createStatement();
        ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id="+brandId+"");
//  JSONArray json_font_names = new JSONArray();
        JSONObject json_font;
        if (rs3.next()){
                        Integer font_id1 = rs3.getInt("font_id1");
                        getFontsList(connection, font_id1);
                        Integer font_id2 = rs3.getInt("font_id2");
                        getFontsList(connection, font_id2);
                        Integer font_id3 = rs3.getInt("font_id3");
                        getFontsList(connection, font_id3);
                        Integer font_id4 = rs3.getInt("font_id4");
                        getFontsList(connection, font_id4);
                        Integer font_id5 = rs3.getInt("font_id5");
                        getFontsList(connection, font_id5);
                        Integer font_id6 = rs3.getInt("font_id6");
                        getFontsList(connection, font_id6);
                        Integer font_id7 = rs3.getInt("font_id7");
                        getFontsList(connection, font_id7);
                        Integer font_id8 = rs3.getInt("font_id8");
                        getFontsList(connection, font_id8);
                        Integer font_id9 = rs3.getInt("font_id9");
                        getFontsList(connection, font_id9);
                        Integer font_id10 = rs3.getInt("font_id10");
                        getFontsList(connection, font_id10);
                        Integer font_id11 = rs3.getInt("font_id11");
                        getFontsList(connection, font_id11);
                        Integer font_id12 = rs3.getInt("font_id12");
                        getFontsList(connection, font_id12);
                        Integer font_id13 = rs3.getInt("font_id13");
                        getFontsList(connection, font_id13);
                        Integer font_id14 = rs3.getInt("font_id14");
                        getFontsList(connection, font_id14);
                        Integer font_id15 = rs3.getInt("font_id15");
                        getFontsList(connection, font_id15);

                    }
        this.json_font_list = json_font_names;
    }catch (Exception e){
        logger.log(Level.SEVERE, "", e);
    }
    return json_font_names;
    }
    
    public void getFontsList(Connection connection, 
            Integer id)throws SQLException{
            ResultSet rs = null;
            Statement stmt = null;
        try {
            JSONObject json_font = new JSONObject();
            
            stmt = connection.createStatement();
            rs = stmt.executeQuery("Select * From tbl_font_family where id="+id+"");
                if (rs.next()){
                    String font_name5 = rs.getString("font_name");
                    String font_family_name5 = rs.getString("font_family_name");
                    font_family_name5 = font_family_name5 + "," + rs.getString("file_name");

                    json_font.put("font_name", font_name5);
                    json_font.put("font_family_name", font_family_name5);
                }
            json_font_names.add(json_font);
        }catch (Exception e){
            logger.log(Level.SEVERE, "", e);
        }finally {
            rs.close();
            stmt.close();
        }
    }    
    public String createImage(ServletRequest servletRequest,String layoutfilename, ServletContext servletContext, String modelname) throws SAXException {
//        throw new UnsupportedOperationException("Not supported yet.");
        
        String uploadPath = AppConstants.BASE_XML_UPLOAD_PATH;
        try {
            StringBuffer htmldata = new StringBuffer();
            String backgroundimage,border_radius, margin_left, Blend_mode, margin_top, blend_mode, opacity, width, height, background_repeat, background_size;
            String textData,font_family, huerotate, font_weight, Drop_shadow_color, font_style, text_align, font_size, font_color, text_shadow, line_height, letter_spacing, webkit_transform, h_shadow, v_shadow, Blur, id, defaulttext;
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
                id = modelElement.getAttribute("type");
                if (modelElement.getAttribute("tag").equalsIgnoreCase("image")) {
                    String filter = "";
                    if (modelElement.getAttribute("filterEnable").equalsIgnoreCase("false")) {
                        h_shadow = modelElement.getAttribute("H-shadow");
                        v_shadow = modelElement.getAttribute("V-shadow");
                        Blur = modelElement.getAttribute("blur");
                        Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                        filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                        logger.log(Level.INFO, filter);
                    } 
                    else if(modelElement.getAttribute("blur").equalsIgnoreCase("undefined")) {
                        filter = "blur(0px) grayscale(0%) sepia(0%) saturate(100%) hue-rotate(0deg) invert(0%) brightness(100%) contrast(100%)";  
                    }
                    else if(modelElement.getAttribute("filterEnable").equalsIgnoreCase("true")){
                        Blur = modelElement.getAttribute("blur");
                        grayscale = (Double.parseDouble(modelElement.getAttribute("grayscale"))) * 100;
                        sepia = (Double.parseDouble(modelElement.getAttribute("sepia"))) * 100;
                        saturate = (Double.parseDouble(modelElement.getAttribute("saturate"))) * 100;
                        huerotate = modelElement.getAttribute("huerotate");
                        invert = (Double.parseDouble(modelElement.getAttribute("invert"))) * 100;
                        brightness = (Double.parseDouble(modelElement.getAttribute("brightness"))) * 100;
                        contrast = (Double.parseDouble(modelElement.getAttribute("contrast"))) * 100;
                        
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
                    htmldata.append("<div id='" + id + "' style='position: absolute; width:" + width + "; height:" + height + "; background-blend-mode:" + Blend_mode + "; background-color:" + blend_mode +"; background-image:" + backgroundimage + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-repeat:" + background_repeat + "; background-position:" + "50% 50%" + "; -webkit-background-size:" + "cover" + ";-webkit-filter:" + filter + ";  opacity:" + opacity + ";'></div>");
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
                    font_family=modelElement.getAttribute("font-family").replace("+", " ") ;
                    
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
                    defaulttext = modelElement.getAttribute("defaulttext");
                    htmldata.append("<textarea id='" + id + "' style='position: absolute; font-weight:" + font_weight + ";font-family:"+font_family+"; width: "+width+"; height:"+height+"; font-style:" + font_style + "; resize:none ; outline:0 ;text-align:" + text_align + "; margin-left:" + margin_left +"px; margin-top:" + margin_top +"px; font-size:" + font_size + "; color:" + font_color + ";text-shadow: " + h_shadow + " " + v_shadow + " " + Blur + " " + text_shadow + "; line-height: " + line_height + ";letter-spacing: " + letter_spacing + "; opacity:" + opacity + ";-webkit-transform: rotate(" + webkit_transform + "deg);background-color: inherit; border:none;overflow:hidden;'>" + defaulttext + "</textarea>");
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("button")) {
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    backgroundimage = modelElement.getAttribute("src").replace("url(", "").replace(")", "");
                    String host = ServletUtil.getServerName(servletRequest.getServletContext());
                    backgroundimage = backgroundimage.replace("../", host);
                    htmldata.append("<img id='" + id + "' style='position: absolute; margin-left: " + margin_left + "; margin-top:" + margin_top + ";' src='"+backgroundimage+"'/>");
                } else if (modelElement.getAttribute("tag").equalsIgnoreCase("logo")) {
                  
                    String filter = "";
                    if (modelElement.getAttribute("filterEnable").equalsIgnoreCase("false")) {
                        h_shadow = modelElement.getAttribute("H-shadow");
                        v_shadow = modelElement.getAttribute("V-shadow");
                        Blur = modelElement.getAttribute("blur");
                        Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                        filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                        logger.log(Level.INFO, filter);
                    } 
                    else if(modelElement.getAttribute("blur").equalsIgnoreCase("undefined")) {
                       
                        filter = "blur(0px) grayscale(0%) sepia(0%) saturate(100%) hue-rotate(0deg) invert(0%) brightness(100%) contrast(100%)";  
                       
     
                    }
                    else if(modelElement.getAttribute("filterEnable").equalsIgnoreCase("true")){
                        Blur = modelElement.getAttribute("blur");
                        grayscale = (Double.parseDouble(modelElement.getAttribute("grayscale"))) * 100;
                        sepia = (Double.parseDouble(modelElement.getAttribute("sepia"))) * 100;
                        saturate = (Double.parseDouble(modelElement.getAttribute("saturate"))) * 100;
                        huerotate = modelElement.getAttribute("huerotate");
                        invert = (Double.parseDouble(modelElement.getAttribute("invert"))) * 100;
                        brightness = (Double.parseDouble(modelElement.getAttribute("brightness"))) * 100;
                        contrast = (Double.parseDouble(modelElement.getAttribute("contrast"))) * 100;
                        
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
                    htmldata.append("<div id='" + id + "' style='position: absolute; width:" + width + "; height:" + height + "; background-blend-mode:" + Blend_mode + "; background-color:" + blend_mode + "; background-image:" + backgroundimage + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-repeat:" + background_repeat + "; -webkit-background-size:" + background_size + ";-webkit-filter:" + filter + ";  opacity:" + opacity + "; '></div>");
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
                    border_radius=modelElement.getAttribute("border-radius");
                    width = modelElement.getAttribute("width");
                    height = modelElement.getAttribute("height");
                    opacity = modelElement.getAttribute("opacity");
                    String background_color = modelElement.getAttribute("background-color");
                    htmldata.append("<div id='" + id + "' style='position: absolute; width:" + width + "; height:" + height + ";opacity:" + opacity + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; background-color:" + background_color + "; -webkit-filter:" + filter + ";border-radius:"+border_radius+"; '></div>");
                }
                else if (modelElement.getAttribute("tag").equalsIgnoreCase("svg")) {
                    String filter = "";
                    
                    h_shadow = modelElement.getAttribute("H-shadow");
                    v_shadow = modelElement.getAttribute("V-shadow");
                    Blur = modelElement.getAttribute("blur");
                    Drop_shadow_color = modelElement.getAttribute("Drop-shadow-color");
                    filter = "drop-shadow("+ Drop_shadow_color+ " " +h_shadow + " " + v_shadow + " " + Blur + ")" ;
                    logger.log(Level.INFO, filter);
                    
                    margin_left = modelElement.getAttribute("x-co-ordinates");
                    margin_top = modelElement.getAttribute("y-co-ordinates");
                    border_radius=modelElement.getAttribute("border-radius");
                    width = modelElement.getAttribute("width");
                    height = modelElement.getAttribute("height");
                    opacity = modelElement.getAttribute("opacity");
                    String background_color = modelElement.getAttribute("background-color");
                    String filename = modelElement.getAttribute("filename");
                    File svgFolder = new File(AppConstants.BASE_TEMP_PATH);
                    String svgContent = FileUtils.readFileToString(new File(svgFolder+File.separator+filename), "UTF-8");
                    
                    htmldata.append("<div id='" + id + "' style='position: absolute; width:" + width + "; height:" + height + "; margin-left:" + margin_left + "px; margin-top:" + margin_top + "px; '>"+svgContent+"</div>");
                }
                
                logger.log(Level.INFO, htmldata.toString());
                logger.info(htmldata.toString());
            }
            PhantomImageConverter phantomImageConverter = new PhantomImageConverter(servletContext);
            
            File imagePngFile = phantomImageConverter.getImage(htmldata.toString(), json_font_list, containerWidth, containerHeight, "0", "0");
            Style_image_name = imagePngFile.getName();
            
            File htmlFolder = new File(AppConstants.LAYOUT_HTML_HOME);
            if (!htmlFolder.exists()) {
                htmlFolder.mkdirs();
            }
            FileUtils.writeStringToFile(new File(htmlFolder.getPath()+File.separator+modelname+".html"), htmldata.toString(), "UTF-8");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        return Style_image_name;
    }
      
}
