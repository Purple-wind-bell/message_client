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

import mobile_message_client.Service.ReceiveService;
import mobile_message_client.Service.SendService;
import mobile_message_client.vo.FormatSMS;

@SuppressWarnings("serial")
public class LoginUI extends JFrame {
	/** 登录手机号 */
	private String sourceAddress = "00000000000";
	/** 密码 */
	private String password = "";
	private String ip = "0.0.0.0";
	ReceiveService rService = null;

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
		JPasswordField jpf1 = new JPasswordField(20);

		JCheckBox jcb1 = new JCheckBox("记住密码");
		JCheckBox jcb2 = new JCheckBox("自动登录");
		JLabel jl5 = new JLabel("IP地址：");
		JTextField jtf2 = new JTextField(20);
		JButton jb1 = new JButton("登录");
		JButton jb2 = new JButton("退出");
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
				String s = jtf1.getText();
				if (s.length() >= 11) {
					e.consume();
				} else {
					sourceAddress = s;
				}
			}
		});

		// 本机ip地址
		jtf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				ip = jtf1.getText();
			}
		});

		// 密码控制为6位
		jpf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf1.getText();
				if (s.length() >= 6) {
					e.consume();
				} else {
					password = s;
				}
			}
		});

		// 按钮1 登录
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 登录手机号长度和密码长度检查
				if (sourceAddress.length() < 11 || password.length() < 6) {
					JOptionPane.showMessageDialog(null, "用户名或密码长度错误！", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					String cmd = "CMD001";
					String status = "0000";
					String content = "DL" + password + ip;
					FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, null, status, content);
					new SendService(sendSMS).send();
					// 接收回复短信
					FormatSMS reveiceSMS = null;
					ReceiveService rService = new ReceiveService();
					rService.start();
					while ((reveiceSMS = rService.getSMS()) != null) {
						rService.close();// 关闭接收端口
						switch (reveiceSMS.getStatus()) {
						case "3000":// 用户不存在
							JOptionPane.showMessageDialog(null, "账号不存在！", "提示", JOptionPane.WARNING_MESSAGE);
							break;
						case "3001":// 密码错误
							JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						case "0000":// 登录成功
							JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.PLAIN_MESSAGE);
							new SendMessageUI(sourceAddress).sendmessage();
							break;
						case "0001":// 未知错误
							JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						default:// 未知错误
							JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
				}
			}
		});

		// 按钮2 退出
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd = "CMD002";
				String status = "0000";
				String content = "0";
				FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, null, status, content);
				new SendService(sendSMS).send();
				// 接收回复短信
				FormatSMS reveiceSMS = null;
				ReceiveService rService = new ReceiveService();
				rService.start();
				while ((reveiceSMS = rService.getSMS()) != null) {
					rService.close();// 关闭接收端口
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
				}
				System.exit(0);
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
}
