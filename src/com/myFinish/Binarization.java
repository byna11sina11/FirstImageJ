package com.myFinish;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

/**
 * ����������ֵ��ͼ��
 * �����ڣ��Ѿ��к�ɫĻ��ʱ�ڵ�����
 * @author st
 *
 */
public class Binarization {
	public static void main(String[] ages){
		OpenDialog openMybmp=new OpenDialog("��һ��RGBͼ��");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openMybmp.getPath().substring(beginIndex)
		ip.setTitle("ԭʼͼ��");
		ip.show();
		System.out.println(openMybmp.getFileName());
		ColorProcessor cp=(ColorProcessor) ip.getProcessor();
		ImagePlus ipTemp=new ImagePlus("C:/Users/st/Desktop/smallArticle/���ָ�/AreaChoice.bmp");
		ImageProcessor iprocessor=ipTemp.getProcessor();
		int[] rgbofMypic=new int[3];
//		int[] black={0,0,0};
//		int[] white={255,255,255};
		//rgbofMypic=ip
		for(int u=0;u<=cp.getWidth();u++){
			for(int v=0;v<=cp.getHeight();v++){
				//ȡ��ÿ�������ԭɫ
				rgbofMypic=cp.getPixel(u, v, rgbofMypic);
				int red=rgbofMypic[0];
				int green=rgbofMypic[1];
				int blue=rgbofMypic[2];
//				
				if(green>red&&blue<green){
					iprocessor.putPixel(u, v, 0);
				}else{
					iprocessor.putPixel(u, v, 255);
				}
				//��rgbΪ��ɫ��  ����Ϊ��ɫ����
//				if(blue==255&&v<200){
//					iprocessor.putPixel(u, v, white);
//				}
				//ǿ�����Ӱ�ᵼ��rgb����ɫ�е�B>R
//				if(blue-green<30&&v<200&&green>50){
//					iprocessor.putPixel(u, v, white);
//				}
//				if(rgbofMypic[1]<rgbofMypic[2]||rgbofMypic[1] <rgbofMypic[0]){
//					//iprocessor.putPixel(u, v, 255);
//					iprocessor.putPixel(u, v, black);
//					//System.out.println("heide");
//				}else{
//					iprocessor.putPixel(u, v, white);
//				}
			}
		}//for
		
		ImagePlus ip2=new ImagePlus("��ֵ����", iprocessor);
		IJ.save(ip2, "C:/Users/st/Desktop/smallArticle/���ָ�/"+openMybmp.getFileName());
		ip2.show();
		
	}
}
