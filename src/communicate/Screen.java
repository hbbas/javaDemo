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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 这个类是外观类，显示面板操作
 * @author hbbas
 *
 */
public class Screen implements ActionListener{

		private JTextArea area = new JTextArea(8,30) ; // 定义文本区
		private JFrame frame = new JFrame("欢迎进入许老师课题组") ;
		private JButton send = new JButton("发送") ;
		private JLabel label = new JLabel("置顶内容：") ;
		private JPanel butPan = new JPanel();
		private ArrayList<String> chatHis;

		public Screen(ArrayList<String> chatHis){
			
			this.chatHis = chatHis;
			this.butPan.add(send) ; // 在面板中加入按钮
			this.frame.setLayout(new BorderLayout(3,3)) ;
			this.frame.add(this.label,BorderLayout.NORTH) ;
			this.frame.add(this.butPan,BorderLayout.SOUTH) ;
			this.frame.add(new JScrollPane(this.area),BorderLayout.CENTER) ;
			this.frame.setSize(630,280) ;
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
				area.setText(this.getText(chatHis));
			}
	
		}
		/*
		 * 用来获取chatHis中的历史记录整理成加上换行符号的String
		 */
		public String getText(ArrayList<String> strA) {
			StringBuilder sb= new StringBuilder();
			Iterator<String> iter=strA.iterator();
			while(iter.hasNext()) {
				sb.append("\\n");
				sb.append(iter.next());
			}
			
			return sb.toString();
		}

}
