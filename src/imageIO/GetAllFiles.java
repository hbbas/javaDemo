package imageIO;

import java.io.File;

public class GetAllFiles {
	File file;
	public GetAllFiles(){
		file= new File("");
	}
	//��ȡ��ǰĿ¼
	public GetAllFiles(String dir){
		file=new File(dir);
	}
	public File[] get(){
		File[] allFile=file.listFiles();
		return allFile;
	}
	//�õ���ǰĿ¼�µ������ļ�·��������
}
