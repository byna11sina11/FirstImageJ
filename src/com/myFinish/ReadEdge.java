package com.myFinish;

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
		String filepath="C:/Users/st/Desktop/imagedeal/edge.xlsx";
		int[][] EdgeArray=readExcel(filepath);
		
		//���ݱ�Ե���ػ�ͼƬ
		ImagePlus iplus= new ImagePlus("E:/javaͼ����/MyImage/RGB��ɫͼ.jpg");
		ImageProcessor ip=iplus.getProcessor();
		ImageProcessor newip=ip.createProcessor(640, 480);
		
		
		//��ͨ���򻮷�
		//����
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
		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
		 ArrayList<Group> groups = AreaDevide2.devideIt(newip);
		 long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
		 System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
		 
		int max=0;
		int index=0;
		System.out.println("���Է�Ϊ"+groups.size()+"��");
		for(int i=0;i<groups.size();i++){

			if(groups.get(i).members.size()>max){
				max=groups.get(i).members.size();
				
				index=i;
			}
		}
		System.out.println("������"+max+"��");
		System.out.println("�Ǳ�Ϊ"+index);
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
		
		
		ImagePlus ipl2=new ImagePlus("reDraw",newip);
		ImagePlus ipl3=new ImagePlus("myResult",ipnew2);
		ipl2.show();
		ipl3.show();
	}

	
	
	
	private static int[][] readExcel(String filepath){
		try{
			InputStream ip=new FileInputStream(filepath);
			wb = new XSSFWorkbook(ip);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		int sheetSize=wb.getNumberOfSheets();
		//System.out.println(sheetSize);
		Sheet sheet=wb.getSheetAt(0);
		//System.out.println(sheet.getLastRowNum()+1+"  "+sheet.getRow(0).getLastCellNum());
		int[][] edgeArray=new int[640][480];
		int rowSize=sheet.getLastRowNum()+1;
		Row row=sheet.getRow(0);
		//System.out.println("i -> "+rowSize+";"+"j -> "+row.getLastCellNum());
		//������ɨ��
		for(int i=0;i<rowSize;i++){
			Row rowlist=sheet.getRow(i);
			for (int j=0;j<rowlist.getLastCellNum();j++){
				Cell cell=rowlist.getCell(j);
				if(cell.toString().equals("FALSE")){
					edgeArray[j][i]=0;
				}else{
					edgeArray[j][i]=1;
				}
			}
			
		}
		return edgeArray;
	}
}
