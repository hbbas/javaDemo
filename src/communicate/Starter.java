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
		String senMes= "hello  i am sender! ";
		String recMes;
		String add = "10.14.82.43";
		int port= 4700;
		DatagramPacket dataP;
		ArrayList<String> chatHis=new ArrayList();

		dataP= new DatagramPacket(new byte[1024],1023);
		Thread sender= new Sender(senMes,add,port); 
		Thread receiver= new Receiver(dataP,port ); 
		Screen screen = new Screen(chatHis);

		receiver.start();
		sender.start();
		recMes=new String(dataP.getData(),0,dataP.getLength());
		System.out.println("end"+recMes);


		while(true) {


		}

	}

}
