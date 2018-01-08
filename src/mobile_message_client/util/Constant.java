package mobile_message_client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 参数配置
 * 
 * @author Administrator
 *
 */
public final class Constant {
	/** 客户端普通短信接收端口 */
	static int clientSMSPort = 5700;
	/** 服务器普通短信收发端口 */
	static int serverSMSPort = 5600;
	/** 服务器注册登录端口 */
	static int registerPort = 5650;
	/** 服务器IP */
	static String serverIP = "127.0.0.1";

	static {
		Properties p = new Properties();
		InputStream in = Constant.class.getClassLoader()
				.getResourceAsStream("mobile_message_client/config/mobileclient.properties");
		try {
			p.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientSMSPort = Integer.parseInt(p.getProperty("clientSMSPort"));
		serverSMSPort = Integer.parseInt(p.getProperty("serverSMSPort"));
		registerPort = Integer.parseInt(p.getProperty("registerPort"));
	}

	private Constant() {
	}

	public static int getClientSMSPort() {
		return clientSMSPort;
	}

	public static int getServerSMSPort() {
		return serverSMSPort;
	}

	public static int getRegisterPort() {
		return registerPort;
	}

	public static String getServerIP() {
		return serverIP;
	}
}
