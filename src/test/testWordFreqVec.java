package test;

import func.WordFreqRepresent;
import utils.FormatTransfTool;

public class testWordFreqVec {
	public static void excTest() throws Exception{
		String base="F:\\ExpData\\DataFromOther\\zn\\";
		String sourceDict=base+"process\\wordDict.txt";
		String sourceUidWords=base+"combineAllFenci.txt";
		String destUidFreqVec=base+"process\\userWeiboFreq_feature.txt";
		
		WordFreqRepresent wordFreqRep=new WordFreqRepresent();
		wordFreqRep.loadWordDictFromFile(sourceDict);
		wordFreqRep.computeWordFreRepresentInFile(sourceUidWords, "\\s{1,}", destUidFreqVec);
		
	}
	public static void excComputeWordFreqVecInFile() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\userWeibo\\weiboFilter\\weiboUserVec\\clusterSimWord\\conceptBased\\freqVec\\";
		String sourceBase=base+"source\\baseConcept2\\";
		String destbase=base+"dest\\baseConcept2\\";

		String sourceDict=sourceBase+"wordDict\\weiboFcDict.txt";
		String sourceUidWords=sourceBase+"uidWordsOneLine\\selUidWCOneLine.txt";
		String destUidFreqVec=destbase+"userWeiboLineVec_feature.txt";

		WordFreqRepresent wordFreqRep=new WordFreqRepresent();
		wordFreqRep.loadWordDictFromFile(sourceDict);
		wordFreqRep.computeWordFreRepresentInFile(sourceUidWords, "\t", destUidFreqVec);
	}
	public static void excTransfCountVec2FreqVec() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\userWeibo\\weiboFilter\\weiboUserVec\\clusterSimWord\\conceptBased\\freqVec\\dest\\baseConcept2\\";
        String countVecFile=base+"userWeiboLineVec_feature.txt";
        String freqVecFile=base+"userWeiboLineFreqVec_feature.txt";
        String freqVecTimesFile=base+"userWeiboLineFreqVec105Times_feature.txt";
        FormatTransfTool.countVec2FreqVecFromFile(countVecFile, "\t", freqVecFile, "\t",1);
        FormatTransfTool.countVec2FreqVecFromFile(countVecFile, "\t", freqVecTimesFile, "\t",100000);
        
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//excTransfCountVec2FreqVec();
		//excComputeWordFreqVecInFile();
		//excTest();
	}

}
