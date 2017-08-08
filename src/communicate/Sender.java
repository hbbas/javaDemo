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
public class Sender {
	private DatagramSocket sendData;
	private String message;
	private DatagramPacket messPackage;
	private InetAddress add;
	private int port;

	public Sender(int port) throws UnknownHostException   {
		this.add= InetAddress.getByName( "10.180.179.76");
		this.port=port;
	}
	/*
	 * �˷�����������
	 */
	public void send() {

		try {
			sendData= new DatagramSocket();
			messPackage=new DatagramPacket(message.getBytes(),message.length(),add,port);
			sendData.send(messPackage);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	public void setMessage(String message){
		this.message=message;
	}
}
