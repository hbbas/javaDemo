package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
 

/**
 * ���datagram��receive�����Ĺ��ܣ���ɺ��ٴ��ݵ�DatagramPackage��ȡ����
 * @author hbbas
 *
 */
public class Receiver extends Thread {
	private MulticastSocket recSoc;
	private DatagramPacket messPackage;
	private int port;
	ArrayList<String> chatHis;
	Screen screen;
	
	/*
	 * ����һ���˿ںź�һ�����ܵ�datagramPackage�����챾����
	 */
	public Receiver(ArrayList<String> chatHis,DatagramPacket dataP,int port)  throws IOException {
		this.port= port;
		messPackage = dataP;
		this.chatHis=chatHis;
		recSoc = new MulticastSocket( this.port);
		recSoc.setLoopbackMode(true);
	}
	/*
	 * �˷�����������
	 */
	public void receive(DatagramPacket recPackge) throws IOException  {
		
		recSoc.receive(recPackge);
	}

	/*�˴��õ�main�����д�����Screen
	 */
	public void setScreen( Screen screen){
		this.screen=screen;
	}
	public void setGroup( InetAddress add){
		try {
			recSoc.joinGroup(add);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
