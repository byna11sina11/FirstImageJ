package com.myDevide;

import java.util.ArrayList;

import org.junit.Test;

import ij.ImagePlus;
import ij.io.OpenDialog;
import ij.process.ImageProcessor;

public class Test2 {
	public static void main(String[] ags){
		OpenDialog ps=new OpenDialog("");
		ImagePlus ip=new ImagePlus(ps.getPath());
		ImageProcessor imageProcessor=ip.getProcessor();
		System.out.print(imageProcessor.getPixel(0, 0)+"\t");
//		for(int u=0;u<imageProcessor.getWidth();u++){
//			for(int v=0;v<imageProcessor.getHeight();v++){
//				System.out.print(imageProcessor.getPixel(u, v)+"\t");
//			}
//			System.out.println("");
//		}
	}

}
