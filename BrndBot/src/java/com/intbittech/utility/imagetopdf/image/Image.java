package com.intbittech.utility.imagetopdf.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import com.intbittech.utility.imagetopdf.Image2BufferedImageConverterPool;

/**
 * 
 * @author Pavan Andhukuri
 * 
 *         Image Object.
 */
public class Image {

	String imageType;
	String sourcePath;
	ArrayList<BufferedImage> bufferedImages;

	@SuppressWarnings("unused")
	private Image() {

	}

	/**
	 * Adds a new image. The image type passed must have converter implemented
	 * and registered
	 * 
	 * @param sourcePath
	 * @param imageType
	 * @throws IOException
	 */
	public Image(String sourcePath, String imageType) throws IOException {
		this.imageType = imageType;
		this.sourcePath = sourcePath;

		// Convert the image to buffered images depending on the image type
		this.bufferedImages = Image2BufferedImageConverterPool.getConverter(
				imageType).convert(this.sourcePath);
	}

	/**
	 * Returns buffered images read from the image. Usually will be 1 for simple
	 * file formats
	 * 
	 * @return
	 */
	public ArrayList<BufferedImage> getBufferedImages() {
		return bufferedImages;
	}
}
