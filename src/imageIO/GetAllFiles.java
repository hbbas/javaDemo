package imageIO;

import java.io.File;

public class GetAllFiles {
	File file;
	public GetAllFiles(){
		file= new File("");
	}
	//获取当前目录
	public GetAllFiles(String dir){
		file=new File(dir);
	}
	public File[] get(){
		File[] allFile=file.listFiles();
		return allFile;
	}
	//得到当前目录下的所有文件路径并返回
}
