package com.smallarticle;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import com.myDevide.AreaDevide2;
import com.myDevide.Group;
import com.myDevide.Point;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

public class AreaDevide {
	public static void main(String[] args){
		//String filePath="C:/Users/st/Desktop/smallArticle/AreaChoice.bmp";
		OpenDialog openIt=new OpenDialog("打开待分割图片");
		ImagePlus ip=new ImagePlus(openIt.getPath());
		ip.setTitle("原始图像");
		ip.show();
		ImageProcessor ip2=ip.getProcessor();
		File file=new File("C:/Users/st/Desktop/smallArticle/已经分割图形/"+openIt.getFileName().substring(0,openIt.getFileName().indexOf(".")));
		
		if(!file.exists()){
			file.mkdir();
		}
		int num=0;
		while (true) {
			ArrayList<Point> areas= areaDevide(ip2);
			if(areas==null){
				break;
			}
			ArrayList<ArrayList<Point>> allAreas=new ArrayList<>();
			if(areas.size()<50){
				for(int i=0;i<areas.size();i++){
					
					ip2.putPixel(areas.get(i).u,areas.get(i).v, 255);
				}
				continue;		
			}
			allAreas.add(areas);
			
			int newWidMax = 0,newHigMax = 0;
			int newWidMin = 1000,newHigMix = 1000;
			for(int i=0;i<areas.size();i++){

				if(areas.get(i).u>newWidMax){
					newWidMax=areas.get(i).u;
				}
				if(areas.get(i).u<newWidMin){
					newWidMin=areas.get(i).u;
				}
				if(areas.get(i).v<newHigMix){
					newHigMix=areas.get(i).v;
				}
				if(areas.get(i).v>newHigMax){
					newHigMax=areas.get(i).v;
				}
			}
			int width=newWidMax-newWidMin+1;
			int height=newHigMax-newHigMix+1;
			ImageProcessor ipdevide1=ip2.createProcessor(width, height);
			for(int u=0;u<width;u++){
				for(int v=0;v<height;v++){
					ipdevide1.putPixel(u, v, 255);
					
				}
			}
			for(int i=0;i<areas.size();i++){
				
				ipdevide1.putPixel(areas.get(i).u-newWidMin,areas.get(i).v-newHigMix, 0);
			}
			ImagePlus ip3=new ImagePlus("area1", ipdevide1);
			ip3.show();
			String savePath="C:/Users/st/Desktop/smallArticle/已经分割图形/"+openIt.getFileName().substring(0,openIt.getFileName().indexOf("."))+"/"+num+".bmp";
			System.out.println(savePath);
			IJ.save(ip3,savePath );
			num++;
			//将该区域直接化为白
			for(int i=0;i<areas.size();i++){
				
				ip2.putPixel(areas.get(i).u,areas.get(i).v, 255);
			}
		}
		
		//System.out.println(areas);
		//ArrayList<Group> groups=AreaDevide2.devideIt(iprocessor);

	}

	public static ArrayList<Point> areaDevide(ImageProcessor iprocessor) {
		// TODO Auto-generated method stub
		Point firstBlack=findFirstBlack(iprocessor);
		if(firstBlack==null){
			System.out.println("没有找到黑色点");
			return null;
		}
		//System.out.println(firstBlack.u+","+firstBlack.v);
		ArrayList<Point> firstAreas=new ArrayList<>();
		LinkedList<Point> q=new LinkedList<>();
		q.addFirst(firstBlack);
		while(!q.isEmpty()){
			Point p=q.removeLast();
			if(isBlack(p,iprocessor)&&isNew(p,firstAreas)&p.u>=0&&p.u<iprocessor.getWidth()&&p.v>=0&&p.v<iprocessor.getHeight()){
				firstAreas.add(p);
				q.addFirst(new Point(p.u+1, p.v+1));
				q.addFirst(new Point(p.u+1, p.v));
				q.addFirst(new Point(p.u, p.v+1));
				q.addFirst(new Point(p.u-1, p.v));
				q.addFirst(new Point(p.u, p.v-1));
				q.addFirst(new Point(p.u-1, p.v-1));
				q.addFirst(new Point(p.u-1, p.v+1));
				q.addFirst(new Point(p.u+1, p.v-1));
			}
		}
		return firstAreas;
	}

	private static boolean isNew(Point p, ArrayList<Point> firstAreas) {
		// TODO Auto-generated method stub
		for(Point p1:firstAreas){
			if(p1.u==p.u&&p1.v==p.v){
				return false;
			}
		}
		return true;
	}

	private static boolean isBlack(Point p, ImageProcessor iprocessor) {
		// TODO Auto-generated method stub
		if(iprocessor.getPixel(p.u, p.v)!=255){
			return true;
		}
		return false;
	}

	private static Point findFirstBlack(ImageProcessor iprocessor) {
		// TODO Auto-generated method stub
		for(int u=0;u<iprocessor.getWidth();u++){
			for(int v=0;v<iprocessor.getHeight();v++){
				if(iprocessor.getPixel(u, v)!=255){
					return new Point(u, v);
				}
			}
		}
		return null;
	}
}
