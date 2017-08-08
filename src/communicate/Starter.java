package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

/**
 * 主程序入口
 * @author hbbas
 *
 */
public class Starter {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		int port= 4700;
		DatagramPacket messPackage;
		ArrayList<String> chatHis=new ArrayList<String>();
		//初始化资源
		Screen screen = new Screen(chatHis);
		messPackage= new DatagramPacket(new byte[1024],1023);
		
		//配置receiver和sender
		Sender sender= new Sender(port);
		screen.setSender(sender);
		
		Receiver receiver= new Receiver(chatHis,messPackage,port); 
		receiver.setScreen(screen);


		receiver.start();


		//System.out.println("end"+recMes);

	}

}
