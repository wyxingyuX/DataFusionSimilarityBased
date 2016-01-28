package test;

import other.WordVec;

public class testSeedVec {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	  String base="E:\\ExpWorkSpace\\curWork\\concept\\conceptVec\\inWeibo\\seed3\\";
	  String sourceBase=base+"source\\";
	  String destBase=base+"dest\\";
	  String wordVecFile="E:\\ExpWorkSpace\\curWork\\userWeibo\\weiboFilter\\weiboWordVec\\selWordVec\\source\\wordVec\\weiboFilter_vector.txt";
	  String seedFile=sourceBase+"seed\\seed_all.txt";
	  String seedVecFile=destBase+"seedVec.txt";
	  
      WordVec seedVec=new WordVec(wordVecFile);
      seedVec.computeSeedVecFromFile(seedFile, "\\s{1,}", seedVecFile, "\t");
	}

}
