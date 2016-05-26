package com.intbittech.divtohtml;

public class ImageProperties extends BaseProperties {

    public static final String backgroundURLKey = "background";
    public static final String srcKey = "src";
    public static final String widthKey = "width";
    public static final String heightKey = "height";

    private String backgroundURL;
    private String width;
    private String height;

    public String getBackgroundURL() {
        return backgroundURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}
