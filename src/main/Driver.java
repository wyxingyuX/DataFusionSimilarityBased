package main;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import func.AlgorithmManager;
import func.CosSimMatrix;
import func.PrepareKeyVecMatrix;
import utils.FormatTransfTool;
import utils.TestTool;

public class Driver {
	public static void excUserTag() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\dataFusionSimBased\\";
		String sourceBase=base+"source\\";
		String destBase=base+"dest\\";
		String tagVecFile=sourceBase+"tagVec\\selTagDiemVec.txt";
		String seedVecFile=sourceBase+"conceptVec\\seedVec.txt";
		String userVecWeiboFile=sourceBase+"userVecWeibo\\userVec_feature.txt";

		String userVecTagFile=sourceBase+"userFreqVecTag\\userTagFreqVec_feature.txt";
		String tagCodeDictFile=sourceBase+"userFreqVecTag\\tagDict.txt";
		String destDenseUserVecTagFile=destBase+"denseUserTagVec_feature.txt";

		PrepareKeyVecMatrix tagVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> tagVec=tagVecPm.prepareMatrix(tagVecFile, "\t");

		PrepareKeyVecMatrix seedVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> seedVec=seedVecPm.prepareMatrix(seedVecFile, "\t");

		CosSimMatrix tagTagCosSimMa=new CosSimMatrix();
		tagTagCosSimMa.computeSimMatrix(tagVec, seedVec);

		PrepareKeyVecMatrix userVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> userVecWeibo=userVecPm.prepareMatrix(userVecWeiboFile, "\t");

		CosSimMatrix userUserCosSimMa=new CosSimMatrix();
		userUserCosSimMa.computeSimMatrix(userVecWeibo, userVecWeibo);

		CosSimMatrix userTagCosSimMa=FormatTransfTool.keyDiemVec2CosSimMatrix(userVecTagFile, "\t", tagCodeDictFile, "\t",1);

		AlgorithmManager alg=new AlgorithmManager();
		alg.prepareTwoDenseMatrix(tagTagCosSimMa, userUserCosSimMa);
		alg.prepareCenterMatrix(userTagCosSimMa);

