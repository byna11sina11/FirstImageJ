package com.digitalimage.util1;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;
/**
 * ��ϰ3.1
 * @author st
 *
 */
public class Exer2Frame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OpenDialog openPng= new OpenDialog("��...");
		ImagePlus ip=new ImagePlus(openPng.getPath());
		ip.setTitle("ԭʼͼ��");
		ip.show();
		ImageProcessor ips=ip.getProcessor();
		
		int w=ips.getWidth();
		int h=ips.getHeight();
		int[][] pixarr=new int[w][h];
		ImageProcessor ipt=ips.createProcessor(w, h);
		for(int i=0;i<=w;i++){
			for(int j=1;j<=h;j++){
				if(i<=10||j<=10||w-i<=10||w-j<=10){
					
					ipt.putPixel(i, j, 255);
				}else{
					int p=ips.getPixel(i, j);
					//pixarr[i][j]=p;
					ipt.putPixel(i, j, p);
				}

			}
		}
//		for(int i=w;i<=2*w;i++){
//			for(int j=0;j<=h;j++){
//				int p=ips.getPixel(2*w-i, j);
//				ipt.putPixel(i, j, p);
//			}
//		}
		ImagePlus ip2=new ImagePlus("���ӱ߿�",ipt);

		
		ip2.show();
	}

}
