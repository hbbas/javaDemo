package imageIO;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class CollectImage {
	File[] srcFile;
	File resFile;
	Point pon;
	Dimension dim;
	

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
		//		cll.config();
		Note note=new Note();
		CollectImage cll=new CollectImage();
		while (true) {
			synchronized (note) {
				try {
					note.wait();
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				cll.srcFile=note.getSrc().listFiles();
				cll.resFile=new File((note.getRes().getPath())+"\\result.jpg");	
			}
			cll.dim=note.getDim();
			cll.pon=note.getPos();
			//		System.out.println(cll.dim.width+cll.pon.toString());
			cll.imageOp(cll.srcFile, cll.pon, cll.dim, cll.resFile);
		}
	}

	public void config(){
		ResourceBundle rsb=ResourceBundle.getBundle("imageIO.Config");
		File tem=new File(rsb.getString("src"));
		srcFile=tem.listFiles();
		resFile=new File(rsb.getString("result"));
		pon=new Point(Integer.parseInt(rsb.getString("positionX")),Integer.parseInt(rsb.getString("positionY")));
		dim=new Dimension(Integer.parseInt(rsb.getString("width")),Integer.parseInt(rsb.getString("height")));
	}
	
	
	public void imageOp(File[] file,Point pon, Dimension dim,File resFile ) throws IOException { 
		/*
		 * ʵ��ͼƬ�Ľ�ȡ
		 */
		int width=1280;		
		int rowNum=(int) Math.floor(1280/dim.width);
		int colNum=(int) Math.ceil(file.length/rowNum+1);
		int heigth=dim.height*colNum;

		if(heigth==0) heigth=dim.height;
		
	    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPG");  
	    ImageReader reader = readers.next(); 	    //��������Դ   
	    ImageReadParam irp = reader.getDefaultReadParam(); 
	    Rectangle rect = new Rectangle(pon,dim);   //������Ҫ�ľ�������
	    irp.setSourceRegion(rect);  
	    
	    ArrayList<int[]> imgRGBs=new ArrayList<int[]>();//����RBG��ֵ
	    int[] imgRGB;
	    for(int i=0;i<file.length;i++){
	    	ImageInputStream iis = ImageIO.createImageInputStream(file[i]);    // ����Դ��ImageReader����  
	    	reader.setInput(iis, true);  
	    	BufferedImage bi = reader.read(0, irp);  
	    	
	    	imgRGB=new int[dim.width*dim.height];
	    	imgRGB=bi.getRGB(0,0,dim.width, dim.height, imgRGB ,0, dim.width);
	    	imgRGBs.add(imgRGB);
	    	iis.close();
	    }
	    BufferedImage result=new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB); //�������ͼƬ����
	    int[ ] backGround=new int[width*heigth];
	    for(int i=0;i<backGround.length;i++) {
	    	backGround[i]=Integer.MAX_VALUE;
	    }
	    result.setRGB(0, 0, width, heigth, backGround, 0, dim.width);//���û���Ϊ��ɫ
	    
	    int rowCount=0;
	    int sx=0,sy=0;
	    for(int i=0;i<file.length;i++){
	    	if(i!=0&&(i)%rowNum==0){
	    		rowCount++;
	    		sy=rowCount*dim.height;
	    		sx=0;
	    	}
	    	result.setRGB(sx, sy, dim.width, dim.height, imgRGBs.get(i), 0, dim.width);
	    	sx+=dim.width;

	    }
	    
	    ImageIO.write(result, "JPG", resFile );        
	}  

}
