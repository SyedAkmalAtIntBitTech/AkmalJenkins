package com.imagetopdf.image.converters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.imagetopdf.Image2BufferedImageConverter;

/**
 * 
 * @author Pavan Andhukuri
 * 
 *         Default converter provided to convert JPEG and PNG images to
 *         BufferedImage
 * 
 */
public class PNGJPEG2BufferedImageConverter implements
		Image2BufferedImageConverter {

	@Override
	public ArrayList<BufferedImage> convert(String sourcePath)
			throws IOException {
		ArrayList<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();

		bufferedImages.add(ImageIO.read(new File(sourcePath)));
		return bufferedImages;
	}

}
