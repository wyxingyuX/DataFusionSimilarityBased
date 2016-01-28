package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import utils.MathTool;
import utils.TestTool;

public class testTrick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Map<String,Integer> lh=new LinkedHashMap<String,Integer>();
      lh.put("wy", 1);
      lh.put("zn", 2);
      lh.put("znwy", 3);
      lh.put("wyzn", 4);
      ArrayList<String> ar=new ArrayList<String> ();
      Iterator itr;
      for(Map.Entry<String, Integer> entry:lh.entrySet()) System.out.println(entry.getKey()+":"+entry.getValue());
      for(int i=-5;i<15;++i)
        TestTool.println(i+":"+MathTool.sigmoid(i));
	}

}
