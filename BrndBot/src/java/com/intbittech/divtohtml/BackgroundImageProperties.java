package com.intbittech.divtohtml;

public class BackgroundImageProperties extends ImageProperties {

	public static boolean isBackgroundImage(String id) {
		if(id.contains("backgroundimage")) return true;
		return false;
	}

}
