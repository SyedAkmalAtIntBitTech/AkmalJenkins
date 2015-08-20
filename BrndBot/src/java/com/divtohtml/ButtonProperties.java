package com.divtohtml;

public class ButtonProperties extends BaseProperties {

	public static final String URLKey = "background: url";

	private String URL;

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public static boolean isButton(String id) {
		if(id.contains("button")) return true;
		return false;
	}

}
