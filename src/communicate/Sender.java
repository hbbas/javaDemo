package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * �������Ҫ������߳��е�datagram.send()���������datagram����
 * @author hbbas
 *
 */
public class Sender {
	private MulticastSocket sendData;
	private String message;
	private DatagramPacket messPackage;
	private InetAddress add;
	private int port;

	public Sender(InetAddress add,int port) throws UnknownHostException   {
		this.add=add;
		this.port=port;
	}
	/*
	 * �˷�����������
	 */
	public void send() {

		try {
			sendData= new MulticastSocket();
			sendData.joinGroup(add);
			sendData.setLoopbackMode(true);
			messPackage=new DatagramPacket(message.getBytes(),message.getBytes().length,add,port);
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
