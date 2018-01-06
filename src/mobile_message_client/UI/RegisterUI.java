package mobile_message_client.UI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mobile_message_client.Service.ReceiveService;
import mobile_message_client.Service.SendService;
import mobile_message_client.vo.FormatSMS;

/**
 * 注册UI
 * 
 * @author 紫风铃
 *
 */
public class RegisterUI extends JFrame {
	/** 登录手机号 */
	private String sourceAddress = "00000000000";
	/** 密码 */
	private String password = "000000";

	void register() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JLabel jl1 = new JLabel("账号：");
		final JTextField jtf1 = new JTextField(25);
		JLabel jl2 = new JLabel("密码：");
		JTextField jtf2 = new JTextField(25);
		JButton jb1 = new JButton("注册");
		JButton jb2 = new JButton("退出");

		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp4.add(jb1);
		jp4.add(jb2);

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

		// 密码控制为6位
		jtf2.addKeyListener(new KeyAdapter() {
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

		// 注册按钮
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 手机号长度和密码长度检查
				if (sourceAddress.length() < 11 || password.length() < 6) {
					JOptionPane.showMessageDialog(null, "用户名或密码长度错误！", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					String cmd = "CMD001";
					String status = "0000";
					String content = "ZC" + password;
					FormatSMS sendSMS = new FormatSMS(cmd, sourceAddress, null, status, content);
					new SendService(sendSMS).send();
					// 接收回复短信
					FormatSMS reveiceSMS = null;
					ReceiveService rService = new ReceiveService();
					rService.start();
					while ((reveiceSMS = rService.getSMS()) != null) {
						rService.close();// 关闭接收端口
						switch (reveiceSMS.getStatus()) {
						case "0000":// 登录成功
							JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.PLAIN_MESSAGE);
							new SendMessageUI(sourceAddress).sendmessage();
							break;
						case "0001":// 注册失败
							JOptionPane.showMessageDialog(null, "注册失败！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						default:// 未知错误
							JOptionPane.showMessageDialog(null, "未知错误！", "提示", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
				}
			}
		});

		// 退出
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		this.setVisible(true);
		this.setTitle("注册");
		this.setSize(400, 200);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(RegisterUI.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

}
