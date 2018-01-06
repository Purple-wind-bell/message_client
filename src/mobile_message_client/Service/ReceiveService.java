package mobile_message_client.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import mobile_message_client.util.FormatUtil;
import mobile_message_client.vo.FormatSMS;

/**
 * 一次性短信接收,单例模式
 * 
 * @author Administrator
 *
 */
public class ReceiveService extends Thread {
	/** 创建端口 */
	ServerSocket server = null;
	/** 端口号 */
	int PORT = 5600;
	BufferedReader bReader = null;
	private Socket socket = null;
	private FormatSMS receiveSMS = null;

	public ReceiveService() {
		super();
		try {
			server = new ServerSocket(PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FormatSMS getSMS() {
		return receiveSMS;
	}

	/**
	 * 关闭监听
	 */
	public void close() {
		if (socket != null) {
			try {
				socket.close();// 关闭
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 监听接收短信
	 */
	public void run() {
		while (true) {
			String smsString;
			try {
				socket = server.accept();// 每个请求交给一个线程去处理
				bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				if ((smsString = bReader.readLine()) != null) {
					receiveSMS = FormatUtil.toFormatSMS(smsString);// 接收的短信
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
