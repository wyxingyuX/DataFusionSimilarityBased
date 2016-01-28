package test;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import func.CosSimMatrix;
import func.PrepareKeyVecMatrix;

public class testCosSimMatrix {

	public static void test() throws Exception{
		String vecFile="E:\\ExpWorkSpace\\curWork\\dataFusionSimBased\\test\\aVec.txt";
		PrepareKeyVecMatrix vecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> vec=vecPm.prepareMatrix(vecFile, "\t");

		CosSimMatrix cosSimMa=new CosSimMatrix();
		cosSimMa.computeSimMatrix(vec, vec);
		cosSimMa.getSimMatrix().print(3, 3);
	}
	public static void writeSimMatrix() throws Exception{
		String dir="F:\\ExpData\\DataIntegate\\source\\colMatrix\\";
		File f=new File(dir);
		File[] fs=f.listFiles();
		for(File d:fs){
			PrepareKeyVecMatrix vecPm=new PrepareKeyVecMatrix();
			Map<String,TreeMap<Integer,Float>> vec=vecPm.prepareMatrix(d.getAbsolutePath()+"\\idData.txt", "\t");
			CosSimMatrix cosSimMa=new CosSimMatrix();
			cosSimMa.computeSimMatrix(vec, vec);
			cosSimMa.writeSimMatrixbyCol2KeyDiemVecFile(d.getAbsolutePath()+"\\simMa.txt","\t",10);
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//writeSimMatrix();
	}

}
