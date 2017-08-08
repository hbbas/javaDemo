package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * �������Ҫ������߳��е�datagram.send()���������datagram����
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
	 * �˷�����������
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
