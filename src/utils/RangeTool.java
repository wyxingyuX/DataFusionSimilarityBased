package utils;

public class RangeTool {
	public static boolean isInRange(double value,double startR,double endR){
		return (Double.compare(value, startR)>0)&&(Double.compare(value,endR)<0);
	}
	public static boolean isInorOnRange(double value,double startR,double endR){
		return (Double.compare(value, startR)>=0)&&(Double.compare(value,endR)<=0);
	}
}
