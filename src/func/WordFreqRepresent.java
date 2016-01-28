package func;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import utils.FileTool;


public class WordFreqRepresent {
	private Map<String,Integer> wordCodeDict;
   
	public WordFreqRepresent(){}
	public WordFreqRepresent(String wordCodeDictDir){
		System.out.println("Start init WordFreqRepresent.....");
		try {
			this.wordCodeDict=this.loadWordCodeDict(wordCodeDictDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End init WordFreqRepresent.");
	}

	protected Map<String,Integer> loadWordCodeDict(String wordCodeDictDir) throws Exception{
		File dir=new File(wordCodeDictDir);
		File[] wordDicts=dir.listFiles();
		Map<String,Integer> wordCodeDict=new HashMap<String,Integer>();
		System.out.println("Start loadWordCodeDict from "+wordCodeDictDir);
		for(File wordDict:wordDicts){
			this.loadWordCodeDict(wordDict.getAbsolutePath(), wordCodeDict,1);
		}
		System.out.println("Total load "+wordCodeDict.size()+" entry to wordCodeCict.");
		System.out.println("End loadWordCodeDict from "+wordCodeDictDir);
		return wordCodeDict;
	}
	public void loadWordDictFromDir(String wordCodeDictDir) throws Exception{
		this.wordCodeDict=this.loadWordCodeDict(wordCodeDictDir);
	}
	public void loadWordDictFromFile(String wordCodeDictFile) throws Exception{
		this.wordCodeDict=new HashMap<String,Integer>();
		this.loadWordCodeDict(wordCodeDictFile, this.wordCodeDict, 0);
	}
	protected void loadWordCodeDict(String wordCodeDictFile,Map<String,Integer> wordCodeMap,int offset) throws Exception{
		BufferedReader brWordCodeDict=new BufferedReader(new FileReader(wordCodeDictFile));

		System.out.println("Start loadWordCodeDict from "+wordCodeDictFile);
		String curWordCodeDictLine="";
		int lineNum=0;
		int readNum=0;
		String[] elms=null;
		String seperater="\t";
		while((curWordCodeDictLine=brWordCodeDict.readLine())!=null){
			elms=curWordCodeDictLine.split(seperater);
			if(!wordCodeMap.containsKey(elms[0])){
				int code=Integer.parseInt(elms[1]);
				code=code+offset;
				wordCodeMap.put(elms[0], code);
				++readNum;
			}
			++lineNum;
		}
		System.out.println("Load "+readNum+" to dict, Read "+lineNum+" from "+wordCodeDictFile);
		brWordCodeDict.close();
		System.out.println("End loadWordCodeDict from "+wordCodeDictFile);
	}
	
	public String computeWordFreRepresentStr(String words,String seperater,String destSeperater){
		Map<Integer,Integer> worFreqVec=this.computeWordFreRepresent(words, seperater);
		String[] elms=words.split(seperater);
		StringBuilder worFreqVecStr=new StringBuilder();
		worFreqVecStr.append(elms[0]+destSeperater);//elms[0] is uid
		String itemSeperater=":";
		for(Map.Entry<Integer, Integer> worFreqVecEntry:worFreqVec.entrySet()){
			worFreqVecStr.append(worFreqVecEntry.getKey()+itemSeperater+worFreqVecEntry.getValue()+destSeperater);
		}
		return worFreqVecStr.toString();
	}
	public String computeWordFreRepresentStr(String words,String seperater){
		return this.computeWordFreRepresentStr(words, seperater, "\t");
	}
	public Map<Integer,Integer> computeWordFreRepresent(String words,String seperater){
		Map<Integer,Integer> worFreqVec=new TreeMap<Integer,Integer>();
		String[] elms=words.split(seperater);
		
		for(int i=1;i<elms.length;++i){//i=1
			int code=-1;
			if(this.wordCodeDict.containsKey(elms[i])) code=this.wordCodeDict.get(elms[i]);
			if(worFreqVec.containsKey(code)){
				int oldFreq=worFreqVec.get(code);
				worFreqVec.put(code, oldFreq+1);
			}else{
				worFreqVec.put(code, 1);
			}
		}
		return worFreqVec;
	}
	
	public void computeWordFreRepresentInFile(String sourceUidWordsFile,String sourceSeperater,String destUidWordFreqRepresentFile) throws Exception{
		this.computeWordFreRepresentInFile(sourceUidWordsFile, sourceSeperater, destUidWordFreqRepresentFile, "\t");
	}
	public void computeWordFreRepresentInFile(String sourceUidWordsFile,String sourceSeperater,String destUidWordFreqRepresentFile,String destSeperater) throws Exception{
		BufferedReader brUidWords=new BufferedReader(new FileReader(sourceUidWordsFile));
		FileTool.guaranteeFileDirExist(destUidWordFreqRepresentFile);
		FileWriter fwUidWordFreqVec=new FileWriter(destUidWordFreqRepresentFile);
		
		System.out.println("Satrt computeWordFreRepresentInFile from "+sourceUidWordsFile+" to "+destUidWordFreqRepresentFile);
		String curUidWordsLine="";
		int lineNum=0;
		while((curUidWordsLine=brUidWords.readLine())!=null){
			String wordFreqVec=this.computeWordFreRepresentStr(curUidWordsLine, sourceSeperater,destSeperater);
			fwUidWordFreqVec.write(wordFreqVec+"\r\n");
			++lineNum;
		}
		System.out.println("Read "+lineNum+" line.");
		
		fwUidWordFreqVec.flush();
		fwUidWordFreqVec.close();
		brUidWords.close();
		System.out.println("End computeWordFreRepresentInFile from "+sourceUidWordsFile+" to "+destUidWordFreqRepresentFile);
	}
    
	public Map<String,Integer> getWordCodeDict(){
		return this.wordCodeDict;
	}

}
