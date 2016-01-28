package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DictTool {
	public static Map<String,Integer> creatDict(String sourceWordsFile,String seperater,int startCode) throws Exception{
		BufferedReader brWords=new BufferedReader(new InputStreamReader(new FileInputStream(sourceWordsFile),"UTF-8"));
		Map<String,Integer> dict=new HashMap<String,Integer>();

		String curWordsLine="";
		String[] elms=null;
		while((curWordsLine=brWords.readLine())!=null){
			if(curWordsLine.equals("")) continue;
			elms=curWordsLine.split(seperater);
			for(int i=0;i<elms.length;++i){
				if(!dict.containsKey(elms[i])){
					int code=dict.size()+startCode;
					dict.put(elms[i], code);
				}
			}
		}
		System.out.println(dict.size()+" words been coded.");
		brWords.close();
		return dict;
	}
	public static Map<String,Integer> loadWordDictFromFile(String wordCodeDictFile,String seperater) throws Exception{
		BufferedReader brWordCodeDict=new BufferedReader(new InputStreamReader(new FileInputStream(wordCodeDictFile),"UTF-8"));
        Map<String,Integer> wordCodeMap=new HashMap<String,Integer>();
		String curWordCodeDictLine="";

		String[] elms=null;
		while((curWordCodeDictLine=brWordCodeDict.readLine())!=null){
			elms=curWordCodeDictLine.split(seperater);
			if(!wordCodeMap.containsKey(elms[0])){
				int code=Integer.parseInt(elms[1]);
				wordCodeMap.put(elms[0], code);
			}
		}
		brWordCodeDict.close();
		
		return wordCodeMap;
	}
}
