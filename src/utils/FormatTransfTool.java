package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import Jama.Matrix;
import func.CosSimMatrix;

public class FormatTransfTool {
	public static String noDiemVec2DiemVec(String noDiemVec,String seperater,int startDiem,String destSeperater){
		String[] elms=noDiemVec.split(seperater);
		StringBuilder diemVecBuilder=new StringBuilder(); 
		String itemSeperater=":";
		for(int i=0;i<elms.length;++i){
			diemVecBuilder.append((i+startDiem)+itemSeperater+elms[i]+destSeperater);
		}
		return diemVecBuilder.toString();
	}
	public static void noDiemVec2DiemVecFromFile(String noDiemVecFile,String noDiemVecSeperater,String destDiemVecFile,String destSeperater,int startDiem) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(noDiemVecFile),"UTF-8")); 
		FileTool.guaranteeFileDirExist(destDiemVecFile);
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(destDiemVecFile),"UTF-8");

		String curLine="";
		String[] elms=null;
		while((curLine=br.readLine())!=null){
			if(curLine.equals("")){
				osw.write(curLine+destSeperater+"1:0"+destSeperater);
			}
			else{
				elms=curLine.split(noDiemVecSeperater);
				if(elms.length>1){
					String key=elms[0];
					String noDiemVec=curLine.substring(key.length()+1,curLine.length());//have latent bug.(if seperater not only ocuupy 1 seat)
					String diemVec=noDiemVec2DiemVec(noDiemVec,noDiemVecSeperater,startDiem,destSeperater);
					osw.write(key+destSeperater+diemVec);
				}
				else{//if word no vec
					String key=elms[0];
					String defaultVec="1:0"+destSeperater+"2:0"+destSeperater;
					osw.write(key+defaultVec);
				}
			}
			osw.write("\r\n");
		}
		osw.flush();
		osw.close();
		br.close();
	}
	public static CosSimMatrix keyDiemVec2CosSimMatrix(String keyDiemVecFile,String keyDiemVecSeperater,String featureCodeDictFile,String featureCodeDictSeperater,int startCodeOffset/*词典起始编码偏离0的量*/) throws Exception{
		CosSimMatrix cosSimMatrix=new CosSimMatrix();
		Map<String,Integer> rowPos=DictTool.loadWordDictFromFile(featureCodeDictFile, featureCodeDictSeperater);
		cosSimMatrix.setRowPos(rowPos);
		int rowNum=rowPos.size();
		int colNum=FileTool.getFileLineNum(keyDiemVecFile);
		Matrix simMatrix=new Matrix(rowNum,colNum,0);
		Map<String,Integer> colPos=new HashMap<String,Integer>();

		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(keyDiemVecFile),"UTF-8"));
		String curLine="";
		String[] elms=null;
		int colIndex=0;
		String itemSeperater=":";
		while((curLine=br.readLine())!=null){
			elms=curLine.split(keyDiemVecSeperater);
			String key=elms[0];
			colPos.put(key,colIndex );

			for(int i=1;i<elms.length;++i){
				String[] elmsItem=elms[i].split(itemSeperater);
				int diem=Integer.parseInt(elmsItem[0]);
				float value=Float.parseFloat(elmsItem[1]);
				diem=diem-startCodeOffset;//矩阵的行是从0开始计数
				simMatrix.set(diem, colIndex, value);//
			}
			++colIndex;
		}
		cosSimMatrix.setColPos(colPos);
		cosSimMatrix.setSimMatrix(simMatrix);

		return cosSimMatrix;

	}
	public static void countVec2FreqVecFromFile(String countVecFile,String countVecSeperater,
			                                    String freqVecFile,String freqVecSeperater,float times) throws Exception{
		BufferedReader br=FileWrapperTool.getStreamBufferedReader(countVecFile);
		FileTool.guaranteeFileDirExist(freqVecFile);
		OutputStreamWriter osw=FileWrapperTool.getStreamWriter(freqVecFile);
		String itemSeperater=":";
		String curLine="";
		String[] elms=null;
		while((curLine=br.readLine())!=null){
		    elms=curLine.split(countVecSeperater);
		    String key=elms[0];
		    osw.write(key+freqVecSeperater);
		    int sum=0;
		    for(int i=1;i<elms.length;++i){
		    	String[] items=elms[i].split(itemSeperater);
		    	sum+=Integer.parseInt(items[1]);
		    }
		    for(int i=1;i<elms.length;++i){
		    	String[] items=elms[i].split(itemSeperater);
		    	float freq=(float) (times*Integer.parseInt(items[1])/(sum*1.0));
		        osw.write(items[0]+itemSeperater+freq+freqVecSeperater);
		    }
		    osw.write("\r\n");
		}
		
		osw.flush();
		osw.close();
		br.close();
	}
}
