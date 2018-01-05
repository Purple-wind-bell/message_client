package mobile_message_client.vo;

import java.io.Serializable;

/**
 * 短信内容格式化存储
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class FormatSMS implements Serializable {
	/** 命令 ，例如003 */
	String cmd;
	/** 源地址 */
	String sourceAddress;
	/** 目标地址 */
	String targetAddress;
	/** 短信长度或返回状态码 */
	String status;
	/** 短信内容 */
	String content;

	public FormatSMS() {
		super();
	}

	public FormatSMS(String cmd, String sourceAddress, String targetAddress, String status, String content) {
		super();
		this.cmd = cmd;
		this.sourceAddress = sourceAddress;
		this.targetAddress = targetAddress;
		this.status = status;
		this.content = content;
	}

	public String getCmd() {
		return cmd;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public String getTargetAddress() {
		return targetAddress;
	}

	public String getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setContent(String content) {
		content = content;
	}

}
