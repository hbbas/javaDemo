package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
 

/**
 * 完成datagram。receive（）的功能，完成后再传递的DatagramPackage中取数据
 * @author hbbas
 *
 */
public class Receiver extends Thread {
	private DatagramSocket recSoc;
	private DatagramPacket messPackage;
	private int port;
	
	/*
	 * 给定一个端口号和一个接受的datagramPackage，构造本对象
	 */
	public Receiver(DatagramPacket dataP,int port)   {
		this.port= port;
		messPackage = dataP;
	}
	/*
	 * 此方法发送数据
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
