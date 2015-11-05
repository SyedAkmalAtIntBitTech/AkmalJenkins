package com.divtohtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProcessHTML {

    private final static String KStyleKey = "style";

    private final static String KElementText = "text";
    private final static String kElementId = "id";
    private final static String kElementGeneral = "general";
    private final static String kElementImage = "image";
    private final static String kElementLogo = "logo";

    private final static String KUserfontcolor = "userfontcolor";
    private final static String KUserbackgroundcolor = "userbackgroundcolor";
    private final static String KExternalvalue = "externalvalue";
    private final static String KUserbordercolor = "userbordercolor";
    private final static String KUserimage = "userimage";

    private final static String KExternalValueMindBody = "mindbody";

    private final static String KbackgroundColorKey = "background-color";
    private final static String KColorKey = "color";
    private final static String kBorderColorKey = "border-color";
    private final static String kBackgroundImageKey = "background";

    private final static String kElementSearchPattern = "*[" + kElementId
            + "]";

    private final String htmlContent;
    private final HashMap<String, String> colorHashmap;
    private final Map<String, String> externalSourceMapper;
    private final String userLogoURL;

    //color hash map should have keys with color1,color2
    public ProcessHTML(String htmlContent, HashMap<String, String> colorHashmap, Map<String, String> externalSourceMapper, String logoURL) {
        this.htmlContent = htmlContent;
        this.colorHashmap = colorHashmap;
        this.externalSourceMapper = externalSourceMapper;
        this.userLogoURL = logoURL;
    }

    public String processHTML() {
        ArrayList<HTMLModel> foundIdsList = new ArrayList<>();

        Document doc = Jsoup.parse(htmlContent);

        Elements divElements = doc.select(kElementSearchPattern);
        for (Element item : divElements) {
            String elementType = item.attr(kElementId);
            String style = item.attr(KStyleKey);
            HashMap<String, String> styleHashmap = getKeyValuePair(style);
            styleHashmap = changeColors(item, styleHashmap);

            if (elementType.contains(kElementGeneral)) {
                // Only colors so far.
            } else if (elementType.contains(KElementText)) {
                String userFontColor = item.attr(KUserfontcolor);
                if (!StringUtil.isEmpty(userFontColor)) {
                    styleHashmap.put(KColorKey, getUserColorForColorKey(userFontColor));
                }

                String externalValue = item.attr(KExternalvalue);
                if (!StringUtil.isEmpty(externalValue)) {
                    String text = getTextForExternalValue(externalValue);
                    item.text(text);
                }

            } else if (elementType.contains(kElementImage)) {
                String customImageKey = item.attr(KUserimage);
                if (!StringUtil.isEmpty(customImageKey)) {
                    item.attr(kBackgroundImageKey,
                            getUserBackgroundImage(customImageKey));
                }

            } else if (elementType.contains(kElementLogo)) {
                item.attr(kBackgroundImageKey, getUserLogoImage());
            }
            item.attr(KStyleKey, createKeyValuePair(styleHashmap));
            String cleanItemString = StringEscapeUtils.unescapeHtml4(item.toString());
            foundIdsList.add(new HTMLModel(elementType, cleanItemString));
        }
        String finalString = replaceExistingTag(foundIdsList, doc.toString());
        return finalString;
    }

//    private String replaceExistingTds(HashMap<String, String> replaceStyleMap, String orgHtml) {
//        String[] lines = orgHtml.split(StringUtil.lineSeparator());
//        StringBuilder newHtml = new StringBuilder();
//        for (String line : lines) {
//            Iterator<Entry<String, String>> it = replaceStyleMap.entrySet()
//                    .iterator();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                if (line.contains(pair.getKey().toString())) {
//                    line = pair.getValue().toString();
//                    it.remove();
//                    break;
//                }
//            }
//            newHtml.append(line);
//        }
//        return newHtml.toString();
//    }
    private String replaceExistingTag(HashMap<String, String> replaceStyleMap, String orgHtml) {
        String finalHtml = null;
        Document html = Jsoup.parse(orgHtml);
        Elements elements = html.getAllElements();
        finalHtml = orgHtml;
        Iterator<Entry<String, String>> it = replaceStyleMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            for (Element element : elements) {
                if (element.attr("id").equalsIgnoreCase(pair.getKey().toString())) {
                    finalHtml = finalHtml.replace(element.toString(), pair.getValue().toString());
                    break;
                }
            }
        }
        return finalHtml;
    }

    private String replaceExistingTag(final ArrayList<HTMLModel> foundIdsList, final String orgHtml) {
        String finalHtml = orgHtml;
        Document html = Jsoup.parse(orgHtml);
        Elements elements = html.getAllElements();
        for (HTMLModel model : foundIdsList) {
            for (Element element : elements) {
                if (element.attr("id").equalsIgnoreCase(model.getId())) {
                    finalHtml = finalHtml.replace(element.toString(), model.getHtml());
                    break;
                }
            }
        }
        return finalHtml;
    }

    private String getUserLogoImage() {
        String logoURL = "invalid or no logo url provided";
        if (!StringUtil.isEmpty(this.userLogoURL)) {
            logoURL = this.userLogoURL;
        }
        return logoURL;
    }

    private String getUserBackgroundImage(String customImageKey) {
        return getValueForExternalSource(customImageKey);
    }

    private String getTextForExternalValue(String externalValueKey) {
        return getValueForExternalSource(externalValueKey);
    }

    private String getValueForExternalSource(String externalValueKey) {
        String text = "Invalid or no proper key specified. Please check the rules.";
        if (!StringUtil.isEmpty(externalValueKey) && externalSourceMapper != null) {
            String[] nameSpace = externalValueKey.split("\\.");
            if (nameSpace.length == 3) {
                if (nameSpace[0].equalsIgnoreCase(KExternalValueMindBody)) {
                    text = (String) externalSourceMapper.get(nameSpace[2]);
                }
            }
        }
        return text;
    }

    private String getUserColorForColorKey(String userBackgroundColorKey) {
        String color = "#000000"; //Default black color if hashmap is not set
        if (!colorHashmap.isEmpty()) {
            if (colorHashmap.containsKey(userBackgroundColorKey) && !StringUtil.isEmpty(colorHashmap.get(userBackgroundColorKey))) {
                color = colorHashmap.get(userBackgroundColorKey);
            }
        }
        return color;
    }

    private HashMap<String, String> changeColors(Element item,
            HashMap<String, String> styleHashmap) {
        String userBackgroundColor = item.attr(KUserbackgroundcolor);
        if (!StringUtil.isEmpty(userBackgroundColor)) {
            styleHashmap.put(KbackgroundColorKey,
                    getUserColorForColorKey(userBackgroundColor));
        }

        String userBorderColor = item.attr(KUserbordercolor);
        if (!StringUtil.isEmpty(userBorderColor)) {
            styleHashmap.put(kBorderColorKey, getUserColorForColorKey(userBorderColor));
        }
        return styleHashmap;
    }

    private HashMap<String, String> getKeyValuePair(String rawValues) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (!StringUtil.isEmpty(rawValues)) {

            String[] entries = rawValues.split("; *(?![^\\[\\]]*\\])");
            for (String entry : entries) {
                String key = null;
                String value = null;
                String[] keyValue = null;
                if (!StringUtil.isEmpty(entry)) {

                    if (entry.contains("background") && entry.contains("url")) {
                        System.out.println(rawValues);
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
}
