package com.myDevide;

import java.util.ArrayList;

import com.smallarticle.AreaDevide;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class AreaDevide2 {
	public static void main(String[] agrs){
		//构建测试图片
		ImagePlus iplus= new ImagePlus("E:/java图像处理/MyImage/8位灰度图像.png");
		ImageProcessor ip=iplus.getProcessor();
		ImageProcessor newip=ip.createProcessor(12,12);
		int[][] pic={
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,0,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,0,0,0,0,0},
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
		
		ArrayList<Point> mylist= AreaDevide.areaDevide(newip);
		
		//devideIt(newip);
		ImagePlus iplus2=new ImagePlus("draw it",newip);
		iplus2.show();
	}

	public static ArrayList<Group> devideIt(ImageProcessor newip) {
		// TODO Auto-generated method stub
		ArrayList<Group> groups=new ArrayList<>();
		
		//找到第一个黑点；
		for(int i=0;i<newip.getWidth();i++){
			
			for(int j=0;j<newip.getHeight();j++){
				if(newip.getPixel(i, j)==0){
					
					boolean newGroup=true;
					int nowgroup=0;
					
					//找到了他的黑色，就要判断他的八领域
					for(int p=-3;p<=3;p++){
						for(int q=-3;q<=3;q++){
							
							//判断八领域的黑点是否属于已有的组；
							if(newip.getPixel(i+p, j+q)==0&&(Math.abs(p)+Math.abs(q))!=0){
								
								for(int x=0;x<groups.size();x++){
									for(int y=0;y<groups.get(x).members.size();y++){
										int[] test=groups.get(x).members.get(y);
										if(test[0]==i+p&&test[1]==j+q&&newGroup){
											//将该点加入该组
											int[] add={i,j};
											groups.get(x).members.add(add);
											nowgroup=x;
											newGroup=false;
										}else if(test[0]==i+p&&test[1]==j+q&&!newGroup){
											//合并两个组
											if(nowgroup==x) break;
											groups.get(nowgroup).members.addAll(groups.get(x).members);
											if(nowgroup<x){
												groups.remove(x);
											}else{
												groups.remove(x);
												nowgroup--;
												
											}
											break;
										}
									}
									
								}
							}
						}

					}
					if(newGroup){
						int[] add={i,j};
						groups.add(new Group(add));
					}
				}
				
			}
		}//完成分组
		//打印出分组信息

		
		
		return groups;
	}
}
