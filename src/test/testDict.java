package test;

import java.util.Map;

import utils.DictTool;
import utils.SaveInfoTool;
import utils.SplitFileTool;

public class testDict {
	public static void main(String [] args) throws Exception{
        String base="E:\\ExpWorkSpace\\curWork\\userWeibo\\weiboFilter\\weiboUserVec\\clusterSimWord\\conceptBased\\replaceWordByConcept\\dest\\baseConcept2\\";
		String keyWordFile=base+"selUidWCOneLine.txt";
		String uidFile=base+"uid.txt";
		String wordsFile=base+"weiboFC.txt";
		String wordDictFile=base+"weiboFcDict.txt";
		SplitFileTool.splitFileElms2Files(keyWordFile, "\t", 1, uidFile, wordsFile, "\t");
		Map wordCodeDict=DictTool.creatDict(wordsFile, "\t", 1);
		SaveInfoTool.saveDict(wordCodeDict, wordDictFile, "\t");
	}
}
