package func;

import Jama.Matrix;
import utils.MatrixTool;
import utils.TestTool;

public class CollbarativeRawAndCol {
	private Matrix X;
	private Matrix Y;
	private Matrix Z;
	private Matrix indicatorMatrixofX;
	private double lmda1;
	private double lmda2;
	private double lmda3;
	public CollbarativeRawAndCol(){
		this.setLmda1(1);
		this.setLmda2(1);
		this.setLmda3(1);
	}
	public CollbarativeRawAndCol(double lmda1,double lmda2,double lmda3){
		this.setLmda1(lmda1);
		this.setLmda2(lmda2);
		this.setLmda3(lmda3);
	}
	protected double computeObjFunctionL(Matrix U,Matrix V,Matrix W){
		Matrix transposeV=V.transpose();

		return (0.5)*(X.minus(U.times(transposeV)).arrayTimesEquals(indicatorMatrixofX).normF())
				+(lmda1/2.0)*(Y.minus(U.times(W.transpose())).normF())
				+(lmda2/2.0)*(Z.minus(V.times(transposeV)).normF())
				+(lmda3/2.0)*(U.normF()+V.normF()+W.normF());
	}
	protected Matrix computeLGradient2U(Matrix U,Matrix V,Matrix W){
		Matrix term1=U.times(V.transpose()).minusEquals(X).arrayTimesEquals(indicatorMatrixofX).times(V);
		Matrix term2=(U.times(W.transpose()).minusEquals(Y).times(W)).timesEquals(lmda1*1.0);
		Matrix term3=U.times(lmda3*1.0);
		return term1.plusEquals(term2).plusEquals(term3);
	}
	protected Matrix computeLGradient2V(Matrix U,Matrix V,Matrix W){
		Matrix transposeV=V.transpose();
		Matrix term1=(U.times(transposeV).minusEquals(X).arrayTimesEquals(indicatorMatrixofX)).transpose().times(U);
		Matrix term2=V.times(transposeV).minusEquals(Z).times(V).timesEquals(2.0*lmda2);
		Matrix term3=V.times(lmda3);
		return term1.plusEquals(term2).plusEquals(term3);
	}
	protected Matrix computeLGradient2W(Matrix U,Matrix V,Matrix W){
		Matrix term1=U.times(W.transpose()).minusEquals(Y).transpose().times(U).timesEquals(1.0*lmda1);
		Matrix term2=W.times(1.0*lmda3);
		return term1.plusEquals(term2);
	}


	public Matrix computeDenseMatrixForX(Matrix X,Matrix Y,Matrix Z,int maxIter,double maxError){
		this.setX(X);
		this.setY(Y);
		this.setZ(Z);
		Matrix iMofX=MatrixTool.computeIndicatorMatrix(X);
		this.setIndicatorMatrixofX(iMofX);

		int m=Y.getRowDimension();
		int n=X.getColumnDimension();
		int l=Y.getColumnDimension();
		int k=30;
		if(n==1) k=n;//k<n
		if(n!=1&&n<50) k=n/2;

		Matrix U=new Matrix(m,k,1);
		Matrix V=new Matrix(n,k,1); 
		Matrix W=new Matrix(l,k,1);
		double Llast=10000000;
		double Lcur=0;
		long st=System.currentTimeMillis();
		double dscL=0;
        
		TestTool.println("Start compute to fill X");
		int t=0;
		while(t<maxIter){
			Lcur=this.computeObjFunctionL(U, V, W);
			dscL=Llast-Lcur;
			//TestTool.println("in "+t+" iter,dscL="+dscL);
			if(Double.compare(dscL,maxError)<0) break;

			double r=1;
			Matrix grdient2U=this.computeLGradient2U(U, V, W);
			Matrix gradient2V=this.computeLGradient2V(U, V, W);
			Matrix gradient2W=this.computeLGradient2W(U, V, W);

			Matrix rGradientU=grdient2U.times(r);
			Matrix rGradientV=gradient2V.times(r);
			Matrix rGradientW=gradient2W.times(r);
			double LsubRGradient=this.computeObjFunctionL(U.minus(rGradientU), V.minus(rGradientV), W.minus(rGradientW));
			while(Double.compare(LsubRGradient,Lcur)>0){
				r=r/2.0;
				rGradientU=grdient2U.times(r);
				rGradientV=gradient2V.times(r);
				rGradientW=gradient2W.times(r);
				LsubRGradient=this.computeObjFunctionL(U.minus(rGradientU), V.minus(rGradientV), W.minus(rGradientW));
				//TestTool.println("  r="+r);
				if(r==0) break;//
			}

			U=U.minusEquals(rGradientU);
			V=V.minusEquals(rGradientV);
			W=W.minusEquals(rGradientW);
			Llast=Lcur;
			++t;
		}
		long ed=System.currentTimeMillis();
		System.out.println("last descent length is "+dscL+","+t+" times iterator cost:"+(ed-st)/1000.0+"s; "+(ed-st)/(t*1000.0)+"s/iter");
//		 Matrix comlementIdicator=MatrixTool.computeComplemetMatrixOfIndicator(this.indicatorMatrixofX);
//		 Matrix fillX=U.times(V.transpose()).arrayTimes(comlementIdicator).plus(X);
		//Matrix fillX=U.times(V.transpose());
        
     	Matrix fillX=U.times(V.transpose()).plus(X);
		return fillX;
	}
	public Matrix getX() {
		return X;
	}
	public void setX(Matrix x) {
		X = x;
	}
	public Matrix getY() {
		return Y;
	}
	public void setY(Matrix y) {
		Y = y;
	}
	public Matrix getZ() {
		return Z;
	}
	public void setZ(Matrix z) {
		Z = z;
	}
	public Matrix getIndicatorMatrixofX() {
		return indicatorMatrixofX;
	}
	public void setIndicatorMatrixofX(Matrix indicatorMatrixofX) {
		this.indicatorMatrixofX = indicatorMatrixofX;
	}
	public double getLmda1() {
		return lmda1;
	}
	public void setLmda1(double lmda1) {
		this.lmda1 = lmda1;
	}
	public double getLmda2() {
		return lmda2;
	}
	public void setLmda2(double lmda2) {
		this.lmda2 = lmda2;
	}
	public double getLmda3() {
		return lmda3;
	}
	public void setLmda3(double lmda3) {
		this.lmda3 = lmda3;
	}
}
