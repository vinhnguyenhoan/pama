package com.lehanh.pama.ui.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class PamaResourceManager extends SWTResourceManager {

	public static Image getImage(String path, String key, int maxW, int maxH) throws FileNotFoundException, IOException {
		Image actualImg = getImage(new FileInputStream(path + key));
		
		Rectangle actualS = actualImg.getBounds();
		
		double ratio = 1;
		if (maxH <= 0) {
			ratio = (double) actualS.width / (double) maxW;
		} else if (maxW <= 0) {
			ratio = (double) actualS.height / (double) maxH;
		} else {
			double ratioByH = (double) actualS.height / (double) maxH;
			double ratioByW = (double) actualS.width / (double) maxW;
			ratio = Math.min(ratioByH, ratioByW);
		}
		int newW = (int) ((double) actualS.width / ratio);
		int newH = (int) ((double) actualS.height / ratio);
		
		Image result = new Image(actualImg.getDevice(), actualImg.getImageData().scaledTo(newW, newH));
		actualImg.dispose();
		return result;
	}
	
//	ImageData imgData = image.getImageData();
//    imgData.scaledTo(150, 150);
//    ImageLoader imageLoader = new ImageLoader();
//    imageLoader.data = new ImageData[] {imgData};
//    imageLoader.save(Variables.getStrResources() + "\\Pics\\" + a.getHerd_id() + ".jpg",SWT.IMAGE_JPEG);

//	double ratio = 1.0 * originalImage.getWidth() / originalImage.getHeight();
//	double ratio = (double) originalImage.getWidth() / originalImage.getHeight();
//	int height = (int) IMG_WIDTH/ratio;
//	int width = (int) IMG_HEIGHT*ratio;
}
