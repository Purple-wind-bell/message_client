package mobile_message_client.config;

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
	/** 服务器IP */
	static String ServerIP = "192.168.3.30";

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

	public static String getServerIP() {
		return ServerIP;
	}

}
