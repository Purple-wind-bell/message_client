package mobile_message_client.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import mobile_message_client.util.FormatUtil;
import mobile_message_client.vo.FormatSMS;

/**
 * 一次性发送socket，发送完成后释放
 * 
 * @author Administrator
 *
 */
public class SendService {
	/** 创建端口 */
	Socket socket = null;
	/** 端口号 */
	int PORT = 5600;
	/** IP地址 */
	String IP = " Constant.getServerIP()";
	/** SMS */
	FormatSMS sms = null;
	PrintWriter pWriter = null;

	public SendService(FormatSMS formatSMS) {
		super();
		this.sms = formatSMS;
	}

	/**
	 * 短信发送
	 * 
	 * @return true-发送成功；false-发送失败，对方可能不在线
	 */
	public boolean send() {
		boolean status = false;
		try {
			socket = new Socket(IP, PORT);
			pWriter = new PrintWriter(socket.getOutputStream());
			String outString = FormatUtil.toStringSMS(sms);
			outString = outString + "\n";
			pWriter.println(outString);// 发送
			pWriter.flush();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			try {
				pWriter.close();// 关闭连接
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return status;
	}
}