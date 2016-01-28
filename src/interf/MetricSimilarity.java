package interf;

import java.util.TreeMap;

public interface MetricSimilarity {
    public double computeTwoUnitVecSim(TreeMap<Integer,Float> unitVec1,TreeMap<Integer,Float> unitVec2);
    public double computeTwoVecSim(TreeMap<Integer,Float> vec1,TreeMap<Integer,Float> vec2);
}
