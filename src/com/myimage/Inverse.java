package com.myimage;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

/**
	 * �������imagej����ͼƬȡ������
	 * @author st
	 *
	 */
public class Inverse {
	public static void main(String[] args){
		OpenDialog opDialog=new OpenDialog("");
		ImagePlus ming=new ImagePlus(opDialog.getPath());
		ming.setTitle("ԭʼͼ��");
		ming.show();
		
		ImageProcessor ip1=ming.getProcessor();
		ImageProcessor ip2=ip1.duplicate();
		ip2.invert();
		ImagePlus ming2=new ImagePlus("after",ip2);
		ming2.show();
		IJ.save(ming2, "C:/Users/st/Desktop/smallArticle/���ָ�/"+opDialog.getFileName());
	}
}
