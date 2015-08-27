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
import javax.servlet.ServletRequest;
import org.apache.commons.io.FileUtils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class ConvertDivToHTML {

    private static final Logger logger = Logger.getLogger(ConvertDivToHTML.class.getName());

    private final static String divDelimiter = "SSS";
    private final static String styleKey = "style";
    private final static String idKey = "id";
    private final static String idSearchPattern = "*[id]";
    private final static String blockDetailsSearchPattern = "*[blockdetails]";
    private final static String tableDelimiter = "::";
    private final ServletRequest servletRequest;

    public ConvertDivToHTML(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getResponsiveHTMLFromDiv(String divContent) throws IOException, Exception {

        divContent = getFile("/Users/AR/Documents/Projects/EclipseWorkspace/ConvertDivToHTMLTemplate/divhtml.html");

        String tableContent = getFile("/Users/AR/Documents/Projects/EclipseWorkspace/ConvertDivToHTMLTemplate/Templatewithtable");

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
        return populateTable(divHashMap, divModel.getHtmlFileContent(), divModel.getDivContent());
    }

    private ArrayList<DivHTMLModel> splitDivContent(String divContent) {
        ArrayList<DivHTMLModel> divContentSplit = new ArrayList<>();
        Document doc = Jsoup.parse(divContent);
        Elements divElements = doc.select(blockDetailsSearchPattern);
        for (Element item : divElements) {
            DivHTMLModel divModel = new DivHTMLModel();
            divModel.setDivContent(item.toString());
            String modelId = item.attr("blockdetails");
            divModel.setModelId(modelId);
            divContentSplit.add(divModel);
        }
        return divContentSplit;
    }

    private String populateTable(HashMap<String, ArrayList<?>> divHashMap, String tableContent, String divContent) throws Exception {
        HashMap<String, String> replaceLinesWithIdMap = new HashMap<String, String>();

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
                        if (!StringUtil.isEmpty(backgroundImageProperty.getBackgroundURL())) {
                            String picLocation = getColorBlocksForThisBackgroundImage(item, orderOfLayeringId, id, divHashMap, divContent);
                            styleMap.put(BackgroundImageProperties.backgroundURLKey, "url(" + picLocation + ")");
                            doc.getElementById(id).attr(styleKey, createKeyValuePair(styleMap));
                            break;
                        }
                    }
                }
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
                        doc.getElementById(id).attr(styleKey, createKeyValuePair(styleMap));
                        doc.getElementById(id).text(textArea.getTextValue());
                        break;
                    }
                }
            } else if (ButtonProperties.isButton(id)) {
                Elements links = doc.select("a");//Getting all a hrefs.
                for (Element link : links) {
                    List<Node> nodes = link.childNodes(); //Picking the button encapsulated in ahref
                    for (Node node : nodes) {
                        String buttonId = node.attr(idKey); //getting the button

                        ArrayList<ButtonProperties> buttonProperties = (ArrayList<ButtonProperties>) divHashMap.get(ButtonProperties.class.getName());
                        for (ButtonProperties button : buttonProperties) {
                            String parsedId = button.getId().split(divDelimiter)[0];
                            if (buttonId.equalsIgnoreCase(parsedId)) {
                                link.attr("href", button.getURL());
                                doc.getElementById(id).attr("href", button.getURL());
                                break;
                            }
                        }
                    }
                }
            } else if (LogoProperties.isLogo(id)) {
                ArrayList<LogoProperties> logoProperties = (ArrayList<LogoProperties>) divHashMap.get(LogoProperties.class.getName());
                for (LogoProperties logo : logoProperties) {
                    String parsedId = logo.getId().split(divDelimiter)[0];
                    if (id.equalsIgnoreCase(parsedId)) {
                        doc.getElementById(id).attr(LogoProperties.srcKey, logo.getBackgroundURL());
                        break;
                    }
                }
            }
        }
//        System.out.println(doc.toString());

        return doc.toString();
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
                for (Element link : links) {
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
                block.setTop(styleMap.get(BlockProperties.topKey));
                block.setLeft(styleMap.get(BlockProperties.leftKey));
                block.setBlockPosition(styleMap.get(BlockProperties.positionKey));
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
                String key = null;
                String value = null;
                String[] keyValue = null;

                if (entry.contains("background") && entry.contains("url")) {
                    keyValue = entry.split(":");
                    key = keyValue[0];
                    int startPosition = entry.indexOf("(") + "(".length();
                    int endPosition = entry.indexOf(")", startPosition);
                    value = entry.substring(startPosition, endPosition);
                } else {
                    keyValue = entry.split(":");
                    key = keyValue[0];
                    value = keyValue[1];
                }
                map.put(key, value);
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

    private String getColorBlocksForThisBackgroundImage(Element item, String[] orderOfLayeringId, String id, HashMap<String, ArrayList<?>> divHashMap, String divContent) throws Exception {
        File compressedBackgroundImageFile = null;
        Elements backgroundImageTdElement = item.select("td");
        Element tdOfBackgroundImage = backgroundImageTdElement.get(0);
        String backgroundImageStyle = tdOfBackgroundImage.attributes().get("style");
        StringBuilder backgroundImageWithBlocksHTML = new StringBuilder();

        Document doc = Jsoup.parse(divContent);
        Elements divElements = doc.select(idSearchPattern);
        for (Element divItem : divElements) {
            String divId = divItem.attr(idKey);
            String parsedDivId = divId.split(divDelimiter)[0];
            String backgroundImageId = orderOfLayeringId[0];

            System.out.println("divId==" + divId + "    id==" + id);
            if (parsedDivId.equalsIgnoreCase(backgroundImageId)) {

                backgroundImageWithBlocksHTML.append(divItem.toString());
                break;
            }
        }

        HashMap<String, String> backgroundImageStyleMap = getKeyValuePair(backgroundImageStyle);
        String backgroundImageWidth = backgroundImageStyleMap.get("width");
        String backgroundImageHeight = backgroundImageStyleMap.get("height");
        // This is the background image with new style

        if (!StringUtil.isEmpty(id) && orderOfLayeringId.length > 1) {
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
                        colorBlockStyleMap.put(BlockProperties.topKey,
                                block.getTop());
                        colorBlockStyleMap.put(BlockProperties.leftKey,
                                block.getLeft());
                        colorBlockStyleMap.put(BlockProperties.positionKey,
                                block.getBlockPosition());
                        colorBlockStyleMap.put(BlockProperties.opacityKey,
                                block.getOpacity());
                    }
                    //Generated new div tags for color blocks
                    colorBlocksDiv.append("<div id=\"" + blockId + "\" style=\"" + createKeyValuePair(colorBlockStyleMap) + "\"></div>" + StringUtil.lineSeparator());

                }
                //merging all color blocks and background image code
                backgroundImageWithBlocksHTML.append(StringUtil.lineSeparator() + colorBlocksDiv);

            }
            String filePath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator;

            PhantomImageConverter phantomImageConverter = new PhantomImageConverter(servletRequest.getServletContext(), filePath);
            compressedBackgroundImageFile = phantomImageConverter.getImage(backgroundImageWithBlocksHTML.toString(), backgroundImageWidth, backgroundImageHeight, "0", "0");
            //Should create the compressed image out of this and replace the background with it.

        }
        return servletRequest.getServerName() + compressedBackgroundImageFile.getPath();
    }
}
