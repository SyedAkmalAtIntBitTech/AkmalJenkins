package com.intbittech.divtohtml;

public class BlockProperties extends BaseProperties {

    public final static String backgroundColorKey = "background-color";
    public final static String opacityKey = "opacity";
    public final static String topKey = "top";
    public final static String leftKey = "left";
    public final static String heightKey = "height";
    public final static String widthKey = "width";
    public final static String positionKey = "position";

    private String backgroundColor;
    private String opacity;
    private String left;
    private String top;
    private String height;
    private String width;
    private String blockPosition;

    public String getLeft() {
        if (StringUtil.isEmpty(left)) {
            left = "0";
        }
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        if (StringUtil.isEmpty(top)) {
            top = "0";
        }
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBlockPosition() {
        if (StringUtil.isEmpty(blockPosition)) {
            blockPosition = "absolute";
        }
        return blockPosition;
    }

    public void setBlockPosition(String blockPosition) {
        this.blockPosition = blockPosition;
    }

    /**
     * @return the height
     */
    public String getHeight() {
        if (StringUtil.isEmpty(height)) {
            return "0";
        }
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public String getWidth() {
        if (StringUtil.isEmpty(width)) {
            return "0";
        }
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @return the backgroundcolorkey
     */
    public static String getBackgroundcolorkey() {
        return backgroundColorKey;
    }

    public String getBackgroundColor() {
        if (StringUtil.isEmpty(backgroundColor)) {
            return "white";
        }
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getOpacity() {
        if (StringUtil.isEmpty(opacity)) {
            return "0";
        }
        return opacity;
    }

    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }

    public static boolean isColorBlock(String id) {
        if (id.contains("colorblock")) {
            return true;
        }
        return false;
    }

}
