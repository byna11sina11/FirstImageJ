package com.smallarticle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myDevide.AreaDevide2;
import com.myDevide.Group;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ReadEdge {
	private static Workbook wb;

	public static void main(String[] args){
		String filepath="C:/Users/st/Desktop/smallArticle/AreaChoice.bmp";
		int[][] EdgeArray=readPic(filepath);
		
		//根据边缘，重绘图片
		ImagePlus iplus= new ImagePlus("E:/java图像处理/MyImage/RGB彩色图.jpg");
		ImageProcessor ip=iplus.getProcessor();
		ImageProcessor newip=ip.createProcessor(640, 480);
		
		
		//连通区域划分
		//利用
		int[] white={255,255,255};
		int[] black={0,0,0};
		for(int i=0;i<newip.getWidth();i++){
			for(int j=0;j<newip.getHeight();j++){
				
				if(EdgeArray[i][j]==1){
					newip.putPixel(i, j, black);
				}else{
					newip.putPixel(i, j, white);
				}
				
			}
		}
		ImagePlus ipl2=new ImagePlus("reDraw",newip);
		ipl2.show();
		long startTime=System.currentTimeMillis();   //获取开始时间
		 ArrayList<Group> groups = AreaDevide2.devideIt(newip);
		 long endTime=System.currentTimeMillis(); //获取结束时间
		 System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
		 
		int max=0;
		int index=0;
		System.out.println("可以分为"+groups.size()+"组");
		for(int i=0;i<groups.size();i++){

			if(groups.get(i).members.size()>max){
				max=groups.get(i).members.size();
				
				index=i;
			}
		}
		System.out.println("最大的是"+max+"个");
		System.out.println("角标为"+index);
		ImageProcessor ipnew2=newip.duplicate();
		for(int i=1;i<=ipnew2.getWidth();i++){
			for(int j=1;j<=ipnew2.getHeight();j++){
				ipnew2.putPixel(i, j, white);
				}
		}
		
//		for(int i=0;i<groups.get(index).members.size();i++){
//			int[] result=groups.get(index).members.get(i);
//			ipnew2.putPixel(result[0], result[1], 0);
//		}
		for(int i=0;i<groups.size();i++){
			int[] randomRgb={(int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random())};
			for(int j=0;j<groups.get(i).members.size();j++){
				int[] point=groups.get(i).members.get(j);
				
				ipnew2.putPixel(point[0], point[1], randomRgb);
			}
			
		}
		
		
		
		ImagePlus ipl3=new ImagePlus("myResult",ipnew2);
		
		ipl3.show();
	}

	
	
	
	private static int[][] readPic(String filepath){
		ImagePlus iplus=new ImagePlus(filepath);
		
		ImageProcessor ipProcessor=iplus.getProcessor();
		int[][] imageArray=new int[ipProcessor.getWidth()][ipProcessor.getHeight()];
		for(int u=0;u<ipProcessor.getWidth();u++){
			for(int v=0;v<ipProcessor.getHeight();v++){
				if(ipProcessor.getPixel(u, v)<100){
					imageArray[u][v]=1;
				}else{
					imageArray[u][v]=0;
				}
			}
		}
		return imageArray;
	}
}
