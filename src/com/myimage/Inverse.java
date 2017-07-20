package com.myimage;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

/**
	 * 该类调用imagej进行图片取反操作
	 * @author st
	 *
	 */
public class Inverse {
	public static void main(String[] args){
		OpenDialog opDialog=new OpenDialog("");
		ImagePlus ming=new ImagePlus(opDialog.getPath());
		ming.setTitle("原始图像");
		ming.show();
		
		ImageProcessor ip1=ming.getProcessor();
		ImageProcessor ip2=ip1.duplicate();
		ip2.invert();
		ImagePlus ming2=new ImagePlus("after",ip2);
		ming2.show();
		IJ.save(ming2, "C:/Users/st/Desktop/smallArticle/待分割/"+opDialog.getFileName());
	}
}
