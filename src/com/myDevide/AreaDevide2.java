package com.myDevide;

import java.util.ArrayList;

import com.smallarticle.AreaDevide;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class AreaDevide2 {
	public static void main(String[] agrs){
		//��������ͼƬ
		ImagePlus iplus= new ImagePlus("E:/javaͼ����/MyImage/8λ�Ҷ�ͼ��.png");
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
		
		//�ҵ���һ���ڵ㣻
		for(int i=0;i<newip.getWidth();i++){
			
			for(int j=0;j<newip.getHeight();j++){
				if(newip.getPixel(i, j)==0){
					
					boolean newGroup=true;
					int nowgroup=0;
					
					//�ҵ������ĺ�ɫ����Ҫ�ж����İ�����
					for(int p=-3;p<=3;p++){
						for(int q=-3;q<=3;q++){
							
							//�жϰ�����ĺڵ��Ƿ��������е��飻
							if(newip.getPixel(i+p, j+q)==0&&(Math.abs(p)+Math.abs(q))!=0){
								
								for(int x=0;x<groups.size();x++){
									for(int y=0;y<groups.get(x).members.size();y++){
										int[] test=groups.get(x).members.get(y);
										if(test[0]==i+p&&test[1]==j+q&&newGroup){
											//���õ�������
											int[] add={i,j};
											groups.get(x).members.add(add);
											nowgroup=x;
											newGroup=false;
										}else if(test[0]==i+p&&test[1]==j+q&&!newGroup){
											//�ϲ�������
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
		}//��ɷ���
		//��ӡ��������Ϣ

		
		
		return groups;
	}
}
