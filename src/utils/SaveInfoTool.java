package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class SaveInfoTool {
	public static void saveDict(Map<String,Integer> wordCodeDict,String destFile,String seperater){
		Map<Integer,String> treeMap=new TreeMap<Integer,String>();
		for(Map.Entry<String, Integer> entry:wordCodeDict.entrySet()){
			String word=entry.getKey();
			int code=entry.getValue();
			treeMap.put(code, word);
		}
		try {
			FileTool.guaranteeFileDirExist(destFile);
			OutputStreamWriter oswMyWordCodeDict=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
            for(Map.Entry<Integer, String> entry:treeMap.entrySet()){
            	int code=entry.getKey();
            	String word=entry.getValue();
            	oswMyWordCodeDict.write(word+seperater+code+"\r\n");
            }
            oswMyWordCodeDict.flush();
            oswMyWordCodeDict.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveKeyValueOfMap(Map<String,String> map,String destFile,String seperater) throws IOException{
		 OutputStreamWriter fw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
		 for(Map.Entry<String, String> entry:map.entrySet()){
			 fw.write(entry.getKey()+seperater+entry.getValue());
			 fw.write("\r\n");
		 }
		 fw.flush();
		 fw.close();
	 }
	public static void saveValueOfMap(Map<String,String> map,String destFile) throws Exception{
		 OutputStreamWriter fw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
		 for(Map.Entry<String, String> entry:map.entrySet()){
			 fw.write(entry.getValue());
			 fw.write("\r\n");
		 }
		 fw.flush();
		 fw.close();
	}
	public static void saveKeyOfMap(Map<String,String> map,String destFile) throws Exception{
		 OutputStreamWriter fw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
		 for(Map.Entry<String, String> entry:map.entrySet()){
			 fw.write(entry.getKey());
			 fw.write("\r\n");
		 }
		 fw.flush();
		 fw.close();
	}

}
