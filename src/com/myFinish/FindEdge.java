package com.myFinish;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

public class FindEdge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenDialog openMybmp=new OpenDialog("��һ��RGBͼ��");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		ip.setTitle("ԭʼͼ��");
		ip.show();
		
		ImageProcessor ips=ip.getProcessor();
		
		ImageProcessor ipsnew=ips.duplicate();
		
		ipsnew.findEdges();
		
		ImagePlus ip2=new ImagePlus("��ֵ����", ipsnew);
		ip2.show();
		
	}

}
