package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 主程序入口
 * @author hbbas
 *
 */
public class Starter {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		InetAddress add;//多播地址
		int port= 4700;//多播端口号
		DatagramPacket messPackage;//接收的数据包
		ArrayList<String> chatHis=new ArrayList<String>();//聊天记录存储区
		HashMap<String,String> mem;//存储用户和ip地址映射
		String name;
		
		//初始化资源 
		Screen screen = new Screen(chatHis);
		messPackage= new DatagramPacket(new byte[1024],1023);
		add= InetAddress.getByName( "224.0.0.2");
		mem=new HashMap<>();
		mem.put("10.14.82.43","何斌斌");
		mem.put("","");
		mem.put("","");
		mem.put("","");
		name=mem.get( InetAddress.getLocalHost().getHostAddress());
		//配置receiver和sender和screen
		Sender sender= new Sender(add,port);

		screen.setSender(sender);
		screen.setName(name);
		Receiver receiver= new Receiver(chatHis,messPackage,port); 
		receiver.setScreen(screen);
		receiver.setGroup(add);
		//启动操作
		receiver.start();
		screen.setText();
		chatHis.add("                           "+name+" 加入了聊天   ");
		screen.setText();
	}

}
