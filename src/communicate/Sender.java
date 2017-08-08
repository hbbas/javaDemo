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
	 * 此方法发送数据
	 */
	public void send() {

		try {
			sendData= new DatagramSocket();
			messPackage=new DatagramPacket(message.getBytes(),message.length(),add,port);
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
