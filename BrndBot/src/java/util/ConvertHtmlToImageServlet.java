/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import admin.controller.Layout;
import com.controller.ApplicationContextListener;
import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
//import com.imagetopdf.Images2PDF;
import com.imagetopdf.image.Image;
import com.imagetopdf.image.ImageTypes;
import com.intbit.AppConstants;
import com.intbit.PhantomImageConverter;
import com.intbit.dao.ScheduleDAO;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.json.simple.JSONArray;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.CCITTFactory;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.text.PDFTextStripper;
/**
 *
 * @author sandeep-kumar
 */
public class ConvertHtmlToImageServlet extends BrndBotBaseHttpServlet {
    private static final Logger logger = Logger.getLogger(ConvertHtmlToImageServlet.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response); 
        PDFParser parser;
        String parsedText = null;
        PDFTextStripper pdfStripper;
        PDDocument pdDoc;
        COSDocument cosDoc;
        PDDocumentInformation pdDocInfo;        
        try {
            String htmlString = request.getParameter("htmlString");
            String width = request.getParameter("containerWidth").replace("px", "");
            String height = request.getParameter("containerHeight").replace("px", "");
            String mediaType = request.getParameter("mediatype");
            getSqlMethodsInstance().session = request.getSession();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            Integer brandID = getSqlMethodsInstance().getBrandID(user_id);
            Layout layout = new Layout();
            JSONArray json_font_list = layout.getFontList(brandID);
            PhantomImageConverter phantomImageConverter = new PhantomImageConverter(getServletContext());
            
            File imagePngFile = phantomImageConverter.getImage(htmlString, json_font_list, width, height, "0", "0");
            
            String filename = imagePngFile.getName();
            
            if (mediaType.equalsIgnoreCase("downloadpdf")){
                
                String image2 = AppConstants.LAYOUT_IMAGES_HOME + File.separator + filename;
                                                   

                String pdf_file_name = filename.replace("png", "pdf");
                String pdf_save_path = AppConstants.PDF_FILES_PATH + File.separator + pdf_file_name;

                File uploadDir = new File(AppConstants.PDF_FILES_PATH);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                } 
                  PDDocument doc = null;
                  try
                    {
                        doc = new PDDocument();

//                        //we will add the image to the first page.
                        PDRectangle rec = new PDRectangle(0, 0, Float.parseFloat(width), Float.parseFloat(height));
                        PDDocument document = new PDDocument();
                        PDPage page = new PDPage(rec);
                        document.addPage(page);
                        doc.addPage(page);
                        
            PDImageXObject ximage;
            if( filename.toLowerCase().endsWith( ".jpg" ) )
            {
                ximage = JPEGFactory.createFromStream(doc, new FileInputStream(imagePngFile));
            }
            else if (filename.toLowerCase().endsWith(".tif") || filename.toLowerCase().endsWith(".tiff"))
            {
                ximage = CCITTFactory.createFromRandomAccess(doc, new RandomAccessFile(imagePngFile,"r"));
            }
            else if (filename.toLowerCase().endsWith(".gif") ||
                    filename.toLowerCase().endsWith(".bmp") ||
                    filename.toLowerCase().endsWith(".png"))
            {
                BufferedImage bim = ImageIO.read(imagePngFile);
                ximage = LosslessFactory.createFromImage(doc, bim);
            }
            else
            {
                throw new IOException( "Image type not supported: " + filename );
            }
            PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);

            //contentStream.drawImage(ximage, 20, 20 );
            // better method inspired by http://stackoverflow.com/a/22318681/535646
            float scale = 1f; // reduce this value if the image is too large
            contentStream.drawXObject(ximage, 0, 0, ximage.getWidth()*scale, ximage.getHeight()*scale);

            contentStream.close();
            doc.save( pdf_save_path );
                }catch (Exception e){
                    Logger.getLogger(ConvertHtmlToImageServlet.class.getName()).log(Level.SEVERE, null, e);
                }finally
                {
                    if( doc != null )
                    {
                        doc.close();
                    }
                }          
            getSqlMethodsInstance().setSocialPostHistory(user_id, "", false, false,null, null, pdf_file_name);

    //                deleteFile(file_name);
            response.setContentType("text/plain");
            response.getWriter().write(pdf_file_name);

            }else if (mediaType.equalsIgnoreCase("downloadimage")){
                getSqlMethodsInstance().setSocialPostHistory(user_id, "", false, false,null, filename, null);

                
                response.setContentType("text/plain");
                response.getWriter().write(filename);
                
            }else if (mediaType.equalsIgnoreCase("continue")){
                getSqlMethodsInstance().session.setAttribute("image_file_name", filename);
                System.err.println(filename);
                response.setContentType("text/plain");
                response.getWriter().write(filename);
            }
        } catch (Exception e) {
            Logger.getLogger(ConvertHtmlToImageServlet.class.getName()).log(Level.SEVERE, null, e);
            response.setContentType("text/html;charset=UTF-8");
            StringBuffer sb = new StringBuffer();
            PrintWriter out = response.getWriter();
            sb.append("<html><body><h2>");
            sb.append(e.getLocalizedMessage());
            sb.append("</body></html>");
            out.println(sb);
            out.close();
        } finally {
            getSqlMethodsInstance().closeConnection();
        }

    }

    public void deleteFile(File file){
        file.delete();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
