package mobile_message_client.main;

import mobile_message_client.UI.LoginUI;
import mobile_message_client.UI.SendMessageUI;

/**
 * 启动客户端
 * 
 * @author Administrator
 *
 */
public class start {
	public static void main(String[] args) {
		new LoginUI().login();
//		new SendMessageUI("17655555555").sendmessage();
	}
}
