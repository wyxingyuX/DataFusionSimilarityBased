package test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import utils.DictTool;
import utils.FormatTransfTool;
import utils.SaveInfoTool;
import utils.SelElmentsTool;
import utils.SplitFileTool;
import utils.ToolKit;

public class testTool {

	public static void excTest() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\tags2userVec\\workForDataFusionSimBased\\uidTag\\";
		String allUidTags=base+"source\\uidTag.txt";
		String selUid=base+"source\\allTrainTestUidGender.txt";
		String destFile=base+"dest\\selUidTag.txt";
		SelElmentsTool.selReserveElmetsFromFile(allUidTags, "\t", selUid, "\t", destFile);
		SplitFileTool.splitFileElms2Files(destFile, "\t", 1, base+"dest\\selUid.txt",  base+"dest\\selTag.txt", "\t");
		Map<String,Integer> tagDict=DictTool.creatDict(base+"dest\\selTag.txt", "\t", 1);
		SaveInfoTool.saveDict(tagDict, base+"dest//tagDict.txt", "\t");
	}
	public static void excSplitAndDict() throws Exception{
		String base="F:\\ExpData\\DataFromOther\\zn\\";
		String allUidTags=base+"combineAllFenci.txt";
		String pBase=base+"process\\";
		String destUid=pBase+"uid.txt";
		String destWords=pBase+"weiboWords.txt";
		String destDict=pBase+"wordDict.txt";
		
		SplitFileTool.splitFileElms2Files(allUidTags, "\\s{1,}", 1, destUid, destWords , "\t");
		Map<String,Integer> dict=DictTool.creatDict(destWords, "\t", 1);
		SaveInfoTool.saveDict(dict, destDict, "\t");
	}
	public static void excSelElms() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\tags2userVec\\workForDataFusionSimBased\\tagVec\\";
		String allTagsVec=base+"source\\WordVec\\ExtractTags_genderpre_18w_vector.txt";
		String selTags=base+"source\\SelTagDict\\tagDict.txt";
		String destFile=base+"dest\\selTagVec.txt";
		SelElmentsTool.selReserveElmetsFromFile(allTagsVec, "\\s{1,}", selTags, "\t", destFile);
	}
	public static void excNoDiemVec2DiemVec() throws Exception{
		String base="E:\\ExpWorkSpace\\curWork\\tags2userVec\\workForDataFusionSimBased\\tagVec\\";
		String noDiemVecFile=base+"dest\\selTagVec.txt";
		String destDiemVecFile=base+"dest\\selTagDiemVec.txt";
		
		FormatTransfTool.noDiemVec2DiemVecFromFile(noDiemVecFile, "\\s{1,}", destDiemVecFile, "\t", 1);
				
	}
	public static void excMergeIdAndData() throws IOException{
		String dir="F:\\ExpData\\DataIntegate\\source\\colMatrix\\";
		File f=new File(dir);
		File[] fs=f.listFiles();
		for(File d: fs){
			ToolKit.mergeIdAndDataFile2IdDataFile(d.getAbsolutePath()+"\\id.txt", "\t", d.getAbsolutePath()+"\\data.txt", "\t",
					d.getAbsolutePath()+"\\idData.txt", "\t");
		}
	}
	public static void excGenerateIdTypeAndTypeData() throws IOException{
		String dir="F:\\ExpData\\DataIntegate\\source\\colMatrix\\";
		File f=new File(dir);
		File[] fs=f.listFiles();
		for(File d: fs){
			ToolKit.generateIdTypeAndTypeData(d.getAbsolutePath()+"\\id.txt", "\t",
					d.getAbsolutePath()+"\\simMa.txt","\t", d.getAbsolutePath()+"\\simMa\\data.txt", "\t");
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//excSelElms();
		//excNoDiemVec2DiemVec();
		//excSplitAndDict();
		//excMergeIdAndData();
		//excGenerateIdTypeAndTypeData();
	}

}
