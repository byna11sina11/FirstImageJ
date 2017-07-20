package com.myFinish;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

/**
 * 该类用来二值化图形
 * 作用于：已经有黑色幕布时期的作物
 * @author st
 *
 */
public class Binarization {
	public static void main(String[] ages){
		OpenDialog openMybmp=new OpenDialog("打开一个RGB图像");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openMybmp.getPath().substring(beginIndex)
		ip.setTitle("原始图像");
		ip.show();
		System.out.println(openMybmp.getFileName());
		ColorProcessor cp=(ColorProcessor) ip.getProcessor();
		ImagePlus ipTemp=new ImagePlus("C:/Users/st/Desktop/smallArticle/待分割/AreaChoice.bmp");
		ImageProcessor iprocessor=ipTemp.getProcessor();
		int[] rgbofMypic=new int[3];
//		int[] black={0,0,0};
//		int[] white={255,255,255};
		//rgbofMypic=ip
		for(int u=0;u<=cp.getWidth();u++){
			for(int v=0;v<=cp.getHeight();v++){
				//取出每个点的三原色
				rgbofMypic=cp.getPixel(u, v, rgbofMypic);
				int red=rgbofMypic[0];
				int green=rgbofMypic[1];
				int blue=rgbofMypic[2];
//				
				if(green>red&&blue<green){
					iprocessor.putPixel(u, v, 0);
				}else{
					iprocessor.putPixel(u, v, 255);
				}
				//将rgb为白色的  放置为绿色、、
//				if(blue==255&&v<200){
//					iprocessor.putPixel(u, v, white);
//				}
				//强光的阴影会导致rgb的绿色中的B>R
//				if(blue-green<30&&v<200&&green>50){
//					iprocessor.putPixel(u, v, white);
//				}
//				if(rgbofMypic[1]<rgbofMypic[2]||rgbofMypic[1] <rgbofMypic[0]){
//					//iprocessor.putPixel(u, v, 255);
//					iprocessor.putPixel(u, v, black);
//					//System.out.println("heide");
//				}else{
//					iprocessor.putPixel(u, v, white);
//				}
			}
		}//for
		
		ImagePlus ip2=new ImagePlus("二值化后", iprocessor);
		IJ.save(ip2, "C:/Users/st/Desktop/smallArticle/待分割/"+openMybmp.getFileName());
		ip2.show();
		
	}
}
