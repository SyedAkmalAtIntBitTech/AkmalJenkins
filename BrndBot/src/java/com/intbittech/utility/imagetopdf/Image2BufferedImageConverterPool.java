package com.intbittech.utility.imagetopdf;

import java.util.HashMap;

/**
 * 
 * @author Pavan Andhukuri
 * 
 *         Contains all the registered converters which are used to convert from
 *         Image to BufferedImage
 * 
 */
public class Image2BufferedImageConverterPool {

	// Hashmap which collects all the converters
	static HashMap<String, Image2BufferedImageConverter> converters = new HashMap<String, Image2BufferedImageConverter>();

	/**
	 * Register a new converter. The object passed in converter parameter should
	 * implement the interface {@link Image2BufferedImageConverter}
	 * 
	 * @param imageType
	 * @param converter
	 */
	public static void registerNewConverter(String imageType,
			Image2BufferedImageConverter converter) {
		converters.put(imageType, converter);
	}

	/**
	 * Returns a registered converter for a particular image type
	 * 
	 * @param imageType
	 * @return
	 */
	public static Image2BufferedImageConverter getConverter(String imageType) {
		return converters.get(imageType);
	}

}
