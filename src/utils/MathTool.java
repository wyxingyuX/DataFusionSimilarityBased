package utils;

public class MathTool {
  public static float sigmoid(int x){
	  return (float) (1.0/(1+Math.pow(Math.E, -x)));
  }
}
