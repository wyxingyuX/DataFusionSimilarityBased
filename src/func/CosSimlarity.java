package func;

import java.util.TreeMap;

import interf.MetricSimilarity;
import utils.MyVecTool;

public class CosSimlarity implements MetricSimilarity {

	@Override
	public double computeTwoUnitVecSim(TreeMap<Integer, Float> unitVec1, TreeMap<Integer, Float> unitVec2) {
		// TODO Auto-generated method stub
		return MyVecTool.vecTimesVec(unitVec1, unitVec2);
	}

	@Override
	public double computeTwoVecSim(TreeMap<Integer, Float> vec1, TreeMap<Integer, Float> vec2) {
		// TODO Auto-generated method stub
		double sim=0;
		float len=MyVecTool.computeVecLength(vec1)*MyVecTool.computeVecLength(vec2);
		if(Float.compare(len, 0)>0){
			sim=MyVecTool.vecTimesVec(vec1, vec2)/len;
		}
		return sim;
	}

}
