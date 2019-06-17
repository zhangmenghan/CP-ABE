/*
 * Function:Making it a graphic user interface to login
 * Author:	
 * Date:	2015-12-9
 */
package login_gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import cpabe.client.Client;
import cpabe.client.DemoClient;
import cpabe.file.FileOperator;
import cpabe.service.StaffService;
import cpabe.systemEntity.ClientBusiness;
import mainpageuser.MainPageUser;

public class LogIn extends JFrame implements ActionListener {
	private static final long serialVersionUID = -3087637L;

	String staff_id;
	String password;
	String str_IP;
	char[] passwordGet;

	JTextField tf_ip;
	JTextField tf_ipIn;
	JTextField tf_id; // display
	JTextField tf_idIn; // editable textfield
	JTextField tf_password;
	JPasswordField pswf_passwordIn;
	JButton btn_close;
	JButton btn_minimize;
	JButton btn_logButton;

	BackGroundPanel backgroundPanel;
	Container c;

	private LogIn() {
		File pub_fileDir = new File("./file_dir/");
		if(!pub_fileDir.exists()){
			pub_fileDir.mkdirs();
		}
		
		File tempDir = new File("./fileTemp/");
		if(!tempDir.exists()){
			tempDir.mkdirs();
		}
		File attributePropFile = new File("./fileTemp/attribute.properties");
		if(!attributePropFile.exists()){
			try {
				attributePropFile.createNewFile();
			} catch (IOException e) {
				//Discard
			}
		}
		
		File dirfilePropFile = new File("./fileTemp/dirfile.properties");
		if(!dirfilePropFile.exists()){
			try {
				dirfilePropFile.createNewFile();
			} catch (IOException e) {
				//Discard
			}
		}
		File infoPropFile = new File("./fileTemp/info.properties");
		if(!infoPropFile.exists()){
			try {
				infoPropFile.createNewFile();
			} catch (IOException e) {
				//Discard
			}
		}
		
		File policyPropFile = new File("./fileTemp/policy.properties");
		if(!policyPropFile.exists()){
			try {
				policyPropFile.createNewFile();
			} catch (IOException e) {
				//Discard
			}
		}
		
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		DemoClient.serverCiphertextDir = fsv.getHomeDirectory().getAbsolutePath()+"/ServerCiphertextDir/";
		File dirServer = new File(DemoClient.serverCiphertextDir);
		if(!dirServer.exists()){
			dirServer.mkdirs();
		}
		DemoClient.clientCiphertextDir = fsv.getHomeDirectory().getAbsolutePath()+"/ClientCiphertextDir/";
		File dirClient = new File(DemoClient.clientCiphertextDir);
		if(!dirClient.exists()){
			dirClient.mkdirs();
		}
		DemoClient.storeHasDecFileDir = fsv.getHomeDirectory().getAbsolutePath() + "/PlaintextDir/";
		File dirPlaintext = new File(DemoClient.storeHasDecFileDir);
		if(!dirPlaintext.exists()){
			dirPlaintext.mkdirs();
		}

		
		
		tf_id = new JTextField("ID", 8);
		tf_idIn = new JTextField("", 15);
		tf_password = new JTextField("PW", 8);
		pswf_passwordIn = new JPasswordField("", 15);
		tf_ip = new JTextField("IP",8);
		tf_ipIn = new JTextField("",15);
		
		btn_close = new JButton();
		btn_minimize = new JButton();
		btn_logButton = new JButton();
		c = this.getContentPane();
		backgroundPanel = new BackGroundPanel(new ImageIcon(getClass().getResource("/iconforuser/img_login_head.png")).getImage());
		backgroundPanel.setBounds(0, 0, 480, 140);
		backgroundPanel.setOpaque(false);

		c.setBackground(new Color(250, 250, 250));
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("/iconforuser/title.png")).getImage());

		this.setSize(480, 340);
		this.setResizable(false);

		tf_id.setEditable(false);
		tf_id.setBackground(null);
		tf_id.setFont(new Font("微软雅黑", 0, 15));
		tf_id.setBorder(null);
		tf_password.setEditable(false);
		tf_password.setBackground(null);
		tf_password.setFont(new Font("微软雅黑", 0, 15));
		tf_password.setBorder(null);
		tf_ip.setEditable(false);
		tf_ip.setBackground(null);
		tf_ip.setFont(new Font("微软雅黑", 0, 15));
		tf_ip.setBorder(null);

		btn_close.setBorder(null);
		btn_close.setContentAreaFilled(false);
		btn_close.setIcon(new ImageIcon(getClass().getResource("/iconforuser/bg_login_close_button.png")));
		btn_minimize.setContentAreaFilled(false);
		btn_minimize.setBorder(null);
		btn_minimize.setIcon(new ImageIcon(getClass().getResource("/iconforuser/bg_login_minimize_button.png")));
		btn_logButton.setBorder(null);
		btn_logButton.setBackground(null);
		btn_logButton.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_login_button.png")));
		btn_logButton.setPressedIcon(new ImageIcon(getClass().getResource("/iconforuser/bg_login_button_pressed.png")));

		c.setLayout(null);
		c.add(tf_ip);
		c.add(tf_ipIn);
		c.add(tf_id);
		c.add(tf_idIn);
		c.add(tf_password);
		c.add(pswf_passwordIn);
		c.add(btn_close);
		c.add(btn_minimize);
		c.add(btn_logButton);
		c.add(backgroundPanel);

		tf_id.setBounds(new Rectangle(45, 180, 60, 30));
		tf_id.setHorizontalAlignment(JTextField.CENTER);
		tf_idIn.setBounds(new Rectangle(120, 180, 230, 30));
		
		tf_password.setBounds(new Rectangle(45, 230, 60, 30));
		tf_password.setHorizontalAlignment(JTextField.CENTER);
		pswf_passwordIn.setBounds(new Rectangle(120, 230, 230, 30));
		
		tf_ip.setBounds(new Rectangle(45, 280, 60, 30));
		tf_ip.setHorizontalAlignment(JTextField.CENTER);
		tf_ipIn.setBounds(new Rectangle(120, 280, 230, 30));

		btn_close.setBounds(new Rectangle(440, 10, 30, 30));
		btn_minimize.setBounds(new Rectangle(400, 15, 30, 30));
		btn_logButton.setBounds(new Rectangle(390, 275, 40, 40));
		this.setLocationRelativeTo(null); // display the window at the center of
											// the screen
		this.setVisible(true);

		btn_close.addActionListener(this);
		btn_minimize.addActionListener(this);
		btn_logButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btn_logButton) {
			str_IP = tf_ipIn.getText();//得到IP地址，存在字符串str_IP中
			Client.SERVER_IP = str_IP;
			
			MainPageUser.socket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress(Client.SERVER_IP,
					Client.SERVER_PORT);
			try {
				MainPageUser.socket.connect(socketAddress, 500000000);
			} catch (IOException we) {
				new Fault("连接服务器失败！");
			}
			
			staff_id = tf_idIn.getText();//得到员工的工号，存在字符串staff_id中

			passwordGet = pswf_passwordIn.getPassword();
			password = new String(passwordGet);//得到员工的密码，存在字符串password中

			ClientBusiness clientBusiness = new ClientBusiness(
					MainPageUser.socket);
			boolean hasLogon = clientBusiness.sendLoginSegment(staff_id,
					password);
			if (hasLogon) {
				Client.STAFF_ID = staff_id;
				this.setVisible(false);
				new MainPageUser();
			} else {
				new Fault("登录失败！请检查职工号和密码！");
			}
		}

		if (source == btn_close) {
			System.exit(0);
		} else if (source == btn_minimize) {
			this.setExtendedState(ICONIFIED);
		}
	}

	public static void main(String[] args) {
		new LogIn();
	}
}
