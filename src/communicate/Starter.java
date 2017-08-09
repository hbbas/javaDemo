package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ���������
 * @author hbbas
 *
 */
public class Starter {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		InetAddress add;//�ಥ��ַ
		int port= 4700;//�ಥ�˿ں�
		DatagramPacket messPackage;//���յ����ݰ�
		ArrayList<String> chatHis=new ArrayList<String>();//�����¼�洢��
		HashMap<String,String> mem;//�洢�û���ip��ַӳ��
		String name;
		
		//��ʼ����Դ 
		Screen screen = new Screen(chatHis);
		messPackage= new DatagramPacket(new byte[1024],1023);
		add= InetAddress.getByName( "224.0.0.2");
		mem=new HashMap<>();
		mem.put("10.14.82.43","�α��");
		mem.put("","");
		mem.put("","");
		mem.put("","");
		name=mem.get( InetAddress.getLocalHost().getHostAddress());
		//����receiver��sender��screen
		Sender sender= new Sender(add,port);

		screen.setSender(sender);
		screen.setName(name);
		Receiver receiver= new Receiver(chatHis,messPackage,port); 
		receiver.setScreen(screen);
		receiver.setGroup(add);
		//��������
		receiver.start();
		screen.setText();
		chatHis.add("                           "+name+" ����������   ");
		screen.setText();
	}

}
