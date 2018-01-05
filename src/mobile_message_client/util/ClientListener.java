package mobile_message_client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import mobile_message_client.vo.FormatSMS;

public class ClientListener {
	/** 创建端口 */
	ServerSocket server = null;
	/** 端口号 */
	int PORT = 5600;
	/** SMS */
	String sms = null;
	FormatSMS formatSMS = null;
	BufferedReader bReader = null;
	private Socket socket = null;

	public ClientListener() {
		super();
		try {
			server = new ServerSocket(PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FormatSMS getFormatSMS() {
		return formatSMS;
	}

	public void start() {
		while (true) {
			try {
				socket = server.accept();// 每个请求交给一个线程去处理
				bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				if (bReader != null) {
					String smsString = bReader.readLine();
					formatSMS = FormatUtil.toFormatSMS(smsString);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
