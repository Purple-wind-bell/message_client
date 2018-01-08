package mobile_message_client.UI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mobile_message_client.Service.RegisterService;
import mobile_message_client.vo.FormatSMS;

@SuppressWarnings("serial")
public class LoginUI extends JFrame {
	/** 登录手机号 */
	private String sourceAddress = "00000000000";
	/** 密码 */
	private String password = "";
	/** 当前用户是否在线 */
	private	boolean onlineStatus = false;
	private String ip = "0.0.0.0";

	public LoginUI() throws HeadlessException {
		super();
	}

	public void login() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JLabel jl1 = new JLabel("账号：");
		final JTextField jtf1 = new JTextField(20);
		JLabel jl2 = new JLabel("密码：");
		final JPasswordField jpf1 = new JPasswordField(20);
		JCheckBox jcb1 = new JCheckBox("记住密码");
		JCheckBox jcb2 = new JCheckBox("自动登录");
		JLabel jl5 = new JLabel("IP地址：");
		final JTextField jtf2 = new JTextField(20);
		JButton jb1 = new JButton("登录");
		JButton jb2 = new JButton("注销");
		JButton jb3 = new JButton("注册账号");

		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jcb1);
		jp2.add(jl2);
		jp2.add(jpf1);
		jp2.add(jcb2);
		jp3.add(jl5);
		jp3.add(jtf2);
		jp3.add(jb3);
		jp4.add(jb1);
		jp4.add(jb2);

		this.setVisible(true);
		this.setTitle("登录");
		this.setSize(400, 200);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(LoginUI.EXIT_ON_CLOSE);
		this.setResizable(false);

		// 输入的手机号控制为11位
		jtf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf1.getText().toString().trim();
				if (s.length() >= 11) {
					e.consume();
				}
				// sourceAddress = s;
				// System.out.println("sourceAddress" + sourceAddress);
				// System.out.println("手机号长度:" + sourceAddress.length());
			}
		});

		// 本机ip地址
		jtf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				// String s = jtf2.getText();
			}
		});

		// 密码控制为6位
		jpf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = new String(jpf1.getPassword()).trim();
//				System.out.println("密码长度：" + s.length());
				if (s.length() >= 6) {
					e.consume();
				}
				// password = s;
				// System.out.println("password" + password);
			}
		});

		// 按钮1 登录
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 登录手机号长度和密码长度检查
				sourceAddress = jtf1.getText().trim();
				password = new String(jpf1.getPassword()).trim();
				ip = jtf2.getText().toString().trim();
				System.out.println("手机号：" + sourceAddress);
				System.out.println("密码：" + password);
				System.out.println("IP:" + ip);
				if (sourceAddress.length() < 11 || password.length() < 6 || !isIp(ip) || onlineStatus) {
					JOptionPane.showMessageDialog(null, "用户名密码或IP错误！", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					String cmd = "CMD001";
					String status = "0000";
					String content = "DL" + password + ip;
					FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, "00000000000", status, content);
					RegisterService register = new RegisterService(sendSMS);// 发送登录请求
					FormatSMS reveiceSMS = register.start();// 接收回复短信
					// =================
					if (reveiceSMS != null) {
						switch (reveiceSMS.getStatus()) {
						case "3000":// 用户不存在
							JOptionPane.showMessageDialog(null, "账号不存在！", "提示", JOptionPane.WARNING_MESSAGE);
							break;
						case "3001":// 密码错误
							JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						case "0000":// 登录成功
							JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.PLAIN_MESSAGE);
							onlineStatus = true;
							new SendMessageUI(sourceAddress).sendmessage();
							break;
						case "0001":// 未知错误
							JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						default:// 未知错误
							JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						}
					} else {
						JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// 按钮2 注销
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd = "CMD002";
				String status = "0000";
				String content = "0";
				FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, "00000000000", status, content);
				RegisterService register = new RegisterService(sendSMS);// 发送登录请求
				FormatSMS reveiceSMS = register.start();// 接收回复短信
				if (reveiceSMS != null) {
					switch (reveiceSMS.getStatus()) {
					case "0000":// 注销成功
						JOptionPane.showMessageDialog(null, "注销成功！", "提示", JOptionPane.PLAIN_MESSAGE);
						break;
					case "0001":// 注销失败
						JOptionPane.showMessageDialog(null, "注销失败！", "提示", JOptionPane.ERROR_MESSAGE);
						break;
					default:// 未知错误
						JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
						break;
					}
				} else {
					System.out.println("null");
					JOptionPane.showMessageDialog(null, "注销失败！", "提示", JOptionPane.ERROR_MESSAGE);
				}
				// System.exit(0);
			}
		});

		// 按钮3 进入注册界面
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterUI().register();
			}
		});
	}

	/**
	 * 判断是否是IP
	 * 
	 * @param ip
	 * @return
	 */
	private boolean isIp(String ip) {
		boolean b = false;
		ip = ip.trim();
		if (ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = ip.split("\\.");
			if (Integer.parseInt(s[0]) < 255 && Integer.parseInt(s[1]) < 255 && Integer.parseInt(s[2]) < 255
					&& Integer.parseInt(s[3]) < 255) {
				b = true;
			}
		}
		return b;
	}
}
