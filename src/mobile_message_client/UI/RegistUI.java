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

public class RegistUI extends JFrame {
	void regist() {
		Container c = this.getContentPane();

		c.setLayout(new FlowLayout());

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		// JPanel jp5 = new JPanel();

		JLabel jl1 = new JLabel("账号：");
		final JTextField jtf1 = new JTextField(25);
		JLabel jl2 = new JLabel("密码：");
		JTextField jtf2 = new JTextField(25);
		JLabel jl3 = new JLabel("密码：");
		JTextField jtf3 = new JTextField(25);

		JButton jb1 = new JButton("注册");
		JButton jb2 = new JButton("退出");

		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		// c.add(jp5);

		jp1.add(jl1);
		jp1.add(jtf1);

		jp2.add(jl2);
		jp2.add(jtf2);

		jp3.add(jl3);
		jp3.add(jtf3);

		jp4.add(jb1);
		jp4.add(jb2);

		// 输入的手机号控制为11位
		jtf1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				String s = jtf1.getText();
				if (s.length() >= 11) {
					e.consume();
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

		// 注册按钮
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.PLAIN_MESSAGE);

			}
		});

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
		this.setDefaultCloseOperation(RegistUI.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

}
