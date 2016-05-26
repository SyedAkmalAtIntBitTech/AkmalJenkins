package com.intbittech.utility.imagetopdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Pavan Andhukuri
 * 
 *         Interface to be implemented by any image to BufferedImage converters.
 */
public interface Image2BufferedImageConverter {

	/**
	 * 
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public ArrayList<BufferedImage> convert(String sourceFile)
			throws IOException;
}
