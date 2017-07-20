package com.digitalimage.util1;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;
/**
 * 练习3.1
 * @author st
 *
 */
public class Exer1Horizontal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenDialog openPng= new OpenDialog("wode");
		ImagePlus ip=new ImagePlus(openPng.getPath());
		ip.setTitle("原始图像");
		ip.show();
		ImageProcessor ips=ip.getProcessor();
		
		int w=ips.getWidth();
		int h=ips.getHeight();
		int[][] pixarr=new int[w][h];
		ImageProcessor ipt=ips.createProcessor(2*w, h);
		for(int i=0;i<=w;i++){
			for(int j=1;j<=h;j++){
				int p=ips.getPixel(i, j);
				//pixarr[i][j]=p;
				ipt.putPixel(i, j, p);
			}
		}
		for(int i=w;i<=2*w;i++){
			for(int j=0;j<=h;j++){
				int p=ips.getPixel(2*w-i, j);
				ipt.putPixel(i, j, p);
			}
		}
		ImagePlus ip2=new ImagePlus("水平翻转",ipt);

		
		ip2.show();
	}

}
