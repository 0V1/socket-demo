package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @ClassName: GreetingServer
 * @Description: Socket 服务端实例 
 * GreetingServer 程序是一个服务器端应用程序，使用 Socket 来监听一个指定的端口。
 * @author qinf QQ:908247035
 * @date 2016年12月21日
 * @version V1.0
 */
public class Server extends Thread{

	private ServerSocket serverSocket;
	
	public Server(int port)throws Exception{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}
	
	public void run(){
		while(true){
			try {
				//服务端等待客户端链接  打印出服务端监听的端口
				System.out.println("waiting for client on port "+serverSocket.getLocalPort()+"....");
				//监听连接套接字并接受它。块,直到连接的方法。
				Socket client = serverSocket.accept();
				//打印出链接过来的客户端地址
				System.out.println("just connected on "+client.getRemoteSocketAddress());
				
				//接收到客户端发送的消息
				InputStream inputStream = client.getInputStream();
				DataInputStream dataInputStream = new DataInputStream(inputStream);
				System.out.println("client says "+dataInputStream.readUTF());
				
				//发送到客户端的消息
				OutputStream outputStream = client.getOutputStream();
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeUTF("server says "+serverSocket.getLocalSocketAddress()+"\nGoodbye!");
				
				client.close();
			} catch (SocketTimeoutException e) {
				System.out.println("system tiem out");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			Thread t = new Server(6066);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
