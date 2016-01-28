package func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Jama.Matrix;
import utils.FileTool;

public class CosSimMatrix {
	private Map<String,Integer> rowPos;
	private Map<String,Integer> colPos;
	private Matrix simMatrix;
	private CosSimlarity simCompute;

	public CosSimMatrix(){
		simCompute=new CosSimlarity();
	}
	public Matrix computeSimMatrix(Map<String,TreeMap<Integer,Float>> rowKvecMatrix,
			Map<String,TreeMap<Integer,Float>> colKvecMatrix){

		if(rowKvecMatrix==colKvecMatrix) 
			simMatrix=this.computeSimMatrixFromSameKeyMatrix(rowKvecMatrix);
		else 
			simMatrix=this.computeSimMatrixFromDiffKeyMatrix(rowKvecMatrix, colKvecMatrix);
		return simMatrix;
	}
	//未测试
	public void loadSimMatrix(String filePath,String seperater) throws Exception{
		BufferedReader reader=FileTool.getBufferedReaderFromFile(filePath);
		int num=FileTool.getFileLineNum(filePath);
		Matrix ma=new Matrix(num,num,0);
		String itemSeperater=":";
		String line="";
		int colIdx=0;
		while((line=reader.readLine())!=null){
			String[] elms=line.split(seperater);
			String id=elms[0];
			colPos.put(id, colIdx);
			int rowIdx=0;
			for(int i=1;i<elms.length;++i){
				colIdx=i-1;
				String[] itemElms=elms[i].split(itemSeperater);
				String rowKey=itemElms[0];
				double value=Double.parseDouble(itemElms[1]);
				rowPos.put(rowKey, rowIdx);
				ma.set(rowIdx, colIdx, value);
			}
			++colIdx;
		}
		this.setSimMatrix(ma);
	}
	
	public void writeSimMatrixbyCol2KeyDiemVecFile(String keyDiemVecFile,String seperater) throws UnsupportedEncodingException, FileNotFoundException{
		this.writeSimMatrixbyCol2KeyDiemVecFile(keyDiemVecFile, seperater, 1.0);
	}
	public void writeSimMatrixbyCol2KeyDiemVecFile(String keyDiemVecFile,String seperater,double times) throws UnsupportedEncodingException, FileNotFoundException{
		Map<Integer,String> posKeyOfRow=this.inverseKeyPosMap(this.rowPos);
		Map<Integer,String> posKeyOfCol=this.inverseKeyPosMap(this.colPos);
        
		String itemSeperater=":";
		PrintWriter writer=FileTool.getPrintWriterForFile(keyDiemVecFile);
		for(int j=0;j<simMatrix.getColumnDimension();++j){
            String id=posKeyOfCol.get(j);
			writer.write(id+seperater);
			for(int i=0;i<simMatrix.getRowDimension();++i){
                String diem=posKeyOfRow.get(i);
                double value=times*simMatrix.get(i, j);
				writer.write(diem+itemSeperater+value+seperater);
			}
			writer.write("\r\n");
		}
       writer.close();
	}
	
	private Map<Integer,String> inverseKeyPosMap(Map<String,Integer> keyPos){
		Map<Integer,String> posKey=new HashMap<Integer,String>();
		for(Map.Entry<String,Integer> entry:keyPos.entrySet() ){
			posKey.put(entry.getValue(), entry.getKey());
		}
		return posKey;
	}

	protected Matrix computeSimMatrixFromDiffKeyMatrix(Map<String,TreeMap<Integer,Float>> rowKvecMatrix,
			Map<String,TreeMap<Integer,Float>> colKvecMatrix){
		Matrix curSimMatrix=new Matrix(rowKvecMatrix.size(),colKvecMatrix.size(),0);
		int rowIndex=0;
		int colIndex=0;

		this.rowPos=new HashMap<String,Integer>();
		this.colPos=new HashMap<String,Integer>();

		for(Map.Entry<String,TreeMap<Integer,Float>> rowKvec:rowKvecMatrix.entrySet()){
			String rowKey=rowKvec.getKey();
			TreeMap<Integer,Float> rowVec=rowKvec.getValue();
			this.rowPos.put(rowKey, rowIndex);

			for(Map.Entry<String, TreeMap<Integer,Float>> colKvec:colKvecMatrix.entrySet()){
				String colKey=colKvec.getKey();
				TreeMap<Integer,Float> colVec=colKvec.getValue();
				double sim=simCompute.computeTwoUnitVecSim(rowVec, colVec);
				curSimMatrix.set(rowIndex, colIndex, sim);
				if(0==rowIndex) this.colPos.put(colKey, colIndex); 
				++colIndex;
			}
			++rowIndex;
			colIndex=0;
		}
		return curSimMatrix;
	}
	protected Matrix computeSimMatrixFromSameKeyMatrix(Map<String,TreeMap<Integer,Float>> kvecMatrix){
		Matrix curSimMatrix=new Matrix(kvecMatrix.size(),kvecMatrix.size(),0);
		int rowIndex=0;
		int colIndex=0;

		this.rowPos=new HashMap<String,Integer>();
		this.colPos=new HashMap<String,Integer>();

		for(Map.Entry<String,TreeMap<Integer,Float>> rowKvec:kvecMatrix.entrySet()){
			String rowKey=rowKvec.getKey();
			TreeMap<Integer,Float> rowVec=rowKvec.getValue();
			this.rowPos.put(rowKey, rowIndex);

			for(Map.Entry<String, TreeMap<Integer,Float>> colKvec:kvecMatrix.entrySet()){
				double sim=0;
				String colKey=colKvec.getKey();
				TreeMap<Integer,Float> colVec=colKvec.getValue();

				if(rowIndex<colIndex){sim=simCompute.computeTwoUnitVecSim(rowVec, colVec);}
				else if(rowIndex==colIndex) {sim=1;}
				else{sim=curSimMatrix.get(colIndex, rowIndex);}

				curSimMatrix.set(rowIndex, colIndex, sim);
				if(0==rowIndex) this.colPos.put(colKey, colIndex); 

				++colIndex;
			}
			++rowIndex;
			colIndex=0;
		}

		return curSimMatrix;
	}

	public Map<String, Integer> getRowPos() {
		return rowPos;
	}

	public void setRowPos(Map<String, Integer> rowPos) {
		this.rowPos = rowPos;
	}

	public Map<String, Integer> getColPos() {
		return colPos;
	}

	public void setColPos(Map<String, Integer> colPos) {
		this.colPos = colPos;
	}

	public Matrix getSimMatrix() {
		return simMatrix;
	}
	public void setSimMatrix(Matrix simMatrix){
		this.simMatrix=simMatrix;
	}
} 
