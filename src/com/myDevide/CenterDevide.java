package com.myDevide;

import java.util.ArrayList;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class CenterDevide {
	public static void main(String[] agrs){
		//构建测试图片
		ImagePlus iplus= new ImagePlus("E:/java图像处理/MyImage/8位灰度图像.png");
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
		for(int i=0;i<newip.getWidth();i++){
			for(int j=0;j<newip.getHeight();j++){
				if(pic[i][j]==0){
					newip.putPixel(i, j, 255);
				}else{
					newip.putPixel(i, j, 0);
				}
				
			}
		}
		centerDevideIt(newip);
//		ImagePlus iplus2=new ImagePlus("draw it",newip);
//		iplus2.show();
	}

	public static ArrayList<Point> centerDevideIt(ImageProcessor newip) {
		// TODO Auto-generated method stub
		
		ArrayList<Point> leaves=new ArrayList<>();//用来存储叶子；
		ArrayList<Point> belongArea=new ArrayList<>();//用来储存已经有的节点
		//find the first black point;
		Point CenterPoint=new Point(newip.getWidth()/2,newip.getHeight()/2);
		Point LeafPoint=new Point(0, 0);
		Point firstBlack= findFirstBlack(CenterPoint,newip);
		//System.out.println(firstBlack.u+","+firstBlack.v);
		leaves.add(firstBlack);
		//find the leafpiont
		while(!leaves.isEmpty()){
			LeafPoint=leaves.get(leaves.size()-1);//取出leaves中的最后一个节点
			//System.out.println("当前叶子节点为"+LeafPoint.u+","+LeafPoint.v);
			belongArea.add(LeafPoint);
			//判断八领域中的黑点；
			for(int p=-3;p<=3;p++){
				for(int q=-3;q<=3;q++){
					int pix=newip.getPixel(LeafPoint.u+p, LeafPoint.v+q);
					//System.out.println("当前叶子节点为"+LeafPoint.u+","+LeafPoint.v+";"+"计算相对位置为"+p+","+q);
					if(pix==0&&(Math.abs(p)+Math.abs(q))!=0){//八领域中找到了黑点
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
//		//找到第一个黑点；
//		for(int i=0;i<newip.getWidth();i++){
//			
//			for(int j=0;j<newip.getHeight();j++){
//				if(newip.getPixel(i, j)==0){
//					
//					boolean newGroup=true;
//					int nowgroup=0;
//					
//					//找到了他的黑色，就要判断他的八领域
//					for(int p=-3;p<=3;p++){
//						for(int q=-3;q<=3;q++){
//							
//							//判断八领域的黑点是否属于已有的组；
//							if(newip.getPixel(i+p, j+q)==0&&(Math.abs(p)+Math.abs(q))!=0){
//								
//								for(int x=0;x<groups.size();x++){
//									for(int y=0;y<groups.get(x).members.size();y++){
//										int[] test=groups.get(x).members.get(y);
//										if(test[0]==i+p&&test[1]==j+q&&newGroup){
//											//将该点加入该组
//											int[] add={i,j};
//											groups.get(x).members.add(add);
//											nowgroup=x;
//											newGroup=false;
//										}else if(test[0]==i+p&&test[1]==j+q&&!newGroup){
//											//合并两个组
//											if(nowgroup==x) break;
//											groups.get(nowgroup).members.addAll(groups.get(x).members);
//											if(nowgroup<x){
//												groups.remove(x);
//											}else{
//												groups.remove(x);
//												nowgroup--;
//												
//											}
//											break;
//										}
//									}
//									
//								}
//							}
//						}
//
//					}
//					if(newGroup){
//						int[] add={i,j};
//						groups.add(new Group(add));
//					}
//				}
//				
//			}
//		}//完成分组
		//打印出分组信息

		
		
//		return groups;
//		return null;
	}

	private static Point findFirstBlack(Point centerPoint, ImageProcessor newip) {
		// TODO Auto-generated method stub
		for(int d=1;d<=newip.getWidth()/2;d++){
			for(int p=-d;p<=d;p++){
				for(int q=-d;q<=d;q++){
					int pix=newip.getPixel(centerPoint.u+p, centerPoint.v+q);
					if(pix==0){
						return(new Point(centerPoint.u+p, centerPoint.v+q));
					}
				}
			}
		}
		return null;
	}
}
