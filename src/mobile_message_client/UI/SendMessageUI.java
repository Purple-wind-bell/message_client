package mobile_message_client.UI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mobile_message_client.Service.SendService;
import mobile_message_client.util.FormatUtil;
import mobile_message_client.vo.FormatSMS;

@SuppressWarnings("serial")
public class SendMessageUI extends JFrame {
	/** 命令 ，例如003 */
	String cmd = "CMD003";
	/** 源地址 */
	String sourceAddress = "17012341234";
	/** 目标地址 */
	String targetAddress = "00000000000";
	/** 短信长度或返回状态码 */
	String status = "0001";
	/** 短信内容 */
	String content = "0";
	/** 显示框内容 */
	StringBuilder mString = new StringBuilder();
	JTextArea jta1 = new JTextArea(25, 30);
	JTextField jtf1 = new JTextField(25);
	JTextField jtf2 = new JTextField(23);

	public SendMessageUI(String sourceAddress) throws HeadlessException {
		super();
		this.sourceAddress = sourceAddress;
	}

	public void sendmessage() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();

		JLabel jl1 = new JLabel("收件人：");
		JLabel jl2 = new JLabel(
				"内容：                                                                                                 ");
		JLabel jl3 = new JLabel("编辑信息：");
		JButton jb1 = new JButton("发送");
		JButton jb2 = new JButton("退出");
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		c.add(jp5);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp2.add(jl2);
		jp3.add(jta1);
		jp4.add(jl3);
		jp4.add(jtf2);
		jp5.add(jb1);
		jp5.add(jb2);
		// -----------
		this.setVisible(true);
		this.setTitle("短信");
		this.setSize(400, 620);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(SendMessageUI.EXIT_ON_CLOSE);
		this.setResizable(false);

		// 输入的手机号控制为11位
		jtf1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf1.getText();
				System.out.println(s);
				if (s.length() >= 11) {
					e.consume();
				} else {
					targetAddress = s;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// 编辑的短信内容控制为140位
		jtf2.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf2.getText();
				if (s.length() >= 140) {
					e.consume();
				} else {
					content = s;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// 发送按钮
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, targetAddress, status, content);
				new SendService(sendSMS).send();// 发送短信
				mString.append(sendSMS.toString() + "/n");// 添加已发短信
				jta1.setText(mString.toString());// 显示收发短信
			}
		});

		// 退出按钮
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	/**
	 * 内部类，解决短信收发
	 * 
	 * @author Administrator
	 *
	 */
	class Receive extends Thread {
		/** 创建端口 */
		ServerSocket server = null;
		/** 端口号 */
		int PORT = 5600;
		BufferedReader bReader = null;
		private Socket socket = null;

		public Receive() {
			super();
			try {
				server = new ServerSocket(PORT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 监听接收短信
		 */
		public void run() {
			while (true) {
				String smsString;
				FormatSMS receiveSMS = null;
				try {
					socket = server.accept();// 每个请求交给一个线程去处理
					bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					if ((smsString = bReader.readLine()) != null) {
						receiveSMS = FormatUtil.toFormatSMS(smsString);// 接收的短信
						mString.append(receiveSMS.toString());// 添加短信
						jta1.setText(receiveSMS.toString());// 显示所有收发短信
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
