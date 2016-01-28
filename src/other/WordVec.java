package other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import utils.FileTool;
import utils.FileWrapperTool;

public class WordVec {
	private String sourceWordVecFileStr;
	private Map<String,float[]> wordVecMatrix;
	public WordVec(String sourceWordVecFileStr){
		this.setSourceWordVecFileStr(sourceWordVecFileStr);
		wordVecMatrix=new HashMap<String,float[]>();
		try {
			this.loadWordVec();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void loadWordVec() throws Exception{
		FileReader fr=new FileReader(this.getSourceWordVecFileStr());
		BufferedReader br=new BufferedReader(fr);

		System.out.println("Start load wordVec from "+this.getSourceWordVecFileStr());
		String curLine=null;
		String[] elms=null;
		br.readLine();br.readLine();
		int count=0;
		String seperater="\\s{1,}";
		while((curLine=br.readLine())!=null){
			++count;
			elms=curLine.split(seperater);
			//如果第一个是空格或tab
			if(elms[0].equals("")||elms[0].equals("\t")){
				float[] vec=new float[elms.length-2];
				for(int i=2;i<elms.length;++i) vec[i-1]=Float.parseFloat(elms[i]);
				this.wordVecMatrix.put(elms[1], vec);
			}
			else{//如果第一个不是是空格
				float[] vec=new float[elms.length-1];
				for(int i=1;i<elms.length;++i) vec[i-1]=Float.parseFloat(elms[i]);
				this.wordVecMatrix.put(elms[0], vec);
			}

		}
		System.out.println("Load "+count+" wordVec.");
		System.out.println("End load wordVec from "+this.getSourceWordVecFileStr());
	}
	protected float[] computeHeVec(float[] vec1,float[] vec2){
		float[] heVec=new float[vec1.length];
		for(int i=0;i<vec1.length;++i){
			heVec[i]=vec1[i]+vec2[i];
		}
		return heVec;
	}
	public void computeSeedVecFromFile(String seedFile,String sourceSeperater,String destSeedVecFile,String destSeperater) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(seedFile),"UTF-8"));
		FileTool.guaranteeFileDirExist(destSeedVecFile);
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(destSeedVecFile),"UTF-8");
		OutputStreamWriter oswDict=FileWrapperTool.getStreamWriter(FileTool.getParentPath(destSeedVecFile)+"\\wordDict.txt");

		String curLine="";
		String[] elms=null;
		float[] heVec=null;
		int cn=1;
		int j=0;
		while((curLine=br.readLine())!=null){
			if(curLine.equals("")) continue;
			elms=curLine.split(sourceSeperater);
			for(int i=0;i<elms.length;++i){
				String word=elms[i];
				if(this.wordVecMatrix.containsKey(word)){
					float[] vec=this.wordVecMatrix.get(word);
					if(j==0) heVec=vec;
					else{
						heVec=this.computeHeVec(vec, heVec);
					}
					++j;
				}
			}
			osw.write("seedLine"+cn+destSeperater);
			oswDict.write("seedLine"+cn+destSeperater+(cn-1));
			if(heVec!=null){
				for(int i=0;i<heVec.length;++i){
					osw.write((i+1)+":"+heVec[i]+destSeperater);
				}
			}
			osw.write("\r\n");
			oswDict.write("\r\n");
			++cn;
		}
		
		osw.flush();
		osw.close();
		oswDict.flush();
		oswDict.close();
		br.close();
	}
	public String getSourceWordVecFileStr() {
		return sourceWordVecFileStr;
	}
	public void setSourceWordVecFileStr(String sourceWordVecFileStr) {
		this.sourceWordVecFileStr = sourceWordVecFileStr;
	}
}
