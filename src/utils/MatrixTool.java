package utils;

import Jama.Matrix;

public class MatrixTool {
	
	public static Matrix computeIndicatorMatrix(Matrix X){
		double verySmall=0.00000001;
		Matrix indicator=new Matrix(X.getRowDimension(),X.getColumnDimension(),1);
		for(int i=0;i<X.getRowDimension();++i){
			for(int j=0;j<X.getColumnDimension();++j){
				double elm=X.get(i, j);
				//  -verySmall<elm<verysmall
				if(ToolKit.equals(elm, 0, verySmall)){
					indicator.set(i, j, 0);
				}
			}
		}
		return indicator;
	}
	
    public static Matrix computeComplemetMatrixOfIndicator(Matrix indicartorMatrix){
    	Matrix complementMatrix=new Matrix(indicartorMatrix.getArray());
    	for(int i=0;i<complementMatrix.getRowDimension();++i){
    		for(int j=0;j<complementMatrix.getColumnDimension();++j){
    			if(complementMatrix.get(i, j)==0)complementMatrix.set(i, j, 1);
    			else complementMatrix.set(i, j, 0);
    		}
    	}
    	return complementMatrix;
    }
}
