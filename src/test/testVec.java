package test;

import java.util.TreeMap;

import utils.MyVecTool;
import utils.TestTool;

public class testVec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap<Integer,Float> vec=new TreeMap<Integer,Float>();
		vec.put(1, 1.0f);
		vec.put(2, 2.0f);
		vec.put(3, 2.0f);
		TestTool.println(vec);
		MyVecTool.unitVec(vec);
		TestTool.println(vec);
	}

}
