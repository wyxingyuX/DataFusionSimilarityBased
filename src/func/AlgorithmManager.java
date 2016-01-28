package func;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import Jama.Matrix;
import utils.FileTool;
import utils.SaveInfoTool;

public class AlgorithmManager {
	/*
	 * Y=UW(T)   X=UV(T)  Z=VV(T)
	 */
	private CosSimMatrix leftMatrixY;
	private CosSimMatrix rightMatrixZ;
	private CosSimMatrix centerMatrixX;
	private CollbarativeRawAndCol crcAlg;
    
	public AlgorithmManager(){
		crcAlg=new CollbarativeRawAndCol();
	}
	public void prepareTwoDenseMatrix(CosSimMatrix leftMatrixY,CosSimMatrix rightMatrixZ){
		this.setLeftMatrixY(leftMatrixY);
		this.setRightMatrixZ(rightMatrixZ);
	}
	//主要是为了让X的 行列对应的 key能与 Y，Z上的对应。
	public void prepareCenterMatrix(CosSimMatrix centerMatrixX){
		//让X的 行列对应的 key能与 Y，Z上的对应。
		//test
		this.setCenterMatrixX(centerMatrixX);
		
	}

	public Matrix fillCenterMatrixX(double lmda1,double lmda2,double lmda3,int maxIter,double maxError){
		Matrix X=this.getCenterMatrixX().getSimMatrix();
		Matrix Y=this.getLeftMatrixY().getSimMatrix();
		Matrix Z=this.getRightMatrixZ().getSimMatrix();

		crcAlg.setLmda1(lmda1);
		crcAlg.setLmda2(lmda2);
		crcAlg.setLmda3(lmda3);
		Matrix denseX=crcAlg.computeDenseMatrixForX(X, Y, Z, maxIter, maxError);
		this.centerMatrixX.setSimMatrix(denseX);
		return denseX;
	}
    public void writeCentrixMatrix2File(String destFile,String seperater,float times) throws UnsupportedEncodingException, FileNotFoundException{
    	this.centerMatrixX.writeSimMatrixbyCol2KeyDiemVecFile(destFile, seperater, times);
    }
	public void writeResult2File(String destUidVecFile,String seperater) throws Exception{
		String parentPath=FileTool.getParentPath(destUidVecFile);
		String uidCodeFile=parentPath+"\\uidCode.txt";
		String tagsCodeFile=parentPath+"\\tagCode.txt";
		Map<String,Integer> userPos=this.centerMatrixX.getColPos();
		Map<String,Integer> tagPos=this.centerMatrixX.getRowPos();
		SaveInfoTool.saveDict(userPos, uidCodeFile, seperater);
		SaveInfoTool.saveDict(tagPos, tagsCodeFile, seperater);
		
		Map<Integer,String> posUser=new HashMap<Integer,String>();
		for(Map.Entry<String, Integer> entry:userPos.entrySet()){ posUser.put(entry.getValue(), entry.getKey());}
		
		this.writeMatrixByCol2File(this.centerMatrixX.getSimMatrix(), posUser, destUidVecFile, seperater);	
	}
	protected void writeMatrixByCol2File(Matrix matrix,Map<Integer,String> posWordMap,String destFile,String seperater) throws Exception{
		FileTool.guaranteeFileDirExist(destFile);
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
		String itemSeperater=":";
		for(int j=0;j<matrix.getColumnDimension();++j){
			String word=posWordMap.get(j);
			osw.write(word+seperater);
			for(int i=0;i<matrix.getRowDimension();++i){
				int diem=i+1;
				osw.write(diem+itemSeperater+matrix.get(i, j)+seperater);
			}
			osw.write("\r\n");
		}
		osw.flush();
		osw.close();
	}
	protected void writeMatrixByRow2File(Matrix matrix,Map<Integer,String> posWordMap,String destFile,String seperater) throws Exception{
		FileTool.guaranteeFileDirExist(destFile);
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
		for(int i=0;i<matrix.getRowDimension();++i){
			String word=posWordMap.get(i);
			osw.write(word+seperater);
			for(int j=0;j<matrix.getColumnDimension();++j){
				osw.write(matrix.get(i, j)+seperater);
			}
			osw.write("\r\n");
		}
		osw.flush();
		osw.close();
	}
	
	public void manageAlgrithmProcess(){
        
	}

	public CosSimMatrix getLeftMatrixY() {
		return leftMatrixY;
	}
	public void setLeftMatrixY(CosSimMatrix leftMatrixY) {
		this.leftMatrixY = leftMatrixY;
	}
	public CosSimMatrix getRightMatrixZ() {
		return rightMatrixZ;
	}
	public void setRightMatrixZ(CosSimMatrix rightMatrixZ) {
		this.rightMatrixZ = rightMatrixZ;
	}

	public CosSimMatrix getCenterMatrixX() {
		return centerMatrixX;
	}

	public void setCenterMatrixX(CosSimMatrix centerMatrixX) {
		this.centerMatrixX = centerMatrixX;
	}
	public CollbarativeRawAndCol getCrcAlg() {
		return crcAlg;
	}
	public void setCrcAlg(CollbarativeRawAndCol crcAlg) {
		this.crcAlg = crcAlg;
	}
}
