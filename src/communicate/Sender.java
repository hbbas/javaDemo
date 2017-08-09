package communicate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 这个类主要是完成线程中的datagram.send()方法，完成datagram构造
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
	 * 此方法发送数据
	 */
	public void send() {

		try {
			sendData= new MulticastSocket();
			sendData.joinGroup(add);
			sendData.setLoopbackMode(true);
			messPackage=new DatagramPacket(message.getBytes(),message.getBytes().length,add,port);
			sendData.send(messPackage);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void setMessage(String message){
		this.message=message;
	}
}