		alg.fillCenterMatrixX(1, 3, 2, 100, 0.7);
		alg.writeResult2File(destDenseUserVecTagFile, "\t");

	}
	public static void excUserConcept() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\dataFusionSimBased\\";
		String sourceBase=base+"source\\";
		String destBase=base+"dest\\";
		String tagVecFile=sourceBase+"tagVec\\selTagDiemVec.txt";
		String seedVecFile=sourceBase+"conceptVec\\expSeed\\seedVec.txt";
		String userVecWeiboFile=sourceBase+"userVecWeibo\\userVec_feature.txt";
		
		String userConceptFreqVecFile=sourceBase+"userFreqVecConcept\\userVecInConcept_feature.txt";
		String conceptDictFile=sourceBase+"userFreqVecConcept\\wordDict.txt";
		String destDenseUserConceptFreqVecFile=destBase+"denseUserConcept\\denseUserConceptFreqVec_feature.txt";

		PrepareKeyVecMatrix tagVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> tagVec=tagVecPm.prepareMatrix(tagVecFile, "\t");

		PrepareKeyVecMatrix seedVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> seedVec=seedVecPm.prepareMatrix(seedVecFile, "\t");

		CosSimMatrix conceptTagCosSimMa=new CosSimMatrix();
		conceptTagCosSimMa.computeSimMatrix(seedVec, tagVec);

		PrepareKeyVecMatrix userVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> userVecWeibo=userVecPm.prepareMatrix(userVecWeiboFile, "\t");

		CosSimMatrix userUserCosSimMa=new CosSimMatrix();
		userUserCosSimMa.computeSimMatrix(userVecWeibo, userVecWeibo);

		CosSimMatrix userConceptCosSimMa=FormatTransfTool.keyDiemVec2CosSimMatrix(userConceptFreqVecFile, "\t", conceptDictFile, "\t",1);

		AlgorithmManager alg=new AlgorithmManager();
		alg.prepareTwoDenseMatrix(conceptTagCosSimMa, userUserCosSimMa);
		alg.prepareCenterMatrix(userConceptCosSimMa);

		alg.fillCenterMatrixX(1, 3, 2, 100, 0.7);
		alg.writeResult2File(destDenseUserConceptFreqVecFile, "\t");
	}
	public static void excUserWeibo() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\dataFusionSimBased\\";
		String sourceBase=base+"source\\";
		String destBase=base+"dest\\";
		String weiboVecFile=sourceBase+"weiboVec\\selWordDiemVec.txt";
		String seedVecFile=sourceBase+"conceptVec\\inWeibo\\seed3\\seedVec.txt";
		String userW2vVecWeiboFile=sourceBase+"userW2vVecWeibo\\avg\\userVec_feature.txt";

		String userFreqVecWeiboFile=sourceBase+"userFreqVecWeibo\\userWeiboLineVec_feature.txt";
		String weiboCodeDictFile=sourceBase+"userFreqVecWeibo\\wordsDict.txt";
		String destDenseUserVecWeiboFile=destBase+"denseUserWeibo\\denseUserWeiboFreqVec_feature.txt";

		PrepareKeyVecMatrix weiboVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> weiboVec=weiboVecPm.prepareMatrix(weiboVecFile, "\t");
		
		PrepareKeyVecMatrix seedVecPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> seedVec=seedVecPm.prepareMatrix(seedVecFile, "\t");

		CosSimMatrix weiboWeiboCosSimMa=new CosSimMatrix();
		weiboWeiboCosSimMa.computeSimMatrix(weiboVec, seedVec);

		PrepareKeyVecMatrix userW2vVecWeiboPm=new PrepareKeyVecMatrix();
		Map<String,TreeMap<Integer,Float>> userW2vVecWeibo=userW2vVecWeiboPm.prepareMatrix(userW2vVecWeiboFile, "\t");

		CosSimMatrix userUserCosSimMa=new CosSimMatrix();
		userUserCosSimMa.computeSimMatrix(userW2vVecWeibo, userW2vVecWeibo);

		CosSimMatrix userWeiboCosSimMa=FormatTransfTool.keyDiemVec2CosSimMatrix(userFreqVecWeiboFile, "\t", weiboCodeDictFile, "\t",1);

		AlgorithmManager alg=new AlgorithmManager();
		alg.prepareTwoDenseMatrix(weiboWeiboCosSimMa, userUserCosSimMa);
		alg.prepareCenterMatrix(userWeiboCosSimMa);

		alg.fillCenterMatrixX(1, 3, 2, 1, 2);
		alg.writeResult2File(destDenseUserVecWeiboFile, "\t");
		
	}
	
	public static void excUserSim() throws Exception{
		String dir="F:\\ExpData\\DataIntegate\\source\\colMatrix\\";
		File f=new File(dir);
		File[] fs=f.listFiles();
        CosSimMatrix[] simMas=new CosSimMatrix[fs.length];
        int i=0;
		for(File d: fs){
			PrepareKeyVecMatrix pm=new PrepareKeyVecMatrix();
			Map<String,TreeMap<Integer,Float>> keyVecMa=pm.prepareMatrix(d.getAbsolutePath()+"\\idData.txt", "\t");
			CosSimMatrix simMa=new CosSimMatrix();
			simMa.computeSimMatrix(keyVecMa, keyVecMa);
			
			TestTool.println(d.getName()+" SimMas perpred Completed.");
			simMas[i]=simMa;
			++i;
		}
		TestTool.println(i+" simMas perpred Completed.");
		
		for(int k=0;k<simMas.length;++k){
			CosSimMatrix X=simMas[k];
			String destFillX=fs[k].getParent()+"\\"+"Fill_"+fs[k].getName()+"\\simMa.txt";
			CosSimMatrix Y=simMas[(k+1)%(simMas.length)];
			CosSimMatrix Z=simMas[(k+2)%(simMas.length)];
		    
			AlgorithmManager alg=new AlgorithmManager();
			alg.prepareTwoDenseMatrix(Y, Z);
			alg.prepareCenterMatrix(X);

			//alg.fillCenterMatrixX(2, 2, 4, 50, 1);
			alg.fillCenterMatrixX(0.1, 10, 1, 30, 1);
			alg.writeCentrixMatrix2File(destFillX, "\t", 10);
		}
	
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//excUserWeibo();
		//excUserSim();
	}

}
