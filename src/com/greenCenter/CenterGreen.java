package com.greenCenter;

import java.util.ArrayList;

import com.myDevide.Point;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

public class CenterGreen {
	public static void main(String[] ages){
		//OpenDialog openMybmp=new OpenDialog("打开一个RGB图像");
		//ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openybmp.getPath().substring(beginIndex)
		ImagePlus iplus= new ImagePlus("E:/java图像处理/MyImage/RGB彩色图.jpg");
		ImageProcessor ip=iplus.getProcessor();
		ImageProcessor newip=ip.createProcessor(12,12);
		int[][] pic={
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,0,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,0,0,1,0},
				{0,0,0,0,0,0,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,0,1,1,0,0},
				{0,0,0,0,0,0,0,1,0,0,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}
		};
		int[] green={0,255,0};
		int[] black={0,0,0};
		for(int i=0;i<newip.getWidth();i++){
			for(int j=0;j<newip.getHeight();j++){
				if(pic[i][j]==0){
					newip.putPixel(i, j,black );
				}else{
					newip.putPixel(i, j,green);
				}
				
			}
		}
		ImagePlus ipnew=new ImagePlus("yuanshi", newip);
		
		ipnew.show();
		//System.out.println(openMybmp.getFileName());
		//ColorProcessor cp=(ColorProcessor) ip.getProcessor();
		Point firstGreen= findFirstGreen(newip);
		ArrayList<Point> centerGreens=centerGreens(newip,firstGreen);
		System.out.println(centerGreens);
//		ImagePlus ip2=new ImagePlus("二值化后", iprocessor);
//		IJ.save(ip2, "C:/Users/st/Desktop/imagedeal/小论文/二值化/"+openMybmp.getFileName());
//		ip2.show();
	}
	public  static ArrayList<Point> centerGreens(ImageProcessor newip, Point firstBlack) {
		// TODO Auto-generated method stub
		//ImageProcessor newip=newip.createProcessor(newip.getWidth(), newip.getHeight());
		ArrayList<Point> leaves=new ArrayList<>();//用来存储叶子；
		ArrayList<Point> belongArea=new ArrayList<>();//用来储存已经有的节点
		//find the first black point;
		//Point CenterPoint=new Point(newip.getWidth()/2,newip.getHeight()/2);
		Point LeafPoint=new Point(0, 0);
		//Point firstBlack= findFirstBlack(CenterPoint,newip);
		//System.out.println(firstBlack.u+","+firstBlack.v);
		leaves.add(firstBlack);
		//find the leafpiont
		while(!leaves.isEmpty()){
			LeafPoint=leaves.get(leaves.size()-1);//取出leaves中的最后一个节点
			System.out.println("leafs="+LeafPoint.u+","+LeafPoint.v);
			//System.out.println("当前叶子节点为"+LeafPoint.u+","+LeafPoint.v);
			belongArea.add(LeafPoint);
			//判断八领域中的黑点；
			for(int p=-1;p<=1;p++){
				for(int q=-1;q<=1;q++){
					//int pix=newip.getPixel(LeafPoint.u+p, LeafPoint.v+q);
					//System.out.println("当前叶子节点为"+LeafPoint.u+","+LeafPoint.v+";"+"计算相对位置为"+p+","+q);
					System.out.println(LeafPoint.u+p+","+LeafPoint.v+q);
					if(isGreen(newip, LeafPoint.u+p, LeafPoint.v+q)&&(Math.abs(p)+Math.abs(q))!=0){//八领域中找到了黑点
						//System.out.println("计算相对位置为"+p+","+q+"为黑色点");
						//判断该点是否已在当前容器中
						boolean isIn=false;
						for(int k=0;k<belongArea.size();k++){
							Point p2=belongArea.get(k);
							if(p2.u==LeafPoint.u+p&&p2.v==LeafPoint.v+q){//该点已在区域里
								isIn=true;
							}
						}
						if(!isIn){//该点不在区域里，则判断是否已在叶子里
							boolean isInLeaves=false;
							for(int k=0;k<leaves.size();k++){
								Point p2=leaves.get(k);
								if(p2.u==LeafPoint.u+p&&p2.v==LeafPoint.v+q){//该点已在叶子里
									isInLeaves=true;
								}
							}
							if(!isInLeaves){
								leaves.add(new Point(LeafPoint.u+p, LeafPoint.v+q));
								//System.out.println("添加的是"+LeafPoint.u+"+"+p+","+LeafPoint.u+"+"+q+";"+(LeafPoint.u+p)+","+(LeafPoint.u+q));
							}
							
						}
					}
				}
			}
			
			leaves.remove(LeafPoint);
			
		}
		
//		System.out.println(belongArea.size());
//		for(int k=0;k<belongArea.size();k++){
//			System.out.println(belongArea.get(k).u+","+belongArea.get(k).v);
//		}
		
		return belongArea;
	}
	public  static Point findFirstGreen(ImageProcessor iprocessor) {
		// TODO Auto-generated method stub
		//ImageProcessor iprocessor=iprocessor.createProcessor(iprocessor.getWidth(), iprocessor.getHeight());
		//Point center=new Point
		Point center=new Point(iprocessor.getWidth()/2,iprocessor.getHeight()/2);
		for(int d=1;d<=iprocessor.getWidth()/2;d++){
			for(int p=-d;p<=d;p++){
				for(int q=-d;q<=d;q++){
					if(isGreen(iprocessor,center.u+p,center.v+q)){
						return(new Point(center.u+p, center.v+q));
					}
				}
			}
		}
		return null;
	}
	public static boolean isGreen(ImageProcessor cp, int i, int j){
		//ImageProcessor iprocessor=cp.createProcessor(cp.getWidth(), cp.getHeight());
		int[] rgbofMypic=new int[3];
//		int[] black={0,0,0};
//		int[] white={255,255,255};
		//rgbofMypic=ip
		rgbofMypic=cp.getPixel(i, j, rgbofMypic);
		int red=rgbofMypic[0];
		int green=rgbofMypic[1];
		int blue=rgbofMypic[2];
		
		if(green>red&&blue<green){
			return true;
		}else{
			return false;
		}
		
//		for(int u=0;u<=cp.getWidth();u++){
//			for(int v=0;v<=cp.getHeight();v++){
//				//取出每个点的三原色
//				rgbofMypic=cp.getPixel(u, v, rgbofMypic);
//				int red=rgbofMypic[0];
//				int green=rgbofMypic[1];
//				int blue=rgbofMypic[2];
////				
//				if(green>red&&blue<green){
//					iprocessor.putPixel(u, v, white);
//				}else{
//					iprocessor.putPixel(u, v, black);
//				}
//				//将rgb为白色的  放置为绿色、、
//				if(blue==255&&v<200){
//					iprocessor.putPixel(u, v, white);
//				}
//				//强光的阴影会导致rgb的绿色中的B>R
//				if(blue-green<30&&v<200&&green>50){
//					iprocessor.putPixel(u, v, white);
//				}
////				if(rgbofMypic[1]<rgbofMypic[2]||rgbofMypic[1] <rgbofMypic[0]){
////					//iprocessor.putPixel(u, v, 255);
////					iprocessor.putPixel(u, v, black);
////					//System.out.println("heide");
////				}else{
////					iprocessor.putPixel(u, v, white);
////				}
//			}
//		}//for
//		return true;
	}
}
