package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 这个类主要是完成线程中的datagram.send()方法，完成datagram构造
 * @author hbbas
 *
 */
public class Sender extends Thread {
	private DatagramSocket sendData;
	private String message;
	private DatagramPacket messPackage;
	private InetAddress add;
	private int port;
	
	public Sender(String message, String add, int port) throws UnknownHostException   {
		this.message=message;
		this.add= InetAddress.getByName(add);
		this.port=port;
	}
	/*
	 * 此方法发送数据
	 */
	public void send() throws IOException {
		sendData= new DatagramSocket();
		messPackage=new DatagramPacket(message.getBytes(),message.length(),add,port);
		sendData.send(messPackage);
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
