package com.myimage;
/**
 * 不调用imagej的方法 对8位
 * @author st
 *
 */

import org.junit.Test;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

public class MyInverse {

	//@Test
	public static void main(String[] args){
		OpenDialog openPng= new OpenDialog("wode");
		ImagePlus ip=new ImagePlus(openPng.getPath());
		ip.show();
		ImageProcessor ips=ip.getProcessor();
		
		int w=ips.getWidth();
		int h=ips.getHeight();
		int[][] pixarr=new int[w][h];
		ImageProcessor ipt=ips.createProcessor(w, h);
		for(int i=0;i<=w;i++){
			for(int j=1;j<=h;j++){
				int p=ips.getPixel(i, j);
				//pixarr[i][j]=p;
				ipt.putPixel(i, j, 255);
			}
		}
		
		ImagePlus ip2=new ImagePlus("quanfan",ipt);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				System.out.print(pixarr[i][j]+"\t");
				
			}
			System.out.println();
		}
		
		ip2.show();
	}
	
	
}
