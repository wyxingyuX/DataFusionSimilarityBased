package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class ToolKit {
  public static boolean equals(double value,double base,double maxError){//maxError 
	  return RangeTool.isInRange(value, base-maxError, base+maxError);
  }
  public static void sortFileLineByKey(String filePath,String seperater) throws Exception{
	  BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
	  Map<String,String> sortMap=new TreeMap<String,String>();
	  String curLine="";
	  String[] elms=null;
	  while((curLine=br.readLine())!=null){
		  elms=curLine.split(seperater);
		  String key=elms[0];
		  String value=curLine.substring(key.length()+1, curLine.length());
		  sortMap.put(key, value);
	  }
	  br.close();
	  
	  OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
	  for(Map.Entry<String, String> entry:sortMap.entrySet()){
		  osw.write(entry.getKey()+seperater+entry.getValue());
		  osw.write("\r\n");
	  }
	  osw.flush();
	  osw.close();
  }
	
	public static void mergeIdAndDataFile2IdDataFile(String idFile,String idSeperater,String dataFile,String dataSeperater,
			                                         String idDataFile,String idDataSeperater) throws IOException{
		BufferedReader idReader=FileTool.getBufferedReaderFromFile(idFile);
		BufferedReader dataReader=FileTool.getBufferedReaderFromFile(dataFile);
		PrintWriter writer=FileTool.getPrintWriterForFile(idDataFile);
		
		String idLine="";
		String dataLine="";
		while((idLine=idReader.readLine())!=null&&(dataLine=dataReader.readLine())!=null){
			String[] elmsId=idLine.split(idSeperater);
			String id=elmsId[0];
			writer.write(id+idDataSeperater);
			String[] elmsData=dataLine.split(dataSeperater);
			for(int i=1;i<elmsData.length;++i){
				writer.write(elmsData[i]+idDataSeperater);
			}
			writer.write("\r\n");
		}
		idReader.close();
		dataReader.close();
		writer.close();
	}
	public static void copy(String srcFile,String destFile) throws IOException{
		BufferedReader reader=FileTool.getBufferedReaderFromFile(srcFile);
		PrintWriter writer=FileTool.getPrintWriterForFile(destFile);
		String line="";
		while((line=reader.readLine())!=null){
			writer.write(line);
			writer.write("\r\n");
		}
		writer.close();
	}
	public static void generateIdTypeAndTypeData(String idFile,String idSeperater,String idDataFile,String idDataSeperater,
			                               String dataFile,String dataSeperater) throws IOException{
	 BufferedReader idReader=FileTool.getBufferedReaderFromFile(idFile);
	 BufferedReader idDataReader=FileTool.getBufferedReaderFromFile(idDataFile);
	 PrintWriter writer=FileTool.getPrintWriterForFile(dataFile);
	 String idLine="";
	 String idDataLine="";
	 while((idLine=idReader.readLine())!=null&&(idDataLine=idDataReader.readLine())!=null){
		 String[] elmsId=idLine.split(idSeperater);
		 String type=elmsId[1];
		 writer.write(type);
		 String[] elmsIdData=idDataLine.split(idDataSeperater);
		 for(int i=1;i<elmsIdData.length;++i){
			 writer.write(dataSeperater+elmsIdData[i]);
		 }
		 writer.write("\r\n");
	 }
	 writer.close();
	 
	 copy(idFile,FileTool.getParentPath(dataFile)+"\\id.txt");
	}
}
