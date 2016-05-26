package com.intbittech.utility.imagetopdf.image.converters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.intbittech.utility.imagetopdf.Image2BufferedImageConverter;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;

/**
 * 
 * @author Pavan Andhukuri
 * 
 *         Default converter provided to convert TIFF image to BufferedImages
 * 
 */
public class TIFF2BufferedImageConverter implements
		Image2BufferedImageConverter {

	@Override
	public ArrayList<BufferedImage> convert(String sourceFile)
			throws IOException {

		// Create an object to return
		ArrayList<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();

		// Load the TIFF image into a seekable stream
		FileSeekableStream stream = new FileSeekableStream(sourceFile);

		// Create an image decoder to decode images out of the tiff
		ImageDecoder dec = ImageCodec.createImageDecoder("tiff", stream, null);

		// Get number of images in the TIFF image
		int numOfPages = dec.getNumPages();

		for (int i = 0; i < numOfPages; i++) {
			// For each image found in the tiff file, decode the image
			RenderedImage image = dec.decodeAsRenderedImage(i);

			// Convert the rendered image into buffered image and add to array
			bufferedImages.add(convertRenderedImageToBufferedImage(image));
		}

		return bufferedImages;
	}

	/**
	 * Converts {@link RenderedImage} to {@link BufferedImage}. This piece is
	 * copied.. :)
	 * 
	 * @param renderedImage
	 * @return
	 */
	private BufferedImage convertRenderedImageToBufferedImage(
			RenderedImage renderedImage) {
		// If BufferedImage is provided, return
		if (renderedImage instanceof BufferedImage) {
			return (BufferedImage) renderedImage;
		}

		ColorModel cm = renderedImage.getColorModel();
		int width = renderedImage.getWidth();
		int height = renderedImage.getHeight();
		WritableRaster raster = cm
				.createCompatibleWritableRaster(width, height);
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		String[] keys = renderedImage.getPropertyNames();
		if (keys != null) {
			for (int i = 0; i < keys.length; i++) {
				properties.put(keys[i], renderedImage.getProperty(keys[i]));
			}
		}
		BufferedImage result = new BufferedImage(cm, raster,
				isAlphaPremultiplied, properties);
		renderedImage.copyData(raster);
		return result;
	}
}
