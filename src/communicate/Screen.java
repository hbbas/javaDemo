package communicate;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * �����������࣬��ʾ������
 * @author hbbas
 *
 */
public class Screen implements ActionListener{

		private JTextArea area = new JTextArea(15,30) ; // �����ı���
		private JFrame frame = new JFrame("��ӭ��������ʦ������") ;
		private JButton send = new JButton("����") ;
		private JTextArea input = new JTextArea(1,30) ; 
		private JPanel butPan = new JPanel();
		private ArrayList<String> chatHis;
		private Sender sender;
		private String name;

		public Screen(ArrayList<String> chatHis){
			area.setEditable(false);
			area.setLineWrap(true);
			this.chatHis = chatHis;
			this.butPan.add(send) ; // ������м��밴ť
			this.frame.setLayout(new BorderLayout(3,3)) ;
			this.frame.add(this.input,BorderLayout.CENTER) ;
			this.frame.add(this.butPan,BorderLayout.SOUTH) ;
			this.frame.add(new JScrollPane(this.area),BorderLayout.NORTH) ;
			this.frame.setSize(300,380) ;
			this.frame.setVisible(true) ;
			this.frame.addWindowListener(
					new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e){
							System.exit(1) ;
						}
					}
					) ;
			this.send.addActionListener(this) ;
		}
		
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==this.send){ 
				String inputm=name+" :  "+input.getText( );
				input.setText("");
				sender.setMessage(inputm);
				sender.send();
				chatHis.add(inputm);
				this.setText();
			}
	
		}
		/*
		 * ������ȡchatHis�е���ʷ��¼����ɼ��ϻ��з��ŵ�String
		 */
		public String getText(ArrayList<String> strA) {
			StringBuilder sb= new StringBuilder();
			Iterator<String> iter=strA.iterator();
			while(iter.hasNext()) {
				sb.append(iter.next());
				sb.append("\n");
			}
			
			return sb.toString();
		}
		
		public void setText(){
			String tem=this.getText(chatHis);
			area.setText("");
			area.setText(tem);
			area.setCaretPosition(tem.length());
		}
		
		public void setSender(Sender sender){
			this.sender=sender;
		}


		public void setName(String name) {
			this.name = name;
		}

}
