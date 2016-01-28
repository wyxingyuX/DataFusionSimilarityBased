package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class SelElmentsTool {
   public static void selReserveElmetsFromFile(String sourceFile,String sourceSeperater,String reserveElemFile,String resSeperater,String destFile) throws Exception{
	   Map<String,String> sourceMap=getMapFromFile(sourceFile,sourceSeperater);
	   BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(reserveElemFile),"UTF-8"));
	   FileTool.guaranteeFileDirExist(destFile);
	   OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
	   
	   String curLine="";
	   String[] elms=null;
	   while((curLine=br.readLine())!=null){
		   elms=curLine.split(resSeperater);
		   String key=elms[0];
		   String line=sourceMap.get(key);
		   if(line!=null) osw.write(line);
		   else osw.write(key);
		   osw.write("\r\n");
	   }
	   osw.flush();
	   osw.close();
	   br.close();    
   }
   protected static Map<String,String> getMapFromFile(String elemFile,String seperater) throws Exception{
	   Map<String,String> map=new HashMap<String,String>();
	   BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(elemFile),"UTF-8"));
	   String curLine="";
	   String[] elms=null;
	   while((curLine=br.readLine())!=null){
		   elms=curLine.split(seperater);
		   String key=elms[0];
		   map.put(key, curLine);
	   }
	   br.close();
	   return map;
   }
}
