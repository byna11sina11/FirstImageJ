package com.myDevide;

import java.util.ArrayList;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class CenterDevide {
	public static void main(String[] agrs){
		//��������ͼƬ
		ImagePlus iplus= new ImagePlus("E:/javaͼ����/MyImage/8λ�Ҷ�ͼ��.png");
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
		
		ArrayList<Point> leaves=new ArrayList<>();//�����洢Ҷ�ӣ�
		ArrayList<Point> belongArea=new ArrayList<>();//���������Ѿ��еĽڵ�
		//find the first black point;
		Point CenterPoint=new Point(newip.getWidth()/2,newip.getHeight()/2);
		Point LeafPoint=new Point(0, 0);
		Point firstBlack= findFirstBlack(CenterPoint,newip);
		//System.out.println(firstBlack.u+","+firstBlack.v);
		leaves.add(firstBlack);
		//find the leafpiont
		while(!leaves.isEmpty()){
			LeafPoint=leaves.get(leaves.size()-1);//ȡ��leaves�е����һ���ڵ�
			//System.out.println("��ǰҶ�ӽڵ�Ϊ"+LeafPoint.u+","+LeafPoint.v);
			belongArea.add(LeafPoint);
			//�жϰ������еĺڵ㣻
			for(int p=-3;p<=3;p++){
				for(int q=-3;q<=3;q++){
					int pix=newip.getPixel(LeafPoint.u+p, LeafPoint.v+q);
					//System.out.println("��ǰҶ�ӽڵ�Ϊ"+LeafPoint.u+","+LeafPoint.v+";"+"�������λ��Ϊ"+p+","+q);
					if(pix==0&&(Math.abs(p)+Math.abs(q))!=0){//���������ҵ��˺ڵ�
						//System.out.println("�������λ��Ϊ"+p+","+q+"Ϊ��ɫ��");
						//�жϸõ��Ƿ����ڵ�ǰ������
						boolean isIn=false;
						for(int k=0;k<belongArea.size();k++){
							Point p2=belongArea.get(k);
							if(p2.u==LeafPoint.u+p&&p2.v==LeafPoint.v+q){//�õ�����������
								isIn=true;
							}
						}
						if(!isIn){//�õ㲻����������ж��Ƿ�����Ҷ����
							boolean isInLeaves=false;
							for(int k=0;k<leaves.size();k++){
								Point p2=leaves.get(k);
								if(p2.u==LeafPoint.u+p&&p2.v==LeafPoint.v+q){//�õ�����Ҷ����
									isInLeaves=true;
								}
							}
							if(!isInLeaves){
								leaves.add(new Point(LeafPoint.u+p, LeafPoint.v+q));
								//System.out.println("��ӵ���"+LeafPoint.u+"+"+p+","+LeafPoint.u+"+"+q+";"+(LeafPoint.u+p)+","+(LeafPoint.u+q));
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
//		//�ҵ���һ���ڵ㣻
//		for(int i=0;i<newip.getWidth();i++){
//			
//			for(int j=0;j<newip.getHeight();j++){
//				if(newip.getPixel(i, j)==0){
//					
//					boolean newGroup=true;
//					int nowgroup=0;
//					
//					//�ҵ������ĺ�ɫ����Ҫ�ж����İ�����
//					for(int p=-3;p<=3;p++){
//						for(int q=-3;q<=3;q++){
//							
//							//�жϰ�����ĺڵ��Ƿ��������е��飻
//							if(newip.getPixel(i+p, j+q)==0&&(Math.abs(p)+Math.abs(q))!=0){
//								
//								for(int x=0;x<groups.size();x++){
//									for(int y=0;y<groups.get(x).members.size();y++){
//										int[] test=groups.get(x).members.get(y);
//										if(test[0]==i+p&&test[1]==j+q&&newGroup){
//											//���õ�������
//											int[] add={i,j};
//											groups.get(x).members.add(add);
//											nowgroup=x;
//											newGroup=false;
//										}else if(test[0]==i+p&&test[1]==j+q&&!newGroup){
//											//�ϲ�������
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
//		}//��ɷ���
		//��ӡ��������Ϣ

		
		
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
