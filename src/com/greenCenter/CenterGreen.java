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
		//OpenDialog openMybmp=new OpenDialog("��һ��RGBͼ��");
		//ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openybmp.getPath().substring(beginIndex)
		ImagePlus iplus= new ImagePlus("E:/javaͼ����/MyImage/RGB��ɫͼ.jpg");
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
//		ImagePlus ip2=new ImagePlus("��ֵ����", iprocessor);
//		IJ.save(ip2, "C:/Users/st/Desktop/imagedeal/С����/��ֵ��/"+openMybmp.getFileName());
//		ip2.show();
	}
	public  static ArrayList<Point> centerGreens(ImageProcessor newip, Point firstBlack) {
		// TODO Auto-generated method stub
		//ImageProcessor newip=newip.createProcessor(newip.getWidth(), newip.getHeight());
		ArrayList<Point> leaves=new ArrayList<>();//�����洢Ҷ�ӣ�
		ArrayList<Point> belongArea=new ArrayList<>();//���������Ѿ��еĽڵ�
		//find the first black point;
		//Point CenterPoint=new Point(newip.getWidth()/2,newip.getHeight()/2);
		Point LeafPoint=new Point(0, 0);
		//Point firstBlack= findFirstBlack(CenterPoint,newip);
		//System.out.println(firstBlack.u+","+firstBlack.v);
		leaves.add(firstBlack);
		//find the leafpiont
		while(!leaves.isEmpty()){
			LeafPoint=leaves.get(leaves.size()-1);//ȡ��leaves�е����һ���ڵ�
			System.out.println("leafs="+LeafPoint.u+","+LeafPoint.v);
			//System.out.println("��ǰҶ�ӽڵ�Ϊ"+LeafPoint.u+","+LeafPoint.v);
			belongArea.add(LeafPoint);
			//�жϰ������еĺڵ㣻
			for(int p=-1;p<=1;p++){
				for(int q=-1;q<=1;q++){
					//int pix=newip.getPixel(LeafPoint.u+p, LeafPoint.v+q);
					//System.out.println("��ǰҶ�ӽڵ�Ϊ"+LeafPoint.u+","+LeafPoint.v+";"+"�������λ��Ϊ"+p+","+q);
					System.out.println(LeafPoint.u+p+","+LeafPoint.v+q);
					if(isGreen(newip, LeafPoint.u+p, LeafPoint.v+q)&&(Math.abs(p)+Math.abs(q))!=0){//���������ҵ��˺ڵ�
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
//				//ȡ��ÿ�������ԭɫ
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
//				//��rgbΪ��ɫ��  ����Ϊ��ɫ����
//				if(blue==255&&v<200){
//					iprocessor.putPixel(u, v, white);
//				}
//				//ǿ�����Ӱ�ᵼ��rgb����ɫ�е�B>R
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
