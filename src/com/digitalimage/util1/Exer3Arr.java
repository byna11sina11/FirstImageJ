package com.digitalimage.util1;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

public class Exer3Arr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenDialog opd=new OpenDialog("打开...");
		ImagePlus imp1=new ImagePlus(opd.getPath());
		imp1.setTitle("无限移动中...");
		imp1.show();
		ImageProcessor ip1=imp1.getProcessor();
		ImageProcessor ip2=ip1.duplicate();
		for(int i=0;i<=ip1.getWidth();i++){
//			//当前列至黑色
//			for(int j=0;j<=ip1.getHeight();j++){
//				ip1.putPixel(i, j, 0);
//			}
			//其前边的画失去的
			for(int k=0;k<=i;k++){
				for (int j = 0; j < ip1.getWidth(); j++) {
					int p=ip2.getPixel(ip1.getWidth()-i+k, j);
					ip1.putPixel(k, j, p);
				}
			}
			//其后列重绘
			for(int k=i+1;k<=ip1.getWidth();k++){
				for (int j = 0; j < ip1.getWidth(); j++) {
					int p=ip2.getPixel(k-i+1, j);
					ip1.putPixel(k, j, p);
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//无限移动
//			i=(i==ip1.getWidth())?i:0;
			if(i==ip1.getWidth()){
				i=0;
			}
			imp1.updateAndDraw();
		}

	}

}
