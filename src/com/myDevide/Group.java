package com.myDevide;

import java.util.ArrayList;
/**
 * 每个group都是一个组
 * @author st
 *
 */
public class Group {
	public ArrayList<int[]> members=new ArrayList<>();
	
	Group(int[] firstmembers){
		members.add(firstmembers);
	}
}
