package com.greenCenter;

import java.util.ArrayList;

import com.myDevide.Point;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

public class DoIt {
	public static void main(String[] args){
		OpenDialog openMybmp=new OpenDialog("打开一个RGB图像");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openMybmp.getPath().substring(beginIndex)
		ip.setTitle("原始图像");
		ip.show();
		System.out.println(openMybmp.getFileName());
		ColorProcessor cp=(ColorProcessor) ip.getProcessor();
		ImageProcessor iprocessor=cp.createProcessor(cp.getWidth(), cp.getHeight());
		Point firstGreen=CenterGreen.findFirstGreen(iprocessor);
		System.out.println(firstGreen);
		ArrayList<Point> points=CenterGreen.centerGreens(iprocessor, firstGreen);
		ImageProcessor drawCenterProcessor=iprocessor.duplicate();
		int[] white={255,255,255};
		for(int u=0;u<drawCenterProcessor.getWidth();u++){
			for(int v=0;v<drawCenterProcessor.getHeight();v++){
				drawCenterProcessor.putPixel(u, v, white);
			}
		}
		int[] green={0,255,0};
		for(int i=0;i<points.size();i++){
			drawCenterProcessor.putPixel(points.get(i).u, points.get(i).v, green);
		}
		ImagePlus ip2=new ImagePlus("gouqiang", drawCenterProcessor);
		ip2.show();
	}
}
