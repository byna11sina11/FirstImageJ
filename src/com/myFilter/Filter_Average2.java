package com.myFilter;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.plugin.filter.Convolver;
import ij.plugin.filter.GaussianBlur;
import ij.process.ImageProcessor;

/**
 * 一个三乘三的特定矩阵的
 * @author st
 *
 */
public class Filter_Average2 {
	public static void main(String[] ages){
		OpenDialog openMybmp=new OpenDialog("打开一个灰度值图像");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		ip.setTitle("原始图像");
		ip.show();
		
		ImageProcessor ips=ip.getProcessor();
		
		ImageProcessor ipsnew=ips.duplicate();
		filter1(ipsnew);
		ImagePlus ip2=new ImagePlus("线性滤波后", ipsnew);
		ip2.show();
		
		ImageProcessor ipsnew2=ips.duplicate();
		filter2(ipsnew2);
		ImagePlus ip3=new ImagePlus("高斯滤波后", ipsnew);
		ip3.show();
	}
	/**
	 * 高斯滤波
	 * @param ipsnew2
	 */
	private static void filter2(ImageProcessor ipsnew2) {
		// TODO Auto-generated method stub
		GaussianBlur gb=new GaussianBlur();
		double r=2.5;
		gb.blur(ipsnew2, r);
	}
	/**
	 * 线性滤波
	 * @param ip
	 */
	private static void filter1(ImageProcessor ip){
		float[] filter={
				0.075f,0.125f,0.075f,
				0.125f,0.2f,  0.125f,
				0.075f,0.125f,0.075f
		};

		Convolver cv=new Convolver();
		cv.convolve(ip, filter, 3, 3);
	}
}
