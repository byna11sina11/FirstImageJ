package com.smallarticle;

import java.util.ArrayList;

import com.myDevide.Point;

import ij.ImagePlus;

public class ScannerArea {
	public static void main(String[] art) throws InterruptedException{
		String filePath="C:/Users/st/Desktop/smallArticle/AreaChoice.bmp";
		ImagePlus mImagePlus=new ImagePlus(filePath);
		mImagePlus.show();
//		Thread.sleep(1000);
//		mImagePlus.close();
		
		//ArrayList<Point> pp;
		ArrayList<ArrayList<Point>> areas=new ArrayList<>();
		//ArrayList<Point[]> areas=new ArrayList<>();
	}
}
