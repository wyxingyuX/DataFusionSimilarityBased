package func;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import utils.MyVecTool;
import utils.TestTool;


public class PrepareKeyVecMatrix {
	private Map<String,TreeMap<Integer,Float>> keyVecMatrix;//最好用LinkedHashMap
	
	protected Map<String,TreeMap<Integer,Float>> loadKeyVecMatrixFromFile(String keyVecfilePath,String seperater) throws Exception{
		File keyVecfile=new File(keyVecfilePath);
		return this.loadKeyVecMatrixFromFile(keyVecfile, seperater);
	}
	protected Map<String,TreeMap<Integer,Float>> loadKeyVecMatrixFromFile(File keyVecfile,String seperater) throws Exception{
		if(!keyVecfile.exists()){
			TestTool.println("warn:"+keyVecfile.getAbsolutePath()+" don't exists.");
		    return null;
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(keyVecfile),"UTF-8"));
		Map<String,TreeMap<Integer,Float>> myKeyVecMatrix=new LinkedHashMap<String,TreeMap<Integer,Float>>();
		
		String curLine="";
		String[] elms=null;
		String[] diemAndValue=null;
		String itemSeperater=":";
		while((curLine=br.readLine())!=null){
			elms=curLine.split(seperater);
		
			TreeMap<Integer,Float> vec=new TreeMap<Integer,Float>();
			for(int i=1;i<elms.length;++i){
				diemAndValue=(elms[i]).split(itemSeperater);
				int diem=Integer.parseInt(diemAndValue[0]);
				float value=Float.parseFloat(diemAndValue[1]);
				vec.put(diem, value);
			}
		
			myKeyVecMatrix.put(elms[0], vec);
		}
		br.close();   
		return myKeyVecMatrix;
	}
	
	protected void unitMatrix(Map<String,TreeMap<Integer,Float>> keyVecMatrix){
		for(Map.Entry<String, TreeMap<Integer,Float>> entry:keyVecMatrix.entrySet()){
			String key=entry.getKey();
			TreeMap<Integer,Float> vec=entry.getValue();
			MyVecTool.unitVec(vec);
			keyVecMatrix.put(key, vec);
		}
	}
	
	public Map<String,TreeMap<Integer,Float>> prepareMatrix(String keyVecfilePath,String seperater) throws Exception{
		Map<String,TreeMap<Integer,Float>> pmatrix=new LinkedHashMap<String,TreeMap<Integer,Float>>();
		pmatrix=this.loadKeyVecMatrixFromFile(keyVecfilePath, seperater);
		this.unitMatrix(pmatrix);
		this.setKeyVecMatrix(pmatrix);
		return this.keyVecMatrix;
	}
	
	public Map<String,TreeMap<Integer,Float>> getKeyVecMatrix() {
		return keyVecMatrix;
	}
	protected void setKeyVecMatrix(Map<String,TreeMap<Integer,Float>> keyVecMatrix) {
		this.keyVecMatrix = keyVecMatrix;
	}	
}
