package com.myFilter;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

/**
 * 一个三乘三的平均滤波器
 * @author st
 *
 */
public class Filter_Average {
	public static void main(String[] ages){
		OpenDialog openMybmp=new OpenDialog("打开一个灰度值图像");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		ip.setTitle("原始图像");
		ip.show();
		
		ImageProcessor ips=ip.getProcessor();
		
		ImageProcessor ipsnew=ips.duplicate();
		
		double[][] filter={
				{1/9,1/9,1,9},
				{1/9,1/9,1,9},
				{1/9,1/9,1,9}
		};
		//System.out.println(filter[1][1]);
		for(int u=1;u<=ipsnew.getWidth()-2;u++){
			for(int v=1;v<=ipsnew.getHeight()-2;v++){
				int sum=0;
				for(int i=-1;i<=1;i++){
					for(int j=-1;j<=1;j++){
						sum+=ips.getPixel(u+i, v+j);
					}
				}
				ipsnew.putPixel(u, v, Math.round(sum/9));
			}
			
		}
		
		
		ImagePlus ip2=new ImagePlus("滤波后", ipsnew);
		ip2.show();
	}
}
