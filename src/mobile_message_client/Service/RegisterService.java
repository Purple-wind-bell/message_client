package mobile_message_client.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import mobile_message_client.config.Constant;
import mobile_message_client.util.FormatUtil;
import mobile_message_client.vo.FormatSMS;

/**
 * 客户端登录注册收发信息
 * 
 * @author Administrator
 *
 */
public class RegisterService {
	/** 创建端口 */
	private static Socket registerSocket = null;
	/** 服务器IP */
	private static String serverIP = Constant.getServerIP();
	/** 端口号 */
	private static int port = Constant.getRegisterPort();
	/** 接收的返回信息 */
	private FormatSMS receiveFormatSMS;
	private static FormatSMS sendFormatSMS;
	private static PrintWriter pWriter = null;
	private static BufferedReader bReader = null;
	private static RegisterService registerService = null;

	static {
		registerService = new RegisterService();
	}

	private RegisterService() {
		super();
		try {
			registerSocket = new Socket(serverIP, port);// 创建端口连接注册服务器
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static RegisterService getRegisterService(FormatSMS sendSMS) {
		sendFormatSMS = sendSMS;
		return registerService;
	}

	/**
	 * 关闭监听
	 */
	public void close() {
		if (registerSocket != null) {
			try {
				registerSocket.close();// 关闭
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 监听接收短信
	 * 
	 * @return
	 */
	public FormatSMS start() {
		String insms = null;
		try {
			pWriter = new PrintWriter(registerSocket.getOutputStream());
			bReader = new BufferedReader(new InputStreamReader(registerSocket.getInputStream()));
			pWriter.println(FormatUtil.toStringSMS(sendFormatSMS));// 发送登录注册短信
			new Thread();
			Thread.sleep(1000);
			if ((insms = bReader.readLine()) != null) {
				receiveFormatSMS = FormatUtil.toFormatSMS(insms);// 格式化信息
			} else {
				receiveFormatSMS = null;
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sendFormatSMS = null;
		}
		return receiveFormatSMS;
	}

}
