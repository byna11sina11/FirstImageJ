package com.myFilter;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

/**
 * һ����������ƽ���˲���
 * @author st
 *
 */
public class Filter_Average {
	public static void main(String[] ages){
		OpenDialog openMybmp=new OpenDialog("��һ���Ҷ�ֵͼ��");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		ip.setTitle("ԭʼͼ��");
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
		
		
		ImagePlus ip2=new ImagePlus("�˲���", ipsnew);
		ip2.show();
	}
}
