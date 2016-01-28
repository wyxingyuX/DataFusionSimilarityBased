package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileTool {
	public static void guaranteeDirExist(String dirPath){
		File dir=new File(dirPath);
		if(!dir.exists()) dir.mkdirs();
	}
	public static void guaranteeFileDirExist(String file){
		File f=new File(file);
		FileTool.guaranteeDirExist(f.getParent());
	}
	public static void glanceFileContent(String filePath,int start,int end) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(filePath));
		String curLine="";
		int lineNum=0;
		while((curLine=br.readLine())!=null){
			++lineNum;
			if(lineNum>=start&&lineNum<=end) System.out.println(curLine);
			if(lineNum>end) break;
		}
	}
	public static int getFileLineNum(String filePath) throws Exception{
		BufferedReader br=new BufferedReader(new FileReader(filePath));

		String curLine="";
		int lineNum=0;
		while((curLine=br.readLine())!=null){
			++lineNum;
		}
		return lineNum;
	}

	public static String getParentPath(String filePath){
		return FileTool.getParentPath(new File(filePath));
	}
	public static String getParentPath(File file){
		return file.getParent();
	}
	public static BufferedReader getBufferedReaderFromFile(String filePath,String encoding) throws UnsupportedEncodingException, FileNotFoundException{
		return new BufferedReader(new InputStreamReader(new FileInputStream(filePath),encoding));
	}
	public static BufferedReader getBufferedReaderFromFile(String filePath) throws UnsupportedEncodingException, FileNotFoundException{
		return getBufferedReaderFromFile(filePath,"utf-8");
	}
	public static PrintWriter getPrintWriterForFile(String filePath,boolean isAppend,String encoding) throws UnsupportedEncodingException, FileNotFoundException{
		guaranteeFileDirExist(filePath);
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,isAppend), encoding)));
	}
	public static PrintWriter getPrintWriterForFile(String filePath,String encoding) throws UnsupportedEncodingException, FileNotFoundException{
		return getPrintWriterForFile(filePath,false,encoding);
	}
	public static PrintWriter getPrintWriterForFile(String filePath,boolean isAppend) throws UnsupportedEncodingException, FileNotFoundException{
		return getPrintWriterForFile(filePath,isAppend,"utf-8");
	}
	public static PrintWriter getPrintWriterForFile(String filePath) throws UnsupportedEncodingException, FileNotFoundException{
		return getPrintWriterForFile(filePath,false,"utf-8");
	}
}
