package mainpageuser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.*;

import cpabe.client.Client;
import login_gui.BackGroundPanel;
import login_gui.Fault;




public class MainPageUser  extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Socket socket = null;

	
	JButton btn_encrypt;             //进行加密的页面的控制按钮
	JButton btn_upload;              //上传加密过的文件到服务器中的控制按钮
	JButton btn_lookover;            //查看服务器中的文件的控制按钮
	JButton btn_already_download;    //已下载的文件，但是未解密的控制按钮
	JButton btn_already_encrypted;   //已经解密的文件的控制按钮
	
	BackGroundPanel background;
	BackGroundPanel littlebackground;

	
	LookOver lookover;			             //查看服务器中的文件
	Encryption encryption;                   //进行加密的页面
	AlreadyDownload alreadydownload;         //已下载的文件，但是未解密
	AlreadyEncrypted alreadyencrypted;       //已经解密的文件
	UploadFile uploadfile;                   //上传加密过的文件到服务器中
	
	public MainPageUser() {
		
		/**
		 * create a new global socket,so that in every GUI page,
		 * it is the same one 
		 * */
//		socket = new Socket();
//		SocketAddress socketAddress = new InetSocketAddress(Client.SERVER_IP, Client.SERVER_PORT);
//		try {
//			socket.connect(socketAddress, 500000000);
//		} catch (IOException e) {
//			new Fault("连接服务器失败！");
//		}
		
		lookover = new LookOver();
		encryption = new Encryption();
		alreadydownload = new AlreadyDownload();
		alreadyencrypted = new AlreadyEncrypted();
		uploadfile = new UploadFile();
		
		btn_encrypt = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_encrypt_button.png")));
		btn_upload = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_upload_button.png")));
		btn_lookover = new JButton(new ImageIcon("src//iconforuser//img_lookover_button_select.png"));
		btn_already_download = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_already_download_button.png")));
		btn_already_encrypted = new JButton(new ImageIcon("src//iconforuser//img_already_encrypted_button.png")); 
		
		background = new BackGroundPanel((new ImageIcon(getClass().getResource("/iconforuser/img_left_bg.png"))).getImage());
		littlebackground = new BackGroundPanel((new ImageIcon(getClass().getResource("/iconforuser/img_clounde.png")).getImage()));
		
		
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(234, 234, 234));
		this.setSize(990, 775);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(getClass().getResource("/iconforuser/title.png")).getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("——基于属性及加密的权限认证的文档共享系统");
		
		setButtonForm(btn_encrypt);
		setButtonForm(btn_upload);
		setButtonForm(btn_lookover);
		setButtonForm(btn_already_download);
		setButtonForm(btn_already_encrypted);
		

		
		btn_lookover.setBounds(new Rectangle(0, 260, 210, 60));
		btn_encrypt.setBounds(new Rectangle(0, 320, 210, 60));
		btn_upload.setBounds(new Rectangle(0, 380, 210, 60));
		btn_already_download.setBounds(new Rectangle(0, 440, 210, 60));
		btn_already_encrypted.setBounds(new Rectangle(0, 500, 210, 60));
		
		background.setBounds(0, 0, 210, 805);
		background.setOpaque(false);
		
		littlebackground.setBounds(210,0,780,80);
		littlebackground.setOpaque(false);
		
		lookover.pn_panel.setBounds(210,80,780,695);				
		encryption.pn_panel.setBounds(210,80,780,695);				
		alreadydownload.pn_panel.setBounds(210,80,780,695);
		alreadyencrypted.pn_panel.setBounds(210,80,780,695);
		uploadfile.pn_panel.setBounds(210,80,780,695);
		
		this.getContentPane().add(lookover.pn_panel, new Integer(37));
		this.getContentPane().add(encryption.pn_panel, new Integer(37));
		this.getContentPane().add(alreadydownload.pn_panel, new Integer(37));
		this.getContentPane().add(alreadyencrypted.pn_panel, new Integer(37));
		this.getContentPane().add(uploadfile.pn_panel, new Integer(37));
		resetPanels();						//重新组装按钮
		lookover.pn_panel.setVisible(true);

		addIt(btn_encrypt, 37);
		addIt(btn_upload, 37);
		addIt(btn_lookover, 37);
		addIt(btn_already_download, 37);
		addIt(btn_already_encrypted, 37);
		addIt(background, 1);
		addIt(littlebackground, 1);
		
		
		btn_encrypt.addActionListener(this);
		btn_upload.addActionListener(this);
		btn_lookover.addActionListener(this);
		btn_already_download.addActionListener(this);
		btn_already_encrypted.addActionListener(this);	
		
		uploadfile.btn_upload_file.addActionListener(uploadfile);
		
		encryption.btnA.addActionListener(encryption);
		encryption.btnB.addActionListener(encryption);
		encryption.btnC.addActionListener(encryption);
		encryption.btnD.addActionListener(encryption);
		encryption.btnE.addActionListener(encryption);
		encryption.btnF.addActionListener(encryption);
		encryption.btnG.addActionListener(encryption);
		encryption.btnH.addActionListener(encryption);
		encryption.en_for_sure.addActionListener(encryption);
		encryption.en_reset.addActionListener(encryption);
		encryption.en_select_file.addActionListener(encryption);
		encryption.final_en_for_sure.addActionListener(encryption);
		encryption.btn1.addActionListener(encryption);
		encryption.btn2.addActionListener(encryption);
		encryption.btn3.addActionListener(encryption);
		encryption.btn4.addActionListener(encryption);
		encryption.btn5.addActionListener(encryption);
		encryption.btn6.addActionListener(encryption);
		encryption.en_continue_select.addActionListener(encryption);
		encryption.en_forsure.addActionListener(encryption);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					MainPageUser.socket.close();
				} catch (IOException e1) {
					//Discard
				}
				MainPageUser.socket = null;
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}
	
	void setButtonForm(JButton b) {
		b.setBorder(null);
		b.setContentAreaFilled(false);
	}
	
	void resetPanels() {
		lookover.pn_panel.setVisible(false);
		encryption.pn_panel.setVisible(false);
		alreadydownload.pn_panel.setVisible(false);
		alreadyencrypted.pn_panel.setVisible(false);
		uploadfile.pn_panel.setVisible(false);
	}
	
	void addIt(Component item, int n) {
		this.getContentPane().add(item, new Integer(n));
	}
	
	void resetButtons() {
		btn_encrypt.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_encrypt_button.png")));
		btn_upload.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_upload_button.png")));
		btn_lookover.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_lookover_button.png")));
		btn_already_download.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_already_download_button.png")));
		btn_already_encrypted.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_already_encrypted_button.png")));
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btn_upload) {
			resetPanels();
			uploadfile.pn_panel.setVisible(true);
			resetButtons();
			btn_upload.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_upload_button_select.png")));
		} else if(source == btn_encrypt){
			resetPanels();
			encryption.pn_panel.setVisible(true);
			resetButtons();
			btn_encrypt.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_encrypt_button_select.png")));
		}else if(source == btn_already_download){
			resetPanels();
			alreadydownload.pn_panel.setVisible(true);
			resetButtons();
			btn_already_download.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_already_download_button_select.png")));
		}else if(source == btn_already_encrypted){
			resetPanels();
			alreadyencrypted.pn_panel.setVisible(true);
			resetButtons();
			btn_already_encrypted.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_already_encrypted_button_select.png")));
		}else if(source == btn_lookover){
			resetPanels();
			lookover.pn_panel.setVisible(true);
			resetButtons();
			btn_lookover.setIcon(new ImageIcon(getClass().getResource("/iconforuser/img_lookover_button_select.png")));
		}
	}

	public static void main(String[] args) {
		new MainPageUser();
	}
}
