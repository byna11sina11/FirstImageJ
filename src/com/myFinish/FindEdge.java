package com.myFinish;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

public class FindEdge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenDialog openMybmp=new OpenDialog("打开一个RGB图像");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		ip.setTitle("原始图像");
		ip.show();
		
		ImageProcessor ips=ip.getProcessor();
		
		ImageProcessor ipsnew=ips.duplicate();
		
		ipsnew.findEdges();
		
		ImagePlus ip2=new ImagePlus("二值化后", ipsnew);
		ip2.show();
		
	}

}
