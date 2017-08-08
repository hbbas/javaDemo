package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
 

/**
 * ���datagram��receive�����Ĺ��ܣ���ɺ��ٴ��ݵ�DatagramPackage��ȡ����
 * @author hbbas
 *
 */
public class Receiver extends Thread {
	private DatagramSocket recSoc;
	private DatagramPacket messPackage;
	private int port;
	ArrayList<String> chatHis;
	Screen screen;
	
	/*
	 * ����һ���˿ںź�һ�����ܵ�datagramPackage�����챾����
	 */
	public Receiver(ArrayList<String> chatHis,DatagramPacket dataP,int port)   {
		this.port= port;
		messPackage = dataP;
		this.chatHis=chatHis;
	}
	/*
	 * �˷�����������
	 */
	public void receive(DatagramPacket recPackge) throws IOException {
		recSoc = new DatagramSocket( port);
		recSoc.receive(recPackge);
	}

	/*�˴��õ�main�����д�����Screen
	 */
	public void setScreen( Screen screen){
		this.screen=screen;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			while (true){
				receive(messPackage);
				chatHis.add(new String(messPackage.getData(),0,messPackage.getLength()));
				screen.setText();
			}
			//System.out.println("get something");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
