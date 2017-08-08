package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

/**
 * ���������
 * @author hbbas
 *
 */
public class Starter {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		int port= 4700;
		DatagramPacket messPackage;
		ArrayList<String> chatHis=new ArrayList<String>();
		//��ʼ����Դ
		Screen screen = new Screen(chatHis);
		messPackage= new DatagramPacket(new byte[1024],1023);
		
		//����receiver��sender
		Sender sender= new Sender(port);
		screen.setSender(sender);
		
		Receiver receiver= new Receiver(chatHis,messPackage,port); 
		receiver.setScreen(screen);


		receiver.start();


		//System.out.println("end"+recMes);

	}

}
