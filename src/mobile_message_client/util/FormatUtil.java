package mobile_message_client.util;

import mobile_message_client.vo.FormatSMS;

/**
 * 格式化短信与字符串短信之间的转换
 * 
 * @author Administrator
 *
 */
public class FormatUtil {

	private FormatUtil() {

	}

	/**
	 * 将字符串短信转换为格式化的短信对象
	 * 
	 * @param message
	 *            字符串短信
	 * @return 格式化的短信对象
	 */
	public static FormatSMS toFormatSMS(String message) {
		// TODO Auto-generated method stub
		String cmd = message.substring(0, 6);
		String sourceAddress = message.substring(6, 17);
		String targetAddress = message.substring(17, 28);
		String status = message.substring(28, 32);
		String SMSContent = message.substring(32);
		return new FormatSMS(cmd, sourceAddress, targetAddress, status, SMSContent);
	}

	/**
	 * 将格式化的短信对象转化为字符串短信
	 * 
	 * @param FormatSMS
	 *            格式化的短信对象
	 * @return 字符串短信
	 */
	public static String toStringSMS(FormatSMS formatSMS) {
		// TODO Auto-generated method stub
		StringBuilder SMSString = new StringBuilder();
		SMSString.append(formatSMS.getCmd() != null ? formatSMS.getCmd() : "000000");
		SMSString.append(formatSMS.getSourceAddress() != null ? formatSMS.getSourceAddress() : "00000000000");
		SMSString.append(formatSMS.getTargetAddress() != null ? formatSMS.getTargetAddress() : "00000000000");
		SMSString.append(formatSMS.getStatus() != null ? formatSMS.getStatus() : "0000");
		SMSString.append(formatSMS.getContent() != null ? formatSMS.getContent() : "0");
		return SMSString.toString();
	}
}
