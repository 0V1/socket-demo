package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName: test
 * @Description: Socket 客户端实例 
 * GreetingClient 是一个客户端程序，该程序通过 socket 连接到服务器并发送一个请求，然后等待一个响应。
 * @author qinf QQ:908247035
 * @date 2016年12月21日
 * @version V1.0
 */
public class Client {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 6066;
		try {
			System.out.println("connecting on "+host+" on port "+port);
			Socket client = new Socket(host, port);
			//返回套接字链接的目标地址
			//测试是否链接到服务端 ，获取服务端的地址 
			System.out.println("just connecting on host "+client.getRemoteSocketAddress());
			
			
			//返回套接字端点的地址
			//向服务端发送消息 说明客户端的地址
			OutputStream outputStream = client.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("hello from "+client.getLocalSocketAddress());
			
			//获取服务端发回的消息
			InputStream inputStream = client.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			System.out.println("service says "+dataInputStream.readUTF());
			
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
