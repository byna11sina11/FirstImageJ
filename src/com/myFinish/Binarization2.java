package com.myFinish;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

import javax.management.openmbean.OpenMBeanAttributeInfo;

import ij.IJ;
import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷值锟斤拷图锟斤拷
 * 锟斤拷锟斤拷锟节ｏ拷锟斤拷锟节碉拷锟斤拷锟斤拷
 * @author st
 *
 */
public class Binarization2 {
	public static void main(String[] ages) throws Exception{
		OpenDialog openMybmp=new OpenDialog("open rgb");
		ImagePlus ip=new ImagePlus(openMybmp.getPath());
		//beginIndex=openMybmp.get
		//String fileName=openMybmp.getPath().substring(beginIndex)
		ip.setTitle("1");
		ip.show();
		System.out.println(openMybmp.getFileName());
		ColorProcessor cp=(ColorProcessor) ip.getProcessor();
	//	ImageProcessor iprocessor=cp.createProcessor(cp.getWidth(), cp.getHeight());
		int[] rgbofMypic=new int[3];
	//	int[] black={0,0,0};
	//	int[] white={255,255,255};
		//rgbofMypic=ip
		//锟斤拷锟斤拷锟侥硷拷
		
		String filePath=openMybmp.getPath().substring(0, openMybmp.getPath().indexOf("."))+".txt";

		//createFile(file);
//		contentToTxt(filePath, "string1"+"\t"+"string2\r\n");
//		contentToTxt(filePath, "string1"+"\t"+"string2");
		for(int u=0;u<=cp.getWidth();u++){
			for(int v=0;v<=cp.getHeight();v++){
				//取锟斤拷每锟斤拷锟斤拷锟斤拷锟皆�
				
				rgbofMypic=cp.getPixel(u, v, rgbofMypic);
				int red=rgbofMypic[0];
				int green=rgbofMypic[1];
				int blue=rgbofMypic[2];
				if(green>30){
					int gr=green-red;
					int gb=green-blue;
					contentToTxt(filePath, gr+"\t"+gb+"\r\n");
				}

			}
		}
		
		ip.close();
	}
	 public static void createFile(File fileName)throws Exception{  
		 //boolean flag=false;  
		  try{  
		   if(!fileName.exists()){  
		    fileName.createNewFile();  
		  //  System.out.println("锟窖达拷锟斤拷"+fileName.getPath());
		    //flag=true;  
		   }  
		  }catch(Exception e){  
		   e.printStackTrace();  
		  }  
		  //return true;  
	}   
	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
		  RandomAccessFile mm=null;  
		  boolean flag=false;  
		  FileOutputStream o=null;  
		  try {  
		   o = new FileOutputStream(fileName);  
		      o.write(content.getBytes("utf-8"));  
		      o.close();  
		//   mm=new RandomAccessFile(fileName,"rw");  
		//   mm.writeBytes(content);  
		   flag=true;  
		  } catch (Exception e) {  
		   // TODO: handle exception  
		   e.printStackTrace();  
		  }finally{  
		   if(mm!=null){  
		    mm.close();  
		   }  
		  }  
		  return flag;  
		 }  
	 public static void contentToTxt(String filePath, String content) {  
	        String str = new String(); //原锟斤拷txt锟斤拷锟斤拷  
	        String s1 = new String();//锟斤拷锟捷革拷锟斤拷  
	        try {  
	            File f = new File(filePath);  
	            if (f.exists()) {  
	               // System.out.print("锟侥硷拷锟斤拷锟斤拷");  
	            } else {  
	                //System.out.print("锟侥硷拷锟斤拷锟斤拷锟斤拷");  
	                f.createNewFile();// 锟斤拷锟斤拷锟斤拷锟津创斤拷  
	            }  
	            BufferedReader input = new BufferedReader(new FileReader(f));  
	  
	            while ((str = input.readLine()) != null) {  
	                s1 += str + "\n";  
	            }  
	           // System.out.println(s1);  
	            input.close();  
	            s1 += content;  
	  
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
	            output.write(s1); 
	            //output.write("\r\n");
	           // output.newLine();
	            output.flush();
	            output.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	  
	        }  
	    }     
}
