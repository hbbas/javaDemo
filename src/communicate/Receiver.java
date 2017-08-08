package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
 

/**
 * ���datagram��receive�����Ĺ��ܣ���ɺ��ٴ��ݵ�DatagramPackage��ȡ����
 * @author hbbas
 *
 */
public class Receiver extends Thread {
	private DatagramSocket recSoc;
	private DatagramPacket messPackage;
	private int port;
	
	/*
	 * ����һ���˿ںź�һ�����ܵ�datagramPackage�����챾����
	 */
	public Receiver(DatagramPacket dataP,int port)   {
		this.port= port;
		messPackage = dataP;
	}
	/*
	 * �˷�����������
	 */
	public void receive(DatagramPacket recPackge) throws IOException {
		recSoc = new DatagramSocket( port);
		recSoc.receive(recPackge);
	}

	
	@Override
	public void run() {
		super.run();
		try {
			receive(messPackage);
			System.out.println("get something");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
