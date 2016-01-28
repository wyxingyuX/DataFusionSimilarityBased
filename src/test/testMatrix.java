package test;

import Jama.Matrix;
import func.CollbarativeRawAndCol;
import utils.MatrixTool;
import utils.TestTool;

public class testMatrix {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Matrix m=new Matrix(2,3,1);
    double[][] mtary={{1,2,0},{4,0,6}};
     Matrix mt=new Matrix(mtary);
     TestTool.println("m:");
     m.print(3, 1);
     TestTool.println("mt:");
     mt.print(3, 1);
     
     Matrix ressult=mt.arrayTimes(m);
     TestTool.println("result:");
     ressult.print(3, 1);
     TestTool.println(ressult.normF());
     CollbarativeRawAndCol ct=new CollbarativeRawAndCol();
     
     Matrix indic=MatrixTool.computeIndicatorMatrix(mt);
     indic.print(3, 1);
     mt.arrayTimes(indic).print(3, 1);
     Matrix squrM=new Matrix(2,3,1);
     squrM.set(0, 1, 2);
     squrM.print(3, 1);
     squrM.transpose().print(3, 1);
    
     float f=0.0001f;
     double d=f;
     
     TestTool.print(d);
     
	}

}
