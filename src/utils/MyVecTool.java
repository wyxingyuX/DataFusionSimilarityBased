package utils;

import java.util.Map;
import java.util.TreeMap;

public class MyVecTool {
	public static void unitVec(TreeMap<Integer,Float> vec){
		double verySmall=0.00000001;
		float len=MyVecTool.computeVecLength(vec);
		if(Double.compare(len, verySmall)>0)
		{
			len= (float) Math.sqrt(len);
			for(Map.Entry<Integer, Float> vecItem:vec.entrySet()){
				float oldValue=vecItem.getValue();
				vecItem.setValue(oldValue/len);
			}  
		}
	}

	public static float computeVecLength(TreeMap<Integer,Float> vec){
		float len=0;//
		for(Map.Entry<Integer, Float> item:vec.entrySet()){
			float value=item.getValue();
			len+=value*value;
		}
		return len;
	}

	public static double vecTimesVec(TreeMap<Integer,Float> vec1,TreeMap<Integer,Float> vec2){
		double sim=0;
		for(Map.Entry<Integer, Float> vec1Item:vec1.entrySet()){
			int vec1ItemDiem=vec1Item.getKey();
			float vec1ItemValue=vec1Item.getValue();
			Float vec2ItemValue=vec2.get(vec1ItemDiem);
			if(vec2ItemValue!=null){
				sim+=vec1ItemValue*vec2ItemValue;
			}
		}
		return sim;
	}
	public static Map<Integer,Float> vecTimesNum(TreeMap<Integer,Float> vec,float fnum){
		Map<Integer,Float> nvec=new TreeMap<Integer,Float>();
		for(Map.Entry<Integer, Float> item:vec.entrySet()){
			int diem=item.getKey();
			float value=item.getValue();
			nvec.put(diem, fnum*value);
		}
		return nvec;
	}

}
