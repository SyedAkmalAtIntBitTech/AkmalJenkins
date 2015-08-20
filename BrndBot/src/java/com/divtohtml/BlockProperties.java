package com.divtohtml;

public class BlockProperties extends BaseProperties {

	public final static String backgroundColorKey = "background-color";
	public final static String opacityKey = "opacity";
	public final static String marginLeftKey = "margin-left";
	public final static String marginRightKey = "margin-right";
	public final static String marginTopKey = "margin-top";
	public final static String marginBottomKey = "margin-bottom";
	public final static String heightKey = "margin-height";
	public final static String widthKey = "margin-width";


	private String backgroundColor;
	private String opacity;
	private String marginLeft;
	private String marginRight;
	private String marginTop;
	private String marginBottom;
	private String height;
	private String width;

	/**
	 * @return the marginTop
	 */
	public String getMarginTop() {
		if(StringUtil.isEmpty(marginTop)) {
			return "0";
		}
		return marginTop;
	}

	/**
	 * @param marginTop the marginTop to set
	 */
	public void setMarginTop(String marginTop) {
		this.marginTop = marginTop;
	}

	/**
	 * @return the marginBottom
	 */
	public String getMarginBottom() {
		if(StringUtil.isEmpty(marginBottom)) {
			return "0";
		}
		return marginBottom;
	}

	/**
	 * @param marginBottom the marginBottom to set
	 */
	public void setMarginBottom(String marginBottom) {
		this.marginBottom = marginBottom;
	}	

	/**
	 * @return the marginLeft
	 */
	public String getMarginLeft() {
		if(StringUtil.isEmpty(marginLeft)) {
			return "0";
		}
		return marginLeft;
	}

	/**
	 * @param marginLeft the marginLeft to set
	 */
	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}

	/**
	 * @return the marginRight
	 */
	public String getMarginRight() {
		if(StringUtil.isEmpty(marginRight)) {
			return "0";
		}
		return marginRight;
	}

	/**
	 * @param marginRight the marginRight to set
	 */
	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		if(StringUtil.isEmpty(height)) {
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
		if(StringUtil.isEmpty(width)) {
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
		if(StringUtil.isEmpty(backgroundColor)) {
			return "white";
		}
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getOpacity() {
		if(StringUtil.isEmpty(opacity)) {
			return "0";
		}
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}

	public static boolean isColorBlock(String id) {
		if (id.contains("colorblock"))
			return true;
		return false;
	}

}
