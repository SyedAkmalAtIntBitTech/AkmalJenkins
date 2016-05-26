package com.intbittech.divtohtml;

public class LogoProperties extends ImageProperties {

	public static boolean isLogo(String id) {
		if(id.contains("logo")) return true;
		return false;
	}

}
