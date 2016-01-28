package test;

import utils.FormatTransfTool;

public class testSelElms {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        String base="E:\\ExpWorkSpace\\curWork\\userWeibo\\weiboFilter\\weiboWordVec\\selWordVec\\";
        String sourceBase=base+"source\\";
        String destBase=base+"dest\\";
        String wordVecFile=sourceBase+"wordVec\\weiboFilter_vector.txt";
        String selWordFile=sourceBase+"selWord\\wordsDict.txt";
        String destSelWordVecFile=destBase+"selWordVec.txt";
        String destSelWordsDiemVecFile=destBase+"selWordDiemVec.txt";
        
//        SelElmentsTool.selReserveElmetsFromFile(wordVecFile, "\\s{1,}", selWordFile, "\t", destSelWordVecFile);
//        FileTool.glanceFileContent(destSelWordVecFile, 1, 3);
//        System.out.println(FileTool.getFileLineNum(destSelWordVecFile));
        FormatTransfTool.noDiemVec2DiemVecFromFile(destSelWordVecFile, "\\s{1,}", destSelWordsDiemVecFile, "\t", 1);
        
	}

}
