package ref; 
  
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.util.Arrays;  
import java.util.Iterator;  
  
import javax.imageio.ImageIO;  
import javax.imageio.ImageReadParam;  
import javax.imageio.ImageReader;  
import javax.imageio.metadata.IIOMetadata;  
import javax.imageio.stream.ImageInputStream;  
  
/** 
 *  
 * Class ImageIOTest.java 
 *  
 * Description javax.imageio ���� 
 *  
 * Company mapbar 
 *  
 * author Chenll E-mail: Chenll@mapbar.com 
 *  
 * Version 1.0 
 *  
 * Date 2012-7-23 ����11:16:17 
 */  
public class Ref1 {  
  
    /** 
     * Java Image I/O API ֧�ֵĶ��룬д����ͨ�ĸ�ʽ�������Ҫ����������ʽ������Ҫ�����������eg JIMI, JMagic 
     */  
    public void formatImageNames() {  
  
        String[] imageFormats = ImageIO.getReaderFormatNames();  
        // [jpg, BMP, bmp, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]  
        System.out.println(Arrays.asList(imageFormats));  
  
        String[] imageFormats1 = ImageIO.getWriterFormatNames();  
        // [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]  
        System.out.println(Arrays.asList(imageFormats1));  
  
    }  
  
    /** 
     * Image I/O�߼����� ImageReader ImageWriter ͨ��ImageReader 
     * ���Ի�ȡͼƬ��Ϣ�����ð�����ͼƬ���ݶ������ڴ档 
     *  
     * @throws IOException 
     */  
    public void imageReaderOp() throws IOException {  
        Iterator<ImageReader> readers = ImageIO  
                .getImageReadersByFormatName("JPG");  
        ImageReader reader = readers.next();  
        // ��������Դ  
        File bigFile = new File("E:\\big.JPG");  
        ImageInputStream iis = ImageIO.createImageInputStream(bigFile);  
        // ����Դ��ImageReader����  
        reader.setInput(iis, true);  
        // ��ȡ��һ��ͼƬ�ĸ߶�,��ͼ�����ļ��ı�Ҫ���֣�ȥ��ȡͼƬ�Ŀ�ȣ������ö�ȡ�κ�һ������  
        int imageIndex = 0;  
        int width = reader.getWidth(imageIndex);  
    }  
  
    /** 
     * ����ͼƬ������ImageReadParam �����ó�����õĿ����ڴ棬����ָ��һ������Ȥ������ 
     *  
     * @throws IOException 
     */  
    public void imageReadParamOp() throws IOException {  
        int imageIndex = 0;  
        Iterator<ImageReader> readers = ImageIO  
                .getImageReadersByFormatName("JPG");  
        ImageReader reader = readers.next();  
        File bigFile = new File("E:\\big.JPG");  
        ImageInputStream iis = ImageIO.createImageInputStream(bigFile);  
        reader.setInput(iis, true);  
        ImageReadParam irp = reader.getDefaultReadParam();  
        int halfWidth = reader.getWidth(imageIndex) / 2;  
        int halfHeight = reader.getHeight(imageIndex) / 2;  
        Rectangle rect = new Rectangle(0, 0, halfWidth, halfHeight);  
        irp.setSourceRegion(rect);  
        BufferedImage bi = reader.read(imageIndex, irp);  
        ImageIO.write(bi, "JPG", new File("E:\\big_half.JPG"));  
    }  
  
    /** 
     * ͼƬԪ��Ϣ 
     *  
     * @throws IOException 
     */  
    public void ImageMetadata() throws IOException {  
        int imageIndex = 0;  
        Iterator<ImageReader> readers = ImageIO  
                .getImageReadersByFormatName("JPG");  
        ImageReader reader = readers.next();  
        File bigFile = new File("E:\\big.JPG");  
        ImageInputStream iis = ImageIO.createImageInputStream(bigFile);  
        reader.setInput(iis, true);  
        IIOMetadata metadata = reader.getImageMetadata(imageIndex);  
    }  
  
    /** 
     * ��������ͼ 
     *  
     * @throws IOException 
     */  
    public void generateSmall() throws IOException {  
        File bigFile = new File("E:\\big.JPG");  
        Image image = ImageIO.read(bigFile);  
        int width = image.getWidth(null); // 3264  
        int height = image.getHeight(null); // 2448  
        BufferedImage buffi = new BufferedImage(width / 2, height / 2,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics g = buffi.getGraphics();  
        g.drawImage(image, 0, 0, width / 2, height / 2, null);  
        g.dispose();  
        ImageIO.write(buffi, "JPG", new File("E:\\small.JPG"));// width:1632 height:1224  
    }  
  
    public static void main(String[] args) throws IOException {  
        Ref1 iot = new Ref1();  
        iot.generateSmall();  
        iot.formatImageNames();  
        iot.imageReaderOp();  
        iot.imageReadParamOp();  
        iot.ImageMetadata();  
    }  
  
} 