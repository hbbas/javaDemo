package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
 

/**
 * 完成datagram。receive（）的功能，完成后再传递的DatagramPackage中取数据
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
	 * 给定一个端口号和一个接受的datagramPackage，构造本对象
	 */
	public Receiver(ArrayList<String> chatHis,DatagramPacket dataP,int port)   {
		this.port= port;
		messPackage = dataP;
		this.chatHis=chatHis;
	}
	/*
	 * 此方法发送数据
	 */
	public void receive(DatagramPacket recPackge) throws IOException {
		recSoc = new DatagramSocket( port);
		recSoc.receive(recPackge);
	}

	/*此处得到main方法中创建的Screen
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
