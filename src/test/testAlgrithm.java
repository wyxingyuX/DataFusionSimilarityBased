package test;

import Jama.Matrix;
import func.CollbarativeRawAndCol;
import utils.MatrixTool;
import utils.TestTool;

public class testAlgrithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CollbarativeRawAndCol crc=new CollbarativeRawAndCol(0,0,0);
				double[][] xArry={{0,2,3,0},
						         {0,0,1,0}};
				double[][] yArry={{1,2,3},
						          {3,4,5}};
				double[][] zArry={{1,2,1,2},
						          {3,2,4,5},
						          {6,7,8,9},
						          {11,12,13,14}};
//		double[][] xArry={{1,0},
//				          {2,3}};
//		double[][] yArry={{1,1},
//				         {1,1}};
//		double[][] zArry={{1,1},
//				          {1,1}};
		Matrix X=new Matrix(xArry);
		Matrix Y=new Matrix(xArry);
		Matrix Z=new Matrix(zArry);
		TestTool.println("X:");
		X.print(3, 1);
		Matrix indicatorX=MatrixTool.computeIndicatorMatrix(X);
		indicatorX.print(3, 1);
		TestTool.println(X.normF());
		//MatrixTool.computeComplemetMatrixOfIndicator(indicatorX).print(3, 1);
		Matrix fillX=crc.computeDenseMatrixForX(X, Y, Z,100,0.0001);
		TestTool.println("fillX:");
		fillX.print(3, 1);
	}

}
