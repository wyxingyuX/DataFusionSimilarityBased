package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SplitFileTool {
	//splitPos是从第几个 seperater 劈开文件
	public static void splitFileElms2Files(String sourceFile,String sourceSeperater,int splitPos,String destFile1,String destFile2,String destSeperater) throws Exception{
		BufferedReader brSource=new BufferedReader(new FileReader(sourceFile));
		FileTool.guaranteeFileDirExist(destFile1);
		FileTool.guaranteeFileDirExist(destFile2);
		FileWriter fw1=new FileWriter(destFile1);
		FileWriter fw2=new FileWriter(destFile2);
		
		System.out.println("Start splitFileElms2Files from "+sourceFile);
		String curLine="";
		String[] elms=null;
		int lineNum=0;
		int readNum=0;
		while((curLine=brSource.readLine())!=null){
			++lineNum;
			if(curLine.equals("")){continue;}
			elms=curLine.split(sourceSeperater);
			for(int i=0;i<splitPos;++i) fw1.write(elms[i]+destSeperater);
			fw1.write("\r\n");
			for(int i=splitPos;i<elms.length;++i) fw2.write(elms[i]+destSeperater);
			fw2.write("\r\n");
			++readNum;
		}
		System.out.println("Read "+lineNum+" line, "+readNum+" line have uid");
		//
		fw1.flush();
		fw2.flush();
		fw1.close();
		fw2.close();
		brSource.close();
		System.out.println("End splitFileElms2Files from "+sourceFile);
	}
}
