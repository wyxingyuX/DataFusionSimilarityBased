package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileWrapperTool {
static String readCode="UTF-8";
static String writeCode="UTF-8";
   public static BufferedReader getStreamBufferedReader(String filePath) throws Exception{
	   return new BufferedReader(new InputStreamReader(new FileInputStream(filePath),readCode));
   }
   public static OutputStreamWriter getStreamWriter(String filePath) throws Exception{
	   return new OutputStreamWriter(new FileOutputStream(filePath),writeCode);
   }
}
