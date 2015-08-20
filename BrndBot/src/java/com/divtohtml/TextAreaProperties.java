package com.divtohtml;

public class TextAreaProperties  extends BaseProperties {
	
	public final static String fontSizeKey = "font-size";
	public final static String fontWeightKey = "font-weight";
	public final static  String fontColorKey = "color";
	public final static  String fontStyleKey = "font-style";
	public final static  String letterSpacingKey = "letter-spacing";
	public final static  String lineHeightKey = "line-height";
	public final static  String textAlignmentKey = "text-align";
	public final static  String fontFamilyKey = "font-family";
	
	
	private String fontSize;
	private String fontColor;
	private String fontStyle;
	private String fontWeight;
	private String letterSpacing;
	private String lineHeight;
	private String textAlignment;
	private String textValue;
	private String fontFamily;
	
	/**
	 * @return the fontSize
	 */
	public String getFontSize() {
		if (StringUtil.isEmpty(fontSize)) {
			return "10px";
		}
		return fontSize;
	}
	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	/**
	 * @return the fontColor
	 */
	public String getFontColor() {
		if (StringUtil.isEmpty(fontColor)) {
			return "black";
		}
		return fontColor;
	}
	/**
	 * @param fontColor the fontColor to set
	 */
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	/**
	 * @return the fontStyle
	 */
	public String getFontStyle() {
		if (StringUtil.isEmpty(fontSize)) {
			return "normal";
		}
		return fontStyle;
	}
	/**
	 * @param fontStyle the fontStyle to set
	 */
	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}
	/**
	 * @return the fontWeight
	 */
	public String getFontWeight() {
		if (StringUtil.isEmpty(fontWeight)) {
			return "normal";
		}
		return fontWeight;
	}
	/**
	 * @param fontWeight the fontWeight to set
	 */
	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}
	/**
	 * @return the letterSpacing
	 */
	public String getLetterSpacing() {
		if(StringUtil.isEmpty(letterSpacing)) {
			return "normal";
		}
		return letterSpacing;
	}
	/**
	 * @param letterSpacing the letterSpacing to set
	 */
	public void setLetterSpacing(String letterSpacing) {
		this.letterSpacing = letterSpacing;
	}
	/**
	 * @return the lineHeight
	 */
	public String getLineHeight() {
		if(StringUtil.isEmpty(lineHeight)) {
			return "normal";
		}
		return lineHeight;
	}
	/**
	 * @param lineHeight the lineHeight to set
	 */
	public void setLineHeight(String lineHeight) {
		this.lineHeight = lineHeight;
	}
	/**
	 * @return the textAlignment
	 */
	public String getTextAlignment() {
		if(StringUtil.isEmpty(textAlignment)) {
			return "left";
		}
		return textAlignment;
	}
	/**
	 * @param textAlignment the textAlignment to set
	 */
	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	
	public String getFontFamily() {
		if(StringUtil.isEmpty(fontFamily)) {
			return "\"Times New Roman\", Georgia, Serif";
		}
		return fontFamily;
	}
	
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	public static boolean isTextArea(String id) {
		id = id.toLowerCase();
		if(id.contains("body") || id.contains("header") || id.contains("footer")) return true; 
		else return false;
	}

}
