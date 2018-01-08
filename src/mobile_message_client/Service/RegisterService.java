package mobile_message_client.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import mobile_message_client.util.Constant;
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
	private Socket registerSocket = null;
	/** 服务器IP */
	private static String serverIP = Constant.getServerIP();
	/** 端口号5650 */
	private static int port = Constant.getRegisterPort();
	/** 接收的返回信息 */
	private FormatSMS receiveFormatSMS;
	private FormatSMS sendFormatSMS;
	private PrintWriter pWriter = null;
	private BufferedReader bReader = null;

	public RegisterService(FormatSMS sendSMS) {
		super();
		try {
			registerSocket = new Socket(serverIP, port);// 创建端口连接注册服务器
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendFormatSMS = sendSMS;
	}

	/**
	 * 注册收发信息
	 * 
	 * @return
	 */
	public FormatSMS start() {
		String insms = null;
		try {
			pWriter = new PrintWriter(registerSocket.getOutputStream());
			bReader = new BufferedReader(new InputStreamReader(registerSocket.getInputStream()));
			System.out.println("sendFormatSMS" + sendFormatSMS.toString());
			System.out.println(FormatUtil.toStringSMS(sendFormatSMS));
			pWriter.println(FormatUtil.toStringSMS(sendFormatSMS));// 发送登录注册短信
			pWriter.flush();
			System.out.println("注册已发送");
			while (insms == null) {
				insms = bReader.readLine();
			}
			receiveFormatSMS = FormatUtil.toFormatSMS(insms);// 格式化信息
//			System.out.println("receiveFormatSMS" + receiveFormatSMS.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sendFormatSMS = null;
			if (registerSocket != null) {
				try {
					registerSocket.close();// 关闭
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return receiveFormatSMS;
	}

}
