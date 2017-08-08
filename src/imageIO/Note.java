package imageIO;
import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileOutputStream ;
import java.io.PrintStream ;
import java.util.Scanner ;
import java.awt.BorderLayout ;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

import javax.swing.JFrame ;
import javax.swing.JTextArea ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.JPanel ;
import javax.swing.JFileChooser ;
import javax.swing.JScrollPane ;

class Note implements ActionListener{
	private File src;
	private File res;
	private String posdim;
	private Point pos=new Point();
	private Dimension dim=new Dimension();

	private JTextArea area = new JTextArea(8,30) ; // 定义文本区
	private JFrame frame = new JFrame("Welcome To MLDN") ;
	private JButton open = new JButton("设置处理路径") ;
	private JButton save = new JButton("设置结果路径") ;
	private JButton pos1 = new JButton("设置坐标") ;
	private JButton start = new JButton("开始拼合") ;
	private JLabel label = new JLabel("一.输入坐标和宽度高度以空格分隔如 0 0 0 0 二.请分别选择结果路径和处理路径") ;
	private JPanel butPan = new JPanel() ;

	public Note(){
		
		this.butPan.add(open) ; // 在面板中加入按钮
		this.butPan.add(save) ; // 在面板中加入按钮
		this.butPan.add(pos1) ; // 在面板中加入按钮
		this.butPan.add(start) ; // 在面板中加入按钮
		this.frame.setLayout(new BorderLayout(3,3)) ;
		this.frame.add(this.label,BorderLayout.NORTH) ;
		this.frame.add(this.butPan,BorderLayout.SOUTH) ;
		this.frame.add(new JScrollPane(this.area),BorderLayout.CENTER) ;
		this.frame.setSize(630,280) ;
		this.frame.setVisible(true) ;
		this.frame.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e){
						System.exit(1) ;
					}
				}
				) ;
		this.open.addActionListener(this) ;
		this.save.addActionListener(this) ;
		this.pos1.addActionListener(this) ;
		this.start.addActionListener(this) ;
	}

	public void actionPerformed(ActionEvent e){
		File file = null ; // 接收文件
		int result = 0 ; // 接收操作状态
		JFileChooser fileChooser = new JFileChooser() ; // 文件选择框
		if(e.getSource()==this.open){ // 表示执行的是打开操作
			//			this.area.setText("") ; // 打开将文字区域的内容清空
			fileChooser.setApproveButtonText("确定") ;
			fileChooser.setDialogTitle("打开文件夹") ;
			fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showOpenDialog(this.frame) ;
			if(result==JFileChooser.APPROVE_OPTION){ // 选择的是确定按钮
				file = fileChooser.getSelectedFile() ; // 得到选择的文件
				this.label.setText("打开的路径为： " + file.getPath()) ;
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("没有选择任何文件") ;
			}else{
				this.label.setText("操作出现错误") ;
			}
			if(file!=null){
				src=file;
			}
		}
		
		if(e.getSource()==this.save){ // 判断是否是保存操作
			//			this.area.setText("") ; // 打开将文字区域的内容清空
			fileChooser.setApproveButtonText("确定") ;
			fileChooser.setDialogTitle("打开文件夹") ;
			fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showOpenDialog(this.frame) ;
			if(result==JFileChooser.APPROVE_OPTION){ // 选择的是确定按钮
				file = fileChooser.getSelectedFile() ; // 得到选择的文件
				this.label.setText("打开的路径为： " + file.getPath()) ;
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("没有选择任何文件 ,默认为保存路径") ;
				res=src;
			}else{
				this.label.setText("操作出现错误") ;
			}
			if(file!=null){
				res=file;

			}
		}

		if(e.getSource()==this.pos1){ // 判断是否是坐标操作
			this.posdim=this.area.getText();
			//System.out.println(this.area.getText());
		}
		//System.out.println(src.toString()+res+posdim);
		if(e.getSource()==this.start){ // 判断是否是保存操作
			if(src!=null&&res!=null&&posdim!=null){
				synchronized (this) {
					this.notifyAll();
				}
			}
			//System.out.println(src.toString()+res+posdim);
		}		
	}
	public File getRes(){
		return res;
	}

	public File getSrc() {
		return src;
	}
	public Point getPos(){
		String[] tem=posdim.split(" ");
		pos.x=Integer.parseInt(tem[0]);
		pos.y=Integer.parseInt(tem[1]);
		return pos;
	}
	public Dimension getDim(){
		String[] tem=posdim.split(" ");
		dim.width=Integer.parseInt(tem[2]);
		dim.height=Integer.parseInt(tem[3]);
		return dim;
	}

}
