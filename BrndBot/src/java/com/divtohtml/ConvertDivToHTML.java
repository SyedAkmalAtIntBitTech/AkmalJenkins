package com.divtohtml;

import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.PhantomImageConverter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import org.apache.commons.io.FileUtils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class ConvertDivToHTML {
    private static final Logger logger = Logger.getLogger(ConvertDivToHTML.class.getName());
    
    private final static String divDelimiter = "SSS";
    private final static String styleKey = "style";
    private final static String idKey = "id";
    private final static String idSearchPattern = "*[id]";
    private final static String blockDetailsSearchPattern = "*[blockdetails]";
    private final static String tableDelimiter = "::";
    private ServletRequest servletRequest;

    private ConvertDivToHTML(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getResponsiveHTMLFromDiv(String divContent) throws IOException, Exception {

//        String divContent = getFile("/Users/AR/Documents/Projects/EclipseWorkspace/ConvertDivToHTMLTemplate/divhtml.html");
//
//        String tableContent = getFile("/Users/AR/Documents/Projects/EclipseWorkspace/ConvertDivToHTMLTemplate/Templatewithtable");

        StringBuilder newHtml = new StringBuilder();
        
        ArrayList<DivHTMLModel> divContentSplit = splitDivContent(divContent);
        SqlMethods sqlMethods = new SqlMethods();
        divContentSplit = sqlMethods.getHTMLforDivHTMLModelList(divContentSplit);
        for (DivHTMLModel divModel : divContentSplit) {
            newHtml.append(getHTMLForModel(divModel));
        }
        return newHtml.toString();
    }

    private String getHTMLForModel(DivHTMLModel divModel) throws Exception {
        HashMap<String, ArrayList<?>> divHashMap = getDivHashMap(divModel.getDivContent());
        return populatedTable(divHashMap, divModel.getHtmlFileContent());
    }
    
    private ArrayList<DivHTMLModel> splitDivContent(String divContent) {
        ArrayList<DivHTMLModel> divContentSplit = new ArrayList<DivHTMLModel>();
        Document doc = Jsoup.parse(divContent);
        Elements divElements = doc.select(blockDetailsSearchPattern);
        for(Element item : divElements) {
                DivHTMLModel divModel = new DivHTMLModel();
                divModel.setDivContent(item.toString());
                String modelId = item.attr("blockdetails");
                divModel.setModelId(modelId);
                divContentSplit.add(divModel);
        }
        return divContentSplit;
    }

    private String populatedTable(HashMap<String, ArrayList<?>> divHashMap, String tableContent) throws Exception {
        HashMap<String, String> replaceContentMap = new HashMap<String, String>();
        HashMap<String, String> replaceStyleMap = new HashMap<String, String>();
        StringBuilder backgroundImageWithBlocksHTML = new StringBuilder();

        Document doc = Jsoup.parse(tableContent);

        Elements divElements = doc.select(idSearchPattern);
        for (Element item : divElements) {
                String id = item.attr(idKey);
                logger.log(Level.INFO, id);
                String style = item.attr(styleKey);
                HashMap<String, String> styleMap = new HashMap<String, String>();
                styleMap = getKeyValuePair(style);
                if (BackgroundImageProperties.isBackgroundImage(id)) {

                        String[] orderOfLayeringId = id.split(tableDelimiter);
                        String backgroundImageId = orderOfLayeringId[0];

                        ArrayList<BackgroundImageProperties> backgroundImageProperties = (ArrayList<BackgroundImageProperties>) divHashMap
                                        .get(BackgroundImageProperties.class.getName());
                        for (BackgroundImageProperties backgroundImageProperty : backgroundImageProperties) {
                                String parsedId = backgroundImageProperty.getId().split(
                                                divDelimiter)[0];
                                if (backgroundImageId.equalsIgnoreCase(parsedId)) {
                                        //we need to check if its outside or inside
                                        item.attr(BackgroundImageProperties.backgroundURLKey,
                                                        backgroundImageProperty.getBackgroundURL());
                                }
                        }
                          //we dont need to set the style map since we know nothing's gonna change
                        //item.attr(styleKey, createKeyValuePair(styleMap));
                        
                        Elements backgroundImageTdElement = item.select("td"); 
                        Element tdOfBackgroundImage = backgroundImageTdElement.get(0);
                        backgroundImageWithBlocksHTML.append("<div "+tdOfBackgroundImage.attributes().toString()+"></div>");
                      
                        // This is the background image with new style

                        if (!StringUtil.isEmpty(id) && orderOfLayeringId.length > 2) {
                                ArrayList<BlockProperties> blockProperties = (ArrayList<BlockProperties>) divHashMap
                                                .get(BlockProperties.class.getName());
                                //zero was the backgroundImage
                                for (int i = 1; i < orderOfLayeringId.length; i++) {
                                        String blockId = orderOfLayeringId[i];
                                        StringBuilder colorBlocksDiv = new StringBuilder();

                                        for (BlockProperties block : blockProperties) {
                                                String parsedId = block.getId().split(divDelimiter)[0];
                                                HashMap<String, String> colorBlockStyleMap = new HashMap<String, String>();
                                                if (blockId.equalsIgnoreCase(parsedId)) {
                                                        colorBlockStyleMap.put(BlockProperties.backgroundColorKey,
                                                                        block.getBackgroundColor());
                                                        colorBlockStyleMap.put(BlockProperties.heightKey,
                                                                        block.getHeight());
                                                        colorBlockStyleMap.put(BlockProperties.widthKey,
                                                                        block.getWidth());
                                                        colorBlockStyleMap.put(BlockProperties.marginBottomKey,
                                                                        block.getMarginBottom());
                                                        colorBlockStyleMap.put(BlockProperties.marginLeftKey,
                                                                        block.getMarginLeft());
                                                        colorBlockStyleMap.put(BlockProperties.marginRightKey,
                                                                        block.getMarginRight());
                                                        colorBlockStyleMap.put(BlockProperties.marginTopKey,
                                                                        block.getMarginTop());
                                                        colorBlockStyleMap.put(BlockProperties.opacityKey,
                                                                        block.getOpacity());
                                                }
                                                //Generated new div tags for color blocks
                                                colorBlocksDiv.append("<div id=\""+blockId+"\" style=\""+createKeyValuePair(colorBlockStyleMap)+"\"></div>"+StringUtil.lineSeparator());

                                        }
                                        //merging all color blocks and background image code
                                        backgroundImageWithBlocksHTML.append(StringUtil.lineSeparator()+colorBlocksDiv);
                                        String filePath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator;
                                        PhantomImageConverter phantomImageConverter = new PhantomImageConverter(servletRequest.getServletContext(), filePath);
                                        File compressedBackgroundImageFile = phantomImageConverter.getImage(backgroundImageWithBlocksHTML.toString(), "400", "400", "0", "0");
                                        //Should create the compressed image out of this and replace the background with it.
                                        item.attr(BackgroundImageProperties.backgroundURLKey, servletRequest.getServerName()+compressedBackgroundImageFile.getPath());
                                }
                        }
                        //Now style map contains the latest line with newly created background image

                } else if (TextAreaProperties.isTextArea(id)) {
                        ArrayList<TextAreaProperties> textareaProperties = (ArrayList<TextAreaProperties>) divHashMap.get(TextAreaProperties.class.getName());
                        for (TextAreaProperties textArea : textareaProperties) {
                                String parsedId = textArea.getId().split(divDelimiter)[0];
                                if (id.equalsIgnoreCase(parsedId)) {
                                        styleMap.put(TextAreaProperties.fontColorKey, textArea.getFontColor());
                                        styleMap.put(TextAreaProperties.fontSizeKey, textArea.getFontSize());
                                        styleMap.put(TextAreaProperties.fontStyleKey, textArea.getFontStyle());
                                        styleMap.put(TextAreaProperties.fontWeightKey, textArea.getFontWeight());
                                        styleMap.put(TextAreaProperties.fontFamilyKey, textArea.getFontFamily());
                                        styleMap.put(TextAreaProperties.letterSpacingKey, textArea.getLetterSpacing());
                                        styleMap.put(TextAreaProperties.lineHeightKey, textArea.getLineHeight());
                                        styleMap.put(TextAreaProperties.textAlignmentKey, textArea.getTextAlignment());
                                        item.text(textArea.getTextValue());
                                }
                        }
                } else if (ButtonProperties.isButton(id)) {
                        ArrayList<ButtonProperties> buttonProperties = (ArrayList<ButtonProperties>) divHashMap.get(ButtonProperties.class.getName());
                        for (ButtonProperties button : buttonProperties) {
                                String parsedId = button.getId().split(divDelimiter)[0];
                                if (id.equalsIgnoreCase(parsedId)) {
                                        replaceContentMap.put(parsedId+"href", button.getURL());
                                }
                        }
                } else if (LogoProperties.isLogo(id)) {
                        ArrayList<LogoProperties> logoProperties = (ArrayList<LogoProperties>) divHashMap.get(LogoProperties.class.getName());
                        for (LogoProperties logo : logoProperties) {
                                String parsedId = logo.getId().split(divDelimiter)[0];
                                if (id.equalsIgnoreCase(parsedId)) {
                                        styleMap.put(LogoProperties.backgroundURLKey, logo.getBackgroundURL());
                                }
                        }
                }
                item.attr(styleKey, createKeyValuePair(styleMap));
                String cleanItemString = StringEscapeUtils.unescapeHtml4(item.toString());
                replaceStyleMap.put(id, cleanItemString);
        }
        String finalString = replaceExistingTds(replaceStyleMap, doc.toString());
        return finalString;
}

private String replaceExistingTds(HashMap<String, String> replaceStyleMap,
                String orgHtml) {
        String[] lines = orgHtml.split(StringUtil.lineSeparator());
        StringBuilder newHtml = new StringBuilder();
        for(String line : lines) {
                Iterator<Entry<String, String>> it = replaceStyleMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if (line.contains(pair.getKey().toString())) {
                        line = pair.getValue().toString();
                        it.remove();
                                break;
                        }
            }
            newHtml.append(line);
        }
        return newHtml.toString();
    }

    private HashMap<String, ArrayList<?>> getDivHashMap(String divContent) {

        HashMap<String, ArrayList<?>> map = new HashMap<String, ArrayList<?>>();
        ArrayList<TextAreaProperties> textAreaList = new ArrayList<TextAreaProperties>();
        ArrayList<ButtonProperties> buttonList = new ArrayList<ButtonProperties>();
        ArrayList<BlockProperties> blockList = new ArrayList<BlockProperties>();
        ArrayList<LogoProperties> logoList = new ArrayList<LogoProperties>();
        ArrayList<BackgroundImageProperties> backgroundImageList = new ArrayList<BackgroundImageProperties>();

        Document doc = Jsoup.parse(divContent);
        Elements divElements = doc.select(idSearchPattern);
        for (Element item : divElements) {
                String id = item.attr(idKey);
                String style = item.attr(styleKey);
                HashMap<String, String> styleMap = new HashMap<String, String>();
                styleMap = getKeyValuePair(style);

                if (TextAreaProperties.isTextArea(id)) {
                        TextAreaProperties textArea = new TextAreaProperties();
                        textArea.setId(id);
                        textArea.setFontColor(styleMap
                                        .get(TextAreaProperties.fontColorKey));
                        textArea.setFontSize(styleMap
                                        .get(TextAreaProperties.fontSizeKey));
                        textArea.setFontStyle(styleMap
                                        .get(TextAreaProperties.fontStyleKey));
                        textArea.setFontWeight(styleMap
                                        .get(TextAreaProperties.fontWeightKey));
                        textArea.setLetterSpacing(styleMap
                                        .get(TextAreaProperties.letterSpacingKey));
                        textArea.setLineHeight(styleMap
                                        .get(TextAreaProperties.lineHeightKey));
                        textArea.setTextAlignment(styleMap
                                        .get(TextAreaProperties.textAlignmentKey));
                        textArea.setFontFamily(styleMap.get(TextAreaProperties.fontFamilyKey));
                        textArea.setTextValue(item.text());
                        textAreaList.add(textArea);
                } else if (ButtonProperties.isButton(id)) {
                        ButtonProperties button = new ButtonProperties();
                        button.setId(id);

                        Elements links = doc.select("a");//Getting all a hrefs.
                        for (Element link : links)
                        {
                                List<Node> nodes = link.childNodes(); //Picking the button encapsulated in ahref
                                for (Node node : nodes) {
                                        String buttonId = node.attr(idKey); //getting the b
                                        if (buttonId.equalsIgnoreCase(id)) {
                                                button.setURL(link.attr("href"));
                                                buttonList.add(button);
                                                break;
                                        }
                                }
                        }
                } else if (BlockProperties.isColorBlock(id)) {
                        BlockProperties block = new BlockProperties();
                        block.setId(id);
                        block.setBackgroundColor(styleMap.get(BlockProperties.backgroundColorKey));
                        block.setHeight(styleMap.get(BlockProperties.heightKey));
                        block.setWidth(styleMap.get(BlockProperties.widthKey));
                        block.setMarginBottom(styleMap.get(BlockProperties.marginBottomKey));
                        block.setMarginLeft(styleMap.get(BlockProperties.marginLeftKey));
                        block.setMarginRight(styleMap.get(BlockProperties.marginRightKey));
                        block.setMarginTop(styleMap.get(BlockProperties.marginTopKey));
                        block.setOpacity(styleMap.get(BlockProperties.opacityKey));
                        blockList.add(block);
                } else if (LogoProperties.isLogo(id)) {
                        LogoProperties logo = new LogoProperties();
                        logo.setId(id);
                        logo.setBackgroundURL(styleMap.get(LogoProperties.backgroundURLKey));
                        logoList.add(logo);
                } else if (BackgroundImageProperties.isBackgroundImage(id)) {
                        BackgroundImageProperties backgroundImage = new BackgroundImageProperties();
                        backgroundImage.setId(id);
                        backgroundImage.setBackgroundURL(styleMap.get(BackgroundImageProperties.backgroundURLKey));
                        backgroundImageList.add(backgroundImage);
                }
                map.put(TextAreaProperties.class.getName(), textAreaList);
                map.put(ButtonProperties.class.getName(), buttonList);
                map.put(BlockProperties.class.getName(), blockList);
                map.put(LogoProperties.class.getName(), logoList);
                map.put(BackgroundImageProperties.class.getName(),
                                backgroundImageList);
        }
        return map;
    }

    private HashMap<String, String> getKeyValuePair(String rawValues) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (!StringUtil.isEmpty(rawValues)) {
                String[] entries = rawValues.split("; *(?![^\\[\\]]*\\])");
                for (String entry : entries) {
                        String[] keyValue = entry.split(":");
                        map.put(keyValue[0], keyValue[1]);
                }
        }
        return map;
    }

    private String createKeyValuePair(HashMap<String, String> map) {
        StringBuilder builder = new StringBuilder();

        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            builder.append(pair.getKey() + ": " + pair.getValue() + ";");
        }

        return builder.toString();
    }

    private String getFile(String fileName) throws IOException {
        String result = null;
        File file = new File(fileName);
        result = FileUtils.readFileToString(file, "UTF-8");
        return result;
    }
}
