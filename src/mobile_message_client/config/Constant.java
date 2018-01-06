package mobile_message_client.config;

import java.net.InetAddress;

/**
 * 参数配置
 * 
 * @author Administrator
 *
 */
public final class Constant {
	/** MySQL数据库连接 */
	static String mysqlUrl = "jdbc:mysql://localhost:3306/mobile_message";
	/** MySQL用户 */
	static String mysqlUser = "root";
	/** MySQL用户密码 */
	static String mysqlPasswd = "123456";
	/** 客户端普通短信接收端口 */
	static int clientSMSPort = 5700;
	/** 服务器普通短信收发端口 */
	static int serverSMSPort = 5600;
	/** 服务器注册登录端口 */
	static int registerPort = 5650;
	/** 服务器IP */
	static String serverIP = "127.0.0.1";

	private Constant() {
	}

	/**
	 * 获得数据库url
	 * 
	 * @return string类型的url
	 */
	public static String getMysqlUrl() {
		return mysqlUrl;
	}

	/**
	 * 获得MySQL用户名
	 * 
	 * @return 用户名
	 */
	public static String getMysqlUser() {
		return mysqlUser;
	}

	/**
	 * 获得MySQL用户密码
	 * 
	 * @return 密码
	 */
	public static String getMysqlPasswd() {
		return mysqlPasswd;
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
