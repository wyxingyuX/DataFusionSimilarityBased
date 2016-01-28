package utils;

import java.util.Map;

public class StatTool {
  public static boolean idicateFunc(Map<String,Integer> reserve,String curkey){
	  return reserve.containsKey(curkey);
  }
}
