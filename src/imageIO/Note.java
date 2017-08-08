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

	private JTextArea area = new JTextArea(8,30) ; // �����ı���
	private JFrame frame = new JFrame("Welcome To MLDN") ;
	private JButton open = new JButton("���ô���·��") ;
	private JButton save = new JButton("���ý��·��") ;
	private JButton pos1 = new JButton("��������") ;
	private JButton start = new JButton("��ʼƴ��") ;
	private JLabel label = new JLabel("һ.��������Ϳ�ȸ߶��Կո�ָ��� 0 0 0 0 ��.��ֱ�ѡ����·���ʹ���·��") ;
	private JPanel butPan = new JPanel() ;

	public Note(){
		
		this.butPan.add(open) ; // ������м��밴ť
		this.butPan.add(save) ; // ������м��밴ť
		this.butPan.add(pos1) ; // ������м��밴ť
		this.butPan.add(start) ; // ������м��밴ť
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
		File file = null ; // �����ļ�
		int result = 0 ; // ���ղ���״̬
		JFileChooser fileChooser = new JFileChooser() ; // �ļ�ѡ���
		if(e.getSource()==this.open){ // ��ʾִ�е��Ǵ򿪲���
			//			this.area.setText("") ; // �򿪽�����������������
			fileChooser.setApproveButtonText("ȷ��") ;
			fileChooser.setDialogTitle("���ļ���") ;
			fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showOpenDialog(this.frame) ;
			if(result==JFileChooser.APPROVE_OPTION){ // ѡ�����ȷ����ť
				file = fileChooser.getSelectedFile() ; // �õ�ѡ����ļ�
				this.label.setText("�򿪵�·��Ϊ�� " + file.getPath()) ;
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("û��ѡ���κ��ļ�") ;
			}else{
				this.label.setText("�������ִ���") ;
			}
			if(file!=null){
				src=file;
			}
		}
		
		if(e.getSource()==this.save){ // �ж��Ƿ��Ǳ������
			//			this.area.setText("") ; // �򿪽�����������������
			fileChooser.setApproveButtonText("ȷ��") ;
			fileChooser.setDialogTitle("���ļ���") ;
			fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showOpenDialog(this.frame) ;
			if(result==JFileChooser.APPROVE_OPTION){ // ѡ�����ȷ����ť
				file = fileChooser.getSelectedFile() ; // �õ�ѡ����ļ�
				this.label.setText("�򿪵�·��Ϊ�� " + file.getPath()) ;
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("û��ѡ���κ��ļ� ,Ĭ��Ϊ����·��") ;
				res=src;
			}else{
				this.label.setText("�������ִ���") ;
			}
			if(file!=null){
				res=file;

			}
		}

		if(e.getSource()==this.pos1){ // �ж��Ƿ����������
			this.posdim=this.area.getText();
			//System.out.println(this.area.getText());
		}
		//System.out.println(src.toString()+res+posdim);
		if(e.getSource()==this.start){ // �ж��Ƿ��Ǳ������
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
