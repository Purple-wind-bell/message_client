package mobile_message_client.UI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginUI extends JFrame {
	/** 登录手机号 */
	private String sourceAddress = "00000000000";
	private String targetAddress = "00000000000";

	public void login() {
		Container c = this.getContentPane();
		// JPanel c = (JPanel) this.getContentPane();
		// c.setOpaque(false);
		// c.setLayout(new GridLayout(12,1));
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
		jtf1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf1.getText();
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

		// 按钮1 登录
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 密码错误
				JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
				// 用户不存在
				JOptionPane.showMessageDialog(null, "账号不存在！", "提示", JOptionPane.WARNING_MESSAGE);
				// 登录成功
				JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.PLAIN_MESSAGE);
				new SendMessageUI(sourceAddress).sendmessage();
			}
		});

		// 按钮2 退出
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		// 按钮3 进入注册界面
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterUI().regist();
			}
		});

	}

}
